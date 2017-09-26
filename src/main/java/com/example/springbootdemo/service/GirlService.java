package com.example.springbootdemo.service;

import com.example.springbootdemo.domain.Girl;
import com.example.springbootdemo.enums.ResultEnums;
import com.example.springbootdemo.exception.GirlException;
import com.example.springbootdemo.respository.GirlRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

@Service
public class GirlService {

    @Autowired
    private GirlRespository girlRespository;

    @Transactional
    public void insertTwo(){
        Girl girlA = new Girl();
        girlA.setAge(18);
        girlA.setCupSize("A");
        girlRespository.save(girlA);

        Girl girlB = new Girl();
        girlB.setAge(18);
        girlB.setCupSize("A");
        girlRespository.save(girlB);
    }

    public void getAge(Integer id) throws Exception {
        Girl girl = girlRespository.findOne(id);
        Integer age = girl.getAge();
        if(age < 10){
            throw new GirlException(ResultEnums.PRIMARY_SCHOOL);
        }else if(age > 10 && age < 16){
            throw new GirlException(ResultEnums.MIDDLE_SCHOOL);
        }
    }

    //待测试的方法
    public Girl findOne(Integer id){
        return girlRespository.findOne(id);
    }
}
