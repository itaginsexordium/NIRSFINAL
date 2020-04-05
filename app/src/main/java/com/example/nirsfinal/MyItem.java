package com.example.nirsfinal;

import java.io.Serializable;

class MyItem implements Serializable {
    public String name;
    public int price;

    public MyItem() {
    }

    MyItem(String name, int price) {
        this.name = name;
        this.price = price;
    }
}