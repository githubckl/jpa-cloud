package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.OpenFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.atguigu.entity.*;
import org.springframework.data.domain.Page;

import javax.annotation.Resource;

@RestController
@RequestMapping("/customer")
@Slf4j
public class OrderController {
    @Resource
    private OpenFeignService openFeignService;

    @GetMapping("/getOne")
    public Customer getOne(Long id) {
        return openFeignService.getOne(id);
    }
}
