package com.abyan.Object;

public class healPotion extends Item {
    private double healAmount = 20;
    public healPotion() {
        super("heal");
    }

    public void usePotion(Monster targer){
        targer.heal(healAmount);
    }
}