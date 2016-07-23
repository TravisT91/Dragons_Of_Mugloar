package dragonsofmugloar.dragons_of_mugloar;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Travis on 7/21/2016.
 */
public class getGame extends AsyncTask<String, Void, String> {
    knight mKnight;
    game mGame;
    String mWeather;
    startNewGame activity;
    String ApiUrl = "http://www.dragonsofmugloar.com";
    String GameURL = ApiUrl + "/api/game";
    String WeatherURL = ApiUrl + "/weather/api/report/";

    getGame(startNewGame activity){
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
            String gameIdAndKnight = "";
            String weatherResponse = "";
            try {
                gameIdAndKnight = requestGame();
                makeGame(gameIdAndKnight);
                weatherResponse = requestWeather(mGame.getID());
                makeWeather(weatherResponse);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return gameIdAndKnight;
        }

    private void makeWeather(String weatherResponse) {
        XMLParser parser = new XMLParser();
        Document doc = parser.getDomElement(weatherResponse); // getting DOM element
        NodeList nl = doc.getElementsByTagName("message");
        Element e = (Element) nl.item(0);
        mWeather = parser.getElementValue(e);
    }

    private void makeGame(String gameIdAndKnight) throws JSONException {
        JSONObject response = new JSONObject(gameIdAndKnight);
        int gameID = response.getInt("gameId");
        JSONObject knight = response.getJSONObject("knight");
        String name = knight.getString("name");
        int attack = knight.getInt("attack");
        int armor = knight.getInt("armor");
        int agility = knight.getInt("agility");
        int endurance = knight.getInt("endurance");
        mKnight = new knight(name,attack,armor,agility,endurance);
        mGame = new game(gameID,mKnight);
        startNewGame.mGameId = gameID;
    }

    @Override
        protected void onPostExecute(String result) {
                    activity.displayKnight(mKnight);
                    activity.displayWeather(mWeather);
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}

        private String requestGame() throws IOException {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(GameURL)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response.body().string();
        }

        private String requestWeather(int gameId) throws IOException {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url(WeatherURL+gameId)
                .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
            e.printStackTrace();
            }
            return response.body().string();
    }
}
