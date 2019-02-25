package politics.andrew.com.villagepolitician.service;

import android.util.Log;

import politics.andrew.com.villagepolitician.interfacevo.Congressman;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

/**
 * @File : ApiService
 * @Date : 2018. 10. 16. AM 10:56
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 정치인 정보 API 연동 서비스
**/
public class ApiService {

    private String eye2webHost = "http://www.eye2weeb.co.kr";
    private Congressman congressman;
    private List<Congressman> congressmanList;

    private Retrofit client = new Retrofit.Builder().baseUrl(eye2webHost).addConverterFactory(GsonConverterFactory.create()).build();

    public String test() {
        congressman = new Congressman();

        congressman = getCongressman(1, 1, "ehdus77!@");

        return congressman.getAides();
    }

    public Congressman getCongressman(int page,  int per_page, String apiKey) {
        try {
            Congressman.CongressmanInterface service = client.create(Congressman.CongressmanInterface.class);
            Call<Congressman> call = service.get_congressman(page, per_page, apiKey);

            congressman = call.execute().body();
        } catch(Exception e) {
            Log.e("Error", "Eye2Web Congressman Api Call Error" + e.toString());
        }

        return congressman;
    }

    public List<Congressman> getContressmanList(int page, int per_page, String apiKey) {
        try {
            Congressman.CongressmanListInterface service = client.create(Congressman.CongressmanListInterface.class);
            Call<List<Congressman>> call = service.get_congressman_list(page, per_page, apiKey);

            congressmanList = call.execute().body();
        } catch(Exception e) {
            Log.e("Error", "Eye2Web Congressman Api Call Error" + e.toString());
        }

        return congressmanList;
    }
}
