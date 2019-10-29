package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.Book;
import be.mc.funfrench.digibooky.domain.Lending;
import be.mc.funfrench.digibooky.domain.users.BaseUser;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LendingRepository {
    public final ConcurrentHashMap<String, Lending> lendingTable;

    public LendingRepository() {
        this.lendingTable = new ConcurrentHashMap<>();
    }

    public Lending persist(Lending lending){
        lendingTable.put(lending.getId(), lending);
        return lending;
    }

    public Collection<Lending> findAll(){
        return lendingTable.values();
    }

    public long countByBook(Book book) {
        return findAll().stream()
                .filter(lending -> lending.getBook() == book)
                .count();
    }
}
