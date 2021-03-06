package com.wangtao.controller;

import com.wangtao.domain.Girl;
import com.wangtao.domain.Result;
import com.wangtao.repository.GirlRepository;
import com.wangtao.service.GirlService;
import com.wangtao.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @Author Created by 廖师兄
 * 2016-11-03 23:15
 */
@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    /**
     * 查询所有女生列表
     *
     * @return
     */
    @GetMapping(value = "/girlList")
    public List<Girl> girlList() {
        return girlRepository.findAll();
    }

    /**
     * 添加一个女生
     *
     * @return
     */
    @GetMapping(value = "/girladd")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){

        return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
            girl.setAge(girl.getAge());
            girl.setCupSize(girl.getCupSize());
            girl.setMoney(girl.getMoney());
            girl.setId(girl.getId());


        return ResultUtil.success(girlRepository.save(girl));
    }

    //查询一个女生
   @GetMapping(value = "/findone/{id}")
   public Girl findOne(@PathVariable("id") Integer id){



        return girlRepository.findOne(id);
   }

    //更新
    @PutMapping(value = "/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);

        return girlRepository.save(girl);
    }

    //删除
    @DeleteMapping(value = "/girldelete/{id}")
    public void girlDelete(@PathVariable("id") Integer id) {
        girlRepository.delete(id);
    }

    //通过年龄查询女生列表
    @GetMapping(value = "/girlListByAge/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age) {
        return girlRepository.findByAge(age);
    }

    @PostMapping(value = "/girls/two")
    public void girlTwo() {
        girlService.insertTwo();
    }
    @GetMapping(value ="/girls/getage/{id}")
    public void getAge(@PathVariable("id") Integer id)throws Exception{

        girlService.getAge(id);
    }


}
