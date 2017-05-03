package com.mobintum.socceranzen;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class FieldActivity extends AppCompatActivity implements PlayerFragment.CallbacksFragment {

    private static final int WIN_SCORE = 5;
    private TextView txtScoreX, txtScoreY;
    private FrameLayout frameKeeperX, frameKeeperY, framePlayer1x, framePlayer2x, framePlayer1y, framePlayer2y;
    private FragmentManager dtRicardo;
    private int scoreX, scoreY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);
        dtRicardo = getSupportFragmentManager();
        txtScoreX = (TextView) findViewById(R.id.txtScoreX);
        txtScoreY = (TextView) findViewById(R.id.txtScoreY);
        frameKeeperX = (FrameLayout) findViewById(R.id.frameKeeperX);
        frameKeeperY = (FrameLayout) findViewById(R.id.frameKeeperY);
        framePlayer1x = (FrameLayout) findViewById(R.id.framePlayer1x);
        framePlayer2x = (FrameLayout) findViewById(R.id.framePlayer2x);
        framePlayer1y = (FrameLayout) findViewById(R.id.framePlayer1y);
        framePlayer2y = (FrameLayout) findViewById(R.id.framePlayer2y);
        loadPlayers();
    }

    @Override
    protected void onStart() {
        super.onStart();
        PlayerFragment playerFragment = (PlayerFragment) dtRicardo.findFragmentByTag("Gerardo");
        playerFragment.showBall(true);

    }

    private void loadPlayers(){
        PlayerFragment playerGerardo = PlayerFragment.newInstance("Gerardo",R.color.yellow_america);
        PlayerFragment playerEdgar = PlayerFragment.newInstance("Edgar",R.color.yellow_america);
        PlayerFragment playerArmando = PlayerFragment.newInstance("Armando",R.color.yellow_america);
        PlayerFragment playerCesar = PlayerFragment.newInstance("Cesar",R.color.blue_pumas);
        PlayerFragment playerJesus = PlayerFragment.newInstance("Jesus",R.color.blue_pumas);
        PlayerFragment playerDaniel = PlayerFragment.newInstance("Daniel",R.color.blue_pumas);

        FragmentTransaction ft = dtRicardo.beginTransaction();
        ft.add(R.id.frameKeeperX,playerGerardo,"Gerardo");
        ft.add(R.id.frameKeeperY,playerCesar,"Cesar");
        ft.add(R.id.framePlayer1y,playerJesus,"Jesus");
        ft.add(R.id.framePlayer2y,playerDaniel,"Daniel");
        ft.add(R.id.framePlayer1x,playerEdgar,"Edgar");
        ft.add(R.id.framePlayer2x,playerArmando,"Armando");
        ft.commit();
    }

    @Override
    public void pass(int position) {
        PlayerFragment playerFragment = (PlayerFragment) dtRicardo.getFragments().get(position);
        if (playerFragment!=null) {
            if (!playerFragment.haveBall)
            if (position == 0 || position == 1)
                if (goal()) {
                    if (position==0) {
                        scoreY++;
                        txtScoreY.setText(""+scoreY);
                        Toast.makeText(this, "Goool of Y", Toast.LENGTH_SHORT).show();
                    }else {
                        scoreX++;
                        txtScoreX.setText(Integer.toString(scoreX));
                        Toast.makeText(this, "Goool of X", Toast.LENGTH_SHORT).show();
                    }
                    validateWinner();
                }
            playerFragment.showBall(true);
        }
    }

    private boolean goal(){
        if(getRandomPosition(0,10)>=5)
            return true;
        return false;
    }

    public static int getRandomPosition(int a, int b){
        Random r = new Random();
        int result = r.nextInt(b-a) + a;
        return result;
    }

    private void validateWinner(){
        if (scoreY == WIN_SCORE || scoreX == WIN_SCORE){
            scoreX =0;
            scoreY =0;
            txtScoreY.setText(""+scoreY);
            txtScoreX.setText(""+scoreX);
            if (scoreX == WIN_SCORE)
                Toast.makeText(this, "Winner X", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Winner Y", Toast.LENGTH_LONG).show();
        }
    }
}
