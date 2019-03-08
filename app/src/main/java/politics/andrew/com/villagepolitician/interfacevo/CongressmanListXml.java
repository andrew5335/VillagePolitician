package politics.andrew.com.villagepolitician.interfacevo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @File : CongressmanListXml
 * @Date : 2019-03-08 오후 1:37
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 국회의원 목록 정보 value object
**/
public class CongressmanListXml implements Serializable {

    private static final long serialVersionUID = -9051625365601252756L;

    @SerializedName("deptCd") int deptCd;    // 부서코드
    @SerializedName("num") int num;    // 식별코드
    @SerializedName("empNm") String empNm;    // 한글이름
    @SerializedName("hjNm") String hjNm;    // 한자이름
    @SerializedName("engNm") String engNm;    // 영문이름
    @SerializedName("reeleGbnNm") String reeleGbnNm;    // 당선횟수
    @SerializedName("origNm") String origNm;    // 선거구
    @SerializedName("jpgLink") String jpgLink;    // 의원사진

    public int getDeptCd() { return deptCd; }
    public int getNum() { return num; }
    public String getEmpNm() { return empNm; }
    public String getHjNm() { return hjNm; }
    public String getEngNm() { return engNm; }
    public String getReeleGbnNm() { return reeleGbnNm; }
    public String getOrigNm() { return origNm; }
    public String getJpgLink() { return jpgLink; }

    public void setDeptCd(int deptCd) { this.deptCd = deptCd; }
    public void setNum(int num) { this.num = num; }
    public void setEmpNm(String empNm) { this.empNm = empNm; }
    public void setHjNm(String hjNm) { this.hjNm = hjNm; }
    public void setEngNm(String engNm) { this.engNm = engNm; }
    public void setReeleGbnNm(String reeleGbnNm) { this.reeleGbnNm = reeleGbnNm; }
    public void setOrigNm(String origNm) { this.origNm = origNm; }
    public void setJpgLink(String jpgLink) { this.jpgLink = jpgLink; }


}
