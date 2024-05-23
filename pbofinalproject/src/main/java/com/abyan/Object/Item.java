package com.abyan.Object;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

abstract class ItemBluePrint{
    private String name;
    public abstract String getName();
    public abstract void setName(String name);
}

public class Item extends ItemBluePrint{
    private String name;
    
    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {      
        this.name = name;
    }
}