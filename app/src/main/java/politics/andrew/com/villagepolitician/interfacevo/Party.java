package politics.andrew.com.villagepolitician.interfacevo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @File : Party
 * @Date : 2019-04-02 오전 9:52
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 정당 목록 vo
**/
public class Party implements Serializable {

    private static final long serialVersionUID = 8981929700199990133L;

    @SerializedName("polyCd") int polyCd;
    @SerializedName("polyNm") String polyNm;

    public int getPolyCd() { return polyCd; }
    public String getPolyNm() { return polyNm; }

    public void setPolyCd(int polyCd) { this.polyCd = polyCd; }
    public void setPolyNm(String polyNm) { this.polyNm = polyNm; }
}
