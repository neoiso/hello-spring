package hello.hellospring.respository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;  //assertThat(result 까지 치니깐 나옴


class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //매번 테스트 함수종료시 마다 초기화해서 순서의존없이 테스트 진행 가능,
    // 아니면 여러개 테스트 실행시 초기화안되서 값이 이상해져서 오류날 수 있음. 중요 기본사항

    @AfterEach
    public void afterEach() {
        repository.clearStore(); //변수 등 초기화 함
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findByID(member.getId()).get();

        //System.out.println("result = " + (result == member));//검증방법1 테스트 출력
        //Assertions.assertEquals(result, member); //검증방법1  초록색 Tests passed 뜸 실패하면 붉은색
        assertThat(member).isEqualTo(result);   //검증방법2
    }

    @Test
    public  void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        //Assertions.assertEquals(result, member1); //검증방법1
        assertThat(result).isEqualTo(member1); //검증방법2

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member1.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        //Assertions.assertEquals(result.size(),2); //검증방법1
        assertThat(result.size()).isEqualTo(2); //검증방법2
    }

}
