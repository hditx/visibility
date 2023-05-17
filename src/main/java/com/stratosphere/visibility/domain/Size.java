package com.stratosphere.visibility.domain;

public class Size {
    private int id;
    private int productId;
    private boolean backSoon;
    private boolean special;

    public Size(int id, int productId, boolean backSoon, boolean special) {
        this.id = id;
        this.productId = productId;
        this.backSoon = backSoon;
        this.special = special;
    }

    public int getId() {
        return this.id;
    }

    public int getProductId() {
        return productId;
    }

    public boolean isBackSoon() {
        return backSoon;
    }

    public boolean isSpecial() {
        return special;
    }
}
