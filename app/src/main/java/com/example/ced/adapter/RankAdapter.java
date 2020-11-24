package com.example.ced.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ced.R;
import com.example.ced.data.RankData;

import java.util.List;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {

    List<RankData> list;    // 랭킹 리스트

    public RankAdapter(List<RankData> list) {
        this.list = list;
    }

    // 뷰를 홀더에 보관하는 객체 -> findViewId 호출을
    public class ViewHolder extends RecyclerView.ViewHolder {   // 리사이클러뷰 위에
        public TextView userRank;   // 순위
        public TextView userId; // 아이디
        public TextView userTime;   // 공부시간

        public ViewHolder(View view) {
            super(view);

            // 뷰 객체에 대한 참조. (hold strong reference)
            // view 내용을 업데이트 하기 위해 findViewById 매번 호출
            this.userRank = view.findViewById(R.id.rankView);
            this.userId = view.findViewById(R.id.rankViewId);
            this.userTime = view.findViewById(R.id.rankViewTime);
        }
    }

    @NonNull
    @Override
    public RankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_challenge_item, parent, false);
        RankAdapter.ViewHolder viewHolder = new RankAdapter.ViewHolder(view);       // 해당 뷰를 만들어진 xml을 기반으로 구성하고 뷰홀더로 생성
        return viewHolder;                                                          // 뷰홀더 리턴
    }

    /* 리사이클러뷰 사용할 때 스크롤뷰에서 사라진 위의 텍스트를 갱신해주기 위함(bind) */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.userRank.setText(Integer.toString(list.get(position).getRank()));              // 뷰 홀더 내부의 아이템뷰 랭킹을 재정의
        holder.userId.setText(list.get(position).getUserId());              // 뷰 홀더 내부의 아이템뷰 아이디을 재정의
        holder.userTime.setText(Integer.toString(list.get(position).getTime()));              // 뷰 홀더 내부의 아이템뷰 시간을 재정의
    }

    @Override
    public int getItemCount() {
        return list.size();
    }   // 리스트 수

    public void setList(List<RankData> list) {  // FragChalleng에서 List넘겨받음
        this.list = list;   // list에 저장
        notifyDataSetChanged(); // 리스트뷰 갱신
    }
}
