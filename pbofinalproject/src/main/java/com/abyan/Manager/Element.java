package com.abyan.Manager;
import com.abyan.Manager.*;
import com.abyan.Object.*;
import com.abyan.Scene.*;

public enum Element {
    API(0),
    TANAH(1),
    ANGIN(2),
    AIR(3),
    ES(4);

    public final int value;

    Element(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public static Element getElementByValue(int value) {
        for (Element e : Element.values()) {
            if (e.getValue() == value) {
                return e;
            }
        }
        return null;
    }
}
