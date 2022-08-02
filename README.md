# 2022 우아한스터디 - 헥사고날 아키텍쳐로 구현하는 작은 스프링 부트 토이 프로젝트
헥사고날 아키텍처를 적용해서 주소록 토이 프로젝트를 만들었습니다.  

처음에는 오직 개인 정보를 저장할 수 있는 주소록 기능만 있었습니다.  

여기에 **로그인 기능을 추가**하여서 로그인 된 멤버만 주소록을 이용할 수 있도록 하였습니다.  

로그인 하지 않은 회원은 url을 알더라도 접근하지 못하도록 스프링 인터셉터를 적용하였습니다.  

또한 로그인한 회원마다 각자 다른 주소록의 개인 데이터를 가질 수 있고, 각자 자신이 저장한 정보만 볼 수 있도록 구현하였습니다.  

처음에는 서버 사이드 렌더링 기술을 활용해서 웹애플리케이션을 만들었습니다.  

직접 화면을 통해 사용하면서 e2e 테스트를 거쳐서 기능이 정상적으로 작동함을 확인하였습니다.  

이후 웹애플리케이션의 모든 기능을 헥사고날 아키텍처를 적용하여 api로도 구현하였습니다.  

api 역시 Postman을 이용해 e2e 테스트하여 기능이 제대로 돌아가는 것을 확인하였습니다.  

