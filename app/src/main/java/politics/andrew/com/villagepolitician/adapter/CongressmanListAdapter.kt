package politics.andrew.com.villagepolitician.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.request.RequestOptions
import org.w3c.dom.Text
import politics.andrew.com.villagepolitician.R
import politics.andrew.com.villagepolitician.interfacevo.Congressman
import politics.andrew.com.villagepolitician.interfacevo.CongressmanListXml
import politics.andrew.com.villagepolitician.viewholder.CongressmanViewHolder

/**
* @File : CongressmanListAdapter
* @Date : 2019-02-26 오후 3:32
* @Author : Andrew Kim
* @Version : 1.0.0
* @Description : 국회의원 리스트 뷰용 아답터
**/
class CongressmanListAdapter (val context: Context, val congressmanList: ArrayList<CongressmanListXml>) : BaseAdapter() {

    /**
    * @File : getView
    * @Date : 2019-02-26 오후 3:38
    * @Author : Andrew Kim
    * @Description : 레이아웃 xml 파일의 view와 데이터 연결 처리
    **/
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //val view:View = LayoutInflater.from(context).inflate(R.layout.congressman_list_item, null)
        val view: View
        val viewHolder: CongressmanViewHolder

        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.congressman_list_item, null)
            viewHolder = CongressmanViewHolder()
            viewHolder.congressmanPhoto = view.findViewById(R.id.congressman_photo)
            viewHolder.congressmanNameKr = view.findViewById(R.id.congressman_name_kr)
            viewHolder.congressmanDistrict = view.findViewById(R.id.congressman_district)
            viewHolder.congressnamNameCh = view.findViewById(R.id.congressman_name_ch)
            viewHolder.congressmanNameEn = view.findViewById(R.id.congressman_name_en)

            view.tag = viewHolder
        } else {
            viewHolder = convertView.tag as CongressmanViewHolder
            view = convertView
        }

        val congressman = congressmanList[position]
        if(null != congressman.jpgLink) {
            var requestOption = RequestOptions()
            requestOption.override(100, 100)
            Glide.with(context).load(congressman.jpgLink).apply(requestOption).into(viewHolder.congressmanPhoto)
        }
        viewHolder.congressmanNameKr.text = congressman.empNm
        viewHolder.congressmanDistrict.text = congressman.origNm
        viewHolder.congressnamNameCh.text = congressman.hjNm
        viewHolder.congressmanNameEn.text = congressman.engNm

        return view
    }

    /**
    * @File : getItem
    * @Date : 2019-02-26 오후 3:38
    * @Author : Andrew Kim
    * @Description : 개별 아이템 처리
    **/
    override fun getItem(position: Int): Any {
        return congressmanList[position]
    }

    /**
    * @File : getItemId
    * @Date : 2019-02-26 오후 3:39
    * @Author : Andrew Kim
    * @Description : 아이템의 위치 ID 반환 처리
    **/

    override fun getItemId(p0: Int): Long {
        return 0
    }

    /**
    * @File : getCount
    * @Date : 2019-02-26 오후 3:39
    * @Author : Andrew Kim
    * @Description : 리스트뷰에 보여질 아이템의 전체 수 반환
    **/
    override fun getCount(): Int {
        return congressmanList.size
    }

}