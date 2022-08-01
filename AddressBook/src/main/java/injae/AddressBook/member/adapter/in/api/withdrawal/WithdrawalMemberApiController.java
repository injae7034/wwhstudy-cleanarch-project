package injae.AddressBook.member.adapter.in.api.withdrawal;

import injae.AddressBook.common.exception.MemberNotFoundException;
import injae.AddressBook.common.exception.NotSamePasswordException;
import injae.AddressBook.member.application.port.in.FindMemberQuery;
import injae.AddressBook.member.application.port.in.WithdrawalMemberUseCase;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class WithdrawalMemberApiController {

    private final WithdrawalMemberUseCase withdrawalMemberUseCase;

    private final FindMemberQuery findMemberQuery;


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/members/{id}")
    void withdrawalMember(@PathVariable Long id,
                          @RequestBody @Valid WithdrawalMemberRequest request) {
        Member findMember = findMemberQuery.findMember(id);

        if (findMember == null) {
            throw new MemberNotFoundException(
                    "해당 id와 일치하는 회원정보가 없어서 회원탈퇴를 할 수 없습니다.");
        }

        if (!findMember.getPassword().equals(request.getPassword())) {
            throw new NotSamePasswordException("기존 비밀번호와 일치하지 않습니다.");
        }

        withdrawalMemberUseCase.withdrawalMember(findMember);
    }
}
