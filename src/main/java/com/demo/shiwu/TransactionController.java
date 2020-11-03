package com.demo.shiwu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/11/12 9:30 AM
 * @Version 1.0
 **/
@RestController
public class TransactionController {

    @Autowired
    private OneService oneService;

    @GetMapping("testTransaction")
    public void testTransaction(@RequestParam(value = "name") String name) {
        oneService.testTransactionService(name);
    }
}
