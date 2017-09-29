package async.retraite;


import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONObject;

import java.text.DecimalFormat;

import services.SessionManager;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 9/24/2017.
 */

public class EstimationRetraiteAsync extends AsyncTask<Void, Void, Double> {
    private TextView textView;

    @Override
    protected Double doInBackground(Void... params) {
        try {

            String url = WSUtil.getUrlServer() + "/retraite/situation-compte/" + SessionManager.getClientConnected().getNoclient();
            WSRequestModele requestModele = new WSRequestModele();
            String resultContent = requestModele.getContent(url);
            Double[] res = new Double[2];
            if (resultContent.compareTo("0") == 0) {
                return null;
            } else {

                JSONObject jsonObject = new JSONObject(resultContent);
                res[0] = new Double(jsonObject.getDouble("mtActuel"));
                res[1] = new Double(jsonObject.getDouble("estimation"));
                System.out.println(res[0]);
                return res[1];
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    @Override
    protected void onPostExecute(Double aDouble) {
        textView.setText(new DecimalFormat("#,##0.00").format(aDouble)+" Ariary");
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
