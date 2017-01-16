package com.alxgrk.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.ToggleButton;

import com.alxgrk.blendedbackground.BlendedBackgroundLayout;

/**
 * Created by alex on 02.12.16.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_main);

        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Solo");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Background");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Complex");
        host.addTab(spec);

        //Tab 4
        spec = host.newTabSpec("Tab Four");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Fixed");
        host.addTab(spec);

        //Tab 5
        spec = host.newTabSpec("Tab Five");
        spec.setContent(R.id.tab5);
        spec.setIndicator("Changing");
        host.addTab(spec);

        // add onClickListener to button for changing the image
        final ImageView ivChanging = (ImageView) findViewById(R.id.iv_changing);
        final ToggleButton btnChange = (ToggleButton) findViewById(R.id.btn_changing);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnChange.isChecked())
                    ivChanging.setImageResource(R.drawable.blue);
                else
                    ivChanging.setImageResource(R.drawable.green);
            }
        });

        // FIXME why does fvbi not work?
        FrameLayout content = host.getTabContentView();
        final BlendedBackgroundLayout bb = (BlendedBackgroundLayout) content.getChildAt(content.getChildCount() - 1);

        final View target = LayoutInflater.from(this).inflate(R.layout.view, null);

        final ToggleButton btnAddRemove = (ToggleButton) findViewById(R.id.btn_add_remove);
        btnAddRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnAddRemove.isChecked()) {
                    bb.addView(target);
                } else {
                    bb.removeView(target);
                }
            }
        });

        final Button btnInvert = (Button) findViewById(R.id.btn_invert);
        btnInvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bb.isInvert()) {
                    bb.setInvert(false);
                } else {
                    bb.setInvert(true);
                }
            }
        });

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        setUpSpinner(spinner, bb);
    }

    private void setUpSpinner(Spinner spinner, final BlendedBackgroundLayout bb) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colors_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedString = String.valueOf(parent.getItemAtPosition(position));
                switch (selectedString) {
                    case "Light Green":
                        bb.setLower(0xff9cac39);
                        break;
                    case "Dark Green":
                        bb.setLower(0xff4a5a10);
                        break;
                    case "Light Blue":
                        bb.setLower(0xff629cac);
                        break;
                    case "Dark Blue":
                        bb.setLower(0xff003173);
                        break;
                    case "Light Red":
                        bb.setLower(0xffcd2010);
                        break;
                    case "Dark Red":
                        bb.setLower(0xff730800);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });
    }
}
