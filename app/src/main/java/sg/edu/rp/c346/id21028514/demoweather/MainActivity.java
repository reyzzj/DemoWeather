package sg.edu.rp.c346.id21028514.demoweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    AsyncHttpClient client;
    ListView lvWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvWeather = findViewById(R.id.lvWeather);
        client = new AsyncHttpClient();
    }
    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Weather> alWeather = new ArrayList<Weather>();

        Log.d("onResume", "onResume");

        client.get("https://api.data.gov.sg/v1/environment/2-hour-weather-forecast", new JsonHttpResponseHandler() {

            String area;
            String forecast;

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Log.d("onSuccess", response.toString());


                try {
                    JSONArray jsonArrItems = response.getJSONArray("items");
                    JSONObject firstObj = jsonArrItems.getJSONObject(0);
                    JSONArray jsonArrForecasts = firstObj.getJSONArray("forecasts");
                    for(int i = 0; i < jsonArrForecasts.length(); i++) {
                        JSONObject jsonObjForecast = jsonArrForecasts.getJSONObject(i);
                        area = jsonObjForecast.getString("area");
                        forecast = jsonObjForecast.getString("forecast");
                        Weather weather = new Weather(area, forecast);
                        alWeather.add(weather);
                    }
                }
                catch(JSONException e){
                    Log.d("exception: ", e.toString());
                }

                ArrayAdapter<Weather> aaWeather = new ArrayAdapter<Weather>(MainActivity.this, android.R.layout.simple_list_item_1, alWeather );
                lvWeather.setAdapter(aaWeather);
            }

            //            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
//
//                Log.d("onSuccess", response.toString());
//
//                try {
//                    JSONArray jsonArrItems = response.getJSONArray("items");
//                    JSONObject firstObj = jsonArrItems.getJSONObject(0);
//                    JSONArray jsonArrForecasts = firstObj.getJSONArray("forecasts");
//                    for(int i = 0; i < jsonArrForecasts.length(); i++) {
//                        JSONObject jsonObjForecast = jsonArrForecasts.getJSONObject(i);
//                        area = jsonObjForecast.getString("area");
//                        forecast = jsonObjForecast.getString("forecast");
//                        Weather weather = new Weather(area, forecast);
//                        alWeather.add(weather);
//                    }
//                }
//                catch(JSONException e){
//                    Log.d("exception: ", e.toString());
//
//                }
//
//                //POINT X – Code to display List View
//                ArrayAdapter<Weather> aaWeather = new ArrayAdapter<Weather>(MainActivity.this, android.R.layout.simple_list_item_1, alWeather );
//                lvWeather.setAdapter(aaWeather);
//
//
//            }//end onSuccess

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.e("error", responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.e("error", throwable.toString());
                Log.e("statuscode", statusCode+"");

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.e("error", errorResponse.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);

                Log.e("onSuccess", responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("onSuccess", response.toString());
            }
        });
    }//end onResume


}