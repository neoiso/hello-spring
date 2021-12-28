package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//리포지토리를 확장하는 구현체
@Repository  //얘도 스프링이 땡겨 쓸려면 이렇게 리포지터리라고 지정해야함, 그래야 서비스에서 땡겨다 쓸 수 있음
public class MemoryMemberRepository implements  MemberRepository {  /*여기까지하고 alt+Enter 하고 implemet methods
                                                                    선택하고 전체 선택하고 OK하면 아래내용 자동완성*/
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);  //store에 넣기전에 member에 id값 세팅
        store.put(member.getId(), member); //Map에 저장
        return member;  //저장된 결과 반환

    }

    @Override
    public Optional<Member> findByID(Long id) {
        return Optional.ofNullable(store.get(id)); //Null 값때문에 Optional 널을 감싸서 처리
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    //단위 테스트 종료시 마다 초기화 함수
    public void clearStore() {
        store.clear();
    }

}
