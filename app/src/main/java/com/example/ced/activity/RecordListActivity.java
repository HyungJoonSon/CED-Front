package com.example.ced.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.ced.R;
import com.example.ced.adapter.RecordAdapter;
import com.example.ced.data.Record;
import com.example.ced.data.RecordList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecordListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ImageButton recordAddButton;
    private ImageButton recordBackButton;
    private ListView recordlist;
    private List<Record> record;
    private String userid;
    public static Context context_record;
    public static RecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordlist);

        recordAddButton = findViewById(R.id.recordaddbtn);
        recordBackButton = findViewById(R.id.recordbackbtn);
        recordlist = findViewById(R.id.recordlist);
        if (context_record == null) {
            context_record = this;
        }
        userid = getIntent().getStringExtra("UserID");
        record = loadRecord();


        recordAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(intent); //다음화면으로 넘어감
            }
        });

        recordBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new RecordAdapter(this, record);
        recordlist.setAdapter(adapter);

        recordlist.setOnItemClickListener(this);
    }

    public List<Record> loadRecord() {
        List<Record> record = new ArrayList<Record>();
        FileInputStream fileInputStream = null;
        StringBuffer stringBuffer = new StringBuffer();
        String json = null;

        try {
            fileInputStream = openFileInput(userid + "_record.json");

            int bufSize = fileInputStream.available();
            byte[] buf = new byte[bufSize];

            fileInputStream.read(buf);

            json = new String(buf);
            if (fileInputStream != null)
                fileInputStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

        if (json == null)
            return record;

        JsonParser Parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) Parser.parse(json);
        JsonArray array = (JsonArray) jsonObject.get("record");

        for (int i = 0; i < array.size(); i++) {
            jsonObject = (JsonObject) array.get(i);
            record.add(new Record(jsonObject.get("title").getAsString(), jsonObject.get("date").getAsString(), jsonObject.get("content").getAsString()));
        }

        return record;
    }

    public void saveRecord(Record saveData) {
        record.add(0, saveData);
        FileOutputStream fileOutputStream = null;
        Gson gson = new Gson();
        String newJson = gson.toJson(new RecordList(record));

        try {
            fileOutputStream = openFileOutput(userid + "_record.json", Context.MODE_PRIVATE);
            fileOutputStream.write(newJson.getBytes());

            if (fileOutputStream != null)
                fileOutputStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Record data = record.get(position);

        Bundle bundle = new Bundle();
        bundle.putString("title", data.getTitle());
        bundle.putString("date", data.getDate());
        bundle.putString("content", data.getContent());

        Intent intent = new Intent(this, RecordActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}