package async.retraite;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;

import com.aro.misaina.smartassurance.BotFragment;
import com.aro.misaina.smartassurance.SRRetraitePopFragment;

import ai.ui.BulleUI;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 9/7/2017.
 */

public class EstimationAsync extends AsyncTask<Double, Void, Double> {
    private BotFragment botFragment;


    @Override
    protected Double doInBackground(Double... params) {
        try {
            Integer intValueAge = params[0].intValue();
            String url = WSUtil.getUrlServer() + "/retraite/estimation/" + intValueAge + "/" + params[1];
            WSRequestModele requestModele = new WSRequestModele();
            Double res = new Double(requestModele.getContent(url));
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Double montant) {
        BulleUI bulle1 = new BulleUI(botFragment.getActivity(), 0);
        bulle1.setTextInBulle("Vous devriez déposer au moins " + montant);

        Button souscrire = new Button(botFragment.getActivity());
        souscrire.setText("Souscrire ?");
        souscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SRRetraitePopFragment retraitePopFragment = new SRRetraitePopFragment();
                retraitePopFragment.setBotFragment(botFragment);
                retraitePopFragment.show(botFragment.getFragmentManager(), "Retraite popup");
            }
        });

        botFragment.updateBotChat(bulle1);
        botFragment.addStandardComponent(souscrire);
    }
    public BotFragment getBotFragment() {
        return botFragment;
    }

    public void setBotFragment(BotFragment botFragment) {
        this.botFragment = botFragment;
    }
}
