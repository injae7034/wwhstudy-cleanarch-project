# 헥사고날 아키텍처를 적용한 주소록 웹프로젝트

# 1. 기술 스택
## 1.1 백엔드 : java, spring, jpa, h2 database
## 1.2 프론트엔드 : thymeleaf, html, javascript, bootstrap

<br><br>

# 2. 패키지구조(헥사고날 아키텍처 적용)
```java
addressbook
|____common
|    |____PersonalCommandValidating
|
|____personal
|    |____adapter
|    |    |____in
|    |    |    |____web
|    |    |         |____correct
|    |    |         |    |____CorrectPersonalController
|    |    |         |    |____CorrectPersonalForm
|    |    |         |
|    |    |         |____erase
|    |    |         |    |____ErasePersonalController
|    |    |         |
|    |    |         |____find
|    |    |         |    |____FindPersonalController
|    |    |         |    |____FindPersonalForm
|    |    |         |
|    |    |         |____record
|    |    |         |    |____RecordPersonalController
|    |    |         |    |____RecordPersonalForm
|    |    |         |
|    |    |         |____HomeController
|    |    |
|    |    |____out
|    |         |____persistence
|    |              |____JpaCorrectPersonalRepository
|    |              |____JpaErasePersonalRepository
|    |              |____JpaFindPersonalRepositroy
|    |              |____JpaGetPersonalRepositroy
|    |              |____JpaGetPersonalsRepository
|    |              |____JpaRecordPersonalRepository
|    |
|    |____application
|    |    |____port
|    |    |    |____in
|    |    |    |    |____correct
|    |    |    |    |    |____CorrectPersonalCommand
|    |    |    |    |    |____CorrectPersonalUsecase
|    |    |    |    |
|    |    |    |    |____erase
|    |    |    |    |    |____ErasePersonalUseCase
|    |    |    |    |
|    |    |    |    |____find
|    |    |    |    |    |____FindPersonalCommand
|    |    |    |    |    |____FindPersonalUseCase
|    |    |    |    |
|    |    |    |    |____get
|    |    |    |    |    |____GetPersonalCommand
|    |    |    |    |    |____GetPersonalQuery
|    |    |    |    |    |____GetPersonalsQuery
|    |    |    |    |
|    |    |    |    |____record
|    |    |    |         |____RecordPersonalCommand
|    |    |    |         |____RecordPersonalUseCase
|    |    |    |
|    |    |    |____out
|    |    |         |____CorrectPersoanlRepository
|    |    |         |____ErasePersonalRepository
|    |    |         |____FindPersonalRepository
|    |    |         |____GetPersonalRepository
|    |    |         |____GetPersonalsRepository
|    |    |         |____RecordPersonalRepository
|    |    |
|    |    |____service
|    |         |____CorrectPersonalService
|    |         |____ErasePersonalService
|    |         |____FindPersonalService
|    |         |____GetPersonalService
|    |         |____GetPersonalsService
|    |         |____RecordPersonalService
|    |
|    |____domain
|         |____Personal
|    
|____AddressBookApplication
```

<br><br>

# 3. 홈화면

