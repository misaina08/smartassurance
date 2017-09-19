package async.client;

import android.os.AsyncTask;

import com.aro.misaina.smartassurance.ProfilActivity;

import modeles.client.ClientView;
import services.SessionManager;
import utilitaire.Util;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 9/14/2017.
 */

public class ProfilClient extends AsyncTask<Void, Void, ClientView> {
    private ProfilActivity profilActivity;
    @Override
    protected ClientView doInBackground(Void... params) {
        try {
            String url = WSUtil.getUrlServer() + "/clients/" + SessionManager.getClientConnected().getId();
            WSRequestModele requestModele = new WSRequestModele();
            ClientView c = (ClientView) requestModele.getOne(url, new ClientView());
            return c;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        }
    }

    @Override
    protected void onPostExecute(ClientView client) {
        if (client != null) {
            profilActivity.nomUser.setText(client.getNom());
            profilActivity.prenomUser.setText(client.getPrenom());
            profilActivity.cin.setText(client.getCin());
            profilActivity.dateNaiss.setText(Util.dateToString(client.getDateNaissance()));
            profilActivity.datesouscription.setText(Util.dateToString(client.getDateSouscription()));
            profilActivity.lieuNaiss.setText(client.getLieuNaissance());
            profilActivity.profession.setText(client.getProfession());
            profilActivity.email.setText(client.getEmail());
            profilActivity.tel.setText(client.getTel());
            profilActivity.adresse.setText(client.getAdresse());
            profilActivity.noclient.setText(client.getNoclient());

        }
    }

    public ProfilActivity getProfilActivity() {
        return profilActivity;
    }

    public void setProfilActivity(ProfilActivity profilActivity) {
        this.profilActivity = profilActivity;
    }
}
