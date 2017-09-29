package async.souscription;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aro.misaina.smartassurance.AccueilActivity;
import com.aro.misaina.smartassurance.BotFragment;
import com.aro.misaina.smartassurance.R;
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
    private BotFragment botFragment;

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
        CardUI cardUI = new CardUI(botFragment);
        try {

            cardUI.setText("Votre souscription a été effectuée. Veuillez consulter la liste des souscriptions en attente de paiement.");

            if (resultWS.getRes() == 1) {
                Button b1 = new Button(botFragment);
                b1.setText("Voir");
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // action à faire
                        Toast.makeText(botFragment, "Action à faire", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(botFragment, AccueilActivity.class);
                        intent.putExtra("tabid", R.id.tab_contrats);
                        botFragment.startActivity(intent);
                    }
                });
                cardUI.getOptionsCardLayout().addView(b1);
            } else {

                throw new Exception("");
            }
            botFragment.sendFromUI(cardUI, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            cardUI.setText("Une erreur est survenue. Veuillez réessayer.");
        }
    }

    public BotFragment getBotFragment() {
        return botFragment;
    }

    public void setBotFragment(BotFragment botFragment) {
        this.botFragment = botFragment;
    }
}
