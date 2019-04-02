package politics.andrew.com.villagepolitician.adapter

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import politics.andrew.com.villagepolitician.R
import politics.andrew.com.villagepolitician.interfacevo.NaverNewsSearch
import politics.andrew.com.villagepolitician.viewholder.NaverNewsViewHolder

/**
 * @File : NaverNewsListAdapter
 * @Date : 2019-04-02 오전 9:48
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 네이버 뉴스 리스트 뷰용 아답터
**/
class NaverNewsListAdapter(val context:Context, val naverNewsList: ArrayList<NaverNewsSearch.Items>): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: NaverNewsViewHolder

        if(convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.naver_news_list_item, null)
            viewHolder = NaverNewsViewHolder()

            viewHolder.title = view.findViewById(R.id.newsTitle)
            viewHolder.description = view.findViewById(R.id.newsDescription)
            viewHolder.pubDate = view.findViewById(R.id.newsPubDate)

            view.tag = viewHolder
        } else {
            viewHolder = convertView.tag as NaverNewsViewHolder
            view = convertView
        }

        val news = naverNewsList[position]

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            viewHolder.title.text = Html.fromHtml(news.title, Html.FROM_HTML_MODE_LEGACY)
        } else {
            viewHolder.title.text = Html.fromHtml(news.title)
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            viewHolder.description.text = Html.fromHtml(news.description, Html.FROM_HTML_MODE_LEGACY)
        } else {
            viewHolder.description.text = Html.fromHtml(news.description)
        }

        viewHolder.pubDate.text = news.pubDate

        return view
    }

    override fun getItem(position: Int): Any {
        return naverNewsList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return naverNewsList.size
    }

}