package com.demo.redis.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/9/17 7:29 PM
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/cache")
@Slf4j
public class TestCache {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private HzsRedisCallBack redisCallBack;

    @GetMapping("/testExe")
    public void testExe() {
//        redisCallBack.setUserVoteCountRedisKey("aaa");
//        redisCallBack.setVideoRealCount("bbb");
//        redisCallBack.setVideoDisplayCount("ccc");
//
//        redisTemplate.execute(redisCallBack);
        log.info("test:" + redisTemplate.opsForValue().increment("test", 1));
        int i = 1 / 0;
        log.info("test1:" + redisTemplate.opsForValue().increment("test1", 1));

    }

    @GetMapping("/testExeRes")
    public void testExeRes() {
        log.info("aaa:" + redisTemplate.opsForValue().get("aaa"));
        log.info("bbb:" + redisTemplate.opsForValue().get("bbb"));
        log.info("ccc:" + redisTemplate.opsForValue().get("ccc"));
        log.info("test1:" + redisTemplate.opsForValue().get("test1"));
        log.info("test:" + redisTemplate.opsForValue().get("test"));

    }

    @GetMapping("/cache1")
    public void testCache() {
        Long res = redisTemplate.opsForValue().increment("testCacheKey", 5);
        redisTemplate.expire("testCacheKey", 600, TimeUnit.SECONDS);
        log.info("result====" + res);

        redisTemplate.opsForValue().increment("不设置过期时间", 1000);
    }

    @GetMapping("/get1")
    public ResponseEntity get1() {
        Integer res = (Integer) redisTemplate.opsForValue().get("testCacheKey");
        log.info("result====" + res);

        Long res1 = redisTemplate.opsForValue().increment("testCacheKey", -1);
        log.info("result====" + res1);

        Integer res2 = (Integer) redisTemplate.opsForValue().getAndSet("testCacheKey", 100);
        log.info("result====" + res2);

        log.info("result====" + redisTemplate.opsForValue().get("testCacheKey"));

        log.info("result====" + redisTemplate.opsForValue().get("不设置过期时间"));

        return new ResponseEntity(res2, HttpStatus.OK);

    }

    public static void main(String[] args) throws Exception {
        /*try{
            try {
                int i = 1/0;
            } catch (Exception e) {
                throw new Exception("test");
            }
        } catch (Exception e ) {
            throw new Exception("草");
        }*/
        Long i = 1L;
        System.out.println(i.equals(1L));
        System.out.println(i > 5);
        Integer j = 1;
        System.out.println(j > 0);
    }
}
