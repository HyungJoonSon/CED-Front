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
import com.example.ced.data.RankData;
import com.example.ced.data.RankListResponse;
import com.example.ced.data.RankUserResponse;
import com.example.ced.data.RankIndexResponse;
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
    private TextView UserRankView;
    private TextView UserNameView;
    private TextView UserTimeView;
    private RecyclerView rankListView;
    private RankAdapter adapter;
    private String userName;
    private String userTime;
    private String userRank;
    private ServiceApi service;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_challenge, container, false);                   // xml과 java 소스 연결

        service = RetrofitClient.getClient().create(ServiceApi.class);      // 통신을 위한 ServiceApi 생성

        renewBtn = view.findViewById(R.id.renew);
        UserRankView = view.findViewById(R.id.userRank);
        UserNameView = view.findViewById(R.id.userId);
        UserTimeView = view.findViewById(R.id.userTime);
        rankListView = view.findViewById(R.id.challengeRecyclerView);       // xml의 컴포넌트와 연결

        userName = getArguments().getString("UserName");            // 전 액티비티에서 UserName에 해당하는 문자열을 받아서 초기화
        getUserRanking(getArguments().getString("UserID"));         // 전 액티비티에서 UserID를 받아서 User 정보를 조회

        rankListView.setLayoutManager(new LinearLayoutManager(getActivity()));  // 리사이클러뷰의 매니저 할당
        adapter = new RankAdapter(new ArrayList<RankData>());                   // 현재 비어있는 List를 어뎁터에 담아서 만들어 줌
        rankListView.setAdapter(adapter);                                       // 해당 어뎁터를 List와 연결
        // 연결된 어뎁터를 기반으로 RecyclerView에 출력
        getListRanking();                                               // 어뎁터를 갱신해주는 함수

        renewBtn.setOnClickListener(new View.OnClickListener() {        // update 버튼
            @Override
            public void onClick(View v) {
                getListRanking();                                       // 정보를 다시만들어와서 어뎁터를 갱신해줌
            }
        });
        return view;
    }

    public void getListRanking() {                                      // 랭킹 List를 받아오는 함수
        service.getListRank().enqueue(new Callback<RankListResponse>() {
            @Override
            public void onResponse(Call<RankListResponse> call, Response<RankListResponse> response) {
                RankListResponse user = response.body();                // 받아온 정보를 응답 객체에 넣어줌
                List<RankIndexResponse> result = user.getResult();      // 객체에서 결과에 해당하는 result를 임시 객체에 넣어줌
                List<RankData> inputList = new ArrayList<RankData>();          // 랭크가 없어서 inputList에 랭크와 함께 정보를 넣음

                for (int i = 0; i < result.size(); i++) {               // 랭크를 같이 넣는 반복문(순위, 아이디, 시간을 리스트에 저장)
                    inputList.add(new RankData(i + 1, result.get(i).getUserName(), changeUITime(result.get(i).getWeekly())));
                }
                adapter.setList(inputList);                             // adapter의 setList함수로 inputList 넘겨주는 동시에 갱신(class 참조 바람)
            }

            @Override
            public void onFailure(Call<RankListResponse> call, Throwable t) {   // 통신 오류가 생길 시 메시지, System 메시지 출력
                Toast.makeText(getActivity(), "통신 오류 발생", Toast.LENGTH_SHORT).show();
                Log.e("통신 오류 발생", t.getMessage());
            }
        });
    }

    public void getUserRanking(String UserID) {                         // 유저의 랭킹을 받아오는 함수
        service.getUserRank(UserID).enqueue(new Callback<RankUserResponse>() {
            @Override
            public void onResponse(Call<RankUserResponse> call, Response<RankUserResponse> response) {
                RankUserResponse tempRank = response.body();                // 받아온 정보를 응답 객체에 넣어줌
                userRank = Integer.toString(tempRank.getUserRank());        // 정보를 변수에 넣어줌
                userTime = changeUITime(tempRank.getTime());                // 정보를 변수에 넣어줌

                UserRankView.setText(userRank);                             // 받아온 정보를 기반으로 TextView 갱신
                UserNameView.setText(userName);                             // 받아온 정보를 기반으로 TextView 갱신
                UserTimeView.setText(userTime);                             // 받아온 정보를 기반으로 TextView 갱신
            }

            @Override
            public void onFailure(Call<RankUserResponse> call, Throwable t) {   // 통신 오류가 생길 시 메시지, System 메시지 출력
                Toast.makeText(getActivity(), "통신 오류 발생", Toast.LENGTH_SHORT).show();
                Log.e("통신 오류 발생", t.getMessage());
            }
        });
    }

    public String changeUITime(int time) {                      // 유저의 정보를 받아오는 함수
        int tempTime = time;                                    // 시간을 받아옴

        int hour = tempTime / 3600;                             // 3600으로 나누면 '시'가 나옴
        tempTime = tempTime % 3600;                             // 시간의 정보를 뺌

        int min = tempTime / 60;                                // 60으로 나오면 '분'이 나옴
        tempTime = tempTime % 60;                               // 시간의 정보를 뺌

        int sec = tempTime;                                     // 마지막 시간은 자동으로 '초'가 됨

        return String.format("%d:%02d:%02d", hour, min, sec);   // 해당 시간을 String으로 바꿔줌(형태 지정)
    }
}