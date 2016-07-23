package dragonsofmugloar.dragons_of_mugloar;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Travis on 7/22/2016.
 */
public class attack extends AsyncTask<String, Void, String> {
    dragon mDragon;
    int gameId;
    String attackURL;
    AttackResult activity;
    attack(AttackResult activity, dragon mDragon, int gameId){
        this.activity = activity;
        this.mDragon = mDragon;
        this.gameId = gameId;
        attackURL = "http://www.dragonsofmugloar.com/api/game/" + Integer.toString(gameId) + "/solution";
    }
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected String doInBackground(String... params) {
        String attackResult = null;
        try {
            attackResult = attackAndGetResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attackResult;
    }

    private String attackAndGetResult() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String json = buildJsonAttack();
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(attackURL)
                    .put(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }

    private String buildJsonAttack() {
        return "{\n" +
                "    \"dragon\": {\n" +
                "        \"scaleThickness\": "+ Integer.toString(mDragon.getScaleThickness()) +",\n" +
                "        \"clawSharpness\": "+ Integer.toString(mDragon.getClawSharpness()) +",\n" +
                "        \"wingStrength\": "+ Integer.toString(mDragon.getWingStrengeth()) +",\n" +
                "        \"fireBreath\": "+ Integer.toString(mDragon.getFireBreath()) +"\n" +
                "    }\n" +
                "}";
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            JSONObject JsonResult = new JSONObject(result);
            activity.displayResult(JsonResult.getString("status"),JsonResult.getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}
}
