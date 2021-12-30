package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepositoty;
import hello.hellospring.repository.JdbcTempleteMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration  //컴포넌트가 아니고 자바로 직접 등록하는 방법, @Service, @Repository 등 컴포넌트 어노테이션을 사용안함
public class SpringConfig {


    private DataSource dataSource; //디비용  아래는 generate >> constructor 한거임
    //application.properties의 디비정보로 dataSource 아래와 같이 주입시켜주고 하단처럼 Bean을 생성해서 사용하게 된다.
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }




    @Bean
    public MemberService memberService(){
        return  new MemberService(memberRepository()); //괄호안에서 Ctrl+P해보면 뭘 더 추가해야하는지 나옴
    }  //Autowired와 같은기능

    @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository(); //메모리용
        //return new JdbcMemberRepositoty(dataSource); //디비용
        return  new JdbcTempleteMemberRepository(dataSource); //jdbctemplete 방식
    }


}
