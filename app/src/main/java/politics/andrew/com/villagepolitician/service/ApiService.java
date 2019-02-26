package politics.andrew.com.villagepolitician.service;

import android.util.Log;

import java.util.List;

import politics.andrew.com.villagepolitician.interfacevo.Congressman;
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

    private String eye2webHost = "http://www.eye2web.co.kr";
    private Congressman congressman;
    private List<Congressman> congressmanList;

    private Retrofit client = new Retrofit.Builder().baseUrl(eye2webHost).addConverterFactory(GsonConverterFactory.create()).build();

    public String test(String apiKey) {
        congressman = new Congressman();

        congressman = getCongressman(1, 1, "ehdus77!@");
        //Log.i("Info", "Congressman Info : " + congressman.getName_kr());
        return congressman.getName_kr().toString();
    }

    public static void main(String[] args) {
        Congressman congressman = new Congressman();
        Retrofit client = new Retrofit.Builder().baseUrl("http://www.eye2web.co.kr").addConverterFactory(GsonConverterFactory.create()).build();

        try {
            Congressman.CongressmanListInterface service = client.create(Congressman.CongressmanListInterface.class);
            Call<List<Congressman>> call = ((Congressman.CongressmanListInterface) service).get_congressman_list(1, 1, "ehdus77!@");

            List<Congressman> tmpCongressMan = call.execute().body();
            congressman = tmpCongressMan.get(0);
        } catch(Exception e) {
            Log.e("Error", "Error : " + e.toString());
        }

        System.out.println("Congressman name : " + congressman.getName_kr().toString());
    }

    public Congressman getCongressman(int page,  int per_page, String apiKey) {
        try {
            Congressman.CongressmanListInterface service = client.create(Congressman.CongressmanListInterface.class);
            Call<List<Congressman>> call = ((Congressman.CongressmanListInterface) service).get_congressman_list(page, per_page, apiKey);

            List<Congressman> tmpCongressMan = call.execute().body();
            congressman = tmpCongressMan.get(0);
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
