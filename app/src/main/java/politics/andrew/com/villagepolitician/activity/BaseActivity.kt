package politics.andrew.com.villagepolitician.activity

import android.content.Context
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import android.widget.AbsListView
import kotlinx.android.synthetic.main.actionbar_title.*
import politics.andrew.com.villagepolitician.handler.BackPressCloseHandler
import kotlin.math.max
import kotlin.math.min

/**
 * @File : BaseActivity
 * @Date : 2019-03-07 오후 4:43
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 모든 Activity에서 공통으로 상속받는 기본 Activity
**/
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}