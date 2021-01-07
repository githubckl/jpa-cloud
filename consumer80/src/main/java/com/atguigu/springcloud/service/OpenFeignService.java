package com.atguigu.springcloud.service;

import com.atguigu.entity.*;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "PROVIDER")
public interface OpenFeignService {
    @GetMapping("customer/getOne")
    public Customer getOne(@RequestParam("id") Long id);
}
