package hello.hellospring.domain;

import javax.persistence.*;

@Entity //jpa
public class Member {  //jpa가 관리하는 엔티티가 됨

    //pk를 매핑해줘야 함
    @Id  @GeneratedValue(strategy =  GenerationType.IDENTITY) //아이덴티티 설정
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
