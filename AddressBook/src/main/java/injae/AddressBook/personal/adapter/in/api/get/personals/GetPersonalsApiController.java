package injae.AddressBook.personal.adapter.in.api.get.personals;

import injae.AddressBook.member.application.port.in.FindMemberQuery;
import injae.AddressBook.member.domain.Member;
import injae.AddressBook.member.exception.MemberNotFoundException;
import injae.AddressBook.personal.adapter.in.api.get.personal.GetPersonalResponse;
import injae.AddressBook.personal.application.port.in.GetPersonalsQuery;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GetPersonalsApiController {

    private final FindMemberQuery findMemberQuery;

    private final GetPersonalsQuery getPersonalsQuery;

    @GetMapping("/members/{memberId}/personals")
    public GetPersonalsResponse getPersonals(@PathVariable Long memberId) {

        Member findMember = findMemberQuery.findMember(memberId);

        if (findMember == null) {
            throw new MemberNotFoundException("해당 멤버를 찾을 수 없습니다.");
        }

        List<GetPersonalResponse> getPersonalsResponses = new ArrayList<>();
        List<Personal> personals = getPersonalsQuery.getPersonals(findMember);

        for (Personal personal : personals) {
            getPersonalsResponses.add(new GetPersonalResponse(
                    personal.getName(),
                    personal.getAddress(),
                    personal.getTelephoneNumber(),
                    personal.getEmailAddress()));
        }

        return new GetPersonalsResponse(getPersonalsResponses.size(), getPersonalsResponses);
    }

}
