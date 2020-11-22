package com.example.ced.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ced.R;
import com.example.ced.data.Record;

import java.util.ArrayList;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    // view에 보여줄 정보를 담고 있는 list
    private List<Record> list;

    // 리스너 객체 참조를 저장하는 변수
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    // OnItemClickListener 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

//    public interface OnItemLongClickListener {
//        void onItemLongClick(View v, int pos);
//    }
//
//    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
//        this.onItemLongClickListener = onItemLongClickListener;
//    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public RecordAdapter(List<Record> list) {
        this.list = list;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView recordTitle;
        public TextView recordDate;

        public ViewHolder(View view) {
            super(view);

            // 뷰 객체에 대한 참조. (hold strong reference)
            this.recordTitle = view.findViewById(R.id.recordTitle);
            this.recordDate = view.findViewById(R.id.recordDate);

            // 해당 뷰를 클릭 했을 때 일어나는 method
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();                // 해당 view의 position을 받음
                    if (position != RecyclerView.NO_POSITION)           // 해당 position가 유효하다면
                        onItemClickListener.onItemClick(v, position);   // item클릭 함수를 호출함(액티비티에서 정의)
                }
            });

//            view.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION)
//                        onItemLongClickListener.onItemLongClick(v, position);
//
//                    return true;
//                }
//            });
        }
    }

    @NonNull
    @Override
    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    public RecordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_record_item, parent, false);
        RecordAdapter.ViewHolder viewHolder = new ViewHolder(view);     // 해당 뷰를 만들어진 xml을 기반으로 구성하고 뷰홀더로 생성
        return viewHolder;                                              // 뷰홀더 리턴
    }

    @Override
    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recordTitle.setText(list.get(position).getTitle());      // 뷰 홀더 내부의 아이템뷰 Title을 재정의
        holder.recordDate.setText(list.get(position).getDate());        // 뷰 홀더 내부의 아이템뷰 Date을 재정의
    }

    @Override
    // getItemCount() - 전체 데이터 갯수 리턴.
    public int getItemCount() {
        return list.size();
    }

    // 해당 객체의 list 리턴
    public List<Record> getList() {
        return list;
    }

    // 해당 position의 Record 리턴
    public Record getRecord(int position) {
        return list.get(position);
    }

    // Record를 list에 추가함
    public void addRecord(Record record) {
        list.add(0, record);
    }

    // 해당 postion의 Record 삭제
    public void removeRecord(int position) {
        list.remove(position);
    }
}