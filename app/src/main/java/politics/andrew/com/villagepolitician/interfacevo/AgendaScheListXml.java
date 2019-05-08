package politics.andrew.com.villagepolitician.interfacevo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @File : AgendaScheListXml
 * @Date : 2019-05-08 오후 3:16
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 의사일정 목록 정보 value object
**/
public class AgendaScheListXml implements Serializable {

    private static final long serialVersionUID = 5095677137322729462L;

    @SerializedName("boardId") String boardId;    // 게시판 아이디
    @SerializedName("cha") String cha;    // 차수
    @SerializedName("committeeId") int committeeId;    // 위원회 아이디
    @SerializedName("committeeName") String committeeName;    // 위원회
    @SerializedName("gubun") String gubun;    // 구분
    @SerializedName("meetingTime") String meetingTime;    // 회의시간
    @SerializedName("meetingDay") String meetingDay;    // 회의일자
    @SerializedName("recordId") int recordId;    // 게시물아이디
    @SerializedName("sessNm") String sessNm;    // 회기
    @SerializedName("title") String title;    // 제목
    @SerializedName("agendaId") int agendaId;    // 본회의 아이디
    @SerializedName("saveFileUrl") String saveFileUrl;    // 결과 보고서 파일 주소

    public String getBoardId() { return boardId; }
    public String getCha() { return cha; }
    public int getCommitteeId() { return committeeId; }
    public String getCommitteeName() { return committeeName; }
    public String getGubun() { return gubun; }
    public String getMeetingTime() { return meetingTime; }
    public String getMeetingDay() { return meetingDay; }
    public int getRecordId() { return recordId; }
    public String getSessNm() { return sessNm; }
    public String getTitle() { return title; }
    public int getAgendaId() { return agendaId; }
    public String getSaveFileUrl() { return saveFileUrl; }

    public void setBoardId(String boardId) { this.boardId = boardId; }
    public void setCha(String cha) { this.cha = cha; }
    public void setCommitteeId(int committeeId) { this.committeeId = committeeId; }
    public void setCommitteeName(String committeeName) { this.committeeName = committeeName; }
    public void setGubun(String gubun) { this.gubun = gubun; }
    public void setMeetingTime(String meetingTime) { this.meetingTime = meetingTime; }
    public void setMeetingDay(String meetingDay) { this.meetingDay = meetingDay; }
    public void setRecordId(int recordId) { this.recordId = recordId; }
    public void setSessNm(String sessNm) { this.sessNm = sessNm; }
    public void setTitle(String title) { this.title = title; }
    public void setAgendaId(int agendaId) { this.agendaId = agendaId; }
    public void setSaveFileUrl(String saveFileUrl) { this.saveFileUrl = saveFileUrl; }
}
