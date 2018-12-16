package com.demo.kryo;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.esotericsoftware.kryo.serializers.JavaSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class KryoCellMapSerializeStrategy implements SerializeStrategy {
    final static KryoPool kryoPool;

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        kryoPool = new KryoPool((int)(availableProcessors * 1.6), () -> {
                            Kryo kryo = new Kryo();
                            kryo.register(Cell.class, new CellSerializer());
                            kryo.register(SubCell.class, new SubCellSerializer());
                            kryo.register(CellsWrap.class);
                            kryo.register(HashMap.class);
//                            kryo.register(Cell.class, new JavaSerializer());

//                            CollectionSerializer serializer = new CollectionSerializer();
//                            serializer.setElementClass(SubCell.class, new SubCellSerializer());
//                            serializer.setElementsCanBeNull(false);
//                            kryo.register(ArrayList.class, serializer);
//                            kryo.register(List.class);
                            kryo.register(ArrayList.class);


                            return kryo;
                        });
    }

    @Override
    public void serializeCell(CellsWrap cellsWrap, OutputStream os) {
        Kryo kryo = null;
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(os);
             Output output = new Output(bufferedOutputStream);) {
            kryo = kryoPool.obtain();
            kryo.writeObject(output, cellsWrap);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (kryo != null) {
                kryoPool.free(kryo);
            }
        }
    }

    @Override
    public CellsWrap deserializeCell(InputStream inputStream) {
        Kryo kryo = null;
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             Input input = new Input(bufferedInputStream);) {
            kryo = kryoPool.obtain();

            return kryo.readObject(input, CellsWrap.class);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (kryo != null) {
                kryoPool.free(kryo);
            }
        }
    }

}
