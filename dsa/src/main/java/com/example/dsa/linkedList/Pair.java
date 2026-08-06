package com.example.dsa.linkedList;

public class Pair{
    private int Key;
    private int value;

    @Override
    public String toString() {
        return "Pair{" +
                "Key=" + Key +
                ", value=" + value +
                '}';
    }

    public Pair() {
    }

    public Pair(int key, int value) {
        Key = key;
        this.value = value;
    }

    public int getKey() {
        return Key;
    }

    public void setKey(int key) {
        Key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
