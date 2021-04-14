package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional  //자동으로 롤백됨
public class MemberServiceIntegrationTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    public void 회원가입() throws Exception{
        //given 뭔가 주어졌을 때
        Member member = new Member();
        member.setName("동우기");

        //when 무엇을 검증하는가
        Long saveId = memberService.join(member);

        //then 결과가 이렇게 나와야됨
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());

    }

    @Test
    public void 중복회원예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("김동욱");
        Member member2 = new Member();
        member2.setName("김동욱");

        //when
        memberService.join(member1);
        //예외가 발생해야됨
        IllegalStateException e = assertThrows(IllegalStateException.class,()->memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


    }
}
