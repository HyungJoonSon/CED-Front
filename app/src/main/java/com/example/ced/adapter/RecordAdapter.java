package com.example.ced.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ced.R;
import com.example.ced.data.Record;

import java.util.List;

public class RecordAdapter extends ArrayAdapter<Record> {

    public RecordAdapter(Context context, List<Record> resource) {
        super(context, 0, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {                  // 기존 뷰가 재사용되는지 확인하고 그렇지 않으면 뷰를 추가한다.
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_record_item, parent, false);
        }

        Record record = getItem(position);          // 해당 위치의 정보를 들고온다.

        // Text 뷰를 들고와서 리스트 뷰에 넣기 위해 해당 Id 값을 가진 요소를 들고온다.
        TextView recordTitle = (TextView) convertView.findViewById(R.id.recordTitle);
        TextView recordDate = (TextView) convertView.findViewById(R.id.recordDate);

        recordTitle.setText(record.getTitle());     // 해당 뷰의 Text를 바꿔준다.
        recordDate.setText(record.getDate());       // 해당 뷰의 Text를 바꿔준다.

        return convertView;                         // 만들어진 뷰를 반환하여 화면에 반영한다.
    }
}
