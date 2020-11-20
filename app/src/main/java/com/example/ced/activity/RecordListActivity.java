package com.example.ced.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

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

public class RecordListActivity extends AppCompatActivity {

    private ImageButton recordAddButton;
    private ImageButton recordBackButton;
    private RecyclerView recordListView;
    private String userId;
    public static Context context_record;
    public static RecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordlist);

        recordAddButton = findViewById(R.id.recordaddbtn);
        recordBackButton = findViewById(R.id.recordbackbtn);
        recordListView = findViewById(R.id.recordlist);

        if (context_record == null) {
            context_record = this;
        }

        userId = getIntent().getStringExtra("UserID");

        recordListView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecordAdapter(loadRecord());
        recordListView.setAdapter(adapter);

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

        adapter.setOnItemClickListener(new RecordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("title", adapter.getRecord(position).getTitle());
                bundle.putString("date",adapter.getRecord(position).getDate());
                bundle.putString("content", adapter.getRecord(position).getContent());

                Intent intent = new Intent(context_record, RecordActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

//        adapter.setOnItemLongClickListener(new RecordAdapter.OnItemLongClickListener() {
//            @Override
//            public void onItemLongClick(View v, int position) {
//
//                if(! ((Activity)context_record).isFinishing()) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(context_record);
//
//                    builder.setTitle("기록 삭제");
//                    builder.setMessage("선택한 기록을 삭제하시겠습니까?");
//                    builder.setPositiveButton("삭제", new DialogInterface.OnClickListener()
//                    {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which)
//                        {
//                            Toast.makeText(context_record, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
//                            adapter.removeRecord(position);
//                            saveRecord(null);
//                            adapter.notifyItemRangeChanged(0,adapter.getItemCount());
//                        }
//                    });
//                    builder.setNegativeButton("취소",null);
//                    builder.show();
//                }
//            }
//        });
    }

    public List<Record> loadRecord() {
        List<Record> record = new ArrayList<Record>();
        FileInputStream fileInputStream = null;
        StringBuffer stringBuffer = new StringBuffer();
        String json = null;

        try {
            fileInputStream = openFileInput(userId + "_record.json");

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
//        if(saveData != null) {
            adapter.addRecord(saveData);
//        }

        FileOutputStream fileOutputStream = null;
        Gson gson = new Gson();
        String newJson = gson.toJson(new RecordList(adapter.getList()));

        try {
            fileOutputStream = openFileOutput(userId + "_record.json", Context.MODE_PRIVATE);
            fileOutputStream.write(newJson.getBytes());

            if (fileOutputStream != null)
                fileOutputStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

//        if(saveData != null)
            adapter.notifyItemRangeChanged(0, adapter.getItemCount());
//        else
//            adapter.notifyDataSetChanged();
    }
}