![홈화면_정리하기_기능_추가](https://user-images.githubusercontent.com/52854217/170682880-9d26d7d5-9f26-4ea3-9086-7b39b2de45e6.JPG)  

<br><br><br><br>

# 4. 기재하기 화면

홈화면에서 **기재하기 버튼**을 클릭하면 기재하기 화면으로 이동합니다.  

**이름, 주소, 전화번호, 이메일주소를 입력한 다음 기재하기 버튼을 클릭**하면  

해당 개인의 정보가 주소록에 기재되고(데이터베이스에 새로 저장되고) **홈화면**으로 돌아갑니다.  

이 때, **홈화면의 개인 정보 리스트에 새로 기재한 개인의 정보가 추가되어 출력**됩니다.  

![기재하기_기본_화면](https://user-images.githubusercontent.com/52854217/170682988-3c4c0b47-d193-4502-8bcc-205afa9b7dea.JPG)

<br><br>

## 4.1 기재하기 예외 화면1(이름, 주소, 전화번호는 필수, 이메일 선택)
**이름, 주소, 전화번호는 반드시 기재**해야합니다.  

이름, 주소, 전화번호 중에 **하나라도 공백으로 비워두면 입력 검증에서 예외를 발생시켜 다음 과정으로 진행되지 않고, 안내 메세지를 출력합니다.**  

즉, 주소록에 등록(데이터베이스에 저장)되지 않게 막았습니다.  

![기재하기_예외_화면_1](https://user-images.githubusercontent.com/52854217/170683275-f39dc05b-b4a8-4919-89a8-f1df18b3618c.JPG)

<br><br>

## 4.2 기재하기 예외 화면2(이메일 형식을 지켜야함)
**이메일은 비워도 되지만 입력한다면 반드시 형식을 지켜야합니다.**  

이메일 형식을 지키지 않으면 입력 검증에서 예외를 발생시켜 다음 과정으로 넘어가지 않고, 안내메세지를 출력합니다.  

즉, 주소록에 등록(데이터베이스에 저장)되지 않게 막았습니다.  

![기재하기_예외_화면_2](https://user-images.githubusercontent.com/52854217/170684563-3e83edec-46be-40ca-a484-b71feed931bd.JPG)

<br><br><br><br>

# 5. 찾기 화면
홈화면에서 **이름으로 찾기 버튼**을 클릭하면 찾기 화면으로 이동합니다.  

![찾기_기본_화면](https://user-images.githubusercontent.com/52854217/170684736-6472d841-706a-4173-9e53-21a4ae7a446e.JPG)

<br><br>

## 5.1 찾기 예외 화면(찾을 이름을 적지 않을 경우)
찾기 화면에서 이름을 적지 않고 **공백으로 찾기 버튼을 클릭하는 경우 예외를 발생**시켜 안내메세지를 출력합니다.  

![찾기_예외_화면](https://user-images.githubusercontent.com/52854217/170685012-ba288417-ebce-4ac3-ab3d-6addd4041f58.JPG)

<br><br>

## 5.2 이름으로 찾은 화면
찾기 화면에서 이름(ex. "홍길동")을 적어서 찾기버튼을 클릭하면 찾을 이름에 해당하는 개인들(동명이인 포함)의 정보를 찾기화면에 출력합니다.  

(물론 찾을 이름에 해당하는 개인이 없다면 아무것도 출력되지 않습니다.)  

![찾은_화면](https://user-images.githubusercontent.com/52854217/170685650-b7278d45-2ff8-4e59-a77f-8c8e91013b13.JPG)

<br><br><br><br>

# 6. 수정하기 화면
**홈화면 또는 찾기 화면**에서 개인들의 정보 옆에 있는 **수정 버튼**을 클릭하면 수정하기 화면으로 이동합니다.  

이 때, 수정하기 전의 개인정보, 즉, **현재 개인정보가 수정하기 화면에 출력**됩니다.  

**이름은 바꾸지 못하게 막았고, 나머지(주소, 전화번호, 이메일주소)는 바꿀 수 있도록 설정하였습니다.**  

따라서 주소, 전화번호, 이메일주소 중에 바꿀 정보만 수정해서 입력한 다음 수정하기 버튼을 클릭하면  

**해당 개인의 정보가 수정되어(데이터베이스에도 반영되어) 홈화면으로 이동하는데 이 때 해당 개인의 정보가 바뀌어서 홈화면에 출력됩니다.**   

![수정하기_기본_화면](https://user-images.githubusercontent.com/52854217/170686065-d18695f7-48f4-4e93-a5fb-328be17347c4.JPG)

<br><br>

## 6.1 수정하기 예외 화면1(주소, 전화번호는 필수, 이메일 선택)  

**주소, 전화번호** 중에 **하나라도 공백으로 비워두면 입력 검증에서 예외를 발생시켜 다음 과정으로 진행되지 않고, 안내 메세지를 출력합니다.**  

즉, 주소록에서 수정(데이터베이스의 데이터를 바꿔서 저장)되지 않게 막았습니다.  

![수정하기_예외_화면_1](https://user-images.githubusercontent.com/52854217/170687389-5041f44f-644d-4458-9adf-acd784e210a4.JPG)

<br><br>

## 6.2 수정하기 예외 화면2(이메일 형식을 지켜야함)

**이메일은 비워도 되지만 입력한다면 반드시 형식을 지켜야합니다.**  

이메일 형식을 지키지 않으면 입력 검증에서 예외를 발생시켜 다음 과정으로 넘어가지 않고, 안내메세지를 출력합니다.  

즉, 주소록에서 수정(데이터베이스의 데이터를 바꿔서 저장)되지 않게 막았습니다.  

<br><br><br><br>

# 7. 지우기 팝업창
**홈화면 또는 찾기 화면**에서 개인들의 정보 옆에 있는 **지우기 버튼**을 클릭하면 **지우기 팝업창**이 뜹니다.  

팝업창에서 **확인 버튼을 누르면 실제로 주소록에서 해당 개인 정보를 지우고(데이터베이스에서 개인 데이터 지움)**,

**취소버튼을 누르면** 주소록에서 팝업창만 사라지고,  

**실제로 주소록에서 해당 개인 정보를 지우지 않습니다.(데이터베이스에서 개인 데이터 지우지 않습니다.)**  

![지우기_팝업창_1](https://user-images.githubusercontent.com/52854217/170688266-c816a16d-5001-48e2-869c-e42406ad4778.JPG)

<br><br><br><br>

# 8. 홈화면에서 정렬하기 체크박스 체크했을 때 화면
홈화면에서 **정렬하기 체크박스 체크하면** 기재된 순서(데이터베이스에 저장된 순서)대로 출력되어 있는 개인 정보들이  

**이름을 기준으로 오름차순으로 정렬되어 출력됩니다.**  

![홈화면에서_정리된상태](https://user-images.githubusercontent.com/52854217/170689745-2d00b047-d319-4440-b08a-ce8ebffc5c74.JPG)

<br><br>

## 8.1 홈화면에 정렬하기 체크박스 선택되어 있는 상태에서 기재하기 화면으로 이동

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

## 8.2 홈화면에 정렬하기 체크박스 선택되어 있는 상태에서 기재했을 때 실제 데이터베이스 저장 순서
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
