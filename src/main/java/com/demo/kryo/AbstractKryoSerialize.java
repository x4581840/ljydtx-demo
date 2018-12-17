package com.demo.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.*;

public abstract class AbstractKryoSerialize implements ISerialize {

    protected abstract KryoPool getKryoPool();

    @Override
    public <T> void serialize(T obj, OutputStream outputStream) {
        Kryo kryo = null;
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
             Output output = new Output(bufferedOutputStream);) {
            kryo = getKryoPool().obtain();
            kryo.writeObject(output, obj);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (kryo != null) {
                getKryoPool().free(kryo);
            }
        }
    }

    @Override
    public <T> T deserialize(Class<T> clazz, InputStream inputStream) {
        Kryo kryo = null;
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             Input input = new Input(bufferedInputStream);) {
            kryo = getKryoPool().obtain();

            return (T)kryo.readObject(input, clazz);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (kryo != null) {
                getKryoPool().free(kryo);
            }
        }
    }
}
