package dragonsofmugloar.dragons_of_mugloar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class startNewGame extends Activity {
    @BindView(R.id.txtKnight) TextView txtKnight;
    @BindView(R.id.txtKnightName) TextView txtKnightName;
    @BindView(R.id.txtWeather) TextView txtWeather;
    @BindView(R.id.txtPointsRemaining) TextView txtPointsRemaining;
    @BindView(R.id.txtScaleThickness) TextView txtScaleThickness;
    @BindView(R.id.txtClawSharpness) TextView txtClawSharpness;
    @BindView(R.id.txtWingStrength) TextView txtWingStrength;
    @BindView(R.id.txtFireBreath) TextView txtFireBreath;
    @BindView(R.id.frmHunting)
    FrameLayout frmHunting;
    public static int mGameId;
    dragon mDragon;
    knight mKnight;
    public static int StatPoints = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_knight);
        ButterKnife.bind(this);
        getGame newGame = new getGame(this);
        newGame.execute("");
        mDragon = new dragon(StatPoints,0,0,0,0);
        displayDragon(mDragon);
    }
    @OnClick({R.id.btnScaleThicknessAdd,R.id.btnClawSharpnessAdd,R.id.btnWingStrengthAdd,R.id.btnFireBreathAdd})
    public void AddAtt(View v){
        if(mDragon.getPoints() > 0){
            if(v.getId() == R.id.btnScaleThicknessAdd){
                adjustAtt(0,1);
            }
            else if(v.getId() == R.id.btnClawSharpnessAdd){
                adjustAtt(1,1);
            }
            else if(v.getId() == R.id.btnWingStrengthAdd){
                adjustAtt(2,1);
            }
            else if(v.getId() == R.id.btnFireBreathAdd){
                adjustAtt(3,1);
            }
        }
    }
    @OnClick({R.id.btnScaleThicknessRemove,R.id.btnClawSharpnessRemove,R.id.btnWingStrengthRemove,R.id.btnFireBreathRemove})
    public void RemoveAtt(View v){
        if(v.getId() == R.id.btnScaleThicknessRemove){
            if(mDragon.getScaleThickness() != 0)
            adjustAtt(0,-1);
        }
        else if(v.getId() == R.id.btnClawSharpnessRemove){
            if(mDragon.getClawSharpness() != 0)
            adjustAtt(1,-1);
        }
        else if(v.getId() == R.id.btnWingStrengthRemove){
            if(mDragon.getWingStrengeth() != 0)
            adjustAtt(2,-1);
        }
        else if(v.getId() == R.id.btnFireBreathRemove){
            if(mDragon.getFireBreath() != 0)
            adjustAtt(3,-1);
        }
    }
    public void adjustAtt(int i, int j) {
            if(i == 0) {
                mDragon.setScaleThickness(mDragon.getScaleThickness() + j);
            }
            if(i == 1){
                mDragon.setClawSharpness(mDragon.getClawSharpness() + j);
            }
            if(i == 2){
                mDragon.setWingStrengeth(mDragon.getWingStrengeth() + j);
            }
            if(i == 3){
                mDragon.setFireBreath(mDragon.getFireBreath() + j);
            }
            mDragon.setPoints(mDragon.getPoints() - j);
            displayDragon(mDragon);
    }
    public void displayDragon(dragon mDragon){
        txtScaleThickness.setText(Integer.toString(mDragon.getScaleThickness()));
        txtClawSharpness.setText(Integer.toString(mDragon.getClawSharpness()));
        txtWingStrength.setText(Integer.toString(mDragon.getWingStrengeth()));
        txtFireBreath.setText(Integer.toString(mDragon.getFireBreath()));
        txtPointsRemaining.setText(Integer.toString(mDragon.getPoints()) + "\n Points Remaining");
    }
    public void displayKnight(knight mKnight) {
        this.mKnight = mKnight;
        txtKnightName.setText(mKnight.getName());
        txtKnight.setText(mKnight.toString());
        frmHunting.setVisibility(View.GONE);
    }
    public void displayWeather(String mWeather) {
        txtWeather.setText(mWeather);
    }
    @OnClick(R.id.btnAttack)
    public void attack(){
        if(mDragon.getPoints() == 0){
            Intent i = new Intent(this, AttackResult.class);
            i.putExtra("gameId",mGameId);
            i.putExtra("ScaleThickness",mDragon.getScaleThickness());
            i.putExtra("ClawSharpness",mDragon.getClawSharpness());
            i.putExtra("WingStrength",mDragon.getWingStrengeth());
            i.putExtra("FireBreath",mDragon.getFireBreath());
            i.putExtra("Name",mKnight.getName());
            i.putExtra("Attack",mKnight.getAttack());
            i.putExtra("Armor",mKnight.getArmor());
            i.putExtra("Agility",mKnight.getAgility());
            i.putExtra("Endurance",mKnight.getEndurace());
            startActivity(i);
        }
        else {
            Toast.makeText(this,"Use remaining points before attacking", Toast.LENGTH_SHORT).show();
        }
    }
}




