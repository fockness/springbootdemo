package com.example.springbootdemo.controller;

import com.example.springbootdemo.domain.Girl;
import com.example.springbootdemo.domain.Result;
import com.example.springbootdemo.respository.GirlRespository;
import com.example.springbootdemo.service.GirlService;
import com.example.springbootdemo.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    @Autowired
    private GirlRespository girlRespository;


    @Autowired
    private GirlService girlService;

    private Logger logger = LoggerFactory.getLogger(GirlController.class);

    /**
     * 查询所有女生列表
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList(){
        logger.info("girlList");
        return girlRespository.findAll();
    }

    /**
     * 添加一个女生,当请求中传入的属性越来越多时，可以直接传Girl对象进来
     * 表单验证:
     * ①在domain需验证属性前在限制和未通过提示
     * ②在请求参数前加@Valid
     * ③将验证结果传入到bindingResult中去
     * ④统一异常处理:
     *      1)定义统一的正常和异常的格式，前台接收处理方便
     *      2)后台定义一个异常格式对象Result
     *      3）无论什么状态都将这个result格式的数据返回给前台
     */
    @PostMapping(value = "/girls")
    public Result<Girl> girlAdd(@Valid  Girl girl, BindingResult bindingResult){
        //Girl这个对象需要验证, 并将验证结果返回到bindingResult中去
        if(bindingResult.hasErrors()){
            return ResultUtil.error(1, bindingResult
                    .getFieldError().getDefaultMessage());
        }
        girl.setAge(girl.getAge());
        girl.setCupSize(girl.getCupSize());
        return ResultUtil.success(girlRespository.save(girl));
    }
    /**
     * 查询一个女生
     */
    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
        return girlRespository.findOne(id);
    }

    /**
     * 更新一个女生
     */
    @PutMapping(value = "/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id, @RequestParam("cupSize") String cupSize, @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRespository.save(girl);
    }
    /**
     * 删除一个女生
     */
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id){
        girlRespository.delete(id);
    }

    @GetMapping("/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age){
        return girlRespository.findByAge(age);
    }

    @PostMapping("/girls/two")
    public void girlTwo(){
        girlService.insertTwo();
    }

    @GetMapping("/girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception{
        girlService.getAge(id);
    }

    //全局默认页,路径不正确或404都会访问这个页面
    @RequestMapping
    public Integer defaultMapping(){
        return 404;
    }
}
