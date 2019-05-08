package com.demo.spring.annotation.autowried_resource.controller;

import com.demo.spring.annotation.autowried_resource.ICar;
import com.demo.spring.annotation.autowried_resource.IHuman;
import com.demo.spring.annotation.autowried_resource.impl.WhiteMan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/4/28 3:33 PM
 * @Version 1.0
 **/

@RestController
@RequestMapping("/human")
public class HumanController {

    @Autowired
    @Qualifier("blackMan")
    private IHuman human;

    @Resource(name = "whiteMan")
    private IHuman human1;

    @Resource(type = WhiteMan.class)
    private IHuman human2;

    @Resource
    @Qualifier("whiteMan")
    private IHuman human3;
    //AutoWired和Resource都可以配合Qualifier使用

    //既没有指定name，也没有指定type，报错
    //@Resource
    //private IHuman human3;

    //@Autowired //容器中没有实例，报错
    //private ICar car;

    @Autowired(required=false) //容器中没有实例，但是没报错，car1是null
    private ICar car1;

    @RequestMapping("/printMessage")
    public void printMessage(@RequestParam(name = "name") String name) {
        human.printMessage(name);
        System.out.println(human);
        human1.printMessage(name);
        System.out.println(human1);
        human2.printMessage(name);
        System.out.println(human2);
        //human3.printMessage(name);

        human3.printMessage(name);
        System.out.println(human3);

        //System.out.println(car);

        System.out.println(car1);
    }
}
