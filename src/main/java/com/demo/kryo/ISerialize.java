package com.demo.kryo;

import java.io.InputStream;
import java.io.OutputStream;

public interface ISerialize {

    <T> void serialize(T obj, OutputStream outputStream);

    <T> T deserialize(Class<T> clazz, InputStream inputStream);
}
