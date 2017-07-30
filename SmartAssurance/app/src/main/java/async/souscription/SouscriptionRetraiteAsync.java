package async.souscription;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aro.misaina.smartassurance.BotActivity;
import com.google.gson.Gson;

import ai.ui.CardUI;
import modeles.souscription.RtSouscription;
import utilitaire.WSUtil;
import ws.Result;
import ws.WSRequestModele;

/**
 * Created by LENOVO on 7/29/2017.
 */

public class SouscriptionRetraiteAsync extends AsyncTask<RtSouscription, Void, String> {
    private BotActivity botActivity;

    @Override
    protected String doInBackground(RtSouscription... params) {

        try {
            WSRequestModele wsRequestModele = new WSRequestModele();
            String url = WSUtil.getUrlServer() + "/souscription/retraite";
            String res = wsRequestModele.post(url, params[0]);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return "{res:0}";
        }

    }

    @Override
    protected void onPostExecute(String res) {
        Result resultWS = new Result();
        Gson gson = new Gson();
        resultWS = gson.fromJson(res, Result.class);
        CardUI cardUI = new CardUI(botActivity);
        try {

            cardUI.setText("Votre souscription a bien été effectuée. Veuillez consulter la liste des souscription en attente de paiement.");

            if (resultWS.getRes() == 1) {
                Button b1 = new Button(botActivity);
                b1.setText("Voir");
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // action à faire
                        Toast.makeText(botActivity, "Action à faire", Toast.LENGTH_SHORT).show();
                    }
                });
                cardUI.getOptionsCardLayout().addView(b1);
            } else {

                throw new Exception("");
            }
            botActivity.sendFromUI(cardUI, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            cardUI.setText("Une erreur est survenue. Veuillez réessayer.");
        }
    }

    public BotActivity getBotActivity() {
        return botActivity;
    }

    public void setBotActivity(BotActivity botActivity) {
        this.botActivity = botActivity;
    }
}
