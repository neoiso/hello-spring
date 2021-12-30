package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTempleteMemberRepository implements  MemberRepository{
    //아래기본내용은 윗줄 implements를 작성하고 alt+Enter 해서 implements Methods(Alt+Shift+Enter)해서 전체선택하여 OK

    private final JdbcTemplate jdbcTemplate;   //윗줄만들면 아래 generate해서 constructor해서 OK해서 DI해주고 아래와같이수정해준다.

    //@Autowired  //생성자가 하나면 생략가능
    public JdbcTempleteMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Member save(Member member) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;

    }


    @Override
    public Optional<Member> findByID(Long id) { //여기서 객체생성 위에서 날린 쿼리 결과를 memberRowMapper()를 통해서 맵핑하고
        List<Member> result =  jdbcTemplate.query("select * from member where id=?", memberRowMapper(), id); //결과값ㅇ을 리스트로 반환
        return result.stream().findAny();
    }


    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result =  jdbcTemplate.query("select * from member where name=?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }



    private RowMapper<Member> memberRowMapper() {  //람다로 변경 가능
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {

                Member member = new Member();
                member.setId((rs.getLong("id")));
                member.setName(rs.getString("name"));
                return member;
            }
        };
    }



}
