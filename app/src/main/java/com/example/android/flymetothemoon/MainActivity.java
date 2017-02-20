package com.example.android.flymetothemoon;
// Γιώργος Στάθης
//465654165416541

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static int TIME_OUT = 5000; //Time to launch the another activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
//                startActivity(intent);
//                finish(); this is also
//            }
//        },TIME_OUT);
        Toast toast = Toast.makeText(this, "Μη διαθέσιμο..." ,Toast.LENGTH_LONG);
        toast.show();
        //Just a change
        //another one?
    }

    /**
     * startSearchActivity called when START button is clicked
     * It opens the SearchActivity.
     */
    public void startSearchActivity(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
