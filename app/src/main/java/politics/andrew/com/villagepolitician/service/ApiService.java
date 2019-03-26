package politics.andrew.com.villagepolitician.service;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import politics.andrew.com.villagepolitician.interfacevo.Congressman;
import politics.andrew.com.villagepolitician.interfacevo.NaverNewsSearch;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @File : ApiService
 * @Date : 2018. 10. 16. AM 10:56
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 정치인 정보 API 연동 서비스
**/
public class ApiService {

    //private String eye2webHost = "http://www.eye2web.co.kr";
    private String naverHost = "https://openapi.naver.com";
    //private Congressman congressman;
    //private ArrayList<Congressman> congressmanList;
    private NaverNewsSearch naverNewsSearch;
    private NaverNewsSearch  naverNewsInfo;

    private Retrofit client = new Retrofit.Builder().baseUrl(naverHost).addConverterFactory(GsonConverterFactory.create()).build();    // json type data 연동을 위한 Retrofit client 생성

    /**
     * @File : getNaverNewsSearchList
     * @Date : 2019-03-25 오후 1:56
     * @Author : Andrew Kim
     * @Description : 네이버 뉴스 검색 결과 리스트 가져오기
    **/
    public NaverNewsSearch getNaverNewsSearchList(String query, int display, int start, String sort) {
        try {
            NaverNewsSearch.NaverSearchInterface service = client.create(NaverNewsSearch.NaverSearchInterface.class);
            Call<NaverNewsSearch> call = ((NaverNewsSearch.NaverSearchInterface) service).get_news_info(query, display, start, sort);

            naverNewsInfo = call.execute().body();
        } catch(Exception e) {
            Log.e("Error", "Naver News Search API Call Error : " + e.toString());
        }

        return naverNewsInfo;
    }
    /**
    public String test(String apiKey) {
        congressman = new Congressman();

        congressman = getCongressman("1", 0, 1, "name_kr", "asc", "", apiKey);
        Log.e("Error", "Congressman Info : " + congressman.getName_kr());
        return congressman.getName_kr();
    }
     **/

    /**
     * @File : getCongressmman
     * @Date : 2019-02-28 오후 12:20
     * @Author : Andrew Kim
     * @Description : 국회의원 정보 가져오기 (1명의 정보 return)
    **/
    /**
    public Congressman getCongressman(String no, int page, int per_page, String sortQuery,  String sort, String queryWord, String apiKey) {
        try {
            Congressman.CongressmanListInterface service = client.create(Congressman.CongressmanListInterface.class);
            Call<ArrayList<Congressman>> call = ((Congressman.CongressmanInfoInterface) service).get_congressman_info(no, page, per_page, sortQuery, sort, queryWord, apiKey);

            List<Congressman> tmpCongressMan = call.execute().body();
            congressman = tmpCongressMan.get(0);
        } catch(Exception e) {
            Log.e("Error", "Eye2Web Congressman Api Call Error" + e.toString());
        }

        return congressman;
    }
     **/

    /**
     * @File : getCongressmanList
     * @Date : 2019-02-28 오후 12:21
     * @Author : Andrew Kim
     * @Description : 국회의원 정보 가져오기 (국회의원 목록 return)
    **/
    /**
    public ArrayList<Congressman> getContressmanList(int page, int per_page, String sortQuery, String sort, String queryWord, String apiKey) {
        try {
            Congressman.CongressmanListInterface service = client.create(Congressman.CongressmanListInterface.class);
            Call<ArrayList<Congressman>> call = service.get_congressman_list(page, per_page, sortQuery, sort, queryWord, apiKey);

            congressmanList = call.execute().body();
        } catch(Exception e) {
            Log.e("Error", "Eye2Web Congressman Api Call Error" + e.toString());
        }

        return congressmanList;
    }
     **/
}
