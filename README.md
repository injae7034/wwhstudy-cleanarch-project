# 헥사고날 아키텍처를 적용한 주소록 웹프로젝트
기존 버전에는 로그인 기능이 없이 주소록 기능만 있었는데 여기에 **로그인 기능을 추가**하였습니다.

# 목차
[1.기술 스택](#기술-스택)  

[2.코딩을 잘하고 싶어요](#coding을-잘하고-싶어요)

# 1. 기술 스택
## 1.1 백엔드 : java, spring, jpa, h2 database
## 1.2 프론트엔드 : thymeleaf, html, javascript, bootstrap

<br><br>

# 2. 도메인
## 2.1 Member
```java
@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "login_id")
    private String email;
    private String password;

    private String name;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Personal> personals = new ArrayList<>();

    public Member(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Member(String email, String password, String name) {
        this(null, email, password, name);
    }

    public void changePassword(String password) {
        this.password = password;
    }

}
```
## 2.2 Personal
```java
@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "id")
public class Personal {

    @Id @GeneratedValue
    @Column(name = "personal_id")
    private Long id;

    private String name;
    private String address;
    private String telephoneNumber;
    private String emailAddress;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Personal(Long id, String name, String address,
                    String telephoneNumber, String emailAddress, Member member) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
        this.member = member;
    }

    public Personal(String name, String address,
                    String telephoneNumber, String emailAddress, Member member) {
        this(null, name, address, telephoneNumber, emailAddress, member);
    }

    public void changePersonalInfo(String address, String telephoneNumber, String emailAddress) {
        if (this.address.compareTo(address) != 0) {
            this.address = address;
        }

        if (this.telephoneNumber.compareTo(telephoneNumber) != 0) {
            this.telephoneNumber = telephoneNumber;
        }

        if (this.emailAddress.compareTo(emailAddress) != 0) {
            this.emailAddress = emailAddress;
        }
    }

}
```
<br><br>

# 3. 처음 화면
![처음화면](https://user-images.githubusercontent.com/52854217/181179740-1fbbc703-a807-43de-9f46-e67dd9a34dfa.JPG)
<br><br>

# 4. 회원가입 화면
![회원가입화면](https://user-images.githubusercontent.com/52854217/181179891-1165bcb5-9933-49c2-924d-834fc06e4e1d.JPG)
<br><br>

## 4.1 아이디 중복 시 예외처리
![회원가입화면_중복아이디_예외처리1](https://user-images.githubusercontent.com/52854217/181180091-cb98796a-237b-4b13-a4fd-df4235e4ebac.JPG)
```java
@RequiredArgsConstructor
@Transactional
@Service
public class RegisterMemberService implements RegisterMemberUseCase {

    private final RegisterMemberRepository registerMemberRepository;

    private final FindByEmailRepository findByEmailRepository;

    @Override
    public Long registerMember(String email, String password, String name) {

        //이미 존재하는 회원 id 여부 확인
        validateDuplicateMember(email);

        //새로운 회원 id이면 Member를 생성해서 DB에 저장함.(회원가입 승인)
        Member newMember = new Member(email, password, name);
        registerMemberRepository.save(newMember);

        return newMember.getId();
    }

    private void validateDuplicateMember(String email) {
        Member findMember = findByEmailRepository.findByEmail(email).orElse(null);

        if (findMember != null) {
            throw new DuplicateMemberException("이미 존재하는 아이디입니다.");
        }
    }
}
```
<br><br>

## 4.2 비밀번호와 확인 비밀번호 불일치시 예외처리
![회원가입화면_비밀번호불일치_예외처리2](https://user-images.githubusercontent.com/52854217/181180644-00cb34d1-b115-4953-b72e-6ea4bcc0a930.JPG)
```java
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class RegisterMemberController {

    private final RegisterMemberUseCase useCase;

    @GetMapping("/register")
    public String registerForm(@ModelAttribute RegisterMemberForm form) {
        return "member/registerMemberForm";
    }

    @PostMapping("/register")
    public String registerMember(@Valid @ModelAttribute RegisterMemberForm form,
                                 BindingResult bindingResult,
                                 HttpServletRequest request) {

        //비밀번호와 확인 비밀번호가 서로 일치 하지 않으면 예외 발생
        if(!form.getPassword().equals(form.getConfirmPassword())) {
            bindingResult.reject("notSamePassword",
                    "비밀번호와 확인 비밀번호가 서로 일치하지 않습니다.");
        }

        //예외가 생기면 다시 돌아감
        if (bindingResult.hasErrors()) {
            return "member/registerMemberForm";
        }

        //성공 로직
        try {
            useCase.registerMember(form.getEmail(), form.getPassword(), form.getName());
        } catch (DuplicateMemberException e) {
            //이미 존재하는 아이디를 입력하여 회원 가입할 경우
            bindingResult.reject("duplicateMember",
                    e.getMessage());
            //예외가 생겨서 다시 돌아감
            return "member/registerMemberForm";
        }

        //예외가 없으면(회원가입이 성공적으로 처리되면) 홈화면으로 돌아감
        return "redirect:/";
    }
}
```
<br><br>

# 5. 로그인 화면
![로그인화면](https://user-images.githubusercontent.com/52854217/181181265-eca20d81-5f2d-447b-8f29-0f454b120348.JPG)
<br><br>

## 5.1 로그인 시 아이디 또는 비밀번호 불일치시 예외처리
![로그인화면_예외처리](https://user-images.githubusercontent.com/52854217/181181654-5b865038-cda4-4360-8d24-91990f39724b.JPG)
```java
@Service
@RequiredArgsConstructor
public class LoginMemberService implements LoginMemberUseCase {

    private final FindByEmailRepository findByEmailRepository;

    @Override
    public Member loginMember(String loginId, String password) {
        return findByEmailRepository.findByEmail(loginId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }
}
```
```java
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class LoginMemberController {

    private final LoginMemberUseCase useCase;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginMemberForm form) {
        return "member/loginMemberForm";
    }

    @PostMapping("/login")
    public String loginMember(@Valid @ModelAttribute LoginMemberForm form,
                              BindingResult bindingResult,
                              @RequestParam(defaultValue = "/") String redirectURL,
                              HttpServletRequest request) {

        //예외 처리
        if (bindingResult.hasErrors()) {
            return "member/loginMemberForm";
        }

        Member loginMember = useCase.loginMember(form.getEmail(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail",
                    "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "member/loginMemberForm";
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션을 반환, 없으면 신규 세션을 생성함
        HttpSession session = request.getSession();
        //세션에 로그인 회원 id 정보 보관
        session.setAttribute("loginMemberId", loginMember.getId());

        return "redirect:" + redirectURL;
    }
}
```
<br><br>

# 6. 로그인 후 화면(회원마다 저장된 데이터가 다름)
## 6.1 김영한 회원의 로그인 후 화면
![로그인후_화면](https://user-images.githubusercontent.com/52854217/181189171-806d8c87-0524-4e27-b793-1d31da42432c.JPG)
## 6.2 박인재 회원의 로그인 후 화면
![로그인후_화면2](https://user-images.githubusercontent.com/52854217/181189229-26b0e49d-d6c2-4aaa-a302-549c7c988454.JPG)
## 6.3 DB에 저장된 Member와 Personal 데이터
![H2테이블](https://user-images.githubusercontent.com/52854217/181183646-eb805708-da51-4635-aa08-c26de52165f0.JPG)
<br><br>

# 7. 비밀번호 변경 화면
![비밀번호변경화면](https://user-images.githubusercontent.com/52854217/181185142-18f5f4ad-1ab6-4051-b506-11e4f3762f24.JPG)
<br><br>

## 7.1 기존비밀번호 불일치시 예외처리
![비밀번호변경화면_기존비밀번호_불일치_에외처리1](https://user-images.githubusercontent.com/52854217/181185336-a7b9fc4f-32b3-4d56-9e42-7f26aad62aca.JPG)
<br><br>

## 7.2 변경 비밀번호와 확인 비밀번호 불일치시 예외처리
![비밀번호변경화면_변경및확인비밀번호_불일치_에외처리2](https://user-images.githubusercontent.com/52854217/181185571-b8dad746-7e69-40fb-b32c-4c23e6f4d74e.JPG)
```java
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class ChangePasswordController {

    private final ChangePasswordUseCase useCase;

    private final FindMemberQuery findMemberQuery;

    @GetMapping("/change")
    public String changePasswordForm(@ModelAttribute ChangePasswordForm form) {
        return "member/changePasswordForm";
    }

    @PostMapping("/change")
    public String changePassword(@Valid @ModelAttribute ChangePasswordForm form,
                                 BindingResult bindingResult,
                                 @RequestParam(defaultValue = "/") String redirectURL,
                                 @SessionAttribute(name = "loginMemberId") Long loginMemberId) {
        //예외처리
        if (bindingResult.hasErrors()) {
            return "member/changePasswordForm";
        }

        Member loginMember = findMemberQuery.findMember(loginMemberId);

        if (loginMember.getPassword().equals(form.getOriginalPassword()) == false) {
            bindingResult.reject("NotSameOriginalPassword",
                    "기존 비밀번호가 일치하지 않습니다.");
            return "member/changePasswordForm";
        }

        if (form.getChangePassword().equals(form.getConfirmPassword()) == false) {
            bindingResult.reject("NotSameChangePassword",
                    "변경 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
            return "member/changePasswordForm";
        }

        //비밀번호 변경
        useCase.changePassword(loginMember.getId(), form.getChangePassword());

        return "redirect:" + redirectURL;
    }
}
```
<br><br>

# 8. 로그아웃 버튼 클릭했을 때
세션을 소멸시키고 처음 화면으로 돌아감.
![처음화면](https://user-images.githubusercontent.com/52854217/181186867-54be2f50-2855-4533-ab33-978c2f3a2dfa.JPG)
```java
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class LogoutMemberController {

    @GetMapping("/logout")
    public String logoutMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

}
```
<br><br>

# 9. 회원탈퇴 버튼을 클릭했을 때
![회원탈퇴처리화면](https://user-images.githubusercontent.com/52854217/181187151-58502b15-d5e3-443e-b3d3-d6a9fd9216ed.JPG)
```java
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
```
아까 위의 Member 도메인 코드에서 Personal 도메인과의 관계를 **cascade = CascadeType.ALL**로 설정했기 때문에 Member를 DB에서 지우면 해당 Member와 관련된 Personal 데이터도 DB에서 모두 지워집니다.  

<br><br>

# 10. 스프링 인터셉터 적용
![스프링인터셉터](https://user-images.githubusercontent.com/52854217/181189312-086c2e99-829a-438b-872b-c14d3f5e1d15.JPG)
개인 정보를 기재하는 url을 기억해둔 뒤에 로그인을 하지 않고 바로 해당 url로 접근할 때 **이를 차단하고, 로그인 화면으로 연결**시킵니다.  

그리고 로그인에 성공하면 개인 정보를 기재하는 url로 이동하도록 구현하였습니다.  

```java
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        HttpSession session = request.getSession();

        if (session.getAttribute("loginMemberId") == null) {
            response.sendRedirect("/member/login?redirectURL=" + requestURI);
            return false;
        }

        return true;
    }
}
```
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/member/register", "/member/login",
                        "/css/**", "/*.ico", "/error");
    }
}
```

<br><br>

# 11. 기재하기 화면

홈화면에서 **기재하기 버튼**을 클릭하면 기재하기 화면으로 이동합니다.  

**이름, 주소, 전화번호, 이메일주소를 입력한 다음 기재하기 버튼을 클릭**하면  

해당 개인의 정보가 주소록에 기재되고(데이터베이스에 새로 저장되고) **홈화면**으로 돌아갑니다.  

이 때, **홈화면의 개인 정보 리스트에 새로 기재한 개인의 정보가 추가되어 출력**됩니다.  

![기재하기_기본_화면](https://user-images.githubusercontent.com/52854217/170682988-3c4c0b47-d193-4502-8bcc-205afa9b7dea.JPG)

<br><br>

## 11.1 기재하기 예외 화면1(이름, 주소, 전화번호는 필수, 이메일 선택)
**이름, 주소, 전화번호는 반드시 기재**해야합니다.  

이름, 주소, 전화번호 중에 **하나라도 공백으로 비워두면 입력 검증에서 예외를 발생시켜 다음 과정으로 진행되지 않고, 안내 메세지를 출력합니다.**  

즉, 주소록에 등록(데이터베이스에 저장)되지 않게 막았습니다.  

![기재하기_예외_화면_1](https://user-images.githubusercontent.com/52854217/170683275-f39dc05b-b4a8-4919-89a8-f1df18b3618c.JPG)

<br><br>

## 11.2 기재하기 예외 화면2(이메일 형식을 지켜야함)
**이메일은 비워도 되지만 입력한다면 반드시 형식을 지켜야합니다.**  

이메일 형식을 지키지 않으면 입력 검증에서 예외를 발생시켜 다음 과정으로 넘어가지 않고, 안내메세지를 출력합니다.  

즉, 주소록에 등록(데이터베이스에 저장)되지 않게 막았습니다.  

![기재하기_예외_화면_2](https://user-images.githubusercontent.com/52854217/170684563-3e83edec-46be-40ca-a484-b71feed931bd.JPG)

<br><br>

# 12. 찾기 화면
홈화면에서 **이름으로 찾기 버튼**을 클릭하면 찾기 화면으로 이동합니다.  

![찾기_기본_화면](https://user-images.githubusercontent.com/52854217/170684736-6472d841-706a-4173-9e53-21a4ae7a446e.JPG)

<br><br>

## 12.1 찾기 예외 화면(찾을 이름을 적지 않을 경우)
찾기 화면에서 이름을 적지 않고 **공백으로 찾기 버튼을 클릭하는 경우 예외를 발생**시켜 안내메세지를 출력합니다.  

![찾기_예외_화면](https://user-images.githubusercontent.com/52854217/170685012-ba288417-ebce-4ac3-ab3d-6addd4041f58.JPG)

<br><br>

## 12.2 이름으로 찾은 화면
찾기 화면에서 이름(ex. "홍길동")을 적어서 찾기버튼을 클릭하면 찾을 이름에 해당하는 개인들(동명이인 포함)의 정보를 찾기화면에 출력합니다.  

(물론 찾을 이름에 해당하는 개인이 없다면 아무것도 출력되지 않습니다.)  

![찾은_화면](https://user-images.githubusercontent.com/52854217/170685650-b7278d45-2ff8-4e59-a77f-8c8e91013b13.JPG)

<br><br>

## 12.3 회원마다 가진 데이터가 다르기 때문에 찾기 결과도 다름
![H2테이블](https://user-images.githubusercontent.com/52854217/181190886-938ebeb5-50f8-43fa-bdef-478cc02d9bd1.JPG)
### 12.3.1 김영한 회원이 박길동을 찾을 경우
![찾기화면1](https://user-images.githubusercontent.com/52854217/181184422-4ab15da2-41a5-4981-9c3a-37d00b78f2e3.JPG)
### 12.3.2 박인재 회원이 박길동을 찾을 경우
![찾기화면2](https://user-images.githubusercontent.com/52854217/181184580-741efc39-f832-4f1c-a0b5-edaa2b9cd939.JPG)
똑같이 박길동을 찾았지만 저장된 데이터가 서로 다르기 때문에 각자에 맞는 박길동의 데이터를 보여줌.  
```java
@Repository
@RequiredArgsConstructor
public class JpaFindPersonalRepository implements FindPersonalRepository {

    private final EntityManager em;

    @Override
    public List<Personal> findByName(Member member, String name) {
        return em.createQuery(
                        "select p from Personal p where p.member = :member and" +
                                " p.name = :name",
                        Personal.class)
                .setParameter("member", member)
                .setParameter("name", name)
                .getResultList();
    }

}
```
<br><br>

# 13. 수정하기 화면
**홈화면 또는 찾기 화면**에서 개인들의 정보 옆에 있는 **수정 버튼**을 클릭하면 수정하기 화면으로 이동합니다.  

이 때, 수정하기 전의 개인정보, 즉, **현재 개인정보가 수정하기 화면에 출력**됩니다.  

**이름은 바꾸지 못하게 막았고, 나머지(주소, 전화번호, 이메일주소)는 바꿀 수 있도록 설정하였습니다.**  

따라서 주소, 전화번호, 이메일주소 중에 바꿀 정보만 수정해서 입력한 다음 수정하기 버튼을 클릭하면  

**해당 개인의 정보가 수정되어(데이터베이스에도 반영되어) 홈화면으로 이동하는데 이 때 해당 개인의 정보가 바뀌어서 홈화면에 출력됩니다.**   

![수정하기_기본_화면](https://user-images.githubusercontent.com/52854217/170686065-d18695f7-48f4-4e93-a5fb-328be17347c4.JPG)

<br><br>

## 13.1 수정하기 예외 화면1(주소, 전화번호는 필수, 이메일 선택)  

**주소, 전화번호** 중에 **하나라도 공백으로 비워두면 입력 검증에서 예외를 발생시켜 다음 과정으로 진행되지 않고, 안내 메세지를 출력합니다.**  

즉, 주소록에서 수정(데이터베이스의 데이터를 바꿔서 저장)되지 않게 막았습니다.  

![수정하기_예외_화면_1](https://user-images.githubusercontent.com/52854217/170687389-5041f44f-644d-4458-9adf-acd784e210a4.JPG)

<br><br>

## 13.2 수정하기 예외 화면2(이메일 형식을 지켜야함)

**이메일은 비워도 되지만 입력한다면 반드시 형식을 지켜야합니다.**  

이메일 형식을 지키지 않으면 입력 검증에서 예외를 발생시켜 다음 과정으로 넘어가지 않고, 안내메세지를 출력합니다.  

즉, 주소록에서 수정(데이터베이스의 데이터를 바꿔서 저장)되지 않게 막았습니다.  

![수정하기_예외_화면_2](https://user-images.githubusercontent.com/52854217/170694297-548a192c-08c3-44fc-ab87-e8c238d226fc.JPG)

<br><br>

# 14. 지우기 팝업창
**홈화면 또는 찾기 화면**에서 개인들의 정보 옆에 있는 **지우기 버튼**을 클릭하면 **지우기 팝업창**이 뜹니다.  

팝업창에서 **확인 버튼을 누르면 실제로 주소록에서 해당 개인 정보를 지우고(데이터베이스에서 개인 데이터 지움)**,

**취소버튼을 누르면** 주소록에서 팝업창만 사라지고,  

**실제로 주소록에서 해당 개인 정보를 지우지 않습니다.(데이터베이스에서 개인 데이터 지우지 않습니다.)**  

![지우기_팝업창_1](https://user-images.githubusercontent.com/52854217/170688266-c816a16d-5001-48e2-869c-e42406ad4778.JPG)

<br><br>

# 15. 홈화면에서 정렬하기 체크박스 체크했을 때 화면
홈화면에서 **정렬하기 체크박스 체크하면** 기재된 순서(데이터베이스에 저장된 순서)대로 출력되어 있는 개인 정보들이  

**이름을 기준으로 오름차순으로 정렬되어 출력됩니다.**  

![홈화면에서_정리된상태](https://user-images.githubusercontent.com/52854217/170689745-2d00b047-d319-4440-b08a-ce8ebffc5c74.JPG)

<br><br>

## 15.1 홈화면에 정렬하기 체크박스 선택되어 있는 상태에서 기재하기 화면으로 이동

홈화면에 정렬하기 체크박스 선택되어 있는 상태에서  

**다른페이지(기재하기, 찾기, 수정하기)화면으로 이동한 다음 다시 홈화면으로 돌아와도 정렬된 상태는 유지되어(정렬하기 체크박스도 체크되어) 있습니다.**  

즉, 홈화면에 정렬하기 체크박스 선택되어 있는 상태에서 기재하기 버튼을 클릭해  

기재하기 화면으로 이동한 다음 **새로운 개인 정보(ex. 나길동)를 기재**한 다음 기재하기 버튼을 클릭하면  

![정렬하기된_상태에서_새로운_개인정보_기재하기](https://user-images.githubusercontent.com/52854217/170690439-cde921e8-7f42-4e2e-bbdc-1aefb7725816.JPG)

다시 홈화면으로 이동했을 때 정렬된 상태는 유지되어(정렬하기 체크박스도 체크되어)  

새로 기재한 개인 정보가 제일 마지막에 출력되는 것이 아니라 **이름을 기준으로 오름차순 정렬에 맞춰서 화면에 출력**됩니다.  

![정렬하기된_상태에서_새로운_개인정보_기재한후_홈화면](https://user-images.githubusercontent.com/52854217/170690475-defc0e0b-8f10-4a9e-b8ad-ca8714e86aca.JPG)

홈화면에서 **이름을 기준으로 오름차순으로 정렬된 출력을 다시 원상태(데이터베이스에 저장된 순서)로 돌릴 수 있는 유일한 방법**은 **홈화면에 있는 정렬하기 체크박스 버튼의 체크를 해지하는 방법**입니다.  

물론 다시 정렬하기 체크박스를 선택하면 다시 이름을 기준으로 오름차순으로 정렬됩니다.  

<br><br>

## 15.2 홈화면에 정렬하기 체크박스 선택되어 있는 상태에서 기재했을 때 실제 데이터베이스 저장 순서
하지만 홈화면에 정렬하기 체크박스 선택되어 있는 상태에서 새로운 개인 정보를 기재했을 때 **실제 데이터베이스 저장 순서는 바뀌지 않습니다.**  

즉, **제일 마지막에 기재한 개인 정보는 실제로 데이터베이스에서는 제일 마지막에 저장**되고, **홈화면에서 출력될 때**만(정렬하기 체크박스가 선택되어 있는 경우)  

**이름을 기준으로 오름차순으로 정렬되어서 출력되지 실제 데이터베이스의 저장 순서에는 영향을 끼치지 않습니다.**  

![데이터베이스에_저장된_순서](https://user-images.githubusercontent.com/52854217/170691042-53aeea82-f417-4f1d-b45d-04053cee3530.JPG)


# 참고링크

아래 깃헙블로그에 코드와 화면 구성에 대한 자세한 설명을 적어놨습니다.  

https://injae7034.github.io/java/addressbook_web_project_01/  

https://injae7034.github.io/java/addressbook_web_project_02/  

https://injae7034.github.io/java/addressbook_web_project_03/  

https://injae7034.github.io/java/addressbook_web_project_04/  
