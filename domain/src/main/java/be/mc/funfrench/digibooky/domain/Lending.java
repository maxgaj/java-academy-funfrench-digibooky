package be.mc.funfrench.digibooky.domain;

import be.mc.funfrench.digibooky.domain.users.Member;

import java.time.LocalDate;
import java.util.UUID;

public class Lending {
    private final String id;
    private final Member member;
    private final Book book;
    private final LocalDate dueDate;

    public Lending(Member member, Book book) {
        this.id = UUID.randomUUID().toString();
        this.member = member;
        this.book = book;
        this.dueDate = LocalDate.now().plusWeeks(3);
    }

    public String getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
