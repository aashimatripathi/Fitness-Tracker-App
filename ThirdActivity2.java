package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThirdActivity2 extends AppCompatActivity {

    String buttonvalue;
    Button startbtn = findViewById(R.id.startbutton);
    private CountDownTimer countDownTimer;
    TextView mtextview = findViewById(R.id.time);
    private boolean mtinerunning;
    private long mtimeleftinmillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        buttonvalue = intent.getStringExtra("value");
        assert buttonvalue != null;
        int intvalue = Integer.parseInt(buttonvalue);

        switch (intvalue){

            case 1:
                setContentView(R.layout.activity_bow_pose);
                break;

            case 2:
                setContentView(R.layout.activity_bridge_pose);
                break;

            case 3:
                setContentView(R.layout.activity_chair_pose);
                break;

            case 4:
                setContentView(R.layout.activity_child_pose);
                break;

            case 5:
                setContentView(R.layout.activity_cobbler_pose);
                break;

            case 6:
                setContentView(R.layout.activity_cow_pose);
                break;

            case 7:
                setContentView(R.layout.activity_play_pose);
                break;

            case 8:
                setContentView(R.layout.activity_pause_pose);
                break;

            case 9:
                setContentView(R.layout.activity_plank_pose);
                break;

            case 10:
                setContentView(R.layout.activity_crunches_pose);
                break;

            case 11:
                setContentView(R.layout.activity_situp_pose);
                break;

            case 12:
                setContentView(R.layout.activity_rotation_pose);
                break;

            case 13:
                setContentView(R.layout.activity_twist_pose);
                break;

            case 14:
                setContentView(R.layout.activity_legup_pose);
                break;

            case 15:
                setContentView(R.layout.activity_windmill_pose);
                break;
        }


        startbtn.setOnClickListener(view -> {
            if (mtinerunning){
                stoptimer();
            }
            else {
                starttimer();
            }
        });

    }


    private void starttimer(){
        final CharSequence value1 = mtextview.getText();
        String num1 = value1.toString();
        String num2 = num1.substring(0,2);
        String num3 = num1.substring(3,5);

        final int num = Integer.parseInt(num2)*60000 + Integer.parseInt(num3)*1000;
        mtimeleftinmillis = num * 1000L;

        countDownTimer =new CountDownTimer(mtimeleftinmillis, 1000) {
            @Override
            public void onTick(long l) {
                long millisUntilFinished;
                millisUntilFinished = 0;
                mtimeleftinmillis = millisUntilFinished;
                updatetimer();
            }

            @Override
            public void onFinish() {
                int new_value = Integer.parseInt(buttonvalue) + 1;
                if (new_value<=7){
                    Intent intent = new Intent(ThirdActivity2.this, ThirdActivity2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value", String.valueOf(new_value));
                    startActivity(intent);
                }
                else {
                    new_value = 1;
                    Intent intent=new Intent(ThirdActivity2.this,ThirdActivity2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value", String.valueOf(new_value));
                    startActivity(intent);

                }
            }
        }.start();
        startbtn.setText(R.string.pause);
        new AtomicBoolean(true);
    }

    private void stoptimer(){
        countDownTimer.cancel();
        mtinerunning = false;
        startbtn.setText(R.string.stop);
    }

    private void updatetimer(){
        int minutes = (int) (mtimeleftinmillis/60000);
        int seconds = (int) (mtimeleftinmillis%60000/1000);


        String timeleft = "";
        if (minutes<10){
            timeleft = "0";
            timeleft = timeleft+minutes+":";
        }
        if (seconds<10){
            timeleft += "0";
            timeleft = timeleft+seconds;
            mtextview.setText(timeleft);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}