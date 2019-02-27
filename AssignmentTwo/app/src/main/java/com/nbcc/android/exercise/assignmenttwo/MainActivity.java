package com.nbcc.android.exercise.assignmenttwo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbcc.android.exercise.assignmenttwo.model.Rochambo;

/***
 * The main activity
 * @author Yinbin Zuo
 * @date Feb 26, 2019
 */
public class MainActivity extends AppCompatActivity {
    // the variable for rochamo
    private Rochambo mRochambo;

    // the variables for player and game imageview
    private ImageView mImgPlayerMove,mImgGameMove;

    // the variable for result textview
    private TextView mTxtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImgPlayerMove = findViewById(R.id.imgPlayerMove);
        mImgGameMove = findViewById(R.id.imgGameMove);
        mTxtResult = findViewById(R.id.lblResult);

        mRochambo = new Rochambo();
    }

    /***
     * play animation
     */
    private void playAnimation(){
        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(mImgPlayerMove,
                "rotationX", 0f, 360f)
                .setDuration(500);

        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(mImgGameMove,
                "rotationY", 0f, 360f)
                .setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorGame, animatorPlayer);
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.start();
    }

    /***
     * setting player move with different image resources
     * @param move the parameter for player move
     */
    private void placePlayerMove(int move){
        switch (move){
            case Rochambo.ROCK:
                mImgPlayerMove.setImageResource(R.drawable.rock);
                break;
            case Rochambo.PAPER:
                mImgPlayerMove.setImageResource(R.drawable.paper);
                break;
            case Rochambo.SCISSOR:
                mImgPlayerMove.setImageResource(R.drawable.scissors);
                break;
        }
    }

    /***
     * setting game move with different image resources
     * @param move the parameter for game move
     */
    private void placeGameMove(int move){
        switch (move){
            case Rochambo.ROCK:
                mImgGameMove.setImageResource(R.drawable.rock);
                break;
            case Rochambo.PAPER:
                mImgGameMove.setImageResource(R.drawable.paper);
                break;
            case Rochambo.SCISSOR:
                mImgGameMove.setImageResource(R.drawable.scissors);
                break;
        }
    }

    /***
     * clicking event when user click on the imageview
     * @param view
     */
    public void onClickImage(View view) {
        switch (view.getId()){
            case R.id.imgRock:
                mRochambo.playerMakesMove(Rochambo.ROCK);
                break;
            case R.id.imgPapper:
                mRochambo.playerMakesMove(Rochambo.PAPER);
                break;
            case R.id.imgScissors:
                mRochambo.playerMakesMove(Rochambo.SCISSOR);
                break;
        }

        placePlayerMove(mRochambo.getPlayerMove());
        placeGameMove(mRochambo.getGameMove());

        playAnimation();

        mTxtResult.setText(mRochambo.winLoseOrDraw());
    }
}
