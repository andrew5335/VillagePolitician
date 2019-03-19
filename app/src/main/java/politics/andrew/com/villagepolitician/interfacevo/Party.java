package politics.andrew.com.villagepolitician.interfacevo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Party implements Serializable {

    private static final long serialVersionUID = 8981929700199990133L;

    @SerializedName("polyCd") int polyCd;
    @SerializedName("polyNm") String polyNm;

    public int getPolyCd() { return polyCd; }
    public String getPolyNm() { return polyNm; }

    public void setPolyCd(int polyCd) { this.polyCd = polyCd; }
    public void setPolyNm(String polyNm) { this.polyNm = polyNm; }
}
