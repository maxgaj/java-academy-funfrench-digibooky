package be.mc.funfrench.digibooky.api.mappers;

import be.mc.funfrench.digibooky.api.dtos.LendingDto;
import be.mc.funfrench.digibooky.api.dtos.ReturnLendingDto;
import be.mc.funfrench.digibooky.domain.Lending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class LendingMapper {

    private BookMapper bookMapper;
    private MemberMapper memberMapper;

    @Autowired
    public LendingMapper(BookMapper bookMapper, MemberMapper memberMapper) {
        this.bookMapper = bookMapper;
        this.memberMapper = memberMapper;
    }

    public LendingDto mapToDto(Lending lending) {
        return new LendingDto()
                .withBookDto(bookMapper.mapToBookDto(lending.getBook()))
                .withId(lending.getId())
                .withMemberDto(memberMapper.mapToDto(lending.getMember()))
                .withDueDate(lending.getDueDate().toString());
    }
}
