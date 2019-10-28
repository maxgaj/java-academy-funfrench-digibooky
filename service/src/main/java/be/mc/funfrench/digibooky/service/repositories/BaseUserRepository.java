package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.users.Admin;
import be.mc.funfrench.digibooky.domain.users.BaseUser;
import be.mc.funfrench.digibooky.domain.users.Member;
import be.mc.funfrench.digibooky.domain.users.UserStatus;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class BaseUserRepository {

    public final ConcurrentHashMap<String, BaseUser> userTable;
    private static int nextId = 0;

    public BaseUserRepository() {
        this.userTable = new ConcurrentHashMap<>();
        createDefaultUsers();
    }

    private void createDefaultUsers() {
        Admin defaultAdmin = new Admin("admin", "Default", "Admin", "root@digibooky.be");
        this.persist(defaultAdmin);
    }

    public BaseUser persist(BaseUser user) {
        String id = "user" + nextId++;
        user.setId(id);
        userTable.put(id, user);
        return user;
    }

    public Collection<BaseUser> findAll(){
        return userTable.values();
    }

    public Collection<BaseUser> findAllByStatus(UserStatus status) {
        return findAll().stream()
                .filter(baseUser -> baseUser.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public BaseUser findOneByIdAndPassword(String id, String password){
        return findAll().stream()
                .filter(baseUser -> baseUser.getId().equals(id))
                .filter(baseUser -> baseUser.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
