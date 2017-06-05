package com.example.guest.myrestaurant.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guest.myrestaurant.Constants;
import com.example.guest.myrestaurant.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
    private DatabaseReference mSearchedLocationReference;

    @Bind(R.id.findRestaurantsButton) Button mFindRestaurantsButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        Typeface ostrichBoldFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        mAppNameTextView.setTypeface(ostrichBoldFont);

        mFindRestaurantsButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == mFindRestaurantsButton) {
            String location = mLocationEditText.getText().toString();
//            if (!(location).equals("")) {
//                addToSharedPreferences(location);
//            }
            saveLocationToFirebase(location);
            Intent intent = new Intent(MainActivity.this, RestaurantsListActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        }
    }

    public void saveLocationToFirebase(String location) {
        mSearchedLocationReference.setValue(location);
    }

//    private void  addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }
}
