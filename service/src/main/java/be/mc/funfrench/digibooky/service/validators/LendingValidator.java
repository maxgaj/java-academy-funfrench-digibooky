package be.mc.funfrench.digibooky.service.validators;

import be.mc.funfrench.digibooky.domain.Book;
import be.mc.funfrench.digibooky.domain.users.Member;
import be.mc.funfrench.digibooky.infrastructure.InvalidLendingException;
import be.mc.funfrench.digibooky.service.repositories.BookRepository;
import be.mc.funfrench.digibooky.service.repositories.LendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LendingValidator {
    private LendingRepository lendingRepository;

    @Autowired
    public LendingValidator(LendingRepository lendingRepository) {
        this.lendingRepository = lendingRepository;
    }

    public void validate(Book book, Member member) {
        bookIsNotNull(book);
        memberIsNotNull(member);
        bookIsNotAlreadyLent(book);
    }


    private void bookIsNotNull(Book book) {
        if(book == null){
            throw new InvalidLendingException("The required book doesn't exist");
        }
    }

    private void memberIsNotNull(Member member) {
        if (member == null){
            throw new InvalidLendingException("The required member doesn't exist");
        }
    }

    private void bookIsNotAlreadyLent(Book book) {
        if (lendingRepository.countByBook(book) > 0){
            throw new InvalidLendingException("The required book is already lent");
        }
    }


}
