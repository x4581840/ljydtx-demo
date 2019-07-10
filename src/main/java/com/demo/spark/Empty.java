//package com.demo.spark;
//
//
//import org.apache.commons.lang3.tuple.Pair;
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaOdpsOps;
//import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.function.Function;
//import org.apache.spark.api.java.function.PairFlatMapFunction;
//import org.apache.spark.api.java.function.PairFunction;
//import org.apache.spark.api.java.function.VoidFunction;
//import org.apache.spark.storage.StorageLevel;
//import org.apache.spark.streaming.Durations;
//import org.apache.spark.streaming.api.java.JavaDStream;
//import org.apache.spark.streaming.api.java.JavaPairDStream;
//import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
//import org.apache.spark.streaming.api.java.JavaStreamingContext;
//import scala.Tuple2;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//public class Empty {
//    final static int numPartitions = 6;
//    final static long refreshTaskReceiveInterval = 2000;
//    final static long aggregateReceiveInterval = 5000;
//
//    public static void main(String[] args) throws UnknownHostException, InterruptedException {
//        SparkConf sparkConf = new SparkConf().setAppName("Empty");
//        final JavaStreamingContext ssc = new JavaStreamingContext(sparkConf, Durations.seconds(10));
//
//        final JavaOdpsOps javaOdpsOps = new JavaOdpsOps(ssc.sparkContext());
//        //根据机器资源，固化切片信息
//        List<SliceInfo> sliceInfos = initSliceInfos();
//        JavaRDD<SliceInfo> sliceInfoRdd = ssc.sparkContext().parallelize(sliceInfos, sliceInfos.size());
//        //数据库读出要初始化的主题
//        String[] subjects = {"subject1", "subject2", "subject3"};
//        final Map<String, JavaRDD<ActualData>> actualDataMap = initActualDataMap(ssc, Arrays.asList(subjects));
//        //监听明细数据刷新任务
//        final JavaDStream<RefreshTask> subjectStream = ssc
//                .receiverStream(new RefreshTaskReceiver(refreshTaskReceiveInterval, StorageLevel.MEMORY_ONLY()));
//        //监听查询任务
//        final JavaDStream<AggregateTask> taskStream = ssc
//                .receiverStream(new AggregateTaskReceiver(aggregateReceiveInterval, StorageLevel.MEMORY_ONLY()));
//        JavaPairDStream<String, Boolean> refreshResultStream = subjectStream.mapToPair(new PairFunction<RefreshTask, String, Boolean>() {
//
//            @Override
//            public Tuple2<String, Boolean> call(RefreshTask task) throws Exception {
//                //actualDataMap.put(task.getSubject(), getActualDataMap(ssc, task.getSubject()));
//                //Tuple2 tp = new Tuple2<RefreshTask, Boolean>(task, true);
//                //return tp;
//                Tuple2<String, Boolean> tp = new Tuple2<String, Boolean>(task.getSubject(), true);
//                System.out.println("*ip:" + getIp() + " " + task.getSubject() + " cache ");
//                return tp;
//            }
//        });
//        refreshResultStream.print();
//
//        JavaPairDStream<AggregateTask, Boolean> aggregateResultStream = taskStream.mapToPair(new PairFunction<AggregateTask, AggregateTask, Boolean>() {
//
//            @Override
//            public Tuple2<AggregateTask, Boolean> call(AggregateTask task) throws Exception {
//
//                System.out.println("*ip:" + getIp() + " " + task.getTaskId() + " map ");
//                JavaRDD<ActualData> actualDataRdd = actualDataMap.get(task.getSubject());
//
//                //System.out.println("*ip:" + getIp() + " count:" + actualDataRdd.count());
//                Tuple2<AggregateTask, Boolean> tp = new Tuple2<AggregateTask, Boolean>(task, true);
//                return tp;
//            }
//
//        });
//        taskStream.print();
//        aggregateResultStream.foreachRDD(new VoidFunction<JavaPairRDD<AggregateTask, Boolean>>() {
//
//            @Override
//            public void call(JavaPairRDD<AggregateTask, Boolean> rdd) throws Exception {
//                System.out.println("* ddddd ");
//                print(rdd.collect());
//
//            }
//
//        });
//        /*
//		final JavaPairDStream<AggregateTask, SliceInfo> sliceStream = taskStream
//				.flatMapToPair(new PairFlatMapFunction<AggregateTask, AggregateTask, SliceInfo>() {
//
//					@Override
//					public Iterator<Tuple2<AggregateTask, SliceInfo>> call(AggregateTask task) throws Exception {
//						System.out.println("*ip:" + getIp() + " taskSliceStream task id:" + task.getTaskId());
//						String subject = task.getSubject();
//
//						ArrayList<Tuple2<AggregateTask, SliceInfo>> sliceInfos = new ArrayList<Tuple2<AggregateTask, SliceInfo>>();
//						for (SliceInfo sliceInfo : task.getSliceInfos()) {
//
//							Tuple2 tp = new Tuple2<AggregateTask, SliceInfo>(task, sliceInfo);
//							sliceInfos.add(tp);
//						}
//						return sliceInfos.iterator();
//					}
//
//				}).repartition(numPartitions);
//		;
//		sliceStream.foreachRDD(new VoidFunction<JavaPairRDD<AggregateTask, SliceInfo>>() {
//
//			@Override
//			public void call(JavaPairRDD<AggregateTask, SliceInfo> rdd) throws Exception {
//				rdd.foreachPartition(new VoidFunction<Iterator<Tuple2<AggregateTask, SliceInfo>>>() {
//
//					@Override
//					public void call(Iterator<Tuple2<AggregateTask, SliceInfo>> tuples) throws Exception {
//						while (tuples.hasNext()) {
//							Tuple2<AggregateTask, SliceInfo> tuple = tuples.next();
//							AggregateTask task = tuple._1();
//							SliceInfo sliceInfo = tuple._2();
//							System.out.println("*ip:" + getIp() + " " + task.getTaskId() + " map "
//									+ sliceInfo.getActualDataSlice());
//							JavaRDD<ActualData> actualDatas = actualDataMap.get(task.getSubject());
//							System.out.println("*ip:" + getIp() + " count:" + actualDatas.count());
//						}
//					}
//
//				});
//			}
//
//		});
//		sliceStream.print();
//		*/
//        // 获取切片信息，加载对应单元格及明细数
//		/*
//		 * JavaRDD<Pair<String, String>> sliceInfoRdd = null;
//		 * JavaRDD<ActualData> actualDataRdd = null; JavaRDD<AggregateData>
//		 * aggregateDataRdd = null;
//		 */
//
//        // 获取事实数据
//		/*
//		 * taskStream.foreachRDD(new VoidFunction<JavaRDD<AggregateTask>>() {
//		 *
//		 * @Override public void call(JavaRDD<AggregateTask> rdd) throws
//		 * Exception { System.out.println("ip:" + ip + " total " + task.id());
//		 * // 获取事实数据 JavaRDD<List<ActualData>> actualData = task.map(new
//		 * Function<AggregateTask, List<ActualData>>() {
//		 *
//		 * @Override public List<ActualData> call(AggregateTask task) throws
//		 * Exception { System.out.println("ip:" + ip + " ActualData task id:" +
//		 * task.getTaskId());
//		 *
//		 * return getActualDatas(null); }
//		 *
//		 * }).repartition(numPartitions); // 获取单元格 JavaRDD<Pair<String,
//		 * List<AggregateData>>> aggregateData = task .map(new
//		 * Function<AggregateTask, Pair<String, List<AggregateData>>>() {
//		 *
//		 * @Override public Pair<String, List<AggregateData>> call(AggregateTask
//		 * task) throws Exception { System.out.println("ip:" + ip +
//		 * " AggregateData task id:" + task.getTaskId());
//		 *
//		 * return Pair.of(task.getTaskId(), getAggregateDatas(task)); }
//		 *
//		 * });
//		 *
//		 * // 聚合操作 JavaRDD<Pair<String, List<AggregateData>>> resultData =
//		 * aggregateData .map(new Function<Pair<String, List<AggregateData>>,
//		 * Pair<String, List<AggregateData>>>() {
//		 *
//		 * @Override public Pair<String, List<AggregateData>> call(Pair<String,
//		 * List<AggregateData>> arg0) throws Exception {
//		 * System.out.println("ip:" + ip + " map task id:" + arg0.getLeft());
//		 * return arg0; }
//		 *
//		 * }); System.out.println(resultData.first().getLeft() + "####"); //
//		 * taskStream.print(); }
//		 *
//		 * });
//		 */
//		/*
//		 * mm.foreachRDD(new VoidFunction<JavaRDD<String>>() {
//		 *
//		 * @Override public void call(JavaRDD<String> arg0) throws Exception {
//		 * System.out.println(arg0.rdd().collect());
//		 *
//		 * javaOdpsOps.saveToTable("xxpt_cynosure_ads_dev",
//		 * "cns_interface_data", "pt=20181105,application=CIP_Forecast",
//		 * arg0.rdd(), saveTransfer);
//		 *
//		 * }
//		 *
//		 * });
//		 *
//		 * mm.print();
//		 */
//		/*
//		 * List<String> sourceData = new ArrayList<String>();
//		 * sourceData.add("9999"); sourceData.add("8888");
//		 * sourceData.add("7777"); sourceData.add("6666");
//		 * sourceData.add("5555"); JavaRDD<String>
//		 * rdd1=ssc.sparkContext().parallelize(sourceData); for(String
//		 * dd:rdd1.collect()){ System.out.println("****"+dd); }
//		 */
//        // javaOdpsOps.saveToTable("xxpt_cynosure_ads_dev",
//        // "cns_interface_data", "pt=20181105,application=CIP_Forecast",
//        // rdd1.rdd(), saveTransfer);
//
//        System.out.println("@------");
//        ssc.start();
//
//        // JavaSparkContext ctx = new JavaSparkContext(sparkConf);
//
//        ssc.awaitTermination();
//        // long end = System.currentTimeMillis();
//
//        // System.out.println(end - start + "######");
//
//    }
//
//    private static List<SliceInfo> initSliceInfos() {
//        List<SliceInfo> sliceInfos = new ArrayList<SliceInfo>();
//        sliceInfos.add(new SliceInfo(1, "a1", "b1"));
//        sliceInfos.add(new SliceInfo(2, "a1", "b2"));
//        sliceInfos.add(new SliceInfo(3, "a1", "b3"));
//        sliceInfos.add(new SliceInfo(4, "a2", "b1"));
//        sliceInfos.add(new SliceInfo(5, "a2", "b2"));
//        sliceInfos.add(new SliceInfo(6, "a2", "b3"));
//        return sliceInfos;
//    }
//
//    private static List<AggregateData> getAggregateDatas(AggregateTask task) {
//        List<AggregateData> datas = new ArrayList<AggregateData>();
//        for (int i = 0; i < 5; i++) {
//            AggregateData data = new AggregateData();
//            data.setRow(i);
//            data.setColumn(0);
//            data.setValue(String.valueOf(i));
//            datas.add(data);
//        }
//        return datas;
//    }
//
//    private static List<ActualData> getActualDatas(String subject, int size) {
//        List<ActualData> datas = new ArrayList<ActualData>();
//        for (int i = 0; i < size; i++) {
//            ActualData data = new ActualData();
//            data.setDimensionIndexs(new int[10]);
//            data.setValue(String.valueOf(i));
//            datas.add(data);
//        }
//        return datas;
//    }
//
//    private static Map<String, JavaRDD<ActualData>> initActualDataMap(JavaStreamingContext ssc, List<String> subjects) {
//        Map<String, JavaRDD<ActualData>> actualDataMap = new HashMap<String, JavaRDD<ActualData>>();
//        for (String subject : subjects) {
//            List<ActualData> actualdatas = getActualDatas(subject, 10);
//            actualDataMap.put(subject, ssc.sparkContext().parallelize(actualdatas).repartition(numPartitions));
//            System.out.println("size:" + actualDataMap.size());
//        }
//        for (JavaRDD<ActualData> rdd : actualDataMap.values()) {
//            print(rdd.collect());
//        }
//
//        return actualDataMap;
//    }
//
//    private static JavaRDD<ActualData> getActualDataMap(JavaStreamingContext ssc, String subject) {
//        List<ActualData> actualdatas = getActualDatas(subject, 20);
//        return ssc.sparkContext().parallelize(actualdatas).repartition(numPartitions);
//    }
//
//    private static void print(List s) {
//        for (Object o : s) {
//            System.out.println("@e:" + o.toString());
//        }
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
