package com.demo.kryo;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;



public class SubCellSerializer extends Serializer<SubCell> {

    @Override
    public void write(Kryo kryo, Output output, SubCell t) {
        String style = t.getStyle();
        String format = t.getFormat();
        int age = t.getAge();
        output.writeString(style);
        output.writeString(format);
        output.writeInt(age);
    }

    @Override
    public SubCell read(Kryo kryo, Input input, Class<SubCell> type) {
        //请注意与写顺序一致
        String style = input.readString();
        String format = input.readString();
        int age = input.readInt();
        SubCell subCell = new SubCell();
        subCell.setStyle(style);
        subCell.setFormat(format);
        subCell.setAge(age);
        return subCell;
    }

}
