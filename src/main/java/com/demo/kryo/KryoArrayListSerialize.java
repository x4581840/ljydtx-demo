package com.demo.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.*;
import java.util.ArrayList;

public class KryoArrayListSerialize implements ISerialize {

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
    public <T> void serialize(T obj, OutputStream outputStream) {
        Kryo kryo = null;
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
             Output output = new Output(bufferedOutputStream);) {
            kryo = kryoPool.obtain();
            kryo.writeObject(output, obj);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (kryo != null) {
                kryoPool.free(kryo);
            }
        }
    }

    @Override
    public <T> T deserialize(Class<T> clazz, InputStream inputStream) {
        Kryo kryo = null;
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             Input input = new Input(bufferedInputStream);) {
            kryo = kryoPool.obtain();

            return (T) kryo.readObject(input, clazz);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (kryo != null) {
                kryoPool.free(kryo);
            }
        }
    }
}
