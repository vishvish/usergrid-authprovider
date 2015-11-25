package io.vish.sawbones;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.jivesoftware.openfire.user.*;
import org.json.JSONArray;
import org.json.JSONObject;

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

    private static final Date NOTIME = new Date(0);

    public UsergridUserProvider() {
        super("users");
    }

    @Override
    public User loadUser(String username) throws UserNotFoundException {
        // TODO: sanitize username for nastiness
        try {
            URL url = new URL("http", this.host, getEndpoint() + "/" + username);
            HttpResponse<JsonNode> jsonResponse = Unirest.get(url.toString())
                    .header("accept", "application/json")
                    .queryString("client_id", this.config.getUsergridClientId())
                    .queryString("client_secret", this.config.getUsergridClientSecret())
                    .asJson();

            int status = jsonResponse.getStatus();
            switch (status) {
                case 200:
                    System.out.print(url + " ");
                    System.out.println(jsonResponse.getBody().toString());
                    // make new JSON Object from the response
                    JSONArray entities = (JSONArray) jsonResponse.getBody().getObject().get("entities");
                    JSONObject user = (JSONObject) entities.get(0);

                    String name = (String) user.get("uuid");
                    String email = (String) user.get("email");
                    Long created = (Long) user.get("created");
                    Long modified = (Long) user.get("modified");

                    Date cdate = new Date(created);
                    Date mdate = new Date(modified);

                    return new User(username, name, email, cdate, mdate);
                default:
                    System.out.print(url + " ");
                    System.out.println(jsonResponse.getBody().toString());
                    throw new UserNotFoundException(jsonResponse.getBody().toString());
            }
        } catch (UnirestException | MalformedURLException e) {
            e.printStackTrace();
        }
        throw new UserNotFoundException();
    }

    @Override
    public User createUser(String s, String s1, String s2, String s3) throws UserAlreadyExistsException {
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
        return null;
    }

    @Override
    public Collection<String> getUsernames() {
        ArrayList<String> usernames = new ArrayList<String>();

        try {
            URL url = new URL("http", this.host, getEndpoint());
            HttpResponse<JsonNode> jsonResponse = Unirest.get(url.toString())
                    .header("accept", "application/json")
                    .queryString("client_id", this.config.getUsergridClientId())
                    .queryString("client_secret", this.config.getUsergridClientSecret())
                    .asJson();

            int status = jsonResponse.getStatus();
            switch (status) {
                case 401: // resource not found
                    System.out.print(url + " ");
                    System.out.println(jsonResponse.getBody().toString());
                    break;
                case 200:
                    System.out.print(url + " ");

                    JSONArray entities = (JSONArray) jsonResponse.getBody().getObject().get("entities");

                    for (int i = 0; i < entities.length(); i++) {
                        JSONObject user = entities.getJSONObject(i);
                        String username = (String) user.get("username");
                        usernames.add(username);
                    }
                    break;
                default:
                    System.out.print(url + " ");
                    System.out.println(jsonResponse.getBody().toString());
                    break;
            }
        } catch (UnirestException | MalformedURLException e) {
            e.printStackTrace();
        }
        return usernames;
    }

    @Override
    public Collection<User> getUsers(int i, int i1) {
        return null;
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
    public Set<String> getSearchFields() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<User> findUsers(Set<String> set, String s) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<User> findUsers(Set<String> set, String s, int i, int i1) throws UnsupportedOperationException {
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
