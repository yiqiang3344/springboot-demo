package yi.sidney.springbootdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yi.sidney.springbootdemo.entity.User;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    User findUserById(Integer id);

    User findUserByMobile(@Param("mobile") String mobile);

    @Query(name = "findUserByName", nativeQuery = true, value = "select * from user where name=:name")
    List<User> findUserByName(@Param("name") String name);
}