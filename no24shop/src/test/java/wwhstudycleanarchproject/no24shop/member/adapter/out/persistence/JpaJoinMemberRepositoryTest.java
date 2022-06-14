package wwhstudycleanarchproject.no24shop.member.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import wwhstudycleanarchproject.no24shop.common.CreateAndUpdateTimeAndBy;
import wwhstudycleanarchproject.no24shop.domain.Address;
import wwhstudycleanarchproject.no24shop.member.application.port.out.GetMemberRepository;
import wwhstudycleanarchproject.no24shop.member.application.port.out.JoinMemberRepository;
import wwhstudycleanarchproject.no24shop.member.domain.Member;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import({JpaJoinMemberRepository.class, JpaGetMemberRepository.class})
@Transactional
class JpaJoinMemberRepositoryTest {

    @Autowired
    JoinMemberRepository joinMemberRepository;

    @Autowired
    GetMemberRepository getMemberRepository;

    @Test
    void saveTest() {
        //given
        Member member = new Member(
                "hong@naver.com",
                "123456",
                "홍길동",
                new Address("서울시", "중구", "123"),
                "GENERAL",
                "USER");

        System.out.println(member.getId());

        //when
        joinMemberRepository.save(member);
        Long memberId = member.getId();
        System.out.println(memberId);

        //then
        assertThat(member).isEqualTo(getMemberRepository.findOne(memberId));
    }
}
