package com.aro.misaina.smartassurance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import ai.Actions;
import ai.ui.BulleUI;
import ai.ui.UIElement;
import async.ActionBotAsync;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import sqlite.bot.CurrentQuestionDao;
import sqlite.bot.NoClientTempDao;
import sqlite.bot.QuestionCotisationDao;

public class BotFragment extends AppCompatActivity {
    private BotFragment botFragment;

    public BotFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_bot);
        getSupportActionBar().setTitle("ARO Assistant");

        botFragment = this;
        initComponents();
    }

    public void initComponents() {
        final EditText saisi = (EditText) findViewById(R.id.textSaisi);
        ImageButton sendButton = (ImageButton) findViewById(R.id.buttonSend);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    updateMyChat(saisi.getText().toString());


                    CurrentQuestionDao currentQuestionDao = new CurrentQuestionDao(botFragment);
                    String currentQuestion = currentQuestionDao.getCurrentQuestion();

                    if (currentQuestion.compareToIgnoreCase("aucun") != 0) {
                        if (currentQuestion.compareToIgnoreCase("saveAgeCotisation") == 0) {
                            Actions action = new Actions(botFragment);
                            botFragment.updateBotChat(action.saveAgeCotisation(new Integer(saisi.getText().toString())));
                        } else if (currentQuestion.compareToIgnoreCase("estimationCotisation") == 0) {
                            QuestionCotisationDao questionCotisationDao = new QuestionCotisationDao(botFragment);

                            Actions action = new Actions(botFragment);
                            action.estimationCotisation(questionCotisationDao.getAge(), new Double(saisi.getText().toString()), botFragment);
                        } else if (currentQuestion.compareToIgnoreCase("situationCompteRetraite") == 0) {
                            NoClientTempDao noClientTempDao = new NoClientTempDao(botFragment);

                            Actions action = new Actions(botFragment);
                            action.situationCompteRetraite(saisi.getText().toString(), botFragment);
                        }
                    } else {
                        sendFromRequest(saisi.getText().toString());
                    }
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
//        recherche de l'action correspondante à la requete

        ActionBotAsync async = new ActionBotAsync();
        async.setBotFragment(this);
        String[] params = {request};
        async.execute(params);

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

    public void sendFromUIMyChat(UIElement uiElement, String request) throws Exception {
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

    public void updateBotChat(final UIElement botResponse) {
//        final ImageView loading = new ImageView(botFragment);
//        loading.setText("en train  d'ecrire");
//
        final GifImageView gifView = new GifImageView(botFragment);

        try {
            GifDrawable gifFromAssets = new GifDrawable( botFragment.getAssets(), "contents_loading.gif" );
            gifView.setImageDrawable(gifFromAssets);
            gifView.setLayoutParams(new LinearLayout.LayoutParams(100, ViewGroup.LayoutParams.WRAP_CONTENT));
            gifView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            addStandardComponent(gifView);
        }catch(Exception ex) {
            ex.printStackTrace();
        }

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    synchronized (this) {
                        wait(2000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                LinearLayout chatContent = (LinearLayout) findViewById(R.id.chatContent);
                                chatContent.removeView(gifView);
                                System.out.println("botresponse "+botResponse);
                                chatContent.addView(botResponse);
                            }
                        });
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        timerThread.start();

    }
    public void addStandardComponent(View component) {
        LinearLayout chatContent = (LinearLayout) findViewById(R.id.chatContent);
        chatContent.addView(component);
    }
}
