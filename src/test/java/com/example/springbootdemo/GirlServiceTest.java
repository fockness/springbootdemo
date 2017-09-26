package com.example.springbootdemo;

import com.example.springbootdemo.domain.Girl;
import com.example.springbootdemo.service.GirlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)//表示程序要在测试环境中运行,底层使用junit测试工具
@SpringBootTest//将启动整个spring工程
public class GirlServiceTest {

    @Autowired
    private GirlService girlService;

    @Test
    public void findOneTest(){
        Girl girl = girlService.findOne(9);
        Assert.assertEquals(new Integer(13), girl.getAge());
    }
}
