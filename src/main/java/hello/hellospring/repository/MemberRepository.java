package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findByID(Long id);   //optional은 null 처리를 위한방법
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
