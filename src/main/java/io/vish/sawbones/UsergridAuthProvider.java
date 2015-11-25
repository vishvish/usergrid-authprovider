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

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by vish on 24/11/2015.
 */
public class UsergridAuthProvider extends UsergridBase implements AuthProvider {

    public UsergridAuthProvider() {
        super("token");
    }

    public void authenticate(String username, String password) throws UnauthorizedException, ConnectionException, InternalUnauthenticatedException {

        try {
            URL url = new URL("http", this.host, getEndpoint());
            HttpResponse<JsonNode> jsonResponse = Unirest.get(url.toString())
                    .header("accept", "application/json")
                    .queryString("grant_type", "password")
                    .queryString("username", username)
                    .queryString("password", password)
                    .asJson();

            int status = jsonResponse.getStatus();
            switch (status) {
                case 200:
                    System.out.print(url + " ");
                    System.out.println("Authentication succeeded.");
                    break;
                default:
                    System.out.print(url + " ");
                    System.out.print("Authentication failed. ");
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
