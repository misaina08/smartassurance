package async;

import android.os.AsyncTask;

import java.util.List;

import modeles.produit.Produit;
import services.ObjetsStatique;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 9/14/2017.
 */

public class ListProduitAsync extends AsyncTask<Void, Void, List<Produit>> {
    @Override
    protected List<Produit> doInBackground(Void... params) {
        try {
            String url = WSUtil.getUrlServer() + "/produits";
            WSRequestModele requestModele = new WSRequestModele();
            List<Produit> rest = (List<Produit>) (List<?>) requestModele.get(url, new Produit());
            return rest;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Produit> produits) {
        if (produits != null) {
            ObjetsStatique.setProduits(produits);
        }
    }
}
