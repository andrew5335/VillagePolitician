package politics.andrew.com.villagepolitician.interfacevo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @File : CongressmanDetailXml
 * @Date : 2019-03-08 오후 1:39
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 국회의원 상세정보 value object
**/
public class CongressmanDetailXml implements Serializable {

    private static final long serialVersionUID = -8363972199395019739L;

    @SerializedName("assemEmail") String assemEmail;    // 메일주소
    @SerializedName("assemTel") String assemTel;    // 전화번호
    @SerializedName("bthDate") String bthDate;    // 생년월일
    @SerializedName("electionNum") String electionNum;    // 당선대수
    @SerializedName("empNm") String empNm;    // 한글이름
    @SerializedName("hjNm") String hjNm;    // 한자이름
    @SerializedName("engNm") String engNm;    // 영문이름
    @SerializedName("examCd") String examCd;    // 특기
    @SerializedName("hbbyCd") String hbbyCd;    // 취미
    @SerializedName("memTitle") String memTitle;    // 약력
    @SerializedName("origNm") String origNm;    // 선거구
    @SerializedName("polyNm") String polyNm;    // 소속정당
    @SerializedName("reeleGbnNm") String reeleGbnNm;    // 당선횟수
    @SerializedName("secretary") String secretary;    // 비서 명단
    @SerializedName("secretary2") String secretary2;    // 비서관 명단
    @SerializedName("shrtNm") String shrtNm;    // 소속위원회
    @SerializedName("staff") String staff;    // 보좌관 명단
    @SerializedName("detpCd") int deptCd;    // 부서코드
    @SerializedName("num") int num;    // 식별번호
    @SerializedName("jpgLink") String jpgLink;    // 국회의원 사진

    public String getAssemEmail() { return assemEmail; }
    public String getAssemTel() { return assemTel; }
    public String getBthDate() { return bthDate; }
    public String getElectionNum() { return electionNum; }
    public String getEmpNm() { return empNm; }
    public String getHjNm() { return hjNm; }
    public String getEngNm() { return engNm; }
    public String getExamCd() { return examCd; }
    public String getHbbyCd() { return hbbyCd; }
    public String getMemTitle() { return memTitle; }
    public String getOrigNm() { return origNm; }
    public String getReeleGbnNm() { return reeleGbnNm; }
    public String getPolyNm() { return polyNm; }
    public String getSecretary() { return secretary; }
    public String getSecretary2() { return secretary2; }
    public String getShrtNm() { return shrtNm; }
    public String getStaff() { return staff; }
    public int getDeptCd() { return deptCd; }
    public int getNum() { return num; }
    public String getJpgLink() { return jpgLink; }

    public void setAssemEmail(String assemEmail) { this.assemEmail = assemEmail; }
    public void setAssemTel(String assemTel) { this.assemTel = assemTel; }
    public void setBthDate(String bthDate) { this.bthDate = bthDate; }
    public void setElectionNum(String electionNum) { this.electionNum = electionNum; }
    public void setEmpNm(String empNm) { this.empNm = empNm; }
    public void setHjNm(String hjNm) { this.hjNm = hjNm; }
    public void setEngNm(String engNm) {this.engNm = engNm; }
    public void setExamCd(String examCd) { this.examCd = examCd; }
    public void setHbbyCd(String hbbyCd) { this.hbbyCd = hbbyCd; }
    public void setMemTitle(String memTitle) { this.memTitle = memTitle; }
    public void setOrigNm(String origNm) { this.origNm = origNm; }
    public void setPolyNm(String polyNm) { this.polyNm = polyNm; }
    public void setReeleGbnNm(String reeleGbnNm) { this.reeleGbnNm = reeleGbnNm; }
    public void setSecretary(String secretary) { this.secretary = secretary; }
    public void setSecretary2(String secretary2) { this.secretary2 = secretary2; }
    public void setShrtNm(String shrtNm) { this.shrtNm = shrtNm; }
    public void setStaff(String staff) { this.staff = staff; }
    public void setDeptCd(int deptCd) { this.deptCd = deptCd; }
    public void setNum(int num) { this.num = num; }
    public void setJpgLink(String jpgLink) { this.jpgLink = jpgLink; }

    public interface CongressmanDetailInterface {
        @GET("getMemberDetailInfoList")
        Call<ArrayList<CongressmanDetailXml>> get_congressman_detail (
                @Query("serviceKey") String serviceKey
                , @Query("numOfRows") int numOfRows
                , @Query("pageNo") int pageNo
                , @Query("dept_cd") String dept_cd
                , @Query("num") int num
        );
    }
}
