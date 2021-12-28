package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //MemberService memberService = new MemberService(memberRepository); //일단 테스트할 서비스 설정하고
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository(); //초기화


    MemberService memberService; //인스턴스를 같이 쓰기 위해 외부에서 입력하는 방식으로 변경
    MemoryMemberRepository memberRepository; //초기화

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }



    @AfterEach
    public void afterEach() {
        memberRepository.clearStore(); //변수 등 초기화 함
    }
    /** 초기화 */

    @Test
    void join() {   //give when  then 형식으로 테스트하는게 기본
        //given
        Member member = new Member();
        member.setName("spring"); //맵버이름샘플을 넣고

        //when
        Long saveID = memberService.join(member);

        //then
        Member findMember =  memberService.findOne(saveID).get();
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


/*
        memberService.join(member1);

        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름의 회원입니다.");
        }
*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}