package app.uteach;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.uteach.R;

public class SearchActivity extends AppCompatActivity {

    JSONArray results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);
        try {
            results = new JSONArray(getIntent().getStringExtra("SEARCH_RESULTS"));
            final ListView listview = (ListView) findViewById(R.id.listview);
            final ArrayList<String> list = new ArrayList<String>();
            for (int i =0; i<results.length(); i++){
                list.add(results.getJSONObject(i).getString("name"));
            }
            final StableArrayAdapter adapter = new StableArrayAdapter(this,
                    android.R.layout.simple_list_item_1, list);
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view,
                                        int position, long id) {
                    Intent intent = new Intent(this, ProfileActivity.class);
                    try {
                        intent.putExtra("TUTOR", results.getJSONObject(position).toString());
                        startActivity(intent);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }

            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

}
