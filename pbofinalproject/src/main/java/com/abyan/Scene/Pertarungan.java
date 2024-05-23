package com.abyan.Scene;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

public interface Pertarungan {
    public double basicAttack(Monster monster);
    public double specialAttack(Monster monster);
    public double elementAttack(Monster monster);
    public double useItem(Item item);
}
