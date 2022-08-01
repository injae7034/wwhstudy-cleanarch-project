package injae.AddressBook.member.adapter.in.api.find;

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
        return findMemberQuery.findMember(id);
    }

}
