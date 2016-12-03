package app.uteach;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void onTutor (View view) {
        Intent intent = new Intent(this, TutorActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button tutor = (Button) findViewById(R.id.tutor);

        // Instantiate the RequestQueue.


    }
}
