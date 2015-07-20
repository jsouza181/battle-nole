package com.electricsheep.battlenole;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * PlacementGrid is a fragment that represents the 10x10 grid in which the players' ships are placed.
 */
public class PlacementGrid extends Fragment {

    private Tile bufferedTile;
    private GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_battle_grid, container, false);

        gridView = (GridView)view.findViewById(R.id.battle_grid);
        TileAdapter gridAdapter = new TileAdapter(getActivity());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View selectedTile,
                                    int position, long id) {

                /* The buffered tile doesn't have a ship on it, place the newly
                 * selected tile in the buffer and highlight it if it contains a ship.
                 */
                if (bufferedTile == null || !bufferedTile.getOccupied()) {
                    if (((Tile) selectedTile).getOccupied())
                        ((Tile) selectedTile).setImageResource(R.mipmap.ship_a_highlighted);

                    bufferedTile = ((Tile) selectedTile);

                }
                /* The buffered tile DOES have a ship on it, put the buffered tile's ship on the
                 * selected tile. Then "remove" the ship from the buffered tile.
                 */
                else {
                    //Remove the ship from every tile the ship was previously on.
                    //Preserve the ship itself to be used later.
                    Ship shipToAdd = bufferedTile.getOccupyingShip();

                    removeShip(parent, bufferedTile, shipToAdd);

                    /* Can't figure out why, but for some reason the 0th tile isn't cleared
                     * properly...
                     */

                    //Add the ship to the selected tile.
                    placeShip(parent, (Tile) selectedTile, shipToAdd);

                    bufferedTile = null;

                }
            }
        });

        return view;
    }

    private void removeShip(AdapterView<?> parent, Tile tileToRemove, Ship shipToAdd) {
        /* Locate the "head node" of the ship we want to remove from its former set of
        * tiles. Then remove the head and each subsequent vertical/horizontal tile
        * containing the ship.
        */
        Tile tileToClear = ((Tile)parent.getAdapter().getItem
                (tileToRemove.getIndex()));

        tileToClear = getHeadNode(parent, tileToClear, shipToAdd);

        int headIndex = tileToClear.getIndex();
        //Ship is vertical
        if(tileToClear.getOccupyingShip().isVertical()) {
            for (int i = 1; i <= 5 ; i++) { //shipToAdd.getSegmentCount(); i++) {
                tileToClear.setOccupied(false);
                tileToClear.setOccupyingShip(null);
                tileToClear.setImageResource(R.mipmap.water);
                tileToClear = ((Tile)parent.getAdapter().getItem
                        (headIndex + (i * 10)));
            }
        }
        //Ship is horizontal
        else {
            for (int i = 1; i <= 5 ; i++) { //shipToAdd.getSegmentCount(); i++) {
                tileToClear.setOccupied(false);
                tileToClear.setOccupyingShip(null);
                tileToClear.setImageResource(R.mipmap.water);
                tileToClear = ((Tile)parent.getAdapter().getItem
                        (headIndex + i));
            }
        }
    }

    private void placeShip(AdapterView<?> parent, Tile tile, Ship ship) {
        //Ship is vertical
        if(ship.isVertical()) {
            for(int i = 0; i < ship.getSegmentCount(); i++) {
                Tile tileToOccupy = ((Tile)parent.getAdapter().getItem(tile.getIndex() + (i * 10)));
                tileToOccupy.setImageResource(ship.getImageSegment(i));
                tileToOccupy.setOccupied(true);
                tileToOccupy.setOccupyingShip(ship);
            }
        }
        //Ship is horizontal
        else {
            for(int i = 0; i < ship.getSegmentCount(); i++) {
                Tile tileToOccupy = ((Tile)parent.getAdapter().getItem(tile.getIndex() + i));
                tileToOccupy.setImageResource(ship.getImageSegment(i));
                tileToOccupy.setOccupied(true);
                tileToOccupy.setOccupyingShip(ship);
            }
        }
    }

    private Tile getHeadNode(AdapterView<?> parent, Tile tileToClear, Ship shipToAdd) {
        Tile headCandidateTile;

        //Ship is vertical
        if(tileToClear.getOccupyingShip().isVertical()) {
                        /* While the "previous" tile is not out of bounds (< 0) and it is also
                         * not in the rightmost column, check to see if it is part of the same ship.
                         * If it is, change the tileToClear to be that tile.
                         */
            while(tileToClear.getIndex() - 10 >= 0) {
                headCandidateTile = ((Tile)parent.getAdapter().getItem
                        (tileToClear.getIndex() - 10));

                if(headCandidateTile.getOccupyingShip() == shipToAdd) {
                    tileToClear = headCandidateTile;
                }
                else {
                    break;
                }
            }
        }
        //Ship is horizontal
        else {
            /* While the "previous" tile is not out of bounds (< 0) and it is also
            * not in the rightmost column, check to see if it is part of the same ship.
            * If it is, change the tileToClear to be that tile.
            */
            while((tileToClear.getIndex() - 1 >= 0) &&
                    (tileToClear.getIndex() - 1 % 10) != 9) {
                headCandidateTile = ((Tile)parent.getAdapter().getItem
                        (tileToClear.getIndex() - 1));

                if(headCandidateTile.getOccupyingShip() == shipToAdd) {
                    tileToClear = headCandidateTile;
                }
                else {
                    break;
                }
            }
        }

        return tileToClear;
    }

    public void rotateSelectedShip()
    {
        Ship shipToAdd = bufferedTile.getOccupyingShip();
        Tile tileToOccupy = getHeadNode(gridView, bufferedTile, shipToAdd);
        removeShip(gridView, bufferedTile, shipToAdd);
        shipToAdd.changeOrientation();
        placeShip(gridView, tileToOccupy, shipToAdd);
        bufferedTile = null;
    }

}
