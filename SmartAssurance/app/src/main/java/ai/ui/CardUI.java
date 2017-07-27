package ai.ui;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aro.misaina.smartassurance.R;

/**
 * Created by Misaina on 27/07/2017.
 */

public class CardUI extends UIElement {
    private LinearLayout cardLayout;
    private LinearLayout contentCardLayout;
    private LinearLayout optionsCardLayout;
    private TextView textContent;

    private String text;

    public CardUI(Context context) {
        super(context);
        init();
    }

    public void init(){
        inflate(getContext(), R.layout.card, this);
        this.cardLayout = (LinearLayout)findViewById(R.id.cardLayout);
        this.contentCardLayout = (LinearLayout)findViewById(R.id.contentCardLayout);
        this.optionsCardLayout = (LinearLayout)findViewById(R.id.optionsCardLayout);
        this.textContent = (TextView) findViewById(R.id.textContent);
    }

    public void changeText() {
        textContent.setText(getText());
    }

    public LinearLayout getCardLayout() {
        return cardLayout;
    }

    public void setCardLayout(LinearLayout cardLayout) {
        this.cardLayout = cardLayout;
    }

    public LinearLayout getContentCardLayout() {
        return contentCardLayout;
    }

    public void setContentCardLayout(LinearLayout contentCardLayout) {
        this.contentCardLayout = contentCardLayout;
    }

    public LinearLayout getOptionsCardLayout() {
        return optionsCardLayout;
    }

    public void setOptionsCardLayout(LinearLayout optionsCardLayout) {
        this.optionsCardLayout = optionsCardLayout;
    }

    public TextView getTextContent() {
        return textContent;
    }

    public void setTextContent(TextView textContent) {
        this.textContent = textContent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        changeText();
    }
}
