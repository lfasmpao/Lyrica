package io.github.lfasmpao.lyrica;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import java.util.HashMap;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle bundle = getIntent().getExtras();
        HashMap<String, String> datas = (HashMap<String, String>) bundle.getSerializable("item");
        setTitle(datas.get("title"));
        TextView textView = findViewById(R.id.lyrics_view);
        DBHandler db = new DBHandler(this);
        Mutator data = db.getLyrics(datas.get("id")).get(0);
        textView.setText(data.getLyrics());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
