package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {

    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    public void 회원가입() throws Exception{
        Member member = new Member();
        member.setName("동우기");

        Long saveId = memberService.join(member);

        Member findMember = memberRepository.findById(saveId).get();

        assertEquals(member.getName(), findMember.getName());

    }

    @Test
    public void 중복회원예외() throws Exception{
        Member member1 = new Member();
        member1.setName("김동욱");
        Member member2 = new Member();
        member2.setName("김동욱");

        memberService.join(member1);
        //예외가 발생해야됨
        IllegalStateException e = assertThrows(IllegalStateException.class,()->memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


    }
}
