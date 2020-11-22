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
        setContentView(R.layout.activity_recordlist);                           // xml, java 연결

        recordAddButton = findViewById(R.id.recordaddbtn);
        recordBackButton = findViewById(R.id.recordbackbtn);
        recordListView = findViewById(R.id.recordlist);                         // xml의 컴포넌트와 각각 연결

        if (context_record == null) {
            context_record = this;                                              // static 변수로 현재 액티비티를 담는다.
        }                                                                       // 해당 변수는 write 액티비티에서 사용예정

        userId = getIntent().getStringExtra("UserID");                      // intent를 통해 넘어온 데이터를 변수에 할당해줌

        recordListView.setLayoutManager(new LinearLayoutManager(this)); // 리사이클러뷰의 매니저 할당

        adapter = new RecordAdapter(loadRecord());                              // 파일에서 읽어온 List를 어뎁터에 담아서 만들어줌
        recordListView.setAdapter(adapter);                                     // 해당 어뎁터를 List와 연결
                                                                                // 연결된 어뎁터를 기반으로 ListView에 출력
        recordAddButton.setOnClickListener(new View.OnClickListener() {         // Write 버튼
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(intent);                                          // Write 액티비티로 넘어감
            }
        });

        recordBackButton.setOnClickListener(new View.OnClickListener() {        // 뒤로가기 버튼
            @Override
            public void onClick(View v) {
                finish();
            }                           // 해당 액티비티를 끝냄
        });

        adapter.setOnItemClickListener(new RecordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {                     // 리사이클러뷰에 있는 viewholder를 터치할 시 할 method
                Bundle bundle = new Bundle();
                bundle.putString("title", adapter.getRecord(position).getTitle());
                bundle.putString("date",adapter.getRecord(position).getDate());
                bundle.putString("content", adapter.getRecord(position).getContent());
                                                                                // 해당 정보를 Bundle에 담고
                Intent intent = new Intent(context_record, RecordActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);                                          // Record 액티비티로 Bundle의 정보와 함께 넘어감
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

    public List<Record> loadRecord() {                                      // 파일을 읽어서 List로 변환해주는 함수
        List<Record> record = new ArrayList<Record>();
        FileInputStream fileInputStream = null;
        StringBuffer stringBuffer = new StringBuffer();
        String json = null;                                                 // 해당 필요한 변수

        try {
            fileInputStream = openFileInput(userId + "_record.json"); // 해당 이름의 파일을 읽음(없으면 null 반환)

            int bufSize = fileInputStream.available();                      // 파일의 바이트 크기만큼 설정 후
            byte[] buf = new byte[bufSize];                                 // 바이트 정보를 담을 배열 생성

            fileInputStream.read(buf);                                      // 해당 읽은 바이트 정보를 배열에 저장

            json = new String(buf);                                         // 바이트 정보를 문자열로 변환
            if (fileInputStream != null)                                    // 읽은 후 Stream이 존재한다면
                fileInputStream.close();                                    // 해당 Stream을 close 해줌
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

        if (json == null)                                           // 만약 읽은 정보가 없다면 자동적으로 
            return record;                                          // String은 null을 나타내므로 반환

        JsonParser Parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) Parser.parse(json);
        JsonArray array = (JsonArray) jsonObject.get("record");     // 읽은 정보를 array에 담아줌

        for (int i = 0; i < array.size(); i++) {                    // JsonObject를 List에 넣어주기 위해 반복
            jsonObject = (JsonObject) array.get(i);                 // array의 한 객체를 담아서 list에 넣어줌(String 변환 필수)
            record.add(new Record(jsonObject.get("title").getAsString(), jsonObject.get("date").getAsString(), jsonObject.get("content").getAsString()));
        }

        return record;                                              // 해당 list 반환
    }

    public void saveRecord(Record saveData) {
//        if(saveData != null) {
            adapter.addRecord(saveData);                            // 어뎁터 내부의 List에 새로운 Record 추가
//        }

        FileOutputStream fileOutputStream = null;
        Gson gson = new Gson();
        String newJson = gson.toJson(new RecordList(adapter.getList()));    // 해당 어뎁터의 List를 기반으로 파일 재작성
                                                                            // List를 Json String 형으로 변환
        try {
            fileOutputStream = openFileOutput(userId + "_record.json", Context.MODE_PRIVATE);
            fileOutputStream.write(newJson.getBytes());                     // 문자열을 byte열로 변환뒤 파일에 출력

            if (fileOutputStream != null)                                   // Stream이 존재한다면
                fileOutputStream.close();                                   // Stream close
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

//        if(saveData != null)
            adapter.notifyItemRangeChanged(0, adapter.getItemCount());  // List가 변경되었으므로 어뎁터 갱신
//        else
//            adapter.notifyDataSetChanged();
    }
}