package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        memoryMemberRepository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("김동욱");

        memoryMemberRepository.save(member);

        Member result =  memoryMemberRepository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("AAA");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("BBB");
        memoryMemberRepository.save(member2);

        Member result = memoryMemberRepository.findByName("AAA").get();

        assertThat(member1).isEqualTo(result);

    }

    @Test
    public void findAll(){
        List<Member> members = new ArrayList<>();
        Member member1 = new Member();
        member1.setName("김동욱");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("이동욱");
        memoryMemberRepository.save(member2);

        Member member3 = new Member();
        member3.setName("박동욱");
        memoryMemberRepository.save(member3);

        members.add(member1);
        members.add(member2);
        members.add(member3);
        List<Member> result = memoryMemberRepository.findAll();

        assertThat(members).isEqualTo(result);
        assertThat(result.size()).isEqualTo(3);

    }

}
