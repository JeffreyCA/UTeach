package app.uteach;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {
    TextView name;
    TextView info;
    TextView profession;
    TextView email;
    TextView phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = (TextView) findViewById(R.id.name_info);
        info = (TextView) findViewById(R.id.info);
        profession= (TextView) findViewById(R.id.prof);
        email = (TextView) findViewById(R.id.email_info);
        phone = (TextView) findViewById(R.id.phone_info);

        try {
            JSONObject tutor = new JSONObject(getIntent().getStringExtra("TUTOR"));
            name.setText(tutor.getString("name"));
            info.setText(tutor.getString("info"));
            profession.setText(tutor.getString("profession"));
            email.setText(tutor.getString("email"));
            phone.setText(tutor.getString("phone"));

            Log.i("NAME", tutor.getString("name").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
