package com.electricsheep.battlenole;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * ShipPlacement is the Activity
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

        rotateButton = (Button)findViewById(R.id.rotate_button);
        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridFragment.rotateSelectedShip();
            }
        });

    }

}
