package hello.hellospring.service;

import com.sun.istack.NotNull;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링컨테이너와 테스트를 함께 실행, 즉 진짜 스프링을 띄워서 테스트
@Transactional  //@Test어노테이션있는 함수는 테스트완료하고 디비입력된거 초기화시켜서 원래데로 돌림(롤백시켜버림)
class MemberServiceIntegrationTest {

    //테스트라 다른데서 가져다 쓸일없으니 이렇게 간단하게 한다.
    @Autowired MemberService memberService; //SpringConfig에 설정된걸 가져와서 초기화
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {   //give when  then 형식으로 테스트하는게 기본
        //given
        Member member = new Member();
        member.setName("spring"); //맵버이름샘플을 넣고

        //when
        Long saveID = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveID).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외확인(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e =  assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름의 회원입니다.");

    }

}