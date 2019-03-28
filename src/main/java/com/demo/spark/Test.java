//package com.demo.spark;
//
//import org.apache.commons.lang3.tuple.Pair;
//import org.apache.spark.Partitioner;
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaOdpsOps;
//import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.api.java.function.Function;
//import org.apache.spark.api.java.function.PairFlatMapFunction;
//import org.apache.spark.api.java.function.PairFunction;
//import org.apache.spark.api.java.function.VoidFunction;
//import org.apache.spark.broadcast.Broadcast;
//import org.apache.spark.storage.StorageLevel;
//import org.apache.spark.streaming.Durations;
//import org.apache.spark.streaming.api.java.JavaDStream;
//import org.apache.spark.streaming.api.java.JavaPairDStream;
//import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
//import org.apache.spark.streaming.api.java.JavaStreamingContext;
//import scala.Tuple2;
//
//import java.beans.Transient;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//public class Test {
//    final static int numPartitions = 1;
//    final static int copyNum = 3;
//    final static long refreshTaskReceiveInterval = 10000;
//    final static long aggregateReceiveInterval = 5000;
//
//    public static void main(String[] args) throws UnknownHostException, InterruptedException {
//
//        final SparkConf sparkConf = new SparkConf().setAppName("Test");
//        final JavaStreamingContext ssc = new JavaStreamingContext(sparkConf, Durations.seconds(2));
//        final JavaOdpsOps javaOdpsOps = new JavaOdpsOps(ssc.sparkContext());
//        // 上下文读出要初始化的主题
//        // String[] subjects = { "subject1", "subject2", "subject3" };
//        // Broadcast<String[]> brocastSubjects =
//        // ssc.sparkContext().broadcast(subjects);
//        // 监听明细数据刷新任务
//        JavaDStream<RefreshTask> refreshTaskStream = ssc
//                .receiverStream(new RefreshTaskReceiver(refreshTaskReceiveInterval, StorageLevel.MEMORY_ONLY()));
//        // 监听查询任务
//        JavaDStream<AggregateTask> aggregateTaskStream = ssc
//                .receiverStream(new AggregateTaskReceiver(aggregateReceiveInterval, StorageLevel.MEMORY_ONLY()));
//
//        JavaPairDStream<String, RefreshTask> refreshResultStream = refreshTaskStream.
//                mapToPair(new PairFunction<RefreshTask, String, RefreshTask>() {
//                    @Override
//                    public Tuple2<String, RefreshTask> call(RefreshTask task) throws Exception {
//                        setActualDataMap(ssc.sparkContext(), task);
//                        Tuple2<String, RefreshTask> tp = new Tuple2<String, RefreshTask>(task.getSubject(), task);
//                        System.out.println("*ip:" + getIp() + " " + task.getSubject() + " cache ");
//                        return tp;
//                    }
//                });
//
//
//        refreshResultStream.print();
//
//        ssc.start();
//
//        ssc.awaitTermination();
//    }
//
//    private static void setActualDataMap(JavaSparkContext jsc, RefreshTask task) {
//        Map<String, ActualData> actualdatas = getActualDatas(task.getSubject(), 20);
//        List<Tuple2<String, ActualData>> actualDataList = new ArrayList<Tuple2<String, ActualData>>();
//        for (Entry<String, ActualData> entry : actualdatas.entrySet()) {
//            actualDataList.add(new Tuple2<String, ActualData>(entry.getKey(), entry.getValue()));
//        }
//        for (Entry<Integer, JavaRDD<?>> entry : jsc.getPersistentRDDs().entrySet()) {
//            System.out.println(entry.getKey() + " v:" + entry.getValue().name());
//            if (entry.getKey().equals(task.getSubject())) {
//                entry.getValue().unpersist(true);
//            }
//        }
//        jsc.parallelizePairs(actualDataList, numPartitions).setName(task.getSubject())
//                .persist(StorageLevel.MEMORY_ONLY());
//    }
//
//    private static Map<String, ActualData> getActualDatas(String subject, int size) {
//        Map<String, ActualData> datas = new HashMap<String, ActualData>();
//        for (int i = 0; i < size; i++) {
//            ActualData data = new ActualData();
//            data.setDimensionIndexs(new int[10]);
//            data.setValue(String.valueOf(i));
//            datas.put(String.valueOf(i), data);
//        }
//        return datas;
//    }
//
//    private static void print(List s) {
//        System.out.println("@e: start");
//        for (Object o : s) {
//            System.out.println("e:" + o.toString());
//        }
//        System.out.println("@e: end");
//    }
//
//    private static String getIp() {
//        InetAddress addr = null;
//        try {
//            addr = InetAddress.getLocalHost();
//        } catch (UnknownHostException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        String ip = addr.getHostAddress().toString();
//        return ip;
//    }
//
//}
//
//
