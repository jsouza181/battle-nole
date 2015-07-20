package com.electricsheep.battlenole;


public class Ship {
    private String shipType;
    private int hitPoints;
    private enum Orientation {
        VERTICAL, HORIZONTAL
    }
    private Orientation orientation = Orientation.VERTICAL;
    private int [] imageSegments;
    private int segmentCount;

    public Ship(String type)
    {
        shipType = type;
        switch(shipType)
        {
            case("Carrier"):
                hitPoints = 5;
                imageSegments = new int[5];
                imageSegments[0] = R.mipmap.ship_a;
                imageSegments[1] = R.mipmap.ship_b;
                imageSegments[2] = R.mipmap.ship_c;
                imageSegments[3] = R.mipmap.ship_d;
                imageSegments[4] = R.mipmap.ship_e;
                segmentCount = 5;
                break;
            case("Battleship"):
                hitPoints = 4;
                break;
            case("Cruiser"):
                hitPoints = 3;
                break;
            case("Submarine"):
                hitPoints = 3;
                break;
            case("Destroyer"):
                hitPoints = 2;
                break;
        }
    }

    public int getImageSegment(int pos) {
        return imageSegments[pos];
    }

    public int getSegmentCount() { return segmentCount; }

    public boolean isVertical() {
        return (orientation == Orientation.VERTICAL);
    }

    public void changeOrientation() {
        if(orientation == Orientation.VERTICAL) {
            orientation = Orientation.HORIZONTAL;
        }
        else {
            orientation = Orientation.VERTICAL;
        }
    }
}
