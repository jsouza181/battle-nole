package com.electricsheep.battlenole;


import android.content.Context;
import android.widget.ImageView;

/*
 * The tile is the class that represents a single unit of the GridView. It can be occupied by a Ship
 * object and has functions to access the ship that occupies it.
 */
public class Tile extends ImageView{

    private boolean occupied = false;
    private Ship occupyingShip;
    private int index; //The tile's position in the grid that contains it

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


}
