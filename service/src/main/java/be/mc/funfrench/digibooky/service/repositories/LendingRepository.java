package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.Lending;
import be.mc.funfrench.digibooky.domain.users.BaseUser;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class LendingRepository {
    public final ConcurrentHashMap<String, Lending> lendingTable;

    public LendingRepository() {
        this.lendingTable = new ConcurrentHashMap<>();
    }

    public Lending persist(Lending lending){
        //TODO
        return null;
    }
}
