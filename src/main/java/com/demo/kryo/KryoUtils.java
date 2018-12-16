//package com.demo.kryo;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import org.apache.commons.codec.binary.Base64;
//
//import com.esotericsoftware.kryo.Kryo;
//import com.esotericsoftware.kryo.io.Input;
//import com.esotericsoftware.kryo.io.Output;
//import com.esotericsoftware.kryo.serializers.BeanSerializer;
//import com.esotericsoftware.kryo.serializers.CollectionSerializer;
//import com.esotericsoftware.kryo.serializers.JavaSerializer;
//import com.esotericsoftware.kryo.serializers.MapSerializer;
//
//public class KryoUtils {
//	/**
//     * 序列化arrayList的方法
//     * @param object
//     * @param clazz
//     * @return
//     * @throws FileNotFoundException
//     */
//    public static <T> byte[] setSerializableList(Object object, Class<T> clazz ) throws FileNotFoundException {
//        Kryo kryo = new Kryo();
//
//        CollectionSerializer serializer = new CollectionSerializer();
//        serializer.setElementClass(clazz, new BeanSerializer<T>(kryo, clazz));
//        serializer.setElementsCanBeNull(false);
//
//        kryo.register(clazz, new BeanSerializer<T>(kryo, clazz));
//        kryo.register(ArrayList.class, serializer);
//
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        Output output = new Output(baos);
//        kryo.writeObject(output, object);
//        output.flush();
//        output.close();
//        byte[] bys = baos.toByteArray();
//        try {
//            baos.flush();
//            baos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String string = new String(new Base64().encode(bys));
//
//        System.out.println(string);
//
//        return bys;
//
//    }
//
//    /**
//     * 反序列化ArrayList的方法
//     * @param clazz
//     * @param data
//     * @return
//     */
//    public static <T> Object getSerializableList(Class<T> clazz, byte[] bytes) {
//        Kryo kryo = new Kryo();
//        //List
//        CollectionSerializer serializer = new CollectionSerializer();
//        serializer.setElementClass(clazz, new BeanSerializer<T>(kryo, clazz));
//        serializer.setElementsCanBeNull(false);
//
//        kryo.register(clazz, new BeanSerializer<T>(kryo, clazz));
//        kryo.register(ArrayList.class, serializer);
//
//        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
//        Input input;
//        Object simple = null;
//        try {
//            input = new Input(bais);
//            input.close();
//            simple = kryo.readObject(input, ArrayList.class, serializer);//list
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return simple;
//    }
//
//    /**
//     * 序列化HashMap<String, clazz>的方法  key为 String类型   value类型为 clazz
//     * @param object
//     * @param clazz
//     * @return
//     * @throws FileNotFoundException
//     */
//    public  static <T> String setSerializableMap(Object object,Class<T> clazz ) throws FileNotFoundException {
//        Kryo kryo = new Kryo();
//        //设置序列化map的key和value类型
//        MapSerializer serializer = new MapSerializer();
//        serializer.setKeyClass(String.class, new JavaSerializer());
//        serializer.setKeysCanBeNull(false);
//        serializer.setValueClass(clazz, new BeanSerializer<T>(kryo, clazz));
//        serializer.setValuesCanBeNull(true);
//        //注册要序列化的对象
//        kryo.register(HashMap.class, serializer);
//        kryo.register(clazz, new BeanSerializer<T>(kryo, clazz));
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        Output output = new Output(baos);
//        kryo.writeObject(output, object);
//        output.flush();
//        output.close();
//        byte[] bys = baos.toByteArray();
//        try {
//            baos.flush();
//            baos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String string = new String(new Base64().encode(bys));
//        System.out.println(string);
//
//        return string;
//
//    }
//
//    /**
//     * HashMap<String,clazz>类型的map反序列化。
//     * @param clazz
//     * @param data
//     * @return
//     */
//    public static <T> Object getSerializableMap(Class<T> clazz, String data) {
//        Kryo kryo = new Kryo();
//
//
//        //Map
//        MapSerializer serializer = new MapSerializer();
//        serializer.setKeyClass(String.class, new JavaSerializer());
//        serializer.setKeysCanBeNull(false);
//        serializer.setValueClass(clazz, new BeanSerializer<T>(kryo, clazz));
//        serializer.setValuesCanBeNull(true);
//
//        kryo.register(HashMap.class, serializer);
//        kryo.register(clazz, new BeanSerializer<T>(kryo, clazz)); //bean
//
//        ByteArrayInputStream bais = new ByteArrayInputStream(new Base64().decode(data.getBytes()));
//        Input input;
//        Object simple = null;
//        try {
//            input = new Input(bais);
//            input.close();
//            simple = kryo.readObject(input, HashMap.class, serializer);  //bean
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return simple;
//    }
//}
