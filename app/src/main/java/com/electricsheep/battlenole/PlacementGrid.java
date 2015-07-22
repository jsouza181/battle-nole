package com.electricsheep.battlenole;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * PlacementGrid is a fragment that represents the 10x10 grid in which the players' ships are
 * placed. It contains all five of the players ships, arranged side by side. The player can then
 * choose to move their ships to any valid spot on the grid by:
 * 1. Selecting the ship they wish to move.
 * 2. Selecting an empty tile where they can place the ship.
 *
 * The player can also rotate the ship currently selected by clicking the "Rotate" button in the
 * fragment's parent view/activity.
 */
public class PlacementGrid extends Fragment {

    //When an occupied tile is selected, it becomes a "buffered" tile. That way, the tile selected
    //is preserved when the player selects a blank tile to place the ship somewhere else.
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

                /* The buffered tile DOESN'T have a ship on it, place the newly
                 * selected tile in the buffer and highlight it if it contains a ship.
                 */
                if (bufferedTile == null || !bufferedTile.getOccupied()) {
                    if (((Tile) selectedTile).getOccupied())
                        placeShip(parent, getHeadNode(parent, (Tile)selectedTile,
                                        (((Tile) selectedTile).getOccupyingShip())),
                                ((Tile) selectedTile).getOccupyingShip(), true);

                    bufferedTile = ((Tile) selectedTile);

                }
                /* The buffered tile DOES have a ship on it, put the buffered tile's ship on the
                 * selected tile. Then "remove" the ship from the buffered tile.
                 */
                else {
                    //Preserve the ship itself to be used later.
                    Ship shipToAdd = bufferedTile.getOccupyingShip();
                    //Remove the ship from every tile the ship was previously on.
                    removeShip(parent, bufferedTile, shipToAdd);

                    /* Can't figure out why, but for some reason the 0th tile isn't cleared
                     * properly...
                     */

                    //Add the ship to the selected tile.
                    placeShip(parent, (Tile) selectedTile, shipToAdd, false);

                    bufferedTile = null;

                }
            }
        });

        return view;
    }

    /*
     * removeShip locates the "head node" of the ship we want to remove from its former set of
     * tiles. Then it removes the head and each subsequent vertical/horizontal tile containing
     * the ship (clears the data of each tile and sets the image to default "water").
     */
    private void removeShip(AdapterView<?> parent, Tile tileToRemove, Ship shipToRemove) {

        //Possibly redundant? Can't remember why this is here, will test later.
        Tile tileToClear = ((Tile)parent.getAdapter().getItem
                (tileToRemove.getIndex()));

        //To remove the ship, we need to find its head node first
        tileToClear = getHeadNode(parent, tileToClear, shipToRemove);
        int headIndex = tileToClear.getIndex();

        /*
         * Now that we have the node, iterate through each tile of the ship using a for loop
         * and iterate until we reach the number of tiles(segments) the ship takes up.
         */

        //Ship is vertical
        if(tileToClear.getOccupyingShip().isVertical()) {
            for (int i = 1; i <= shipToRemove.getSegmentCount() ; i++) {
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

    /*
     * placeShip pretty much works just like removeShip, only with additional consideration for
     * whether the ship is currently selected(highlighted).
     */
    private void placeShip(AdapterView<?> parent, Tile tile, Ship ship, boolean selected) {
        //Ship is vertical
        if(ship.isVertical()) {
            for(int i = 0; i < ship.getSegmentCount(); i++) {
                Tile tileToOccupy = ((Tile)parent.getAdapter().getItem(tile.getIndex() + (i * 10)));
                if(selected) {
                    tileToOccupy.setImageResource(ship.getImageSegmentSelected(i));
                }
                else {
                    tileToOccupy.setImageResource(ship.getImageSegment(i));
                }
                tileToOccupy.setOccupied(true);
                tileToOccupy.setOccupyingShip(ship);
            }
        }
        //Ship is horizontal
        else {
            for(int i = 0; i < ship.getSegmentCount(); i++) {
                Tile tileToOccupy = ((Tile)parent.getAdapter().getItem(tile.getIndex() + i));
                if(selected) {
                    tileToOccupy.setImageResource
                            (ship.getImageSegmentSelected(ship.getSegmentCount() - i - 1));
                }
                else {
                    tileToOccupy.setImageResource(ship.getImageSegment(ship.getSegmentCount() - i - 1));
                }
                tileToOccupy.setOccupied(true);
                tileToOccupy.setOccupyingShip(ship);
            }
        }
    }

    private Tile getHeadNode(AdapterView<?> parent, Tile headNode, Ship shipToAdd) {
        Tile headCandidateTile;

        //Ship is vertical
        if(headNode.getOccupyingShip().isVertical()) {
            /* While the next potential head tile is not out of bounds (< 0), check to see if it
             * is part of the same ship. If it is, change the headNode to be that tile.
             */
            while(headNode.getIndex() - 10 >= 0) {
                headCandidateTile = ((Tile)parent.getAdapter().getItem
                        (headNode.getIndex() - 10));

                if(headCandidateTile.getOccupyingShip() == shipToAdd) {
                    headNode = headCandidateTile;
                }
                else {
                    break;
                }
            }
        }
        //Ship is horizontal
        else {
            /* While the next potential head tile is not out of bounds (< 0) and is also
             * not in the rightmost column(which would indicate that the loop has reached the 0th
             * tile of that row and is now wrapping around to the previous row), check to see if it
             * is part of the same ship. If it is, change the headNode to be that tile.
             */
            while((headNode.getIndex() - 1 >= 0) &&
                    (headNode.getIndex() - 1 % 10) != 9) {
                headCandidateTile = ((Tile)parent.getAdapter().getItem
                        (headNode.getIndex() - 1));

                if(headCandidateTile.getOccupyingShip() == shipToAdd) {
                    headNode = headCandidateTile;
                }
                else {
                    break;
                }
            }
        }

        return headNode;
    }

    /*
     * Called by pressing the rotate button, check to see if there is a buffered tile and if it
     * contains a ship. If so:
     * 1. Make a copy of the ship to rotate.
     * 2. Remove the ship from its original spot.
     * 3. Change the orientation of the ship.
     * 4. Place the copy on the same tile.
     */
    public void rotateSelectedShip()
    {
        if(bufferedTile != null && bufferedTile.getOccupied()) {
            Ship shipToAdd = bufferedTile.getOccupyingShip();
            Tile tileToOccupy = getHeadNode(gridView, bufferedTile, shipToAdd);
            removeShip(gridView, bufferedTile, shipToAdd);
            shipToAdd.changeOrientation();
            placeShip(gridView, tileToOccupy, shipToAdd, false);
            bufferedTile = null;
        }

    }

}
