package com.electricsheep.battlenole;

/*
 * The ship class is the object representation of a BattleNole ship. It's primarily used to maintain
 * basic data such as its orientation, graphical representation, and hit points.
 */
public class Ship {
    private String shipType;
    private int hitPoints;
    private enum Orientation {
        VERTICAL, HORIZONTAL
    }
    private Orientation orientation = Orientation.VERTICAL;
    private int [] imageSegments;
    private int [] imageSegmentsSelected;
    private int segmentCount;

    public Ship(String type)
    {
        shipType = type;
        switch(shipType)
        {
            case("Carrier"):
                hitPoints = 5;
                imageSegments = new int[5];
                imageSegments[0] = R.mipmap.carrier_vert_a;
                imageSegments[1] = R.mipmap.carrier_vert_b;
                imageSegments[2] = R.mipmap.carrier_vert_c;
                imageSegments[3] = R.mipmap.carrier_vert_d;
                imageSegments[4] = R.mipmap.carrier_vert_e;
                imageSegmentsSelected = new int[5];
                imageSegmentsSelected[0] = R.mipmap.carrier_vert_a_selected;
                imageSegmentsSelected[1] = R.mipmap.carrier_vert_b_selected;
                imageSegmentsSelected[2] = R.mipmap.carrier_vert_c_selected;
                imageSegmentsSelected[3] = R.mipmap.carrier_vert_d_selected;
                imageSegmentsSelected[4] = R.mipmap.carrier_vert_e_selected;
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

    public int getImageSegmentSelected(int pos) {
        return imageSegmentsSelected[pos];
    }

    public int getSegmentCount() { return segmentCount; }

    public boolean isVertical() {
        return (orientation == Orientation.VERTICAL);
    }

    public void changeOrientation() {
        if(orientation == Orientation.VERTICAL) {
            orientation = Orientation.HORIZONTAL;
            imageSegments[0] = R.mipmap.carrier_horiz_a;
            imageSegments[1] = R.mipmap.carrier_horiz_b;
            imageSegments[2] = R.mipmap.carrier_horiz_c;
            imageSegments[3] = R.mipmap.carrier_horiz_d;
            imageSegments[4] = R.mipmap.carrier_horiz_e;
            imageSegmentsSelected[0] = R.mipmap.carrier_horiz_a_selected;
            imageSegmentsSelected[1] = R.mipmap.carrier_horiz_b_selected;
            imageSegmentsSelected[2] = R.mipmap.carrier_horiz_c_selected;
            imageSegmentsSelected[3] = R.mipmap.carrier_horiz_d_selected;
            imageSegmentsSelected[4] = R.mipmap.carrier_horiz_e_selected;
        }
        else {
            orientation = Orientation.VERTICAL;
            imageSegments[0] = R.mipmap.carrier_vert_a;
            imageSegments[1] = R.mipmap.carrier_vert_b;
            imageSegments[2] = R.mipmap.carrier_vert_c;
            imageSegments[3] = R.mipmap.carrier_vert_d;
            imageSegments[4] = R.mipmap.carrier_vert_e;
            imageSegmentsSelected[0] = R.mipmap.carrier_vert_a_selected;
            imageSegmentsSelected[1] = R.mipmap.carrier_vert_b_selected;
            imageSegmentsSelected[2] = R.mipmap.carrier_vert_c_selected;
            imageSegmentsSelected[3] = R.mipmap.carrier_vert_d_selected;
            imageSegmentsSelected[4] = R.mipmap.carrier_vert_e_selected;
        }
    }
}
