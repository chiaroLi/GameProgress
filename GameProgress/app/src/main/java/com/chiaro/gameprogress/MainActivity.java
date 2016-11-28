package com.chiaro.gameprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chiaro.gameprogress.util.CalculateOneTime;
import com.chiaro.gameprogress.util.KnockToNext;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 假设一共5个角色，每个人的速度为以下值
        int[] speed = new int[]{165, 151, 143, 128, 125};
        // 假设总行动条为以下值
        double perLength = 200.0;
        int time = 20000;

        CalculateOneTime.gameProcessCal(speed, perLength,time);

        KnockToNext.startPlay(speed,perLength);


    }
}
