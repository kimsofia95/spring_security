package app.service;

import app.model.Role;
import app.model.User;

import java.util.ArrayList;

public interface AdminService {
    ArrayList<Role> allRoles();
    ArrayList<User> allUsers();
    void deleteUser(User user);
    void addUser(User user, int[] roles);
    void ChangeUser(User user, int[] roles);
    void createDefaultRows();
}
