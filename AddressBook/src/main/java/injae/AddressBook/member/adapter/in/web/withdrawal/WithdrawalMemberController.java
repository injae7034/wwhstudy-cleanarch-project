package injae.AddressBook.member.adapter.in.web.withdrawal;

import injae.AddressBook.member.application.port.in.FindMemberQuery;
import injae.AddressBook.member.application.port.in.WithdrawalMemberUseCase;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class WithdrawalMemberController {

    private final WithdrawalMemberUseCase useCase;

    private final FindMemberQuery query;

    @GetMapping("/withdrawal")
    public String withdrawalMember(@SessionAttribute(name = "loginMemberId")
                                           Long loginMemberId,
                                   HttpServletRequest request) {

        //세션 초기화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        //멤버 삭제
        Member loginMember = query.findMember(loginMemberId);

        useCase.withdrawalMember(loginMember);

        return "redirect:/";
    }

}
