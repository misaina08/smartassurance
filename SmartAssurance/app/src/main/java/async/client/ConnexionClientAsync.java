package async.client;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;

import com.aro.misaina.smartassurance.AccueilActivity;
import com.aro.misaina.smartassurance.ConnexionActivity;
import com.aro.misaina.smartassurance.R;

import java.util.List;

import modeles.client.ClientView;
import services.SessionManager;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by Misaina on 23/07/2017.
 */

public class ConnexionClientAsync extends AsyncTask<String, Void, ClientView> {
    private ConnexionActivity connexionActivity;

    @Override
    protected ClientView doInBackground(String... params) {
        WSRequestModele wsRequestModele = null;
        try {
            wsRequestModele = new WSRequestModele();
            String url = WSUtil.getUrlServer() + "/clients/authentification/" + params[0] + "/" + params[1];
            ClientView client = new ClientView();
            List<ClientView> listResult = (List<ClientView>) (List<?>) wsRequestModele.get(url, client);
            return listResult.get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ClientView client) {
        try {
            if (client != null) {
                SessionManager sessionManager=new SessionManager(connexionActivity.getBaseContext());
                sessionManager.createUserSession(client);
                Intent intent = new Intent(connexionActivity, AccueilActivity.class);
                connexionActivity.startActivity(intent);
            } else {
                Snackbar.make(connexionActivity.findViewById(R.id.contentConnexion), "Email ou mot de passe erroné", Snackbar.LENGTH_INDEFINITE).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConnexionActivity getConnexionActivity() {
        return connexionActivity;
    }

    public void setConnexionActivity(ConnexionActivity connexionActivity) {
        this.connexionActivity = connexionActivity;
    }
}
