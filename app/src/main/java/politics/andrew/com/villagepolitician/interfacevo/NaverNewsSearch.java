package politics.andrew.com.villagepolitician.interfacevo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import kotlin.UseExperimental;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * @File : NaverNewsSearch
 * @Date : 2019-03-20 오후 5:07
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 네이버 뉴스 검색용 VO 및 interface
**/
public class NaverNewsSearch implements Serializable {

    private static final long serialVersionUID = -3650858043306190103L;

    @SerializedName("items") ArrayList<Items> itemList;
    @SerializedName("lastBuildDate") String lastBuildDate;
    @SerializedName("total") int total;
    @SerializedName("start") int start;
    @SerializedName("display") int display;

    public class Items implements Serializable {

        private static final long serialVersionUID = 8232567916009873417L;

        @SerializedName("title") String  title;    // 뉴스 제목
        @SerializedName("originallink") String originallink;    // 언론사 링크 주소
        @SerializedName("link")  String link;    // 네이버 링크 주소
        @SerializedName("description") String description;    // 검색 결과 내용 요약 정보
        @SerializedName("pubDate") String pubDate;    // 뉴스 발행 시간

        public String getTitle() { return title; }
        public String getOriginallink() { return originallink; }
        public String getLink() { return link; }
        public String getDescription() { return description; }
        public String getPubDate() { return pubDate; }

        public void setTitle(String title) { this.title = title; }
        public void setOriginallink(String originallink) { this.originallink = originallink; }
        public void setLink(String link) { this.link = link; }
        public void setDescription(String description) { this.description = description; }
        public void setPubDate(String pubDate) { this.pubDate = pubDate ; }
    }

    public ArrayList<Items> getItemList() { return itemList; }
    public String getLastBuildDate() { return lastBuildDate; }
    public int getTotal() { return total; }
    public int getStart() { return start; }
    public int getDisplay() { return display; }

    public interface NaverSearchInterface {
        @Headers({"X-Naver-Client-Id: FmCk55S0fsU0oxmKKJZE", "X-Naver-Client-Secret: W65VXtmHr3"})
        @GET("/v1/search/news.json")
        Call<NaverNewsSearch> get_news_info(
                @Query("query") String query
                , @Query("display") int display
                , @Query("start") int start
                , @Query("sort") String sort
        );
    }

    public interface NaverSearchListInterface {
        @Headers({"X-Naver-Client-Id: FmCk55S0fsU0oxmKKJZE", "X-Naver-Client-Secret: W65VXtmHr3"})
        @GET("/v1/search/news.json")
        Call<ArrayList<NaverNewsSearch>> get_news_list(
                @Query("query") String query
                , @Query("display") int display
                , @Query("start") int start
                , @Query("sort") String sort
        );
    }

}
