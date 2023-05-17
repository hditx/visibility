package com.stratosphere.visibility.domain;


public class Product {
    private int id;
    private int sequence;

    public Product(int id, int sequence) {
        this.id = id;
        this.sequence = sequence;
    }

    public int getId() {
        return id;
    }

    public int getSequence() {
        return sequence;
    }

}
