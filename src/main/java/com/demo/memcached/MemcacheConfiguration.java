//package com.demo.memcached;
//
//import com.danga.MemCached.MemCachedClient;
//import com.danga.MemCached.SockIOPool;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author zh
// * @ClassName cn.saytime.config.config.MemcacheConfiguration
// * @Description Memcache配置
// */
//@Configuration
//public class MemcacheConfiguration {
//
//    @Value("${memcache.servers}")
//    private String[] servers;
//    @Value("${memcache.failover}")
//    private boolean failover;
//    @Value("${memcache.initConn}")
//    private int initConn;
//    @Value("${memcache.minConn}")
//    private int minConn;
//    @Value("${memcache.maxConn}")
//    private int maxConn;
//    @Value("${memcache.maintSleep}")
//    private int maintSleep;
//    @Value("${memcache.nagel}")
//    private boolean nagel;
//    @Value("${memcache.socketTO}")
//    private int socketTO;
//    @Value("${memcache.aliveCheck}")
//    private boolean aliveCheck;
//
//    @Bean
//    public SockIOPool sockIOPool() {
//        SockIOPool pool = SockIOPool.getInstance();
//        pool.setServers(servers);
//        pool.setFailover(failover);
//        pool.setInitConn(initConn);
//        pool.setMinConn(minConn);
//        pool.setMaxConn(maxConn);
//        pool.setMaintSleep(maintSleep);
//        pool.setNagle(nagel);
//        pool.setSocketTO(socketTO);
//        pool.setAliveCheck(aliveCheck);
//        pool.initialize();
//        return pool;
//    }
//
//    @Bean
//    public MemCachedClient memCachedClient() {
//        return new MemCachedClient();
//    }
//
//}
