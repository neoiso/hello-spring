package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{


    private final EntityManager em;  //스프링이 jpa 라이브러리로 엔티티매니저를 만들어줌 우리는 이걸 사용해서 인젝션 하고 사용하면 됨됨

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }



    //이정도만 해도 알아서 다해줌
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }


    @Override
    public Optional<Member> findByID(Long id) {
        Member member = em.find(Member.class, id); //조회할 타입, 식별자로 처리
        return Optional.ofNullable(member); //널값이있을수 있으므로 이렇게 리턴
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result =  em.createQuery("select m from Member m where m.name = :name", Member.class)
                                .setParameter("name", name)
                                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {

        //List<Member> result =  em.createQuery("select m from Member m", Member.class).getResultList();
        //return result;
        return em.createQuery("select m from Member m", Member.class).getResultList(); //객체 m 자체를 셀렉트함
        // 인라인방식으로 합침



    }

}
