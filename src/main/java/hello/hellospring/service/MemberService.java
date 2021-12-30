package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service  //컨트롤러에서 가져다 쓸수있도록 알려줌 이거 없으면 이 서비스는 단순 클래스가 되어 스프링이 얘를 어디서 가져다 쓸지 못찾음
//이렇게 어노테이션을 쓰는게 컴포넌트 스캔과 자동 의존관계 설정방식임
@Transactional //jpa에서 필수
public class MemberService {
    //Test생성은 Ctrl+shift+T

    //서비스를 하기 위해서는 일단 레포지토리가 있어야 한다.

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //테스트와 같은 인스턴스를 사용하기 위해 아래와 같이 바꾼다.
    private final MemberRepository memberRepository;

    //@Autowired  //이 멤버서비스는 리포지토리가 필요해서 DI해줘서 연결시킴
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    /** 회원가입 */
    public Long join(Member member) {
        //같은 이름이 있으면 안된다는 조건으로 설정
        validateDuplicateMember(member); //중복이름회원체크  //같은 이름이 있으면 안된다는 조건으로 설정
        memberRepository.save(member);
        return member.getId();
    }


    //Ctrl+alt+M으로 함수 뺴냄
    private void validateDuplicateMember(Member member) {
        //  memberRepository.findByName(member.getName());  여기서 Ctrl + alt + v 하면 아래껄로 자동 변경해줌
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이름의 회원입니다.");
            });
    }


    /**전체회원조회*/
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /** id로 검색 */
    public  Optional<Member> findOne(Long memberID){
        return memberRepository.findByID(memberID);
    }

}
