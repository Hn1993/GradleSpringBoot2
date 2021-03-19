package com.an.gradlespringboot.repository;

import com.an.gradlespringboot.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    // 但条件查询
    User findByAge(Integer age);
    // 多条件查询
    User findByNameAndAge(String name, Integer age);
    // 自定义查询
    @Query("from User u where u.name=:name")
    User findSql(@Param("name") String name);

    // 自定义查询
    @Query("from User u where u.account=:account")
    User findUserByAccount(@Param("account") String account);

    /**
     * 根据用户年龄，分页查询用户列表（使用JPQL语句）
     */
    @Query("SELECT u FROM User u WHERE u.age = :age")
    public Page<User> getUserPageByAge(@Param("age")int age, Pageable pageable);

}
