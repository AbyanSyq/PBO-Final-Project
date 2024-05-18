package com.abyan.Manager;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

public class LVManager{
    public static final int expToLvUp = 10;
    public static final double baseMaksHp = 100;//Hp minimal
    public static final double baseDamagae = 20;//Base Damage
    

    public static double getMaksHpByLv(double lv){
        return baseMaksHp * lv;
    }
    public static double getBaseDamageByLv(double lv){
        return baseDamagae * lv;
    }
}
