package com.abyan.Object;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

public class Item {
    private String name;
    private double effect;

    public Item(String name, double effect) {
        this.name = name;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getEffect() {
        return effect;
    }

    public void setEffect(double effect) {
        this.effect = effect;
    }
}