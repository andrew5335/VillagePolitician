package politics.andrew.com.villagepolitician.service;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import politics.andrew.com.villagepolitician.interfacevo.CongressmanDetailXml;
import politics.andrew.com.villagepolitician.interfacevo.CongressmanListXml;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * @File : XmlApiService
 * @Date : 2019-03-08 오후 1:39
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : xml data call api service
**/
public class XmlApiService {

    private String publicDataHost = "http://apis.data.go.kr/9710000/NationalAssemblyInfoService/";    // 공공데이터 포탈의 국회의원 정보 서비스 기본 호스트
    private String listServiceUrl = "";    // 국회의원 리스트 정보 호출 서비스 주소값
    private String detailServiceUrl = "";    // 국회의원 상세정보 호출 서비스 주소값

    //rivate Retrofit client = new Retrofit.Builder().baseUrl(publicDataUrl).addConverterFactory(SimpleXmlConverterFactory.create()).build();

    private ArrayList<CongressmanListXml> congressmanList;    // 국회의원 리스트 객체
    private CongressmanListXml congressman;
    private CongressmanDetailXml congressmanDetailXml;    // 국호의원 상세정보 객체

    private Document doc = null;

    public String test(String apiKey) {

        return null;
    }

    /**
     * @File : getCongressmanList
     * @Date : 2019-03-08 오후 2:00
     * @Author : Andrew Kim
     * @Description : 국회의원 목록 조회
    **/
    public ArrayList<CongressmanListXml> getCongressmanList(String serviceKey, int numOfRows, int pageNo, String serviceUrl) {
        congressmanList = new ArrayList<CongressmanListXml>();

        // 서비스키와 서비스 주소가 있어야 API 호출이 가능하므로 서비스키와 서비스 주소 유무를 확인한다.
        if(null != serviceKey && !"".equals(serviceKey)) {
            if(null != serviceUrl && !"".equals(serviceUrl)) {
                try {
                    listServiceUrl = publicDataHost + serviceUrl + "?serviceKey=" + serviceKey + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo;
                    URL url = new URL(listServiceUrl);

                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();

                    doc = builder.parse(new InputSource(url.openStream()));
                    doc.getDocumentElement().normalize();

                    NodeList nodeItem = doc.getElementsByTagName("item");
                    NodeList nodeDeptCd = doc.getElementsByTagName("deptCd");
                    NodeList nodeNum = doc.getElementsByTagName("num");
                    NodeList nodeEmpNm = doc.getElementsByTagName("empNm");
                    NodeList nodeHjNm = doc.getElementsByTagName("hjNm");
                    NodeList nodeEngNm = doc.getElementsByTagName("engNm");
                    NodeList nodeReeleGbnNm = doc.getElementsByTagName("reeleGbnNm");
                    NodeList nodeOrigNm = doc.getElementsByTagName("origNm");
                    NodeList nodeJpgLink = doc.getElementsByTagName("jpgLink");

                    // 국회의원 정보 리스트 값이 있을 경우
                    for (int i = 0; i < nodeItem.getLength(); i++) {
                        congressman = new CongressmanListXml();

                        // 정보를 가져오고자 하는 노드가 존재하며 세부 데이터가 있을 경우
                        // 노드의 확인은 해당 nodelist의 length 및 childnode 를 가지고 있는지 유무로 판단
                        if(0 < nodeDeptCd.getLength()) { if(nodeDeptCd.item(i).hasChildNodes()) { congressman.setDeptCd(Integer.parseInt(nodeDeptCd.item(i).getFirstChild().getNodeValue())); } }
                        if(0 < nodeNum.getLength()) { if(nodeNum.item(i).hasChildNodes()) { congressman.setNum(Integer.parseInt(nodeNum.item(i).getFirstChild().getNodeValue())); } }
                        if(0 < nodeEmpNm.getLength()) { if(nodeEmpNm.item(i).hasChildNodes()) { congressman.setEmpNm(nodeEmpNm.item(i).getFirstChild().getNodeValue()); } }
                        if(0 < nodeHjNm.getLength()) { if(nodeHjNm.item(i).hasChildNodes()) { congressman.setHjNm(nodeHjNm.item(i).getFirstChild().getNodeValue()); } }
                        if(0 < nodeEngNm.getLength()) { if(nodeEngNm.item(i).hasChildNodes()) { congressman.setEngNm(nodeEngNm.item(i).getFirstChild().getNodeValue()); } }
                        if(0 < nodeReeleGbnNm.getLength()) { if(nodeReeleGbnNm.item(i).hasChildNodes()) { congressman.setReeleGbnNm(nodeReeleGbnNm.item(i).getFirstChild().getNodeValue()); } }
                        if(0 < nodeOrigNm.getLength()) { if(nodeOrigNm.item(i).hasChildNodes()) { congressman.setOrigNm(nodeOrigNm.item(i).getFirstChild().getNodeValue()); } }
                        if(0 < nodeJpgLink.getLength()) { if(nodeJpgLink.item(i).hasChildNodes()) { congressman.setJpgLink(nodeJpgLink.item(i).getFirstChild().getNodeValue()); } }

                        congressmanList.add(congressman);
                    }
                } catch (Exception e) {
                    Log.e("Error", "Congressman List XML API Call Error : " + e.toString());
                }
            } else {
                Log.e("Error", "Congressman List API Call Error : API 호출에 필요한 호출 URL이 없습니다.");
            }
        } else {
            Log.e("Error", "CongressmanListAPI Call Error : API 호출에 필요한 서비스키가 없습니다.");
        }

        return congressmanList;
    }

