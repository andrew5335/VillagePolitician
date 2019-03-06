package politics.andrew.com.villagepolitician.viewholder;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * @File : CongressmanViewHolder
 * @Date : 2019-02-28 오후 12:18
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 국회의원 정보를 담기 위한 view holder
**/
public class CongressmanViewHolder {

    public ImageView congressmanPhoto;    // 국회의원 사진
    public TextView congressmanParty;    // 국회의원 소속 정당
    public TextView congressmanDistrict;    // 국회의원 지역구
    public TextView congressmanNameKr;    // 국회의원 한글 이름
    public TextView congressnamNameCh;    // 국회의원 한문 이름
    public TextView congressmanNameEn;    // 국회의원 영문 이름
}
