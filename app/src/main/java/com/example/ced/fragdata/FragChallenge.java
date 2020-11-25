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
    private TextView UserID;
    private TextView UserTime;
    private TextView UserRank;
    private ServiceApi service;
    private String Time;
    private String Name;
   // private String Rank;
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
        Time = getArguments().getString("Time");

        UserID.setText(Name);
        UserTime.setText(Time);

        rankListView.setLayoutManager(new LinearLayoutManager(getActivity())); // 리사이클러뷰의 매니저 할당

        adapter = new RankAdapter(new ArrayList<RankData>());
        rankListView.setAdapter(adapter);
        renewRank();

        renewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // renewTime(new RankRequest(Name, changeTime(Time)));
                renewRank();
            }
        });
        return view;
    }

    /* DB에 시간 넣는 함수 */
    public void renewTime(RankRequest data) {
        service.renewalRank(data).enqueue(new Callback<CodeResponse>() {    // renewalRank에 data 인큐
            @Override
            public void onResponse(Call<CodeResponse> call, Response<CodeResponse> response) {
                CodeResponse code = response.body();
                if (code.getCode() == 200) {
                    Toast.makeText(getActivity(), "저장에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CodeResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "통신 오류 발생", Toast.LENGTH_SHORT).show();
                Log.e("통신 오류 발생", t.getMessage());
            }
        });
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
                    inputList.add(new RankData(i + 1, result.get(i).getUserid(), result.get(i).getWeekly())); // 순위, 아이디, 시간 리스트에 저장

                    if((result.get(i).getUserid()).equals(getArguments().getString("UserId"))) {
                        String tmp = Integer.toString(i + 1);
                        UserRank.setText(tmp);
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

    /* 시간 int로 바꾸고 누적하는 함수 */
    public int changeTime(String time){
        int hour, min, sec, result;

        String[] Time = time.split(":");
        hour = Integer.parseInt(Time[0]);
        min = Integer.parseInt(Time[1]);
        sec = Integer.parseInt(Time[2]);

        String tempHour=Integer.toString(hour);
        String tempMin;
        if(min<10){
            tempMin="0"+Integer.toString(min);
        }else if(min==0) {
            tempMin="00";
        }else{
            tempMin=Integer.toString(min);
        }
        String tempSec;
        if(min<10){
            tempSec="0"+Integer.toString(sec);
        }else if(sec==0) {
            tempSec="00";
        }else{
            tempSec=Integer.toString(sec);
        }
        result = Integer.parseInt(tempHour+tempMin+tempSec);
        return result;
    }
}
