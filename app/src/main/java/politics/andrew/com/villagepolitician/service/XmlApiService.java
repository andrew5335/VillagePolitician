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
    private String listServiceUrl = "";    // 국회의원 리스트 서비스 주소값

    //rivate Retrofit client = new Retrofit.Builder().baseUrl(publicDataUrl).addConverterFactory(SimpleXmlConverterFactory.create()).build();

    private ArrayList<CongressmanListXml> congressmanList;
    private CongressmanListXml congressman;

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

            for(int i=0; i < nodeItem.getLength(); i++) {
                congressman = new CongressmanListXml();

                int deptCd = Integer.parseInt(nodeDeptCd.item(i).getFirstChild().getNodeValue());
                int num = Integer.parseInt(nodeNum.item(i).getFirstChild().getNodeValue());
                String empNm = nodeEmpNm.item(i).getFirstChild().getNodeValue();
                String hjNm = nodeHjNm.item(i).getFirstChild().getNodeValue();
                String engNm = nodeEngNm.item(i).getFirstChild().getNodeValue();
                String reeleGbnNm = nodeReeleGbnNm.item(i).getFirstChild().getNodeValue();
                String origNm = nodeOrigNm.item(i).getFirstChild().getNodeValue();
                String jpgLink = nodeJpgLink.item(i).getFirstChild().getNodeValue();

                congressman.setDeptCd(deptCd);
                congressman.setNum(num);
                congressman.setEmpNm(empNm);
                congressman.setHjNm(hjNm);
                congressman.setEngNm(engNm);
                congressman.setReeleGbnNm(reeleGbnNm);
                congressman.setOrigNm(origNm);
                congressman.setJpgLink(jpgLink);

                congressmanList.add(congressman);
            }
        } catch(Exception e) {
            Log.e("Error", "Congressman List XML API Call Error : " + e.toString());
        }

        return congressmanList;
    }
}
