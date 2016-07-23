package dragonsofmugloar.dragons_of_mugloar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AttackResult extends Activity {
    @BindView(R.id.txtDragon) TextView txtDragon;
    @BindView(R.id.txtKnight) TextView txtKnight;
    @BindView(R.id.txtKnightName) TextView txtKnightName;
    @BindView(R.id.txtResult) TextView txtResult;
    @BindView(R.id.txtMessage) TextView txtMessage;
    @BindView(R.id.txtVictories) TextView txtVictories;
    @BindView(R.id.txtDefeats) TextView txtDefeats;
    @BindView(R.id.frmBattle)
    FrameLayout frmBattle;

    int gameId;
    int victories;
    int defeats;
    dragon mDragon;
    knight mKnight;
    SharedPreferences mSharedPrefs;
    String MY_PREFS_NAME = "com.dragonsofmugloar.prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attack_result);
        ButterKnife.bind(this);
        mSharedPrefs = getSharedPreferences(MY_PREFS_NAME,0);
        victories = mSharedPrefs.getInt("victories",0);
        defeats = mSharedPrefs.getInt("defeats",0);
        Intent intent = getIntent();
        mDragon = new dragon(startNewGame.StatPoints,
                getStats("ScaleThickness", intent),
                getStats("ClawSharpness", intent),
                getStats("WingStrength", intent),
                getStats("FireBreath", intent));
        mKnight = new knight(intent.getStringExtra("Name"),
                getStats("Attack",intent),
                getStats("Armor",intent),
                getStats("Agility",intent),
                getStats("Endurance",intent));
        gameId = intent.getIntExtra("gameId",0);
        attack launchAttack = new attack(this, mDragon, gameId);
        launchAttack.execute("");
    }
    public int getStats(String stat,Intent intent){
        int statVal = intent.getIntExtra(stat,0);
        return statVal;
    }
    public void displayResult(String result, String message) {
        updateStats(result);
        txtVictories.setText(Integer.toString(victories));
        txtDefeats.setText(Integer.toString(defeats));
        txtKnightName.setText(mKnight.getName());
        txtDragon.setText(mDragon.toString());
        txtKnight.setText(mKnight.toString());
        txtResult.setText(result+"!");
        //Flood message has a period where other messages don't.
        if(!message.contains("flood")){
            message+= "!\n";
        }
        else{
            message+= "\n";
        }
        txtMessage.setText(message);
        frmBattle.setVisibility(View.GONE);
    }
    private void updateStats(String result) {
        SharedPreferences.Editor edt = mSharedPrefs.edit();
        if(result.equals("Victory")){
            edt.putInt("victories",victories+1);
            victories++;
        }
        else {
            edt.putInt("defeats",defeats+1);
            defeats++;
        }
        edt.commit();
    }

    @OnClick(R.id.btnNewGame)
    public void newGame(){
            Intent i = new Intent(this, startNewGame.class);
            startActivity(i);
    }
}
