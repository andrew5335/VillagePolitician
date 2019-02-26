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
import org.w3c.dom.Text
import politics.andrew.com.villagepolitician.R
import politics.andrew.com.villagepolitician.interfacevo.Congressman
import politics.andrew.com.villagepolitician.viewholder.CongressmanViewHolder

/**
* @File : CongressmanListAdapter
* @Date : 2019-02-26 오후 3:32
* @Author : Andrew Kim
* @Version : 1.0.0
* @Description : 국회의원 리스트 뷰용 아답터
**/
class CongressmanListAdapter (val context: Context, val congressmanList: ArrayList<Congressman>) : BaseAdapter() {

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
        //val congressman_photo = view.findViewById<ImageView>(R.id.congressman_photo)
        //val congressman_name_kr = view.findViewById<TextView>(R.id.congressman_name_kr)
        //val congressman_district = view.findViewById<TextView>(R.id.congressman_district)
        //val congressman_name_ch = view.findViewById<TextView>(R.id.congressman_name_ch)
        //val congressman_name_en = view.findViewById<TextView>(R.id.congressman_name_en)

        val congressman = congressmanList[position]
        val resourceId = context.resources.getIdentifier(congressman.photo, "drawable", context.packageName)
        viewHolder.congressmanPhoto.setImageResource(resourceId)
        viewHolder.congressmanNameKr.text = congressman.name_kr
        viewHolder.congressmanDistrict.text = congressman.district
        viewHolder.congressnamNameCh.text = congressman.name_cn
        viewHolder.congressmanNameEn.text = congressman.name_en

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