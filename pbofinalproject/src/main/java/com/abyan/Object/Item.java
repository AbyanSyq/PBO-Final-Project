package com.abyan.Object;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

abstract class ItemBluePrint{
    private String name;
    protected double healAmount;
    protected double damageAmount;
    public abstract String getName();
    public abstract void setName(String name);
}

public class Item extends ItemBluePrint{
    private String name;
    protected double healAmount;
    protected double damageAmount;

    public Item(String name, double healAmount, double damageAmount) {
        this.name = name;
        this.healAmount = healAmount;
        this.damageAmount = damageAmount;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {      
        this.name = name;
    }
}