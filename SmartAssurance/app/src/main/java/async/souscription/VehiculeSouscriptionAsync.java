package async.souscription;

import android.os.AsyncTask;
import android.widget.TextView;

import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.TabVehiculeSousAutoFragment;

import modeles.souscription.VehiculeWS;
import services.ObjetsStatique;
import utilitaire.Util;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by LENOVO on 8/5/2017.
 */

/**
 * retourne le véhicule d'un souscirption auto-moto
 * param : idsouscription
 */
public class VehiculeSouscriptionAsync extends AsyncTask<Integer, Void, VehiculeWS> {
    private TabVehiculeSousAutoFragment fragment;
    @Override
    protected VehiculeWS doInBackground(Integer... params) {
        try {
            String url = WSUtil.getUrlServer() + "/vehicules/vehicule/" + params[0];
            WSRequestModele request = new WSRequestModele();
            return (VehiculeWS) request.getOne(url, new VehiculeWS());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(VehiculeWS vehicule) {
        if (vehicule != null) {
            TextView vNoimm = (TextView) fragment.getView().findViewById(R.id.vNoimm);
            TextView vMarque = (TextView) fragment.getView().findViewById(R.id.vMarque);
            TextView vNoSerie = (TextView) fragment.getView().findViewById(R.id.vNoSerie);
            TextView vDateMisCirc = (TextView) fragment.getView().findViewById(R.id.vDateMisCirc);
            TextView vPuissance = (TextView) fragment.getView().findViewById(R.id.vPuissance);
            TextView vSe = (TextView) fragment.getView().findViewById(R.id.vSe);
            TextView vNbRoues = (TextView) fragment.getView().findViewById(R.id.vNbRoues);
            TextView vNbPlace = (TextView) fragment.getView().findViewById(R.id.vNbPlace);
            TextView tUsage = (TextView) fragment.getView().findViewById(R.id.tUsage);

            vNoimm.setText(vehicule.getNoImm());
            vMarque.setText(vehicule.getMarque());
            vNoSerie.setText(vehicule.getNoSerie());
            System.out.println("puissance "+vehicule.getNoImm());
            System.out.println("puissance "+vehicule.getPuissance());
            vDateMisCirc.setText(Util.dateToString(vehicule.getDateCirculation()));
            vPuissance.setText(vehicule.getPuissance().toString());
            vSe.setText(vehicule.getSe());
            vNbRoues.setText(vehicule.getNbRoues().toString());
            vNbPlace.setText(vehicule.getNbPlaces().toString());
            tUsage.setText("Usage : véhicule "+ ObjetsStatique.getUsagesVehicule().get(vehicule.getIdUsage()-1));

        }
    }

    public TabVehiculeSousAutoFragment getFragment() {
        return fragment;
    }

    public void setFragment(TabVehiculeSousAutoFragment fragment) {
        this.fragment = fragment;
    }
}
