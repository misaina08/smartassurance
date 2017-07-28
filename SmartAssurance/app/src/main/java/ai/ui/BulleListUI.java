package ai.ui;

import android.content.Context;
import android.widget.LinearLayout;

import com.aro.misaina.smartassurance.R;

import java.util.List;

/**
 * Created by LENOVO on 7/28/2017.
 */

public class BulleListUI extends UIElement {
    private LinearLayout bulleContentList;

    public BulleListUI(Context context) {
        super(context);
        init();
    }

    public void init() {
        inflate(getContext(), R.layout.bulle_list, this);
        bulleContentList = (LinearLayout) findViewById(R.id.bulleContentList);
    }

    public void addBulles(List<BulleUI> bulles) {
        for (BulleUI bulle : bulles) {
            bulleContentList.addView(bulle);
        }
    }

    public LinearLayout getBulleContentList() {
        return bulleContentList;
    }

    public void setBulleContentList(LinearLayout bulleContentList) {
        this.bulleContentList = bulleContentList;
    }
}
