package injae.AddressBook.member.adapter.in.api.change;

import injae.AddressBook.member.exception.MemberNotFoundException;
import injae.AddressBook.member.exception.NotSamePasswordException;
import injae.AddressBook.member.application.port.in.ChangePasswordUseCase;
import injae.AddressBook.member.application.port.in.FindMemberQuery;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class ChangeMemberApiController {

    private final ChangePasswordUseCase changePasswordUseCase;

    private final FindMemberQuery findMemberQuery;

    @PutMapping("/members/{id}")
    public ResponseEntity changeMember(@PathVariable Long id,
                                       @RequestBody @Valid ChangeMemberRequest request) {
        Member findMember = findMemberQuery.findMember(id);

        if (findMember == null) {
            throw new MemberNotFoundException(
                    "해당 id와 일치하는 회원정보가 없어서 비밀번호를 변경할 수 없습니다.");
        }

        if (!request.getOriginalPassword().equals(findMember.getPassword())) {
            throw new NotSamePasswordException("기존 비밀번호와 일치하지 않습니다.");
        }

        if (!request.getChangePassword().equals(request.getConfirmPassword())) {
            throw new NotSamePasswordException("변경 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        changePasswordUseCase.changePassword(id, request.getChangePassword());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("")
                .buildAndExpand()
                .toUri();

        return ResponseEntity.ok(location);
    }

}
