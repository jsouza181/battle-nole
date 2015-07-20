package com.electricsheep.battlenole;


import android.content.Context;
import android.widget.ImageView;

public class Tile extends ImageView{

    private boolean occupied = false;
    private Ship occupyingShip;
    private int index;

    public Tile(Context c){
        super(c);
    }

    public boolean getOccupied(){
        return occupied;
    }

    public void setOccupied(boolean b){
        occupied = b;
    }

    public Ship getOccupyingShip() {
        return occupyingShip;
    }

    public void setOccupyingShip(Ship s) { occupyingShip = s; }

    public int getIndex() { return index; }

    public void setIndex(int i) { index = i; }

    public void changeOrientation() {
        occupyingShip.changeOrientation();
    }

}