# 목차
[1. 기술 스택](#1-기술-스택)  
[2. 도메인](#2-도메인)  

SSR(서버사이드 렌더링)  
[3. 처음 화면](#3-처음-화면)  
[4. 회원가입 화면](#4-회원가입-화면)  
[5. 로그인 화면](#5-로그인-화면)  
[6. 로그인 후 화면(회원마다 저장된 데이터가 다름)](#6-로그인-후-화면회원마다-저장된-데이터가-다름)  
[7. 비밀번호 변경 화면](#7-비밀번호-변경-화면)  
[8. 로그아웃 버튼 클릭했을 때](#8-로그아웃-버튼-클릭했을-때)  
[9. 회원탈퇴 버튼 클릭했을 때](#9-회원탈퇴-버튼을-클릭했을-때)  
[10. 스프링 인터셉터 적용](#10-스프링-인터셉터-적용)  
[11. 기재하기 화면](#11-기재하기-화면)  
[12. 찾기 화면](#12-찾기-화면)  
[13. 수정하기 화면](#13-수정하기-화면)  
[14. 지우기 팝업창](#14-지우기-팝업창)  
[15. 홈화면에서 정렬하기 체크박스 체크했을 때 화면](#15-홈화면에서-정렬하기-체크박스-체크했을-때-화면)  

API  
[16. 예외처리 및 validation 체크](#16-예외처리-및-validation-체크)  
[17. member 도메인 패키지 구조](#17-member-도메인-패키지-구조)  
[18. 회원가입 API](#18-회원가입-API)  
[19. 로그인 API](#19-로그인-API)  
[20. 회원 정보 찾기 API](#20-회원-정보-찾기-API)  
[21. 로그아웃 API](#21-로그아웃-API)  
[22. 비밀번호 변경 API](#22-비밀번호-변경-API)  
[23. 회원탈퇴 API](#23-회원탈퇴-API)  
[24. personal 도메인 패키지 구조](#24-personal-도메인-패키지-구조)  
[25. 기재하기 API](#25-기재하기-API)  
[26. 개인 정보 하나 얻기 API](#26-개인-정보-하나-얻기-API)  
[27. 전체 개인 정보 얻기 API](#27-전체-개인-정보-얻기-API)  
[28. 이름으로 개인 정보 찾기 API](#28-이름으로-개인-정보-찾기-API)  




# 1. 기술 스택
## 1.1 백엔드 : java, spring, jpa, h2 database
## 1.2 프론트엔드 : thymeleaf, html, javascript, bootstrap

<br><br>

# 2. 도메인
도메인에는 회원인 Member와 주소록의 개인 정보를 의미하는 Personal이 있습니다.  

Member는 Personal의 참조값들을 List로 가지고 있고, Personal은 Member의 단일 참조값을 가지고 있습니다.  

즉, **일대다 양방향 관계**로 설정하였습니다.  


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

그리고 기재된 개인의 정보는 당연히 현재 로그인된 멤버와 연관되어(현재 멤버의 id값을 외래키로 하여) DB에 저장됩니다.  
```java
@Controller
@RequiredArgsConstructor
@RequestMapping("/personal")
public class RecordPersonalController {

    private final RecordPersonalUseCase useCase;

    private final FindMemberQuery query;

    @GetMapping("/record")
    public String createForm(Model model) {
        model.addAttribute("recordPersonalForm", new RecordPersonalForm());
        return "personal/recordPersonalForm";
    }

    @PostMapping("/record")
    public String recordPersonal(@Valid RecordPersonalForm form, BindingResult result,
                                 @SessionAttribute(name = "loginMemberId")
                                         Long loginMemberId) {

        if (result.hasErrors()) {
            return "personal/recordPersonalForm";
        }

        Member loginMember = query.findMember(loginMemberId);

        useCase.recordPersonal(form.getName(), form.getAddress(),
                form.getTelephoneNumber(), form.getEmailAddress(), loginMember);

        return "redirect:/";
    }
}
```
```java
@RequiredArgsConstructor
@Transactional
@Service
public class RecordPersonalService implements RecordPersonalUseCase {

    private final RecordPersonalRepository recordRepository;

    private final FindByEmailRepository findRepository;

    @Override
    public Long recordPersonal(String name, String address, String telephoneNumber,
                               String emailAddress, Member member) {

        //db에 저장할 새로운 personal 객체 생성
        Personal personal = new Personal(name, address, telephoneNumber,
                emailAddress, member);

        //db에 저장(personal은 member의 참조값을 알고 있음)
        recordRepository.save(personal);

        //member는 현재 personal의 참조값을 모르기 때문에 연관관계를 맺어줘야 함.
        // 멤버를 찾음으로써 영속성 컨텍스트를 활성화시킨다.
        Member findMember = findRepository.findByEmail(member.getEmail()).orElse(null);

        //이메일에 해당하는 멤버가 있으면
        if (findMember != null) {
            //멤버에 새로 생성한 personal의 참조값을 추가한다.
            findMember.getPersonals().add(personal);

            return personal.getId();
        } else {
            //멤버가 없으면 예외를 발생시킨다.
            throw new IllegalStateException();
        }
    }
}
```

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

<br>

**더티체킹**을 통해 변경사항을 db에 반영합니다.  

```java
@Repository
@RequiredArgsConstructor
public class JpaCorrectPersonalRepository implements CorrectPersonalRepository {

    private final EntityManager em;

    @Override
    public void update(Long id, String address,
                       String telephoneNumber, String emailAddress) {
        Personal findPersonal = em.find(Personal.class, id);

        findPersonal.changePersonalInfo(address, telephoneNumber, emailAddress);

    }
}
```


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

## 15.3 정리하기 기능과 관련된 Controller 
```java
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final GetPersonalsQuery getPersonalsQuery;

    private final ArrangePersonalQuery arrangePersonalQuery;

    private final FindMemberQuery findMemberQuery;

    private boolean isArrangeChecked = false;

    private List<Personal> personals;

    @GetMapping("/")
    public String home(
            @SessionAttribute(name = "loginMemberId", required = false) Long loginMemberId,
            HttpServletRequest request,
            Model model) {

        //세션에 회원 데이타가 없으면 home
        if (loginMemberId == null) {
            //세션이 있으면 있는 세션을 반환, 없으면 신규 세션을 생성함
            HttpSession session = request.getSession();

            return "home";
        }

        Member loginMember = findMemberQuery.findMember(loginMemberId);

        if(isArrangeChecked == false) {
            personals = getPersonalsQuery.getPersonals(loginMember);
        } else {
            personals = arrangePersonalQuery.arrangePersonal(loginMember);
        }

        model.addAttribute("personals", personals);
        model.addAttribute("isArrangeChecked", isArrangeChecked);
        model.addAttribute("member", loginMember);

        return "loginHome";
    }

    @PostMapping("/")
    public String arrangePersonal( @SessionAttribute(name = "loginMemberId", required = false)
                                               Long loginMemberId,
                                   HttpServletRequest request,
                                   Model model) {

        Member loginMember = findMemberQuery.findMember(loginMemberId);

        if(isArrangeChecked == false) {
            isArrangeChecked = true;
            personals = arrangePersonalQuery.arrangePersonal(loginMember);
        } else {
            isArrangeChecked = false;
            personals = getPersonalsQuery.getPersonals(loginMember);
        }
        model.addAttribute("personals", personals);
        model.addAttribute("isArrangeChecked", isArrangeChecked);
        model.addAttribute("member", loginMember);

        return "loginHome";
    }

}
```

## 15.4 db에서 로그인 된 멤버와 관련된 personal 데이터만 가져 오는 Repository
```java
@Repository
@RequiredArgsConstructor
public class JpaGetPersonalsRepository implements GetPersonalsRepository {

    private final EntityManager em;

    @Override
    public List<Personal> findAll(Member member) {
        return em.createQuery("select p from Personal p where p.member = :member",
                        Personal.class)
                .setParameter("member", member)
                .getResultList();
    }

}
```

## 15.5 db에서 로그인 된 멤버와 관련된 personal 데이터를 정렬해서 가져 오는 Repository
```java
@Repository
@RequiredArgsConstructor
public class JpaArrangePersonalByNameRepository implements ArrangePersonalRepository {

    private final EntityManager em;

    @Override
    public List<Personal> arrange(Member member) {
        return em.createQuery("select p from Personal p where p.member = :member order by p.name ",
                        Personal.class)
                .setParameter("member", member)
                .getResultList();
    }
}
```
<br><br>

# 16. 예외처리 및 validation 체크
## 16.1 ExceptionResponse

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
}
```
예외가 발생했을 경우나 validation에서 통과하지 못할 경우 ExceptionResponse의 객체를 JSON형태로 반환하여 사용합니다.  

## 16.2 CustomizedResponseEntityExceptionHandler
```java
@RestControllerAdvice(annotations = RestController.class)
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handlerAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateMemberException.class)
    public final ResponseEntity<Object> handleDuplicateMemberExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotSamePasswordException.class)
    public final ResponseEntity<Object> handlePasswordNotSameAsConfirmPasswordExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({MemberNotFoundException.class, PersonalNotFoundException.class})
    public final ResponseEntity<Object> NotFoundExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(), "Validation Failed", ex.getBindingResult().toString());

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
```
각종 예외처리들과 validation 예외가 발생했을 때 처리를 하는 클래스입니다.  

서버 사이드 렌더링과는 별도로 api에 적용하기 위해 RestController 애너테이션이 붙은 클래스에만 작동하도록 설정하였습니다.  
<br><br>

# 17. member 도메인 패키지 구조
![member디렉토리구조](https://user-images.githubusercontent.com/52854217/182298173-419c34d8-af28-4f3d-9bf8-a2b8867a764e.JPG)

<br>

api와 webapplication 패키지를 각각 만들어 webapplication에서는 서버사이드 렌더링 쪽과 관련된 코드를 작성하였고, API와 관련된 코드들은 api 패키지에 작성하였습니다.  

<br><br>

# 18. 회원가입 API
## 18.1 RegisterMemberRequest
```java
@Data
public class RegisterMemberRequest {

    @NotBlank(message = "아이디는 필수로 적어야 합니다.")
    @Email(message = "이메일 형식을 지켜주세요.")
    private String email; // 로그인 ID

    @NotBlank(message = "비밀번호는 필수로 적어야 합니다.")
    private String password;

    @NotBlank(message = "확인 비밀번호는 비밀번호와 똑같이 적어야 합니다.")
    private String confirmPassword;

    @NotBlank(message = "이름은 필수로 적어야 합니다.")
    private String name; // 사용자 이름

}
```
JSON형태로 넘어오는 데이터를 전달하는 DTO역할을 담당합니다.  

## 18.2 RegisterMemberApiController
```java
@RestController
@RequiredArgsConstructor
public class RegisterMemberApiController {

    private final RegisterMemberUseCase useCase;

    @PostMapping("/members")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity registerMember(@RequestBody @Valid
                                                         RegisterMemberRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new NotSamePasswordException(
                    "비밀번호와 확인 비밀번호가 서로 일치하지 않습니다.");
        }

        Long savedId = useCase.registerMember(request.getEmail(), request.getPassword(), request.getName());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedId)
                .toUri();

        return ResponseEntity.created(location).build();

    }
}
```

## 18.3 RegisterMemberApiController postman 테스트
![registerMemberApiPostman](https://user-images.githubusercontent.com/52854217/182304729-1e8aebd7-19f4-4f86-bed8-b2ef2f3ee1b2.JPG)
postman을 활용하여 http body에 RegisterMemberRequest와 일치하는 형태의 JSON형식의 데이터를 POST 메소드를 이용하여 해당 url에 보낸 결과  

정상적으로 생성이 되었다는 상태코드 **201 Created**와 새로 생성된 멤버의 Location을 헤더에 실어서 반환하였습니다.  

또한 h2 db에도 정상적으로 저장되었음을 확인할 수 있습니다.  

![registerMemberApiPostman결과](https://user-images.githubusercontent.com/52854217/182305382-fd652375-cb7e-4978-a1b3-80a87f458af5.JPG)

## 18.4 중복 아이디로 회원 가입시 예외처리
똑같은 이메일 아이디로 회원 가입을 하려고 하면 아까 위에서 작성한 코드에 따라 예외처리가 되는데 이 때 중복아이디 예외일 경우 상태코드는 Conflict이고 그에 맞는 예외 메세지가 출력됩니다.  

![registerMemberApiPostman예외처리](https://user-images.githubusercontent.com/52854217/182306677-4fcd9276-28e0-4f08-8bc4-810c709470fd.JPG)

## 18.5 이름을 공백으로 한 다음 회원 가입시 Validation check

![registerMemberApiPostmanValidationCheck](https://user-images.githubusercontent.com/52854217/182318626-d91c9c05-56d6-47a1-aeaf-c0e6c032c8e2.JPG)


위에서 정의한 Validation 체크 코드를 통해 validation 에러가 발생하면 상태코드 400 Bad Request와 안내 메세지가 출력됩니다.  

<br><br>

# 19. 로그인 API
## 19.1 LoginMemberRequest
```java
@Data
public class LoginMemberRequest {

    @NotBlank(message = "아이디는 필수로 적어야 합니다.")
    @Email(message = "이메일 형식을 지켜주세요.")
    private String email;

    @NotBlank(message = "비밀번호는 필수로 적어야 합니다.")
    private String password;

}
```
JSON형태로 넘어오는 데이터를 전달하는 DTO역할을 담당합니다.  

## 19.2 LoginMemberApiController
```java
@RestController
@RequiredArgsConstructor
public class LoginMemberApiController {

    private final LoginMemberUseCase useCase;

    @PostMapping("/members/login")
    public void loginMember(
            @RequestBody @Valid LoginMemberRequest request,
            HttpServletRequest httpServletRequest) {

        Member loginMember = useCase.loginMember(request.getEmail(), request.getPassword());

        if (loginMember == null) {
            throw new MemberNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션을 반환, 없으면 신규 세션을 생성함
        HttpSession session = httpServletRequest.getSession();
        //세션에 로그인 회원 id 정보 보관
        session.setAttribute("loginMemberId", loginMember.getId());
    }
}
```

## 19.3 LoginMemberApiController postman 테스트

![loginMemberApiPostman](https://user-images.githubusercontent.com/52854217/182307935-bab7208c-9b92-4c45-bcfd-282cf12d9212.JPG)
post메소드를 활용하여 http body에 로그인에 필요한 정보를 전달한 결과 성공적으로 JSESSIONID가 쿠키로 설정되는 것을 볼 수 있습니다.  

## 19.4 로그인할 때 비밀번호 또는 아이디가 일치하지 않는 경우
![loginMemberApiPostman예외처리](https://user-images.githubusercontent.com/52854217/182308400-1c74027c-9efe-4daa-9a8d-b6aada2c3156.JPG)
위의 예외처리코드에서 이 경우 404 Not Found로 상태코드를 정의하였고, 예외메세지도 이에 맞게 출력됩니다.  

<br><br>

# 20. 회원 정보 찾기 API
## 20.1 GetPersonalByMemberResponse
```java
@Data
@AllArgsConstructor
public class GetPersonalByMemberResponse {

    private String name;
    private String address;
    private String telephoneNumber;
    private String emailAddress;

}
```
제일 위의 도메인 코드를 보면 아시다시피 **현재 Member와 Personal은 양방향 순환 참조**를 하고 있습니다.  

그래서 Member의 정보를 얻기 위해 그냥 Member에 있는 데이터를 그대로 반환하면 Member가 Personal을 부르고,  

Personal이 또 Member를 계속 불러서 **무한 반복**이 됩니다.  

이를 예방하기 위해 Member가 가지고 있는 Personal 정보에서 Member에 대한 참조값을 뺀 GetPersonalByMemberResponse을 별도로 생성합니다.  

## 20.2 FindMemberResponse
```java
@Data
@AllArgsConstructor
public class FindMemberResponse {

    private String email;
    private String name;
    private int personalCount;
    private List<GetPersonalByMemberResponse> personals;

}
```
회원 정보 찾기 API의 반환값으로 사용되는데 이 때 위에서 언급한대로 무한반복을 막기위해 List에는 Personal 객체 대신  

Member에 대한 참조값이 빠진 GetPersonalByMemberResponse을 이용합니다.  

또한 id 값을 빼고, 꼭 필드한 정보들만 필드로 가지도록 설정하였습니다.  

## 20.3 FindMemberApiController
```java
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
```
먼저 url을 통해 전달받은 id를 통해 멤버를 찾고 찾은 멤버에서 personal 값을 구한 다음에 member에 대한 참조값과 id값을 뺀  

꼭 필요한 정보 이름, 주소, 전화번호, 이메일주소만 GetPersonalByMemberResponse객체에 담습니다.  

그리고 이 값을 반환합니다.  

## 20.4 FindMemberApiController postman 테스트

![findMemberApiPostman](https://user-images.githubusercontent.com/52854217/182312644-77240f81-1bb1-4857-b25e-71269fc010bc.JPG)
postman 테스트를 통해 무한반복이 되지 않고 꼭 필요한 회원정보만 반환하는 것을 확인할 수 있습니다.  

## 20.5 회원 정보를 찾을 때 해당 id와 일치하는 회원이 없을 경우 예외처리
![findMemberApiPostman예외처리](https://user-images.githubusercontent.com/52854217/182313310-77d4e072-e923-4f37-a681-cb4c96d3d20f.JPG)
404Not Found 상태코드와 회원을 찾을 수 없다는 예외 메세지를 반환합니다.  

<br><br>

# 21. 로그아웃 API
## 21.1 LogoutMemberApiController
```java
@RestController
public class LogoutMemberApiController {

    @PostMapping("/members/logout")
    public void logoutMember(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
```
## 21.2 LogoutMemberApiController postman 테스트

![logoutMemberPostman로그아웃전](https://user-images.githubusercontent.com/52854217/182319825-a620c813-06a5-4513-af67-2d3d53326f81.JPG)

로그아웃 전에는 헤더의 set-cookie에 sessionid가 설정되어 있습니다.  

![logoutMemberPostman로그아웃후](https://user-images.githubusercontent.com/52854217/182319958-00b079ef-2da8-4bed-ae32-eeafac1d1134.JPG)

로그아웃 후에는 헤더의 set-cookie가 사라진 것을 볼 수 있습니다.  

<br><br>

# 22. 비밀번호 변경 API
## 22.1 ChangeMemberRequest
```java
@Data
public class ChangeMemberRequest {

    @NotBlank(message = "비밀번호 변경하기 전에 기존 비밀번호를 먼저 적어야 합니다.")
    private String originalPassword;

    @NotBlank(message = "바꿀 비밀번호는 필수로 적어야 합니다.")
    private String changePassword;

    @NotBlank(message = "확인 비밀번호는 필수로 적어야 합니다.")
    private String confirmPassword;

}
```
비밀번호 변경시 그에 맞는 데이터를 전달하는데 사용하는 dto 클래스입니다.  

## 22.2 ChangeMemberApiController
```java
@RestController
@RequiredArgsConstructor
public class ChangeMemberApiController {

    private final ChangePasswordUseCase changePasswordUseCase;

    private final FindMemberQuery findMemberQuery;

    @PutMapping("/members/{id}")
    public void changeMember(@PathVariable Long id,
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
    }

}
```
url의 id에 해당하는 멤버가 있는지 체크하고, 입력한 비밀번호가 기존 비밀번호와 일치하는지 체크하고, 변경 비밀번호와 확인 비밀번호가 일치하는지 체크합니다.  

그리고 이 모든 게 다 통과되면 비밀번호를 변경합니다.  

## 22.3 ChangeMemberApiController postman 테스트
![changePasswordApiPostman](https://user-images.githubusercontent.com/52854217/182322678-9e955b97-c34d-490c-b3b1-f538242932dd.JPG)

기존비밀번호와 변경 비밀번화 확인 비밀번호를 htttp body에 실어서 put 메소드를 통해 해당 url을 통해 send하면 성공적으로 비밀번호가 변경되는 것을 알 수 있습니다.  


## 22.4 기존 비밀번호와 입력한 비밀번호 불일치시 예외처리
![changePasswordApiPostman기존비밀번호불일치예외](https://user-images.githubusercontent.com/52854217/182322795-44fde03c-52a0-485f-92e9-f143bed89b71.JPG)

## 22.5 변경 비밀번호와 확인 비밀번호 불일치시 예외처리
![changePasswordApiPostman변경및확인비밀번호불일치예외](https://user-images.githubusercontent.com/52854217/182322943-87068256-a2ef-48f6-8de6-4e7325b3638c.JPG)

<br><br>

# 23. 회원탈퇴 API
## 23.1 WithdrawalMemberRequest
```java
@Data
public class WithdrawalMemberRequest {

    @NotBlank(message = "회원 탈퇴를 하려면 기존비밀번호를 입력하셔야 합니다.")
    private String password;

}
```
회원탈퇴를 위해 사용자 비밀번호를 입력 받을 때 이 비밀번호를 전달하기 위한 dto클래스입니다.  

## 23.2 WithdrawalMemberApiController
```java
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
```
url을 통해 전달 받은 회원 id를 통해 회원이 있는지 찾고, 회원이 있는 경우  

httpBody를 통해 전달받은 비밀번호가 기존 비밀번호가 일치하는지 확인한 다음에  

이를 모두 통과하면 회원을 탈퇴시킵니다.  

## 23.3 WithdrawalMemberApiController postman 테스트

![withdrawalMemberPostman](https://user-images.githubusercontent.com/52854217/182324702-ad780a80-5e6c-4e2c-90a7-cfde533c034a.JPG)

성공적으로 지워질 경우 상태코드를 204 No Content가 되도록 설정하였습니다.  

## 23.4 기존비밀번호와 일치하지 않는 경우

![withdrawalMemberPostman예외처리](https://user-images.githubusercontent.com/52854217/182325381-414484a6-e592-456b-a5a3-f39bf6abcd35.JPG)
비밀번호가 일치하지 않는다는 안내문구와 상태코드 409 Conflict를 반환하도록 설정하였습니다.  

<br><br>

# 24. personal 도메인 패키지 구조
![personal디렉토리구조](https://user-images.githubusercontent.com/52854217/182328851-9e65be8b-5f04-4d8f-b9d1-dc7fc3f2c609.JPG)

<br><br>

# 25. 기재하기 API
## 25.1 RecordPersonalByMemberRequest
```java
@Data
public class RecordPersonalByMemberRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;
    @NotBlank(message = "주소는 필수입니다.")
    private String address;
    @NotBlank(message = "전화번호는 필수입니다.")
    private String telephoneNumber;
    @Email(message = "이메일 형식을 지켜주세요.")
    private String emailAddress;

}
```
주소록에 저장할 개인정보를 받아서 전달하는 dto 클래스입니다.  

## 25.2 RecordPersonalByMemberApiController
```java
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
```
url을 통해 전달 받은 memberId를 통해 id와 일치하는 회원정보를 찾고, RecordPersonalByMemberRequest의 정보를 바탕으로 Personal 객체를 생성하여 주소록에 기재합니다.  

기재가 끝난 다음 새로 생성된 Personal 객체에 대한 정보를 얻을 수 있는 url을 HTTP 헤더의 location에 저장하고, 상태코드를 201 Created로 하여 반환합니다.  

## 25.3 RecordPersonalByMemberApiController postman 테스트

![recordPersonalApiPostman](https://user-images.githubusercontent.com/52854217/182331183-d81a84a8-39b5-41a4-86b1-bb533d4f2c17.JPG)

성공적으로 개인 정보가 send 되면 상태코드 201 Created와 Http Header의 location에 새로 생성된 개인 정보를 확인할 수 있도록 설정하였습니다.  

![recordPersonalApiPostman후h2db](https://user-images.githubusercontent.com/52854217/182331776-ddbdee89-a924-4890-a879-73813a53ce16.JPG)

h2 db에도 성공적으로 저장된 것을 확인할 수 있습니다.  

예외처리나 validation의 경우 위와 같으므로 생략하도록 하겠습니다.  

<br><br>

# 26. 개인 정보 하나 얻기 API
## 26.1 GetPersonalByMemberResponse
```java
@Data
@AllArgsConstructor
public class GetPersonalByMemberResponse {

    private String name;
    private String address;
    private String telephoneNumber;
    private String emailAddress;

}
```
아까 위에서 회원 정보 찾기 API에서 사용한 dto클래스인데 앞에서 말했듯이 무한반복을 막기 위해 Personal에서 Member에 대한 참조값을 빼고, id값을 빼어 꼭 필요한 정보만 필드로 가집니다.  

## 26.2 GetPersonalByMemberApiController
```java
@RestController
@RequiredArgsConstructor
public class GetPersonalByMemberApiController {

    private final FindMemberQuery findMemberQuery;

    private final GetPersonalQuery getPersonalQuery;

    @GetMapping("/members/{memberId}/personals/{personalId}")
    public GetPersonalByMemberResponse getPersonalByMember(@PathVariable Long memberId,
                                                           @PathVariable Long personalId) {

        Member findMember = findMemberQuery.findMember(memberId);

        if (findMember == null) {
            throw new MemberNotFoundException("해당 id와 일치하는 멤버를 찾을 수 없습니다.");
        }

        Personal findPersonal = getPersonalQuery.getPersonal(findMember, personalId);

        if (findPersonal == null) {
            throw new PersonalNotFoundException("해당하는 개인 정보를 찾을 수 없습니다.");
        }

        return new GetPersonalByMemberResponse(
                findPersonal.getName(),
                findPersonal.getAddress(),
                findPersonal.getTelephoneNumber(),
                findPersonal.getEmailAddress());
    }
}
```
```java
@Repository
@RequiredArgsConstructor
public class JpaGetPersonalRepository implements GetPersonalRepository {

    private final EntityManager em;

    @Override
    public Personal findOne(Member member, Long personalId) {
        return em.createQuery(
                        "Select p from Personal p where p.member = :member and" +
                                " p.id = :personalId",
                        Personal.class)
                .setParameter("member", member)
                .setParameter("personalId", personalId)
                .getResultList()
                .stream()
                .findAny()
                .orElse(null);
    }

}
```
전달받는 url에서 우선 memberId를 통해 멤버를 찾고, 멤버가 있으면 그 멤버객체와 personalId를 getPersonal의 매개변수로 넘깁니다.  

getPersonal, 즉, 서비스 계층에서 레포지토리로 이 값이 전달되어 findOne 메소드에서는 이 매개변수를 바탕으로 Personal 객체 정보를 찾습니다.  

Member와 personalId 둘 다 일치해야 Personal 객체가 반환될 것이고, 아니면 null 값이 반환될 것입니다.  

즉, 해당 멤버가 자신의 데이터(개인 정보)에만 접근할 수 있도록 설정하였습니다.  

personalId가 일치하더라도 자신의 데이터가 아니면 접근할 수 없도록 설정하였습니다.  

찾은 후에는 무한반복을 막기 위해 별도로 GetPersonalByMemberResponse를 생성하여 이를 반환합니다.  

## 26.3 GetPersonalByMemberApiController postman 테스트
![getPersonalApiPostman](https://user-images.githubusercontent.com/52854217/182335624-a707cc6e-992f-4189-9473-e06107620f8e.JPG)
멤버와 personalId가 일치하면 자신이 저장한 데이터(개인 정보)를 반환받을 수 있습니다.  

## 26.4 자신의 데이터가 아닌 데이터에 접근하려고 할 때 postman 테스트

![getPersonalApiPostman예외처리](https://user-images.githubusercontent.com/52854217/182336739-bb0e1ef4-0662-4dbb-a382-a4baffb19391.JPG)

보시다시피 personalId가 일치하더라도 본인의 데이터가 아니면 접근할 수 없고, 404 Not Found 상태코드와 예외 메세지가 반환됩니다.  

<br><br>

# 27. 전체 개인 정보 얻기 API
## 27.1 GetPersonalsByMemberResponse
```java
@Data
@AllArgsConstructor
public class GetPersonalsByMemberResponse {

    int count;
    List<GetPersonalByMemberResponse> personals;

}
```
해당 회원이 가지고 있는 개인 정보의 개수와 개인 정보 리스트들을 반환하기 위한 dto 클래스입니다.  

이 때 List에 저장된 개인 정보는 무한반복을 방지하기 위해 Personal이 아니라 위의 개인 정보 하나 얻기 API에서 사용한 GetPersonalByMemberResponse를 사용합니다.  

## 27.2 GetPersonalsByMemberApiController
```java
@RestController
@RequiredArgsConstructor
public class GetPersonalsByMemberApiController {

    private final FindMemberQuery findMemberQuery;

    private final GetPersonalsQuery getPersonalsQuery;

    @GetMapping("/members/{memberId}/personals")
    public GetPersonalsByMemberResponse getPersonalsByMember(@PathVariable Long memberId) {

        Member findMember = findMemberQuery.findMember(memberId);

        if (findMember == null) {
            throw new MemberNotFoundException("해당 멤버를 찾을 수 없습니다.");
        }

        List<GetPersonalByMemberResponse> getPersonalsResponses = new ArrayList<>();
        List<Personal> personals = getPersonalsQuery.getPersonals(findMember);

        for (Personal personal : personals) {
            getPersonalsResponses.add(new GetPersonalByMemberResponse(
                    personal.getName(),
                    personal.getAddress(),
                    personal.getTelephoneNumber(),
                    personal.getEmailAddress()));
        }

        return new GetPersonalsByMemberResponse(getPersonalsResponses.size(), getPersonalsResponses);
    }

}
```
자신의 데이터만 가지고 올 수 있도록 하였고, 무한반복을 막기 위해 마지막에 찾은 개인 Personal들을 모두 GetPersonalByMemberResponse로 변환하여 저장한 다음에 GetPersonalsByMemberResponse를 반환합니다.  

## 27.3 GetPersonalsByMemberApiController postman 테스트
![getPersonalsApiPostman](https://user-images.githubusercontent.com/52854217/182338831-ff121aba-d9e0-43ee-a159-6c29a296d7e0.JPG)

자신이 저장한 개인정보의 개수와 각각의 데이터 값들이 반환되는 것을 확인할 수 있습니다.  

<br><br>

# 28. 이름으로 개인 정보 찾기 API
## 28.1 FindPersonalByMemberRequest
@Data
public class FindPersonalByMemberRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

}
HTTP Body에서 찾을 이름을 전달 받기 위한 dto 역할을 하는 클래스입니다.  

## 28.2 FindPersonalByMemberResponse
@Data
@AllArgsConstructor
public class FindPersonalByMemberResponse {

    private int count;
    private List<GetPersonalByMemberResponse> personals;
}
이름으로 찾은 개인들(동명이인이 있을 수 있기 때문에)의 정보를 개수와 List로 전달하는 dto 역할을 하는 클래스입니다.  

## 28.3 FindPersonalByMemberApiController
```java
@RestController
@RequiredArgsConstructor
public class FindPersonalByMemberApiController {

    private final FindMemberQuery findMemberQuery;

    private final FindPersonalUseCase findPersonalUseCase;

    @PostMapping("/members/{memberId}/personals/find")
    public ResponseEntity<FindPersonalByMemberResponse> findPersonalByMember(
            @PathVariable Long memberId,
            @RequestBody @Valid
                    FindPersonalByMemberRequest request) {

        Member findMember = findMemberQuery.findMember(memberId);

        if (findMember == null) {
            throw new MemberNotFoundException("해당 id와 일치하는 멤버를 찾을 수 없습니다.");
        }

        List<Personal> findPersonals = findPersonalUseCase.findPersonalByName(
                findMember, request.getName());

        List<GetPersonalByMemberResponse> getPersonalsResponses = new ArrayList<>();

        for (Personal personal : findPersonals) {
            getPersonalsResponses.add(new GetPersonalByMemberResponse(
                    personal.getName(),
                    personal.getAddress(),
                    personal.getTelephoneNumber(),
                    personal.getEmailAddress()));
        }

        int count = getPersonalsResponses.size();

        FindPersonalByMemberResponse findPersonalByMemberResponse =
                new FindPersonalByMemberResponse(
                        count, getPersonalsResponses);

        if (count == 0) {
            return new ResponseEntity<>(findPersonalByMemberResponse, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(findPersonalByMemberResponse, HttpStatus.OK);
    }

}
```
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
post 메소드와 find url을 이용하여 이름으로 개인 정보 찾기 API를 구현합니다.  

일단 url을 통해 전달받은 memberId를 통해 멤버를 구하고, 구한 멤버와 Http Body를 통해 전달받은 이름을 findPersonalByName 메소드의 매개변수로 넘깁니다.  

그러면 서비스 계층에서 이 매개변수를 레포지토리 계층까지 넘기고 레포지토리에서 해당 멤버 자신의 데이터 중에서 현재 찾을 이름에 해당하는 개인 정보가 있는지 찾습니다.  

있으면 해당 개인들의 정보가 List로 반환될 것이고, 없으면 null값이 반환될 것입니다.  

즉, 해당 이름의 개인 정보도 본인의 데이터에서만 찾고 남의 데이터에서는 찾지 않도록 하였습니다.  

마지막으로 해당 이름으로 찾은 데이터가 있으면 상태코드 200 Ok를 반환하도록 하였고, 찾은 데이터가 없으면 상태코드 204 No Content를 반환하도록 하였습니다.  

## 28.4 FindPersonalByMemberApiController postman 테스트
'박길동'이라는 이름으로 개인 정보를 찾을 것이고, 현재 db에는 '박길동'이라는 개인 정보는 3개가 저장되어 있습니다.  
![findMemberByNameApiH2db2](https://user-images.githubusercontent.com/52854217/182342710-6c47e393-011c-489f-b85d-3ba4b7df3ad5.JPG)

<br><br>

여기서 memberId가 1인 회원이 '박길동'이라는 이름으로 개인 정보를 찾을 경우 db 결과는 다음과 같습니다. 

![findMemberByNameApiH2db](https://user-images.githubusercontent.com/52854217/182342908-7ffb950d-26e2-4c65-91b9-ec21787ef5b2.JPG)

<br><br>

이를 postman에서 실험해보면 정확히 본인 데이터만 접근할 수 있음을 확인할 수 있습니다.  

![findMemberByNamePostmanApi](https://user-images.githubusercontent.com/52854217/182343260-27e92b87-6d5b-4e86-8a5c-67f64ed59a71.JPG)

<br><br>



# 참고링크

아래 깃헙블로그에 코드와 화면 구성에 대한 자세한 설명을 적어놨습니다.  

https://injae7034.github.io/java/addressbook_web_project_01/  

https://injae7034.github.io/java/addressbook_web_project_02/  

https://injae7034.github.io/java/addressbook_web_project_03/  

https://injae7034.github.io/java/addressbook_web_project_04/  
