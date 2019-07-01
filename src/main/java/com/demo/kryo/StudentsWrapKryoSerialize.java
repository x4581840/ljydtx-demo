package com.demo.kryo;

import com.esotericsoftware.kryo.Kryo;

import java.util.ArrayList;

public class StudentsWrapKryoSerialize extends AbstractKryoSerialize {

    final static KryoPool kryoPool;

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        kryoPool = new KryoPool((int) (availableProcessors * 1.6), () -> {
            Kryo kryo = new Kryo();
            kryo.register(Student.class, new StudentSerializer());
            kryo.register(StudentsWrap.class);
            kryo.register(ArrayList.class);
            return kryo;
        });
    }

    @Override
    protected KryoPool getKryoPool() {
        return kryoPool;
    }
}
