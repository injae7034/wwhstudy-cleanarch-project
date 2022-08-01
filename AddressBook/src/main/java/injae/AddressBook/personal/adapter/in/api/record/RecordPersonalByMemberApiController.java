package injae.AddressBook.personal.adapter.in.api.record;

import injae.AddressBook.member.application.port.in.FindMemberQuery;
import injae.AddressBook.member.domain.Member;
import injae.AddressBook.member.exception.MemberNotFoundException;
import injae.AddressBook.personal.application.port.in.RecordPersonalUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class RecordPersonalByMemberApiController {

    private final RecordPersonalUseCase useCase;

    private final FindMemberQuery query;

    @PostMapping("/members/{memberId}/personals")
    public ResponseEntity recordPersonalByMember(@PathVariable Long memberId,
                                                 @RequestBody @Valid
                                                         RecordPersonalByMemberRequest request) {
        Member findMember = query.findMember(memberId);

        if (findMember == null) {
            throw new MemberNotFoundException("해당 id와 일치하는 멤버를 찾을 수 없습니다.");
        }

        Long personalId = useCase.recordPersonal(request.getName(), request.getAddress(),
                request.getTelephoneNumber(), request.getEmailAddress(), findMember);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{personalId}")
                .buildAndExpand(personalId)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
