package com.demo.kryo;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.esotericsoftware.kryo.serializers.MapSerializer;
import org.roaringbitmap.RoaringBitmap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KryoUtils_1 {

    //序列化对象，对象里面可以包含List和Map等集合
    public static <T extends Serializable> ByteArrayOutputStream serializationObject(T obj) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(obj.getClass(), new JavaSerializer());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeClassAndObject(output, obj);
        output.flush();
        output.close();
        return baos;
    }

    //反序列化对象，对象里面可以包含List和Map等集合
    public static <T extends Serializable> T deserializationObject(InputStream inputStream, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(clazz, new JavaSerializer());
//        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Input input = new Input(inputStream);
        return (T) kryo.readClassAndObject(input);
    }

    public static <T extends Serializable> byte[] serializationList(List<T> obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
        kryo.register(clazz, new JavaSerializer());
        kryo.register(ArrayList.class, serializer);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();
        return baos.toByteArray();
    }

    public static void main(String[] args) {
        Map<String, Map<String, RoaringBitmap>> map = new HashMap<>();
        Map<String, RoaringBitmap> map1 = new HashMap<>();
        RoaringBitmap roaringBitmap = new RoaringBitmap();
        roaringBitmap.add(100);
        map1.put("t", roaringBitmap);
        map.put("test", map1);
        KryoUtils_1.serializationMap(map, RoaringBitmap.class);
    }

    public static <T extends Serializable> byte[] serializationMap(Map<String, Map<String, T>> obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
        MapSerializer serializer = new MapSerializer();
        serializer.setKeyClass(String.class, new JavaSerializer());
        serializer.setKeysCanBeNull(false);
        serializer.setValueClass(clazz, new JavaSerializer());
        serializer.setValuesCanBeNull(true);
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashMap.class, serializer);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();
        return baos.toByteArray();
    }

    private <T extends Serializable> Map<String, T> deserializationMap(InputStream inputstrem, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
        MapSerializer serializer = new MapSerializer();
        serializer.setKeyClass(String.class, new JavaSerializer());
        serializer.setKeysCanBeNull(false);
        serializer.setValueClass(clazz, new JavaSerializer());
        serializer.setValuesCanBeNull(true);
        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashMap.class, serializer);
        Input input = new Input(inputstrem);
        return (Map<String, T>) kryo.readObject(input, HashMap.class, serializer);
    }

    public static <T extends Serializable> List<T> deserializationList(byte[] bytes, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
        kryo.register(clazz, new JavaSerializer());
        kryo.register(ArrayList.class, serializer);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Input input = new Input(bais);
        return (List<T>) kryo.readObject(input, ArrayList.class, serializer);
    }

    public static <T extends Serializable> List<T> deserializationList(InputStream inputstream, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);
        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);
        kryo.register(clazz, new JavaSerializer());
        kryo.register(ArrayList.class, serializer);
        Input input = new Input(inputstream);
        return (List<T>) kryo.readObject(input, ArrayList.class, serializer);
    }
}
