package com.demo.kryo;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.util.List;


public class CellSerializer extends Serializer<Cell> {

    @Override
    public void write(Kryo kryo, Output output, Cell t) {
        String name = t.getName();
        int time = t.getTime();
        List<SubCell> subCells = t.getSubCells();

        output.writeString(name);
        output.writeInt(time);
        kryo.writeClassAndObject(output, subCells);
    }

    @Override
    public Cell read(Kryo kryo, Input input, Class<Cell> type) {
        //请注意与写顺序一致
        String name = input.readString();
        int time = input.readInt();
        List<SubCell> subCells = (List<SubCell>) kryo.readClassAndObject(input);
        Cell cell = new Cell();
        cell.setName(name);
        cell.setTime(time);
        cell.setSubCells(subCells);
        return cell;
    }

}
