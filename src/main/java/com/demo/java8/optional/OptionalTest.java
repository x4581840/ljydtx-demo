package com.demo.java8.optional;

import com.demo.java8.optional.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class OptionalTest {
    Logger logger = LoggerFactory.getLogger(OptionalTest.class);

    @Test(expectedExceptions = NoSuchElementException.class)
    public static void whenCreateEmptyOptional_thenNull() {
        Optional<User> emptyOpt = Optional.empty();
        //会报错Exception in thread "main" java.util.NoSuchElementException: No value present
        emptyOpt.get();
    }

    @Test(/*expectedExceptions = NullPointerException.class*/)
    public void whenCreateOfEmptyOptional_thenNullPointerException() {
        User user = null;

        //把null作为参数传递进去，会直接报空指针java.lang.NullPointerException
        //Optional<User> opt = Optional.of(user);


        //如果对象即可能是 null 也可能是非 null，你就应该使用 ofNullable()方法
        //这样不会报错
        Optional<User> opt = Optional.ofNullable(user);
        //如果用了get方法，还是会报错
        //java.util.NoSuchElementException: No value present
        opt.get();
    }


    //从 Optional 实例中取回实际值对象的方法之一是使用 get() 方法
    @Test
    public void whenCreateOfNullableOptional_thenOk() {
        String name = "john@gmail.com";
        Optional<String> opt = Optional.ofNullable(name);
        //避免异常，你可以选择首先验证是否有值
        assertTrue(opt.isPresent());

        //ifPresent() 方法。该方法除了执行检查，还接受一个Consumer(消费者) 参数，
        // 如果对象不是空的，就对执行传入的 Lambda 表达式：
        opt.ifPresent( u -> assertEquals(opt.get(), "john@gmail.com"));

        assertEquals("john@gmail.com", opt.get());
        System.out.println(opt.get());
    }

    /**
     * Optional 类提供了 API 用以返回对象值，或者在对象为空的时候返回默认值。
     这里你可以使用的第一个方法是 orElse()，
     它的工作方式非常直接，如果有值则返回该值，否则返回传递给它的参数值
     */
    @Test
    public void whenEmptyValue_thenReturnDefaultOrIgnore() {
        /*User user = null;
        User user2 = new User("zhangSan", "12345");
        User result = Optional.ofNullable(user).orElse(user2);

        //这里 user 对象是空的，所以返回了作为默认值的 user2。
        assertEquals(user2.getUserName(), result.getUserName());*/

        //如果对象的初始值不是 null，那么默认值会被忽略：
        User user = new User("zhangSan","1234");
        User user2 = new User("lisi", "1234");
        User result = Optional.ofNullable(user).orElse(user2);

        assertEquals("zhangSan", result.getUserName());

        //对象的初始值不是null，返回user，但是createNewUser方法还是会执行
        User result2 = Optional.ofNullable(user).orElse(createNewUser());

    }

    /**
     * orElse() 和 orElseGet() 的不同之处
     */
    @Test
    public void givenEmptyValue_whenCompare_thenOk() {
        User user = null;
        logger.debug("Using orElse");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        logger.debug("Using orElseGet");
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());

        //输出为
        /*
            Using orElse
            Creating New User
            Using orElseGet
            Creating New User
         */
        //由此可见，当对象为空而返回默认对象时，行为并无差异。
    }

    @Test
    public void givenPresentValue_whenCompare_thenOk() {
        User user = new User("zhangsan", "1234");
        logger.info("Using orElse");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        logger.info("result: name is " + result.getUserName()); //得到的还是zhansan而不是test
        logger.info("Using orElseGet");
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
        //输出
        /*
            Using orElse
            Creating New User
            Using orElseGet
         */
        //前面的user不为空时，orElseGet() 方法不创建 User 对象
    }

    private User createNewUser() {
        logger.debug("Creating New User");
        return new User("test", "1234");
    }

    /**
     * 返回异常
     * Optional 还定义了 orElseThrow() API —— 它会在对象为空的时候抛出异常，而不是返回备选的值
     */
    @Test(/*expectedExceptions = IllegalArgumentException.class*/)
    public void whenThrowException_thenOk() {
        User user = null;
        User result = Optional.ofNullable(user)
                .orElseThrow( () -> new IllegalArgumentException());
        //这里，如果 user 值为 null，会抛出 IllegalArgumentException。
        //这个方法让我们有更丰富的语义，可以决定抛出什么样的异常，而不总是抛出 NullPointerException
    }

    /**
     * 转换值
     * 有很多种方法可以转换 Optional  的值。我们从 collection() 和 flatMap() 方法开始
     */
    @Test
    public void whenMap_thenOk() {
        User user = new User("longjianyong", "1234");
        String userName = Optional.ofNullable(user)
                .map(u -> u.getUserName()).orElse("aidisheng");
        //collection() 对值应用(调用)作为参数的函数，然后将返回的值包装在 Optional 中
        assertEquals(userName, user.getUserName());
    }

    ////相比这下，flatMap() 也需要函数作为参数，并对值调用这个函数，然后直接返回结果。
    //既然 getter 方法返回 String 值的 Optional，你可以在对 User 的 Optional
    // 对象调用 flatMap() 时，用它作为参数。其返回的值是解除包装的 String 值
    @Test
    public void whenFlatMap_thenOk() {
        User user = new User("lisi", "1234");
        user.setPosition("Developer");
        String position = Optional.ofNullable(user)
                .flatMap(u -> u.getPosition()).orElse("default");
        logger.info("position:" + position);
        assertEquals(position, user.getPosition().get());
    }

    /**
     * 过滤值
     * filter() 接受一个 Predicate 参数，返回测试结果为 true 的值。
     * 如果测试结果为 false，会返回一个空的 Optional。
     */
    //来看一个根据基本的电子邮箱验证来决定接受或拒绝 User(用户) 的示例
    //如果通过过滤器测试，result 对象会包含非空值。
    @Test
    public void whenFilter_thenOk() {
        User user = new User("anna@gmail.com", "1234");
        Optional<User> result = Optional.ofNullable(user)
                .filter(u -> u.getUserName() != null && u.getUserName().contains("@"));

        assertTrue(result.isPresent());
    }

    /**
     * Optional 类的链式方法
     * 为了更充分的使用 Optional，你可以链接组合其大部分方法，因为它们都返回相同类似的对象
     */
    @Test
    public void whenChaining_thenOk() {
        User user = new User("anna@gmail.com", "1234");

        //现在可以删除 null 检查，替换为 Optional 的方法
        /*String result = Optional.ofNullable(user)
                .flatMap(u -> u.getAddress())
                .flatMap(a -> a.getCountry())
                .collection(c -> c.getIsocode())
                .orElse("default");*/

        //上面的代码可以通过方法引用进一步缩减
        String result = Optional.ofNullable(user)
                .flatMap(User::getAddress)
                .flatMap(Address::getCountry)
                .map(Country::getIsocode)
                .orElse("default");

        assertEquals(result, "default");
    }

    /**
     * java9增强
     * Java 9 为 Optional 类添加了三个方法：or()、ifPresentOrElse() 和 stream()
     */

    /*@Test
    public void whenEmptyOptional_thenGetValueFromOr() {
        User user = null;
        User result = Optional.ofNullable(user)
                .or( () -> Optional.of(new User("default","1234"))).get();

        assertEquals(result.getUserName(), "default");
        //如果 user 变量是 null，它会返回一个 Optional，
        // 它所包含的 User 对象，其电子邮件为 “default”
    }*/


    /**
     * fPresentOrElse() 方法需要两个参数：一个 Consumer 和一个 Runnable。
     * 如果对象包含值，会执行 Consumer 的动作，否则运行 Runnable。
     * 如果你想在有值的时候执行某个动作，或者只是跟踪是否定义了某个值，那么这个方法非常有用：
     */
   /* Optional.ofNullable(user).ifPresentOrElse( u -> logger.info("User is:" + u.getEmail()),
            () -> logger.info("User not found"));*/

    /**
     * 最后介绍的是新的 stream() 方法，它通过把实例转换为 Stream 对象，
     * 让你从广大的 Stream API 中受益。
     * 如果没有值，它会得到空的 Stream；有值的情况下，Stream 则会包含单一值。
     */
    /*@Test
    public void whenGetStream_thenOk() {
        User user = new User("john@gmail.com", "1234");
        List<String> emails = Optional.ofNullable(user)
                .stream()
                .filter(u -> u.getEmail() != null && u.getEmail().contains("@"))
                .collection( u -> u.getEmail())
                .collect(Collectors.toList());

        assertTrue(emails.size() == 1);
        assertEquals(emails.get(0), user.getEmail());
    }*/

    /**
     * Optional  应该怎样用？
     * 要的一点是 Optional 不是 Serializable。因此，它不应该用作类的字段。
     */

    //Optional 类有一个非常有用的用例，就是将其与流或其它返回 Optional 的方法结合，
    // 以构建流畅的API。
    //我们来看一个示例，使用 Stream 返回 Optional 对象的 findFirst() 方法：
    @Test
    public void whenEmptyStream_thenReturnDefaultOptional() {
        List<User> users = new ArrayList<>();
        User user = users.stream().findFirst().orElse(new User("default", "1234"));

        assertEquals(user.getUserName(), "default");
    }

    //另一个示例
    public static void main(String[] args) {
        //这里的open返回的是Optional.empty，后面才会执行onMessage方法
        onOpen(true).orElseGet(() -> onMessage());
    }

    public static Optional<String> onOpen(boolean flag) {
        System.out.println("flag:"+flag);
        if(flag) {
            return Optional.empty();
        }else {
            return Optional.of("open error");
        }
    }

    public static String  onMessage() {
        System.out.println("onMessage");
        return "onMessage";
    }
}
