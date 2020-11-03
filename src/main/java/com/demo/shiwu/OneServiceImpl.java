package com.demo.shiwu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/11/12 9:39 AM
 * @Version 1.0
 **/
@Service
public class OneServiceImpl implements OneService {

    @Autowired
    private ITransactionService transactionService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void testTransactionService(String name) {
        transactionService.serivce1(name+"1");
        transactionService.service2(name+"2");
        transactionService.service3(name+"3");
    }
}
