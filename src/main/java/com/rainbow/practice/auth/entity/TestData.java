package com.rainbow.practice.auth.entity;

import lombok.Data;

import java.io.*;

@Data
public class TestData implements Externalizable {

    private Integer id;

    private String name;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeChars(this.toString());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        in.readChar();
    }
}
