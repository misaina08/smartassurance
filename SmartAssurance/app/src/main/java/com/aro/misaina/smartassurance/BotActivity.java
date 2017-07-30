package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import ai.Bot;
import ai.ui.BulleUI;
import ai.ui.UIElement;

public class BotActivity extends AppCompatActivity {
    private BotActivity botActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);
        botActivity = this;
        initComponents();
    }

    public void initComponents() {
        final EditText saisi = (EditText) findViewById(R.id.textSaisi);
        Button sendButton = (Button) findViewById(R.id.buttonSend);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sendFromRequest(saisi.getText().toString());
                    saisi.setText("");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    /**
     * Envoi depuis le textinput
     *
     * @param request
     * @throws Exception
     */
    public void sendFromRequest(String request) throws Exception {
        Bot bot = new Bot();
        UIElement result = bot.execute(request, botActivity);
        updateMyChat(request);
        updateBotChat(result);
    }

    /**
     * Envoi depuis une autre affichage
     *
     * @param uiElement
     * @param request
     * @throws Exception
     */
    public void sendFromUI(UIElement uiElement, String request) throws Exception {
        if (request != null) {
            updateMyChat(request);
        }
        updateBotChat(uiElement);
    }
    public void updateMyChat(String myRequest) {
        LinearLayout chatContent = (LinearLayout) findViewById(R.id.chatContent);
        BulleUI bulle = new BulleUI(this, 1);
        bulle.setTextInBulle(myRequest);
        chatContent.addView(bulle);
    }

    public void updateBotChat(UIElement botResponse) {
        LinearLayout chatContent = (LinearLayout) findViewById(R.id.chatContent);
        chatContent.addView(botResponse);
    }

    public void initSuggestions() {

    }
}
