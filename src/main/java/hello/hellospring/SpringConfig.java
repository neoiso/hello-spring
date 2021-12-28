package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //컴포넌트가 아니고 자바로 직접 등록하는 방법, @Service, @Repository 등 컴포넌트 어노테이션을 사용안함
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return  new MemberService(memberRepository()); //괄호안에서 Ctrl+P해보면 뭘 더 추가해야하는지 나옴
    }  //Autowired와 같은기능

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }


}
