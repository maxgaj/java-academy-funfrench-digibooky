package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.users.Admin;
import be.mc.funfrench.digibooky.domain.users.BaseUser;
import be.mc.funfrench.digibooky.domain.users.Member;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class BaseUserRepository {

    public final ConcurrentHashMap<String, BaseUser> userTable;

    public BaseUserRepository() {
        this.userTable = new ConcurrentHashMap<>();
        Admin defaultAdmin = new Admin("admin", "Default", "Admin", "root@digibooky.be");
        defaultAdmin.setId("1");
        this.userTable.put(defaultAdmin.getId(), defaultAdmin);
    }

    public BaseUser persist(BaseUser user) {
        String id = UUID.randomUUID().toString();
        user.setId(id);
        userTable.put(id, user);
        return user;
    }

    public Collection<BaseUser> findAll(){
        return userTable.values();
    }

    public Collection<Member> findAllByRoles(String role) {
        return findAll().stream()
                .filter(baseUser -> baseUser.getRoles().contains(role))
                .map(baseUser -> (Member) baseUser)
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
