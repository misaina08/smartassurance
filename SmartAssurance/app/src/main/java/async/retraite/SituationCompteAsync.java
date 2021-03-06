package async.retraite;

import android.os.AsyncTask;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;

import com.aro.misaina.smartassurance.BotFragment;
import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.SRRetraitePopFragment;

import org.json.JSONObject;

import java.text.DecimalFormat;

import ai.ui.BulleUI;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 9/7/2017.
 */

public class SituationCompteAsync extends AsyncTask<String, Void, Double[]> {
    private BotFragment botFragment;

    @Override
    protected Double[] doInBackground(String... params) {
        try {
            String url = WSUtil.getUrlServer() + "/retraite/situation-compte/" + params[0];
            WSRequestModele requestModele = new WSRequestModele();
            String resultContent = requestModele.getContent(url);
            Double[] res = new Double[2];
            if (resultContent.compareTo("0") == 0) {
                return null;
            } else {

                JSONObject jsonObject = new JSONObject(resultContent);
                res[0] = new Double(jsonObject.getDouble("mtActuel"));
                res[1] = new Double(jsonObject.getDouble("estimation"));
                return res;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Double[] values) {
        if (values != null) {
            BulleUI bulle1 = new BulleUI(botFragment, 0);
            bulle1.setTextInBulle("Vous avez déposé au total " + new DecimalFormat("#,##0.00").format(values[0]) + " Ariary");

            BulleUI bulle2 = new BulleUI(botFragment, 0);
            bulle2.setTextInBulle("À l'age de retraite vous retirerez " + new DecimalFormat("#,##0.00").format(values[1]) + " Ariary");

            botFragment.updateBotChat(bulle1);
            botFragment.updateBotChat(bulle2);
        } else {
            BulleUI bulle2 = new BulleUI(botFragment, 0);
            bulle2.setTextInBulle("Le client n'est pas encore souscrit au produit assurance retraite");
            Button souscrire = new Button(new ContextThemeWrapper(botFragment, android.R.style.Widget_Material_Button_Borderless_Colored));
            souscrire.setText("Souscrire");
            souscrire.setBackground(botFragment.getResources().getDrawable(R.drawable.ripple_circle));
            souscrire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SRRetraitePopFragment retraitePopFragment = new SRRetraitePopFragment();
                    retraitePopFragment.setBotFragment(botFragment);
                    retraitePopFragment.show(botFragment.getFragmentManager(), "Retraite popup");
                }
            });
            botFragment.updateBotChat(bulle2);
            botFragment.addStandardComponent(souscrire);
        }
    }

    public BotFragment getBotFragment() {
        return botFragment;
    }

    public void setBotFragment(BotFragment botFragment) {
        this.botFragment = botFragment;
    }
}
