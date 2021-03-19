package com.an.gradlespringboot.service;


import com.an.gradlespringboot.entity.User;
import com.an.gradlespringboot.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository ;

    // 保存
    public void addUser (User user){
        userRepository.save(user) ;
    }

    // 根据年龄查询
    public User findByAge (Integer age){
        return userRepository.findByAge(age) ;
    }

    // 多条件查询
    public User findByNameAndAge (String name, Integer age){
        return userRepository.findByNameAndAge(name,age) ;
    }

    // 自定义SQL查询
    public User findSql (String name){
        return userRepository.findSql(name) ;
    }

    // 自定义SQL查询
    public User findByAccount (String account){
        return userRepository.findUserByAccount(account) ;
    }

    // 根据ID修改
    public void update (User user){
        userRepository.save(user) ;
    }

    //根据id删除一条数据
    public void deleteStudentById(Integer id){
        userRepository.deleteById(id);
    }

    public Page<User> findUsersByAge(int age,int page,int size){
        //排序
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        //分页
        Pageable pageable = PageRequest.of(page, size, sort);

        return userRepository.getUserPageByAge(age,pageable);
    }
}
