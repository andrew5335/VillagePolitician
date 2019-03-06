package politics.andrew.com.villagepolitician.interfacevo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @File : Congressman.java
 * @Date : 29/10/2018 11:34 PM
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 국회의원 정보 value object & interface
**/
public class Congressman implements Serializable {

    private static final long serialVersionUID = 2472869833554749578L;

        @SerializedName("no") String no;    // 번호
        @SerializedName("name_kr") String name_kr;    // 한글 이름
        @SerializedName("name_cn") String name_cn;    // 한자 이름
        @SerializedName("name_en") String name_en;    // 영문 이름
        @SerializedName("birth") String birth;    // 생년월일
        @SerializedName("party") String party;    // 소속정당
        @SerializedName("district") String district;    // 지역구
        @SerializedName("committee") String committee;    // 소속위원회
        @SerializedName("when_elected") String when_elected;    // 당 회수
        @SerializedName("off_phone") String off_phone;    // 전화번호
        @SerializedName("homepage") String homepage;    // 홈페이지주소
        @SerializedName("email") String email;    // 메일주소
        @SerializedName("aides") String aides;    // 보좌관정보
        @SerializedName("pr_secrs") String pr_secrs;    // 비서관
        @SerializedName("sc_secrs") String sc_secrs;    // 비서
        @SerializedName("hobby") String hobby;    // 취미
        @SerializedName("experience") String experience;    // 경력사항
        @SerializedName("photo") String photo;    // 사진 주소
        @SerializedName("url") String url;    // 국회 홈페이지 상세 주소

        public String getNo() { return no; }
        public String getName_kr() { return name_kr; }
        public String getName_cn() { return name_cn; }
        public String getName_en() { return name_en; }
        public String getBirth() { return birth; }
        public String getParty() { return party; }
        public String getDistrict() { return district; }
        public String getCommittee() { return committee; }
        public String getWhen_elected() { return when_elected; }
        public String getOff_phone() { return off_phone; }
        public String getHomepage() { return homepage; }
        public String getEmail() { return email; }
        public String getAides() { return aides; }
        public String getPr_secrs() { return pr_secrs; }
        public String getSc_secrs() { return sc_secrs; }
        public String getHobby() { return hobby; }
        public String getExperience() { return experience; }
        public String getPhoto() { return photo; }
        public String getUrl() { return url; }

    /**
     * @File : CongressmanListInterface
     * @Date : 2019-03-06 오후 5:28
     * @Author : Andrew Kim
     * @Description : API 호출 interface (국회의원 목록 호출)
    **/
    public interface CongressmanListInterface {
        @GET("/politician/congressman/v1/1.0.0/congressmanList.php")
        Call<ArrayList<Congressman>> get_congressman_list(
          @Query("page") int page
          , @Query("per_page") int per_page
          , @Query("sortQuery") String  sortQuery
          , @Query("sort") String sort
          , @Query("queryWord") String queryWord
          , @Query("apiKey") String apiKey
        );
    }

    /**
     * @File : CongressmanInfoInterface
     * @Date : 2019-03-06 오후 5:34
     * @Author : Andrew Kim
     * @Description : API 호출 interface (국회의원 1명의 정보 호출)
    **/
    public interface CongressmanInfoInterface {
        @GET("/politician/congressman/v1/1.0.0/congressmanList.php")
        Call<ArrayList<Congressman>> get_congressman_info(
                @Query("no") String no
                , @Query("page") int page
                , @Query("per_page") int per_page
                , @Query("sortQuery") String  sortQuery
                , @Query("sort") String sort
                , @Query("queryWord") String queryWord
                , @Query("apiKey") String apiKey
        );
    }

}
