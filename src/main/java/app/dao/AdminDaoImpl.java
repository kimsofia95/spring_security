package app.dao;

import app.model.Role;
import app.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
@Repository
public class AdminDaoImpl implements AdminDao{

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;
    @Override
    public ArrayList<User> allUsers() {
        return (ArrayList<User>) entityManager.createQuery("select n from User n").getResultList();
    }

    @Override
    public ArrayList<Role> allRoles() {
        return (ArrayList<Role>) entityManager.createQuery("select n from Role n").getResultList();
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    public void addUser(User user, int[] roles) {
        Set<Role> rolesSet = new HashSet<>();
        for(int role: roles) {
            Role getRole = (Role) entityManager.createQuery("select n from Role n where n.id ='" + role + "'").getSingleResult();
            rolesSet.add(getRole);
        }
        user.setRolesSecond(rolesSet);
        entityManager.persist(user);
    }

    @Override
    public void ChangeUser(User user, int[] roles) {
        Set<Role> rolesSet = new HashSet<>();
        for(int role: roles) {
            Role getRole = (Role) entityManager.createQuery("select n from Role n where n.id ='" + role + "'").getSingleResult();
            rolesSet.add(getRole);
        }
        user.setRolesSecond(rolesSet);
        entityManager.merge(user);
    }

    @Override
    public void createDefaultRows() {
        Set<Role> rolesSet = new HashSet<>();
        Role adminRole = new Role(1L, "ROLE_ADMIN");
        Role userRole = new Role(2L, "ROLE_USER");
        rolesSet.add(adminRole);
        rolesSet.add(userRole);
        User admin = new User(1L, "admin", "admin", rolesSet);
        entityManager.merge(adminRole);
        entityManager.merge(userRole);
        entityManager.merge(admin);
    }
}
