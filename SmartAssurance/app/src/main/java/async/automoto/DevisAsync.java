package async.automoto;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;

import com.aro.misaina.smartassurance.BotFragment;
import com.aro.misaina.smartassurance.SRAutoMotoPopFragment;

import java.text.DecimalFormat;

import ai.ui.BulleUI;
import ai.ui.CardUI;
import modeles.souscription.SaisiGaranti;
import modeles.souscription.VehiculeWS;
import services.ObjetsStatique;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 9/8/2017.
 */

public class DevisAsync extends AsyncTask<VehiculeWS, Void, Double> {
    private Integer nbMois;
    private BotFragment botFragment;
    private VehiculeWS vehicule;

    @Override
    protected Double doInBackground(VehiculeWS... params) {
        try {
            String url = WSUtil.getUrlServer() + "/vehicules/devis/" + nbMois;
            WSRequestModele requestModele = new WSRequestModele();
            vehicule = params[0];
            Double res = new Double(requestModele.post(url, params[0]));
            return res;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Double aDouble) {
        if (aDouble != null) {
            CardUI cardUI = new CardUI(botFragment.getActivity());
            try {

                cardUI.setText("La prime nette est de " + new DecimalFormat("#,##0.00").format(aDouble) + " Ariary");
                Button b1 = new Button(botFragment.getActivity());
                b1.setText("Souscrire ?");
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // action à faire
                        SRAutoMotoPopFragment amPopFragment = new SRAutoMotoPopFragment();
                        amPopFragment.setBotFragment(botFragment);
                        amPopFragment.show(botFragment.getFragmentManager(), "Automoto popup");
                    }
                });
                cardUI.getOptionsCardLayout().addView(b1);

                String infoVehicule = "Véhicule à usage " + ObjetsStatique.getUsagesVehicule().get(vehicule.getIdUsage() - 1)
                        + "\nSource d'énergie " + vehicule.getSe()
                        + "\nPuissance fiscale " + vehicule.getPuissance()
                        + "\n" + vehicule.getNbRoues() + " roues "
                        + "\n" + vehicule.getNbPlaces() + " places ";

                String infoMois = "Durée de " + nbMois+" mois";

                String garanties = "Mes garanties : ";
                for (SaisiGaranti saisiGaranti : vehicule.getGaranties()) {
                    garanties += "\n"+saisiGaranti.getLibelle() + " à "+new DecimalFormat("#,##0.00").format(saisiGaranti.getLimite())+" Ar";
                }

                BulleUI bulleGaranties = new BulleUI(botFragment.getActivity(), 1);
                bulleGaranties.setTextInBulle(garanties);

                BulleUI bullereponse = new BulleUI(botFragment.getActivity(), 0);
                bullereponse.setTextInBulle("C'est faite. Voilà le devis de votre de l'assurance de votre véhicule");

                botFragment.updateMyChat(infoVehicule);
                botFragment.updateMyChat(garanties);
                botFragment.updateMyChat(infoMois);

                botFragment.sendFromUI(bullereponse, null);
                botFragment.sendFromUI(cardUI, null);
            } catch (Exception ex) {
                ex.printStackTrace();
                cardUI.setText("Une erreur est survenue. Veuillez réessayer.");
            }
        }
    }

    public Integer getNbMois() {
        return nbMois;
    }

    public void setNbMois(Integer nbMois) {
        this.nbMois = nbMois;
    }

    public BotFragment getBotFragment() {
        return botFragment;
    }

    public void setBotFragment(BotFragment botFragment) {
        this.botFragment = botFragment;
    }
}
