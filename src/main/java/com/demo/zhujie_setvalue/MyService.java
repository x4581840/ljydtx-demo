package com.demo.zhujie_setvalue;

public class MyService {

    public void myMethod(@InjectValue("myValue") String myParam) {
        System.out.println("Parameter value: " + myParam);
    }

//    public void myMethod() {
//        System.out.println("Parameter value: null" );
//    }
}
