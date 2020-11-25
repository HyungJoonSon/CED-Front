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

    // RecyclerView는 내부에 List를 만들어 두지 않으므로 List를 생성해준다.
    List<RankData> list;

    // 생성자
    public RankAdapter(List<RankData> list) {
        this.list = list;
    }

    // ListView와 다르게 ViewHolder 내부에 View가 존재하게 된다.
    // View와 ViewHolder는 같다고 보는게 편하다.
    // 내부 클래스로 ViewHolder의 생성자가 있는 것을 볼 수 있다.
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView userRank;
        public TextView userId;
        public TextView userTime;

        public ViewHolder(View view) {
            super(view);

            // 뷰 객체에 대한 참조. (hold strong reference)
            this.userRank = view.findViewById(R.id.rankView);
            this.userId = view.findViewById(R.id.rankViewId);
            this.userTime = view.findViewById(R.id.rankViewTime);
        }
    }

    @NonNull
    @Override
    // RecycleView를 만들 때는 초기에 ViewHolder를 만들어 준다.
    // 해당 ViewHolder는 스크롤이 되면서 보이게 되지 않는 이상,
    // 함수를 사용하지 않으면 내용이 바뀌지 않는다.
    public RankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_challenge_item, parent, false);
        RankAdapter.ViewHolder viewHolder = new RankAdapter.ViewHolder(view);       // 해당 뷰를 만들어진 xml을 기반으로 구성하고 뷰홀더로 생성
        return viewHolder;                                                          // 뷰홀더 리턴
    }

    @Override
    // 만약 List의 크기가 커서 스크롤이 생긴하면
    // ListView와는 다르게 사라진 ViewHolder를 삭제하지 않고
    // 내용만 수정해서 밑에 만든다.
    // 즉, 객체가 사라지지 않고 setter를 통해서 내용이 바뀐 뒤 재사용 된다.
    // 다른말로는 ListView는 삭제와 생성을 반복하고, RecycleView는 수정이 반복된다고 보면 된다.
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.userRank.setText(Integer.toString(list.get(position).getRank()));              // 뷰 홀더 내부의 아이템뷰 랭킹을 재정의
        holder.userId.setText(list.get(position).getUserId());                                // 뷰 홀더 내부의 아이템뷰 아이디을 재정의
        holder.userTime.setText(Integer.toString(list.get(position).getTime()));              // 뷰 홀더 내부의 아이템뷰 시간을 재정의
    }

    @Override
    // 현재 List 내부의 항목 갯수를 반환한다.
    public int getItemCount() {
        return list.size();
    }

    // 이 함수는 Rank를 리프레쉬할 때 사용하는 함수로, List의 내용이 바뀌는 동시에
    // 보여지는 List도 갱신된다.
    // adapter를 갱신하므로써 보여지는 레이아웃을 갱신한다.
    // RecycleView를 정보가 바뀔 때 자동 갱신이 안되므로
    // 수동으로 해줘서 내용을 바꿔준다고 생각하면 쉽다.
    public void setList(List<RankData> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}