package com.example.traveliker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.example.traveliker.BottomBarBehaviour;
import com.example.traveliker.Fragments.HotelFragment;
import com.example.traveliker.Fragments.PlacesFragment;
import com.example.traveliker.Fragments.TransportFragment;
import com.example.traveliker.R;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

public class Dashboard extends AppCompatActivity {

    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BubbleNavigationLinearView navigationBar = findViewById(R.id.navigationBar);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigationBar.getLayoutParams();
        layoutParams.setBehavior(new BottomBarBehaviour());

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new PlacesFragment()).commit();

        navigationBar.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position) {
                    case 0:
                        fragment = new PlacesFragment();
                        break;
                    case 1:
                        fragment = new HotelFragment();
                        break;
                    case 2:
                        fragment = new TransportFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
            }
        });

    }
}