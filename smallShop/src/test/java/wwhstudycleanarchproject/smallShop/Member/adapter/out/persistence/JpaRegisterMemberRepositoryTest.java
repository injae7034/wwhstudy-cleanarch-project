package wwhstudycleanarchproject.smallShop.Member.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import wwhstudycleanarchproject.smallShop.Member.application.port.out.RegisterMemberRepository;
import wwhstudycleanarchproject.smallShop.Member.domain.Member;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(JpaRegisterMemberRepository.class)
@Transactional
class JpaRegisterMemberRepositoryTest {

    @Autowired
    RegisterMemberRepository repository;

    @Test
    void saveTest() {
        //given
        Member member = new Member(
                "hong@naver.com",
                "123456",
                "홍길동",
                "서울시 중구"
        );
        System.out.println(member.getId());

        //when
        Member savedMember = repository.save(member);
        System.out.println(savedMember.getId());

        //then
        assertThat(savedMember).isEqualTo(member);
    }
}
