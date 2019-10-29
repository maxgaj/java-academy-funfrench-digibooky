package be.mc.funfrench.digibooky.api.dtos;

import be.mc.funfrench.digibooky.domain.Book;
import be.mc.funfrench.digibooky.domain.users.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "public informations about a specific lending")
public class LendingDto {
    @ApiModelProperty(notes = "The ID of the lending")
    private String id;
    @ApiModelProperty(notes = "Information about the lent book")
    private BookDto bookDto;
    @ApiModelProperty(notes = "Informations about the member")
    private MemberDto memberDto;
    @ApiModelProperty(notes = "Due date of the lending")
    private String dueDate;

    public String getId() {
        return id;
    }

    public LendingDto withId(String id) {
        this.id = id;
        return this;
    }

    public BookDto getBookDto() {
        return bookDto;
    }

    public LendingDto withBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
        return this;
    }

    public MemberDto getMemberDto() {
        return memberDto;
    }

    public LendingDto withMemberDto(MemberDto memberDto) {
        this.memberDto = memberDto;
        return this;
    }

    public String getDueDate() {
        return dueDate;
    }

    public LendingDto withDueDate(String dueDate) {
        this.dueDate = dueDate;
        return this;
    }
}
