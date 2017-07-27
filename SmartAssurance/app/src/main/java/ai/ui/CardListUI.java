package ai.ui;

import android.content.Context;
import android.widget.LinearLayout;

import com.aro.misaina.smartassurance.R;

import java.util.List;

/**
 * Created by Misaina on 27/07/2017.
 */

public class CardListUI extends UIElement{
    private LinearLayout contentList;
    public CardListUI(Context context) {
        super(context);
        init();
    }

    public void init(){
        inflate(getContext(), R.layout.card_list, this);
         contentList = (LinearLayout)findViewById(R.id.contentList);
    }
    public void addCards(List<CardUI> cards){

        for(CardUI card : cards){
            System.out.println("contentlist "+contentList);
            System.out.println("card-- "+card);
            contentList.addView(card);
            System.out.println("--adding");
        }
    }

}
