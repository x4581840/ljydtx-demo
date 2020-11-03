package com.demo.redis.cache;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/9/19 9:45 AM
 * @Version 1.0
 **/


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ValueOperations;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class HzsRedisCallBack implements SessionCallback {

    private String userVoteCountRedisKey;
    private String videoRealCount;
    private String videoDisplayCount;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Object execute(RedisOperations operations) throws DataAccessException {
        operations.multi();
        Map<String, Long> res = this.setOperations(operations);
        operations.exec();
        return res;
    }

    private Map<String, Long> setOperations(RedisOperations operations) {
        log.info("userVoteCountRedisKey:" + userVoteCountRedisKey);
        log.info("videoRealCount:" + videoRealCount);
        log.info("videoDisplayCount:" + videoDisplayCount);
        Map<String, Long> res = new HashMap<>();
        ValueOperations valueOperations = operations.opsForValue();
//        Long userVoteCount = redisTemplate.opsForValue().increment(userVoteCountRedisKey, 1);
        log.info("userVoteCount:" + redisTemplate.opsForValue().increment(userVoteCountRedisKey, 1));
//        if (userVoteCount > 5) {
//            throw new RuntimeException("今天次数已用完，明天再来哦～");
//        }

        Long voteNumberReal = valueOperations.increment(videoRealCount, 1);
        log.info("视频ID：{} 真实投票数加1,总数为：", videoRealCount, voteNumberReal);

        //int i= 1/0;

        Long voteNumberDisplay = valueOperations.increment(videoDisplayCount, 1);
        log.info("视频ID：{} 显示投票数加1,总数为：", videoDisplayCount, voteNumberDisplay);

        res.put(userVoteCountRedisKey, 0L);
        res.put(videoRealCount, voteNumberReal);
        res.put(videoDisplayCount, voteNumberDisplay);

        return res;
    }

    public String getUserVoteCountRedisKey() {
        return userVoteCountRedisKey;
    }

    public void setUserVoteCountRedisKey(String userVoteCountRedisKey) {
        this.userVoteCountRedisKey = userVoteCountRedisKey;
    }

    public String getVideoRealCount() {
        return videoRealCount;
    }

    public void setVideoRealCount(String videoRealCount) {
        this.videoRealCount = videoRealCount;
    }

    public String getVideoDisplayCount() {
        return videoDisplayCount;
    }

    public void setVideoDisplayCount(String videoDisplayCount) {
        this.videoDisplayCount = videoDisplayCount;
    }
}
