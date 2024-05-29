package com.abyan.Manager;
import com.abyan.Object.*;

public interface Pertarungan {
    public double basicAttack(Monster monster);
    public double specialAttack(Monster monster);
    public double elementAttack(Monster monster);
    public double useItem(Item item);
    public void surrender();
}
