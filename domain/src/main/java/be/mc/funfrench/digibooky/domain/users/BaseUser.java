package be.mc.funfrench.digibooky.domain.users;

import java.util.List;

public interface BaseUser {
    String getId();
    void setId(String id);
    String getPassword();
    List<String> getRoles();
}
