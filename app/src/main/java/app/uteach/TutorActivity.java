package app.uteach;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TutorActivity extends AppCompatActivity {
    EditText name;
    EditText info;
    EditText email;
    EditText phone;
    Button send;
    Spinner location;
    Spinner profession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (EditText) findViewById(R.id.username);
        info = (EditText) findViewById(R.id.description);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        send = (Button) findViewById(R.id.sendbutton);
        location = (Spinner) findViewById(R.id.Location);
        profession = (Spinner) findViewById(R.id.profession);
    }

    public void onSend (View v) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://91.240.86.47:9000/create";
        try {
            final JSONObject jsonBody = new JSONObject("{\"type\":\"example\"}");
            Map<String, String> params = new HashMap();
            params.put("name", name.getText().toString());
            params.put("location", location.getSelectedItem().toString());
            params.put("profession", profession.getSelectedItem().toString());
            params.put("info", info.getText().toString());
            params.put("email", email.getText().toString());
            params.put("phone", phone.getText().toString());
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
        } catch (JSONException e) {
            e.printStackTrace();
            //Log.i("TAG", response.toString());

        }
        Intent intent = new Intent(this, Success.class);
        startActivity(intent);
    }
}
