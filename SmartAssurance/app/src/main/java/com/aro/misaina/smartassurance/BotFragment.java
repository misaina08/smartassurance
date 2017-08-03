package com.aro.misaina.smartassurance;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import ai.Bot;
import ai.ui.BulleUI;
import ai.ui.UIElement;

public class BotFragment extends Fragment {
    private BotFragment botFragment;

    public BotFragment(){

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        botFragment = this;
        initComponents();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_bot, container, false);
    }

    public void initComponents() {
        final EditText saisi = (EditText) getView().findViewById(R.id.textSaisi);
        Button sendButton = (Button) getView().findViewById(R.id.buttonSend);
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
        UIElement result = bot.execute(request, botFragment);
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
        LinearLayout chatContent = (LinearLayout) getView().findViewById(R.id.chatContent);
        BulleUI bulle = new BulleUI(this.getActivity(), 1);
        bulle.setTextInBulle(myRequest);
        chatContent.addView(bulle);
    }

    public void updateBotChat(UIElement botResponse) {
        LinearLayout chatContent = (LinearLayout) getView().findViewById(R.id.chatContent);
        chatContent.addView(botResponse);
    }

    public void initSuggestions() {

    }
}
