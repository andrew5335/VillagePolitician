package politics.andrew.com.villagepolitician.interfacevo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @File : AgendaDetailXml
 * @Date : 2019-05-09 오후 3:43
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 의사일정 상세 정보 value object
**/
public class AgendaDetailXml implements Serializable {

    private static final long serialVersionUID = 611588356393730480L;

    @SerializedName("gubun") String gubun;    // 구분
    @SerializedName("title") String title;    // 제목
    @SerializedName("recordId") int recordId;    // 번호
    @SerializedName("sessNm") String sessNm;    // 회기
    @SerializedName("cha") String cha;    // 차수
    @SerializedName("agendaId") String agendaId;    // 위원회
    @SerializedName("meetingTi") String meetingTime;    // 회의시간
    @SerializedName("meetingDa") String meetingDay;    // 회의일자
    @SerializedName("content") String content;    // 내용
    @SerializedName("expectBillHwpLink") String expectBillHwpLink;    // 예정보고서 hwp 파일 경로
    @SerializedName("resultReportPdfLink") String resultReportPdfLink;    // 결과보고서 pdf 파일 경로

    public String getGubun() { return gubun; }
    public String getTitle() { return title; }
    public int getRecordId() { return recordId; }
    public String getSessNm() { return sessNm; }
    public String getCha() { return cha; }
    public String getAgendaId() { return agendaId; }
    public String getMeetingTime() { return meetingTime; }
    public String getMeetingDay() { return meetingDay; }
    public String getContent() { return content; }
    public String getExpectBillHwpLink() { return expectBillHwpLink; }
    public String getResultReportPdfLink() { return resultReportPdfLink; }

    public void setGubun(String gubun) { this.gubun = gubun; }
    public void setTitle(String title) { this.title = title; }
    public void setRecordId(int recordId) { this.recordId = recordId; }
    public void setSessNm(String sessNm) { this.sessNm = sessNm; }
    public void setCha(String cha) { this.cha = cha; }
    public void setAgendaId(String agendaId) { this.agendaId = agendaId; }
    public void setMeetingTime(String meetingTime) { this.meetingTime = meetingTime; }
    public void setMeetingDay(String meetingDay) { this.meetingDay = meetingDay; }
    public void setContent(String content) { this.content = content; }
    public void setExpectBillHwpLink(String expectBillHwpLink) { this.expectBillHwpLink = expectBillHwpLink; }
    public void setResultReportPdfLink(String resultReportPdfLink) { this.resultReportPdfLink = resultReportPdfLink; }
}
