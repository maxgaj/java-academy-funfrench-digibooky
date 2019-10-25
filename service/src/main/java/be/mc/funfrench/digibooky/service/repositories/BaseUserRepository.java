package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.users.Admin;
import be.mc.funfrench.digibooky.domain.users.BaseUser;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BaseUserRepository {

    public final ConcurrentHashMap<String, BaseUser> userTable;

    public BaseUserRepository() {
        this.userTable = new ConcurrentHashMap<>();
        Admin defaultAdmin = new Admin("admin", "Default", "Admin", "root@digibooky.be");
        defaultAdmin.setId("1");
        this.userTable.put(defaultAdmin.getId(), defaultAdmin);
    }

    public Collection<BaseUser> findAll(){
        return userTable.values();
    }

    public BaseUser findOneByIdAndPassword(String id, String password){
        return findAll().stream()
                .filter(baseUser -> baseUser.getId().equals(id))
                .filter(baseUser -> baseUser.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
