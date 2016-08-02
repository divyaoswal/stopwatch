package com.hfad.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;

public class StopwatchActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("stopwatch", "create called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
            Log.d("wasRunningCreate", String.valueOf(wasRunning));
        }
        runTimer();
    }

    //Start the stopwatch running when the Start button is clicked.
    public void onClickStart(View view){
        running = true;
    }

    //Stop the watch running when the stop button is clicked.
    public void onClickStop(View view){
        running = false;
    }

    //Reset the stopwatch when the reset button is clicked.
    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }

    private void runTimer(){
        final TextView timeview = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeview.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void onSaveInstanceState(Bundle b){
        Log.d("stopwatch", "save called");
        b.putInt("seconds",seconds);
        b.putBoolean("running", running);
        b.putBoolean("wasRunning", wasRunning);
        Log.d("wasRunningSave", String.valueOf(wasRunning));

    }

    @Override


    protected void onStop(){
        Log.d("stopwatch", "stop called");
        super.onStop();
        wasRunning = running;
        running = false;
    }


    @Override
    protected void onStart(){
        Log.d("stopwatch", "start called");
        super.onStart();
        if(wasRunning){
            running = true;
        }
    }

    @Override
    protected void onDestroy(){
        Log.d("stopwatch", "destroy called");
        super.onDestroy();
    }

}
