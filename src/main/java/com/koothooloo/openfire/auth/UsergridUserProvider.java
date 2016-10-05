package com.koothooloo.openfire.auth;

import org.apache.usergrid.java.client.Usergrid;
import org.apache.usergrid.java.client.model.UsergridEntity;
import org.apache.usergrid.java.client.model.UsergridUser;
import org.apache.usergrid.java.client.query.UsergridQuery;
import org.apache.usergrid.java.client.response.UsergridResponse;
import org.jivesoftware.openfire.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

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
        UsergridQuery query = new UsergridQuery("users").eq("username", username);
        UsergridResponse response = Usergrid.GET(query);

        if (response.getEntities().size() != 1) throw new UserNotFoundException();
        if (!(response.first() instanceof UsergridUser)) throw new UserNotFoundException();

        UsergridUser user = (UsergridUser) response.first();
        User openfireUser = new User(user.getUsername(), user.getName(), user.getEmail(), new Date(user.getCreated() * 1000), new Date(user.getModified() * 1000));
        String openfireUserPassword = UUID.randomUUID().toString();

        try {
            openfireUser = UserManager.getInstance().createUser(user.getUsername(), openfireUserPassword, user.getName(), user.getEmail());

        } catch (UserAlreadyExistsException e) {
            LOG.error(e.getMessage(), e);
            openfireUser = UserManager.getInstance().getUser(username);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            LOG.info(e.getMessage(), e);
            openfireUser.setEmail(user.getEmail());
            openfireUser.setName(user.getName());
        } finally {
            return openfireUser;
        }
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
        UsergridResponse response = Usergrid.GET(new UsergridQuery("users"));
        ArrayList<String> usernames = new ArrayList<>();
        List<UsergridEntity> entities = response.getEntities();

        for (int i = 0; i < entities.size(); i++) {
            usernames.add(entities.get(i).getName());
        }
        return usernames;
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
