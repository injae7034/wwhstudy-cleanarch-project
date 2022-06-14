package wwhstudycleanarchproject.no24shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import wwhstudycleanarchproject.no24shop.domain.Address;
import wwhstudycleanarchproject.no24shop.member.application.port.out.GetMemberRepository;
import wwhstudycleanarchproject.no24shop.member.application.port.out.JoinMemberRepository;
import wwhstudycleanarchproject.no24shop.member.domain.Member;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class No24shopApplicationTests {

	@Autowired
	JoinMemberRepository joinMemberRepository;

	@Autowired
	GetMemberRepository getMemberRepository;

	@Test
	@Transactional
	@Rollback(value = false)
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
