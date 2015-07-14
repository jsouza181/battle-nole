package com.example.android.bluetoothchat;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eric on 07/13/2015.
 */
public class Ball implements Serializable {

    /*
    * Ball attributes
    * */

    private Map<Integer,String> colorMap;
    private String color;
    private int colorOrder;

    public Ball() {
        color = "";
        colorOrder = 0;
        colorMap = new HashMap<Integer, String>();
    }

    public String getColor() {
        return color;
    }

    public String getColorMap() {
        /*String allColorsString = new String(); // string to append all colors

        for (Map.Entry<Integer,String> m : colorMap.entrySet())
            allColorsString = Integer.toString(m.getKey()) + "  \n";
*/
        return colorMap.toString();
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setColorMap(String color) {
        colorMap.put(colorOrder,color);
        ++colorOrder;
    }

    public int getColorOrder() {
        return colorOrder;
    }

    public void setColorOrder(int colorOrder) {
        this.colorOrder = colorOrder;
    }
}
