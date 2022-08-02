package injae.AddressBook.member.adapter.in.api.find;

import injae.AddressBook.member.exception.MemberNotFoundException;
import injae.AddressBook.member.application.port.in.FindMemberQuery;
import injae.AddressBook.member.domain.Member;
import injae.AddressBook.personal.adapter.in.api.get.personal.GetPersonalByMemberResponse;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FindMemberApiController {

    private final FindMemberQuery findMemberQuery;

    @GetMapping("/members/{id}")
    public FindMemberResponse loginMemberController(@PathVariable Long id) {
        Member findMember = findMemberQuery.findMember(id);

        if (findMember == null) {
            throw new MemberNotFoundException("해당 id와 일치하는 회원정보를 찾을 수 없습니다.");
        }

        List<GetPersonalByMemberResponse> getPersonalsResponses = new ArrayList<>();

        for (Personal personal : findMember.getPersonals()) {
            getPersonalsResponses.add(new GetPersonalByMemberResponse(
                    personal.getName(),
                    personal.getAddress(),
                    personal.getTelephoneNumber(),
                    personal.getEmailAddress()));
        }

        return new FindMemberResponse(
                findMember.getEmail(), findMember.getName(),
                getPersonalsResponses.size(), getPersonalsResponses);
    }

}
