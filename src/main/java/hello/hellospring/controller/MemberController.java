package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    //private final MemberService memberService = new MemberService();
    // --> 스프링컨테이너에 일단 등록부터하고 같은 걸 여기저기서 같이 사용한다.
    private final MemberService memberService; //일단 등록

    //생성자(컨스트럭터) 만듦
    @Autowired  //memberService를 스프링이 MemberService에서 가져다가 연결시켜줌 // DI Dependency Injection 이다. 의존성을 주입해줘서 연결시켜줌
    //근데 Service에 @Service 가 있어야 가져올수있고 없으면 스프링이 해당 서비스를 못찾음
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
