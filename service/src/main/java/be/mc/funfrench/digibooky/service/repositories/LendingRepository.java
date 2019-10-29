package be.mc.funfrench.digibooky.service.repositories;

import be.mc.funfrench.digibooky.domain.Book;
import be.mc.funfrench.digibooky.domain.Lending;
import be.mc.funfrench.digibooky.infrastructure.LendingNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LendingRepository {

    private final ConcurrentHashMap<String, Lending> lendingTable;
    private final Logger logger = LoggerFactory.getLogger(LendingRepository.class);

    public LendingRepository() {
        this.lendingTable = new ConcurrentHashMap<>();
    }

    public Lending persist(Lending lending){
        lending.getBook().setLent(true);
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

    public Lending findById(String lendingId) {
        Lending lending = lendingTable.get(lendingId);
        if (lending == null) {
            return manageLendingNotFound(lendingId);
        }
        return lending;
    }

    private Lending manageLendingNotFound(String lendingId) {
        logger.error(createLendingNotFoundMessage(lendingId));
        throw new LendingNotFoundException(createLendingNotFoundMessage(lendingId));
    }

    private String createLendingNotFoundMessage(String lendingId) {
        return "No lending was found for the given id: '" + lendingId + "'.";
    }

    public Lending deleteById(String lendingId) {
        Lending lendingDeleted = lendingTable.remove(lendingId);
        if(lendingDeleted == null) {
            manageLendingNotFound(lendingId);
        }
        return lendingDeleted;
    }
}
