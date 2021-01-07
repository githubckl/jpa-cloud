package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.impl.CustomerImpl;
import com.example.demo.service.impl.LinkManImpl;
import com.example.demo.service.impl.RoleImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Set;


@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    CustomerImpl customerImpl;
    @Autowired
    LinkManImpl linkManImpl;
    @Autowired
    RoleImpl roleImpl;

    @Transactional
    @Rollback(value = false)
    @RequestMapping("save")
    void save(String name, Long id) {
        Customer customer = new Customer();
        customer.setCustId(id);
        customer.setName(name);
        customerImpl.save(customer);
    }

    @GetMapping("getOne")
    Customer getOne(@RequestParam("id")Long id){
        return customerImpl.findOne(id);
    }

    @Transactional
    @Rollback(value = false)
    @RequestMapping("delete")
    void delete(Long id) {
        customerImpl.delete(id);
    }

    @Transactional
    @Rollback(value = false)
    @RequestMapping("oneToManySave")
    void oneToManySave() {
        Customer customer = new Customer();
        LinkMan linkMan = new LinkMan();
        customer.setCustId(1l);
        customer.setName("ckl");
        linkMan.setLkmId(1l);
        customer.getLinkMEN().add(linkMan);
        linkManImpl.save(linkMan);
        customerImpl.save(customer);

    }

    @Transactional
    @Rollback(value = false)
    @RequestMapping("oneToManyFind")
    void oneToManyFind() {
        Customer customer = customerImpl.findOne(2l);
        Set<LinkMan> set = customer.getLinkMEN();
        System.out.println(set.toArray());
    }

    @Transactional
    @Rollback(value = false)
    @RequestMapping("queryBySpecification")
    Page queryBySpecification() {
        return customerImpl.findBySpecification();
    }

    @Transactional
    @Rollback(value = false)
    @RequestMapping("manyToManySave")
    void manyToManySave() {
        Customer customer = new Customer();
        customer.setCustId(6l);
        Role role = new Role();
        role.setRoleName("教师");
        roleImpl.save(role);
        customer.getRoles().add(role);
        role = new Role();
        role.setRoleName("程序员");
        roleImpl.save(role);
        customer.getRoles().add(role);
        customerImpl.save(customer);
    }

}
