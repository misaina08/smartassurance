package ai.ui;

import android.content.Context;
import android.widget.TextView;

import com.aro.misaina.smartassurance.R;

/**
 * Created by Misaina on 27/07/2017.
 */

public class BulleUI extends UIElement {
    private TextView textContent;

    private String textInBulle;
    // 0 : bot, 1 : user
    private int user;

    public BulleUI(Context context, int user) {
        super(context);
        this.user = user;
        init();
    }

    public void init() {
        if (user == 0) {
            inflate(getContext(), R.layout.bulle_bot, this);
            textContent = (TextView) findViewById(R.id.textBot);
        } else {
            inflate(getContext(), R.layout.bulle_user, this);
            textContent = (TextView) findViewById(R.id.textUser);
        }
    }

    public void changeText() {
        textContent.setText(getTextInBulle());
    }

    public String getTextInBulle() {
        return textInBulle;
    }

    public void setTextInBulle(String textInBulle) {
        this.textInBulle = textInBulle;
        changeText();
    }
}
