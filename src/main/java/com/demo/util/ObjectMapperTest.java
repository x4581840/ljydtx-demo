package com.demo.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ObjectMapper
 * jackson提供的，主要是用来把对象转换成为一个json字符串
 */
/*
第一，ObjectMapper在自动识别java类时，如果类中有一个节点是Node，但是在ObjectMapper眼中它却是node（小写的n）；
这样如果你的json字符串中节点是Node，此时就会匹配不上。

第二，ObjectMapper在自动识别java类时，如果类中有一个节点是ID,则在ObjectMapper眼中它是id。

第三，所以定义类的属性要规范，首字母小写，对于ID，就应该写id，而不是ID或者iD。要规范。第一点和第二点也就是我犯错的原因。

第四，如果json字符串中，key的首字母大些了，则要用replace转换成小写。比如{"Node":"n1"},要替换成{"node":"n1"}。

第五，为什么选择ObjectMapper，因为效率高。
 */
public class ObjectMapperTest {

    @Test
    public void test1() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        //序列化的时候序列对象的所有属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        Person person = new Person(1L, "zxc", new Date());
        //这是最简单的一个例子,把一个对象转换为json字符串
        String personJson = objectMapper.writeValueAsString(person);
        System.out.println("personJson1:" + personJson);

        //默认为true,会显示时间戳
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        personJson = objectMapper.writeValueAsString(person);
        System.out.println("personJson2:" + personJson);

        /**
         *personJson1:{"id":1,"name":"zxc","birthday":"2018-08-08 21:30:35"}
         *personJson2:{"id":1,"name":"zxc","birthday":1533735035953}
         */
    }

    @Test
    public void test2() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        //序列化的时候序列对象的所有属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //如果是空对象的时候,不抛异常,也就是对应的属性没有get方法(必须把所有的属性的get方法注释掉才抛错)
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        Person person = new Person(1L, "ljy", new Date());

        String personJson = objectMapper.writeValueAsString(person);
        System.out.println(personJson);

        //默认是true,即会抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
        personJson = objectMapper.writeValueAsString(person);
        System.out.println(personJson);
        /**
         * 输出
         * {}
         com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class com.demo.util.Person and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS)
         */
    }

    @Test
    public void test3() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //序列化的时候序列对象的所有属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//		Person person = new Person(1, "zxc", new Date());

//		String personJson = objectMapper.writeValueAsString(person);
//		System.out.println(personJson);

        //注意,age属性是不存在在person对象中的
        String personStr = "{\"id\":1,\"name\":\"zxc\",\"age\":\"zxc\"}";

        //这里的person必须要有无参的构造函数
        Person person = objectMapper.readValue(personStr, Person.class);
        System.out.println(person);

        //默认为true
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        person = objectMapper.readValue(personStr, Person.class);
        System.out.println(person);

        /**
         * 输出
         *Person [id=1, name=zxc, birthDate=null]
         com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "age"
         */
    }

    /**
     * Include.ALWAYS  是序列化对像所有属性
       Include.NON_NULL 只有不为null的字段才被序列化
       Include.NON_EMPTY 如果为null或者 空字符串和空集合都不会被序列化
     */


    /**
     * 如何把一个对象集合转换为一个 Java里面的数组
     */
    @Test
    public void test4() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //序列化的时候序列对象的所有属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);

        Person person1 = new Person(1L, "zxc", new Date());
        Person person2 = new Person(2L, "ldh", new Date());

        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);

        //先转换为json字符串
        String personStr = objectMapper.writeValueAsString(persons);

        //反序列化为List<user> 集合,1需要通过 TypeReference 来具体传递值
        List<Person> persons2 = objectMapper.readValue(personStr, new TypeReference<List<Person>>() {});

        for(Person person : persons2) {
            System.out.println(person);
        }

        //2,通过 JavaType 来进行处理返回
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, Person.class);
        List<Person> persons3 = objectMapper.readValue(personStr, javaType);

        for(Person person : persons3) {
            System.out.println(person);
        }
    }
}
