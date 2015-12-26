package io.vish.sawbones;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.jivesoftware.openfire.user.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Created by vish on 25/11/2015.
 */
public class UsergridUserProvider extends UsergridBase implements UserProvider {
    private static final Logger LOG = LoggerFactory.getLogger(UsergridUserProvider.class);

    public UsergridUserProvider() {
        super("users");
    }

    @Override
    public User loadUser(String username) throws UserNotFoundException {
        // TODO: sanitize username for nastiness
        try {
            URL url = new URL("http", this.host, this.port, getEndpoint() + "/" + username);
            HttpResponse<JsonNode> jsonResponse = Unirest.get(url.toString())
                    .header("accept", "application/json")
                    .queryString("client_id", this.config.getUsergridClientId())
                    .queryString("client_secret", this.config.getUsergridClientSecret())
                    .asJson();

            int status = jsonResponse.getStatus();
            if (status == 200) {
                LOG.debug(url + " ");
                LOG.debug(jsonResponse.getBody().toString());
                // make new JSON Object from the response
                JSONArray entities = (JSONArray) jsonResponse.getBody().getObject().get("entities");
                JSONObject user = (JSONObject) entities.get(0);

                String name = (String) user.get("uuid");
                String email = (String) user.get("email");
                Long created = (Long) user.get("created");
                Long modified = (Long) user.get("modified");

                Date cdate = new Date(created);
                Date mdate = new Date(modified);

                LOG.info(cdate.toString());
                LOG.info(mdate.toString());

                return UserManager.getInstance().createUser(username, username, name, email);
            } else {
                LOG.debug(url + " ");
                LOG.debug(jsonResponse.getBody().toString());
                throw new UserNotFoundException(jsonResponse.getBody().toString());
            }
        } catch (UnirestException | JSONException e) {
            LOG.error(e.getMessage(), e);
            throw new UserNotFoundException();
        } catch (MalformedURLException | UserAlreadyExistsException e) {
            LOG.error(e.getMessage(), e);
        }
        throw new UserNotFoundException();
    }

    @Override
    public User createUser(String username, String password, String name, String email) throws UserAlreadyExistsException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUser(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getUserCount() {
        return 0;
    }

    @Override
    public Collection<User> getUsers() {
        return new ArrayList<>();
    }

    @Override
    public Collection<String> getUsernames() {
        ArrayList<String> usernames = new ArrayList<>();

        try {
            URL url = new URL("http", this.host, this.port, getEndpoint());
            HttpResponse<JsonNode> jsonResponse = Unirest.get(url.toString())
                    .header("accept", "application/json")
                    .queryString("client_id", this.config.getUsergridClientId())
                    .queryString("client_secret", this.config.getUsergridClientSecret())
                    .asJson();

            int status = jsonResponse.getStatus();
            LOG.debug(url + " ");
            LOG.debug(jsonResponse.getBody().toString());
            switch (status) {
                case 401: // resource not found
                    break;
                case 200:
                    JSONArray entities = (JSONArray) jsonResponse.getBody().getObject().get("entities");
                    for (int i = 0; i < entities.length(); i++) {
                        usernames.add(getUsername(entities, i));
                    }
                    break;
                default:
                    break;
            }
        } catch (UnirestException | MalformedURLException e) {
            LOG.error(e.getMessage(), e);
        }
        return usernames;
    }

    private String getUsername(JSONArray entities, int i) {
        JSONObject user = entities.getJSONObject(i);
        return (String) user.get("username");
    }

    @Override
    public Collection<User> getUsers(int i, int i1) {
        return new ArrayList<>();
    }

    @Override
    public void setName(String s, String s1) throws UserNotFoundException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setEmail(String s, String s1) throws UserNotFoundException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCreationDate(String s, Date date) throws UserNotFoundException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setModificationDate(String s, Date date) throws UserNotFoundException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<String> getSearchFields() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<User> findUsers(Set<String> set, String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<User> findUsers(Set<String> set, String s, int i, int i1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }

    @Override
    public boolean isNameRequired() {
        return false;
    }

    @Override
    public boolean isEmailRequired() {
        return false;
    }
}
