package io.vish.sawbones;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.jivesoftware.openfire.auth.AuthProvider;
import org.jivesoftware.openfire.auth.ConnectionException;
import org.jivesoftware.openfire.auth.InternalUnauthenticatedException;
import org.jivesoftware.openfire.auth.UnauthorizedException;
import org.jivesoftware.openfire.user.UserNotFoundException;
import org.jivesoftware.util.JiveProperties;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by vish on 24/11/2015.
 */
public class UsergridAuthProvider implements AuthProvider {

    private UsergridAuthProviderConfig config;

    public UsergridAuthProvider() {
        JiveProperties jiveProperties = JiveProperties.getInstance();
        config = new UsergridAuthProviderConfig(jiveProperties);
    }


    public void authenticate(String username, String password) throws UnauthorizedException, ConnectionException, InternalUnauthenticatedException {
        String host = config.getUsergridBaseUrl();
        String org = config.getUsergridOrganization();
        String app = config.getUsergridApplication();
        String resource = config.getUsergridResource();

        Path endpoint = Paths.get(org, app, resource);

        try {
            URL url = new URL("http", host, endpoint.toString());
            HttpResponse<JsonNode> jsonResponse = Unirest.get(url.toString())
                    .header("accept", "application/json")
                    .queryString("grant_type", "password")
                    .queryString("username", username)
                    .queryString("password", password)
                    .asJson();

            int status = jsonResponse.getStatus();
            switch (status) {
                case 200:
                    System.out.println("Authentication succeeded.");
                    break;
                default:
                    System.out.println("Authentication failed.");
                    System.out.println(jsonResponse.getBody().toString());
                    throw new UnauthorizedException(jsonResponse.getBody().toString());
            }
        } catch (UnirestException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void authenticate(String username, String token, String digest) throws UnauthorizedException, ConnectionException, InternalUnauthenticatedException {
        throw new UnsupportedOperationException("Usergrid provider does not support digests.");
    }

    public String getPassword(String username) throws UserNotFoundException, UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public void setPassword(String username, String password) throws UserNotFoundException, UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public boolean supportsPasswordRetrieval() {
        return false;
    }

    public boolean isPlainSupported() {
        return true;
    }

    public boolean isDigestSupported() {
        return false;
    }

    public boolean isScramSupported() {
        return false;
    }


}
