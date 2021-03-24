package app.dao;

import app.model.Role;
import app.model.User;

import java.util.ArrayList;

public interface AdminDao {
    ArrayList<User> allUsers();
    ArrayList<Role> allRoles();
    void deleteUser(User user);
    void addUser(User user, int[] roles);
    void ChangeUser(User user, int[] roles);
    void createDefaultRows();
}
