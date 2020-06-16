package app.user;

import app.user.entity.User;
import famework.annotation.Service;
import famework.configReader.ConfigBag;

import java.util.ArrayList;

@Service
public class UserManager {

    public ConfigBag config;

    private ArrayList<User> users;

    public UserManager(ConfigBag test) {
        this.config = test;
        this.users = new ArrayList<>();

    }

    public ArrayList<User> getUser() {
        return users;
    }
    public ArrayList<User> getUser(String username) {
        for (User user:this.users) {
            if (user.getUsername().equals(username)) {
                ArrayList<User> users = new ArrayList<>();
                users.add(user);
                return users;
            }
        }
        return null;
    }

    public void newUser(User user) {
        this.users.add(user);
    }

    public void removeUser(String username) {
      this.users.remove(findUserByName(username));
    }

    private User findUserByName(String username) {
        User found = null;
        for (User user: this.users) {
            if (user.getUsername().equals(username)) {
                found=user;
            }
        }
        return found;
    }
}
