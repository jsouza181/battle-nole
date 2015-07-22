package com.electricsheep.battlenole;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * TileAdapter is a custom adapter used with the PlacementGrid's GridView. It is functionally
 * similar to a ListAdapter but it is also responsible for setting the ships in their initial positions
 */
public class TileAdapter extends BaseAdapter {
    private Context context;
    private Tile[] tiles;

    public TileAdapter(Context c) {
        context = c;
        tiles = new Tile[100];
    }

    public int getCount() {
        return 100;
    }

    public Object getItem(int position) {
        return tiles[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {


            if(convertView == null){
                tiles[position] = new Tile(context);
                tiles[position].setLayoutParams
                        (new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
            } else {
                tiles[position] = (Tile)convertView;
            }

            /*
             * Set the initial ship locations
             */
            if(position % 10 == 0 && position <= 40) {  //Set carrier
                tiles[position].setOccupyingShip(new Ship("Carrier"));
                tiles[position].setOccupied(true);
                tiles[position].setImageResource
                        (tiles[position].getOccupyingShip().getImageSegment(position / 10));
            }
            else {
                tiles[position].setImageResource(R.mipmap.water);
            }

            /*
             * Adjust the tile's settings so that the grid is scalable, and set its grid index
             */
            tiles[position].setAdjustViewBounds(true);
            tiles[position].setScaleType(ImageView.ScaleType.FIT_CENTER);
            tiles[position].setIndex(position);
            return tiles[position];
        }
}
