package app.uteach;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StudentActivity extends AppCompatActivity {
    Spinner location;
    Spinner profession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main);
        location = (Spinner) findViewById(R.id.Student_Location);
        profession = (Spinner) findViewById(R.id.What_you_look_for);
    }
    public void onSearch(View v) {
        String url ="http://91.240.86.47:9000/find";

            Map<String, String> params = new HashMap();
            params.put("location", location.getSelectedItem().toString());
            params.put("profession", profession.getSelectedItem().toString());
            JSONObject parameters = new JSONObject(params);

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("TAG", response.toString());
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    //Log.i("TAG", response.toString());
                    //TODO: handle failure
                }
            });

            Volley.newRequestQueue(this).add(jsonRequest);

    }
}