    /**
     * @File : getCongressman
     * @Date : 2019-03-13 오후 6:30
     * @Author : Andrew Kim
     * @Description : 국회의원 상세 정보 조회
    **/
    public CongressmanDetailXml getCongressman(String serviceKey, int numOfRows, int pageNo, String serviceUrl, int dept_cd, int num) {
        congressmanDetailXml = new CongressmanDetailXml();

        // 서비스키와 서비스 주소, 부서코드와 식별번호가 있어야 국회의원 상세정보 확인이 가능하므로 서비스키, 서비스 주소, 부서코드 및 식별번호 정상 유무를 확인한다.
        if(null != serviceKey && !"".equals(serviceKey)) {
            if(null != serviceUrl && !"".equals(serviceUrl)) {
                if(null != String.valueOf(dept_cd) && 0 < dept_cd) {
                    if(null != String .valueOf(num) && 0 < num) {
                        try {
                            detailServiceUrl = publicDataHost + serviceUrl + "?serviceKey=" + serviceKey + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&dept_cd=" + dept_cd + "&num=" + num;
                            URL url = new URL(detailServiceUrl);

                            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                            DocumentBuilder builder = factory.newDocumentBuilder();

                            doc = builder.parse(new InputSource(url.openStream()));
                            doc.getDocumentElement().normalize();

                            NodeList nodeItem = doc.getElementsByTagName("item");
                            NodeList nodeAssemEmail = doc.getElementsByTagName("assemEmail");
                            NodeList nodeAssemTel = doc.getElementsByTagName("assemTel");
                            NodeList nodeBthDate = doc.getElementsByTagName("bthDate");
                            NodeList nodeElectionNum = doc.getElementsByTagName("electionNum");
                            NodeList nodeEmpNm = doc.getElementsByTagName("empNm");
                            NodeList nodeEngNm = doc.getElementsByTagName("engNm");
                            NodeList nodeExamCd = doc.getElementsByTagName("examCd");
                            NodeList nodeHbbyCd = doc.getElementsByTagName("hbbyCd");
                            NodeList nodeHjNm = doc.getElementsByTagName("hjNm");
                            NodeList nodeMemTitle = doc.getElementsByTagName("memTitle");
                            NodeList nodeOrigNm = doc.getElementsByTagName("origNm");
                            NodeList nodePolyNm = doc.getElementsByTagName("polyNm");
                            NodeList nodeReeleGbnNm = doc.getElementsByTagName("reeleGbnNm");
                            NodeList nodeSectary = doc.getElementsByTagName("sectary");
                            NodeList nodeSectary2 = doc.getElementsByTagName("sectary2");
                            NodeList nodeShrtNm = doc.getElementsByTagName("shrtNm");
                            NodeList nodeStaff = doc.getElementsByTagName("staff");

                            for(int i=0; i < nodeItem.getLength(); i++) {
                                if(0 < nodeAssemEmail.getLength()) { if(nodeAssemEmail.item(i).hasChildNodes()) { congressmanDetailXml.setAssemEmail(nodeAssemEmail.item(i).getFirstChild().getNodeValue()); } }
                                if(0 < nodeAssemTel.getLength()) { if(nodeAssemTel.item(i).hasChildNodes()) { congressmanDetailXml.setAssemTel(nodeAssemTel.item(i).getFirstChild().getNodeValue()); } }
                                if(0 < nodeBthDate.getLength()) { if(nodeBthDate.item(i).hasChildNodes()) { congressmanDetailXml.setBthDate(nodeBthDate.item(i).getFirstChild().getNodeValue()); } }
                                if(0 < nodeElectionNum.getLength()) { if(nodeElectionNum.item(i).hasChildNodes()) { congressmanDetailXml.setElectionNum(nodeElectionNum.item(i).getFirstChild().getNodeValue()); } }
                                if(0 < nodeEmpNm.getLength()) { if(nodeEmpNm.item(i).hasChildNodes()) { congressmanDetailXml.setEmpNm(nodeEmpNm.item(i).getFirstChild().getNodeValue()); } }
                                if(0 < nodeEngNm.getLength()) { if(nodeEngNm.item(i).hasChildNodes()) { congressmanDetailXml.setEngNm(nodeEngNm.item(i).getFirstChild().getNodeValue()); } }
                                if(0 < nodeExamCd.getLength()) { if(nodeExamCd.item(i).hasChildNodes()) { congressmanDetailXml.setExamCd(nodeExamCd.item(i).getFirstChild().getNodeValue()); } }
                                if(0 < nodeHbbyCd.getLength()) { if(nodeHbbyCd.item(i).hasChildNodes()) { congressmanDetailXml.setHbbyCd(nodeHbbyCd.item(i).getFirstChild().getNodeValue()); } }
                                if(0 < nodeHjNm.getLength()) { if(nodeHjNm.item(i).hasChildNodes()) { congressmanDetailXml.setHjNm(nodeHjNm.item(i).getFirstChild().getNodeValue()); } }
                                if(0 < nodeMemTitle.getLength()) { if(nodeMemTitle.item(i).hasChildNodes()) { congressmanDetailXml.setMemTitle(nodeMemTitle.item(i).getFirstChild().getNodeValue()); } }
                                if(0 < nodeOrigNm.getLength()) { if(nodeOrigNm.item(i).hasChildNodes()) { congressmanDetailXml.setOrigNm(nodeOrigNm.item(i).getFirstChild().getNodeValue()); } }
                                if(0 < nodePolyNm.getLength()) { if(nodePolyNm.item(i).hasChildNodes()) { congressmanDetailXml.setPolyNm(nodePolyNm.item(i).getFirstChild().getNodeValue()); } }
                                if(0 < nodeReeleGbnNm.getLength()) { if(nodeReeleGbnNm.item(i).hasChildNodes()) { congressmanDetailXml.setReeleGbnNm(nodeReeleGbnNm.item(i).getFirstChild().getNodeValue()); } }
                                if(0 < nodeSectary.getLength()) { if(nodeSectary.item(i).hasChildNodes()) { congressmanDetailXml.setSecretary(nodeSectary.item(i).getFirstChild().getNodeValue()); } }
                                if(0 < nodeSectary2.getLength()) { if(nodeSectary2.item(i).hasChildNodes()) { congressmanDetailXml.setSecretary2(nodeSectary2.item(i).getFirstChild().getNodeValue()); } }
                                if(0 < nodeShrtNm.getLength()) { if(nodeShrtNm.item(i).hasChildNodes()) { congressmanDetailXml.setShrtNm(nodeShrtNm.item(i).getFirstChild().getNodeValue()); } }
                                if(0 < nodeStaff.getLength()) { if(nodeStaff.item(i).hasChildNodes()) { congressmanDetailXml.setStaff(nodeStaff.item(i).getFirstChild().getNodeValue()); } }
                                if(0 < dept_cd) { congressmanDetailXml.setDeptCd(dept_cd); }
                                if(0 < num) { congressmanDetailXml.setNum(num); }
                            }
                        } catch (Exception e) {
                            Log.e("Error", "Congressman Detail API Call Error : " + e.toString());
                        }
                    } else {
                        Log.e("Error", "Congressman Detail API Call Error : 국회의원 상세정보 호출에 필요한 식별코드가 없습니다.");
                    }
                } else {
                    Log.e("Error", "Congressman Detail API Call Error : 국회의원 상세정보 호출에 필요한 부서코드가 없습니다.");
                }
            } else {
                Log.e("Error", "Congressman Detail API Call Error : API 호출에 필요한 호출 URL이 없습니다.");
            }
        } else {
            Log.e("Error", "Congressman Detail API Call Error : API 호출에 필요한 서비스키가 없습니다.");
        }

        return congressmanDetailXml;
    }
}