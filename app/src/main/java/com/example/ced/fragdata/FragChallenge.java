package com.example.ced.fragdata;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ced.R;
import com.example.ced.adapter.RankAdapter;
import com.example.ced.adapter.RecordAdapter;
import com.example.ced.data.CodeResponse;
import com.example.ced.data.RankData;
import com.example.ced.data.RankRequest;
import com.example.ced.data.RankResponse;
import com.example.ced.data.RankUser;
import com.example.ced.data.UserRank;
import com.example.ced.network.RetrofitClient;
import com.example.ced.network.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragChallenge extends Fragment {
    private View view;
    private ImageButton renewBtn;
    private TextView UserRank;
    private TextView UserID;
    private TextView UserTime;
    private ServiceApi service;
    private String Name;
    private String ID;
    private String Time;
    private String Rank;
    private RecyclerView rankListView;
    private RankAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_challenge, container, false);
        renewBtn = view.findViewById(R.id.renew);
        UserRank = view.findViewById(R.id.userRank);
        UserID = view.findViewById(R.id.userId);
        UserTime = view.findViewById(R.id.userTime);
        service = RetrofitClient.getClient().create(ServiceApi.class);
        rankListView = view.findViewById(R.id.challengeRecyclerView);

        Name = getArguments().getString("UserName") + "님";
        ID = getArguments().getString("UserID");
        //Time = getArguments().getString("Time");

        UserID.setText(Name);
        //UserTime.setText(Time);

        rankListView.setLayoutManager(new LinearLayoutManager(getActivity())); // 리사이클러뷰의 매니저 할당
        adapter = new RankAdapter(new ArrayList<RankData>());
        rankListView.setAdapter(adapter);

        renewRank();

        renewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //renewTime(new RankRequest(Name, changeTime(Time)));
                //renewRank();
                //renewMine(Name);
            }
        });
        return view;
    }

    /* DB에서 리스트 불러오는 함수 */
    public void renewRank() {
        service.getRank().enqueue(new Callback<RankResponse>() {
            @Override
            public void onResponse(Call<RankResponse> call, Response<RankResponse> response) {
                RankResponse user = response.body();
                List<UserRank> result = user.getResult();   // result에 값 넣기
                List<RankData> inputList = new ArrayList<RankData>();   // inputList에 랭크 넣기
                //리스트에 출력하기
                for(int i = 0; i< result.size(); i++) {
                    int listTime = result.get(i).getWeekly();
                    inputList.add(new RankData(i + 1, result.get(i).getUserid(), changeTime(listTime))); // 순위, 아이디, 시간 리스트에 저장

                    if((result.get(i).getUserid()).equals(getArguments().getString("UserName"))) {
                        String tmp = Integer.toString(i + 1);
                        UserRank.setText(tmp);

                        Time = changeTime(result.get(i).getWeekly());
                        UserTime.setText(Time);
                    }
                }
                adapter.setList(inputList); // adapter의 setList함수로 inputList넘겨줌
            }

            @Override
            public void onFailure(Call<RankResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "통신 오류 발생", Toast.LENGTH_SHORT).show();
                Log.e("통신 오류 발생", t.getMessage());
            }
        });
    }

    public String changeTime(int time){
        int hour = time/10000; //시간
        int min = time/100 - hour*100; // (시간+분) - 시간
        int sec = time - (time/100)*100; // 전체시간 - (시간+분)
        return String.format("%d:%02d:%02d", hour, min, sec);
    }
}