package com.electricsheep.battlenole;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * ShipPlacement is the Activity that presents the player with a grid where they can set the initial
 * position of their ships. It contains a PlacementGrid fragment which contains a GridView
 * and all of the methods needed for the player to interact with it.
 */
public class ShipPlacement extends Activity {
    private Button rotateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_placement);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        final PlacementGrid gridFragment = new PlacementGrid();
        fragmentTransaction.add(R.id.grid_frame, gridFragment);
        fragmentTransaction.commit();

        /*
         * When the player clicks the rotate button, simply call the PlacementGrid fragment's
         * corresponding method to handle it.
         */
        rotateButton = (Button)findViewById(R.id.rotate_button);
        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridFragment.rotateSelectedShip();
            }
        });

    }

}
