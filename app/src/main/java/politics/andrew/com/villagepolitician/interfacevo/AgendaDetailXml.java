package politics.andrew.com.villagepolitician.interfacevo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AgendaDetailXml implements Serializable {

    private static final long serialVersionUID = 611588356393730480L;

    @SerializedName("gubun") String gubun;    // 구분
    @SerializedName("title") String title;    // 제목
    @SerializedName("recordId") int recordId;    // 번호
    @SerializedName("sessNm") String sessNm;    // 회기
    @SerializedName("cha") String cha;    // 차수
    @SerializedName("agendaId") String agendaId;    // 위원회
    @SerializedName("meetingTi") String meetingTi;    // 회의시간
    @SerializedName("meetingDa") String meetingDa;    // 회의일자
    @SerializedName("content") String content;    // 내용
    @SerializedName("expectBillH") String expectBillH;    // 예정보고서 hwp 파일 경로
    @SerializedName("resultRepo") String resultRepo;    // 결과보고서 pdf 파일 경로

    public String getGubun() { return gubun; }
    public String getTitle() { return title; }
    public int getRecordId() { return recordId; }
    public String getSessNm() { return sessNm; }
    public String getCha() { return cha; }
    public String getAgendaId() { return agendaId; }
    public String getMeetingTi() { return meetingTi; }
    public String getMeetingDa() { return meetingDa; }
    public String getContent() { return content; }
    public String getExpectBillH() { return expectBillH; }
    public String getResultRepo() { return resultRepo; }

    public void setGubun(String gubun) { this.gubun = gubun; }
    public void setTitle(String title) { this.title = title; }
    public void setRecordId(int recordId) { this.recordId = recordId; }
    public void setSessNm(String sessNm) { this.sessNm = sessNm; }
    public void setCha(String cha) { this.cha = cha; }
    public void setAgendaId(String agendaId) { this.agendaId = agendaId; }
    public void setMeetingTi(String meetingTi) { this.meetingTi = meetingTi; }
    public void setMeetingDa(String meetingDa) { this.meetingDa = meetingDa; }
    public void setContent(String content) { this.content = content; }
    public void setExpectBillH(String expectBillH) { this.expectBillH = expectBillH; }
    public void setResultRepo(String resultRepo) { this.resultRepo = resultRepo; }
}
