package injae.AddressBook.member.adapter.in.api.find;

import injae.AddressBook.common.exception.MemberNotFoundException;
import injae.AddressBook.member.application.port.in.FindMemberQuery;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FindMemberApiController {

    private final FindMemberQuery findMemberQuery;

    @GetMapping("/members/{id}")
    public Member loginMemberController(@PathVariable Long id) {
        Member findMember = findMemberQuery.findMember(id);

        if (findMember == null) {
            throw new MemberNotFoundException("해당 id의 회원정보를 찾을 수 없습니다.");
        }
        
        return findMember;
    }

}
