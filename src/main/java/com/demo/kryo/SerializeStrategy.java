package com.demo.kryo;


import java.io.InputStream;
import java.io.OutputStream;


public interface SerializeStrategy {

    void serializeCell(CellsWrap cellsWrap, OutputStream os);

    CellsWrap deserializeCell(InputStream inputStream);

}
