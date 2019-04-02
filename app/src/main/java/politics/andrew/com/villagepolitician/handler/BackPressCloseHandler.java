package politics.andrew.com.villagepolitician.handler;

import android.app.Activity;
import android.widget.Toast;

/**
 * @File : BackPressCloseHandler
 * @Date : 2019-02-28 오후 12:18
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 뒤로가기 버튼 두번 클릭 시 앱 종료 처리를 위한 핸들러
**/
public class BackPressCloseHandler {

    private Activity  activity;
    private long backKeyPressedTime;
    private Toast toast;

    public BackPressCloseHandler(Activity context) {
        this.backKeyPressedTime = 0;
        this.activity = context;
    }

    /**
     * @File : onBackPressed
     * @Date : 2019-04-02 오전 9:51
     * @Author : Andrew Kim
     * @Description : 뒤로가기 버튼 클릭 시 처리
    **/
    public void onBackPressed() {
        if(System.currentTimeMillis() > this.backKeyPressedTime +  2000) {
            this.backKeyPressedTime = System.currentTimeMillis();
            showGuide();
        } else if(System.currentTimeMillis() <= this.backKeyPressedTime + 2000) {
            this.activity.finish();
            this.toast.cancel();
        }
    }

    /**
     * @File : showGuide
     * @Date : 2019-04-02 오전 9:52
     * @Author : Andrew Kim
     * @Description : 안내문 보여주기
    **/
    public void showGuide() {
        this.toast = Toast.makeText(this.activity, "뒤로가기 버튼을 한 번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_LONG);
        this.toast.show();
    }
}
