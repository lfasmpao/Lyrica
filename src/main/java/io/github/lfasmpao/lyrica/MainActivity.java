package io.github.lfasmpao.lyrica;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lyrics_list_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DBHandler db = new DBHandler(this);
        List<Mutator> values =  db.getAllLyrics();
        final ArrayList<HashMap<String, String>> datas = new ArrayList<>();
        HashMap<String,String> item;
        for (Mutator value: values){
            item = new HashMap<>();
            item.put("id", value.getID());
            item.put("title", value.getTitle());
            item.put("artist", value.getArtist());
            datas.add(item);
        }
        adapter = new SimpleAdapter(
                this, datas,
                R.layout.content_list,
                new String[] { "title","artist" },
                new int[] {R.id.line_a, R.id.line_b});

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, String> itemValue = (HashMap<String, String>) listView.getItemAtPosition(i);
                Intent intent = new Intent(view.getContext(), ScrollingActivity.class);
                intent.putExtra("item", itemValue);
                startActivityForResult(intent, 0);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Created by: @lfasmpao", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
