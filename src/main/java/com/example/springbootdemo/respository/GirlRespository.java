package com.example.springbootdemo.respository;

import com.example.springbootdemo.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GirlRespository extends JpaRepository<Girl, Integer>{
    //通过年龄来查询
    public List<Girl> findByAge(Integer age);
}
