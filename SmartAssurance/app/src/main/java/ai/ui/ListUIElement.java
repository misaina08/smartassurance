package ai.ui;

import android.content.Context;
import android.widget.LinearLayout;

import com.aro.misaina.smartassurance.R;

import java.util.List;

/**
 * Created by misa on 9/24/2017.
 */

public class ListUIElement extends UIElement {
    private LinearLayout contentList;
    public ListUIElement(Context context) {
        super(context);
        init();
    }
    public void init() {
        inflate(getContext(), R.layout.uielement_list, this);
        contentList = (LinearLayout) findViewById(R.id.contentList);
    }

    public void addUIElements(List<UIElement> uiElements) {
        for (UIElement ui : uiElements) {
            contentList.addView(ui);
        }
    }
}
