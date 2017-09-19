package async.bot;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aro.misaina.smartassurance.BotFragment;

import java.util.ArrayList;
import java.util.List;

import ai.ui.BulleListUI;
import ai.ui.BulleUI;
import modeles.Terme;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 9/7/2017.
 */

public class SignificationTermeAsync extends AsyncTask<String, Void, List<Terme>> {
    private BotFragment botFragment;

    @Override
    protected List<Terme> doInBackground(String... params) {
        try {
            String url = WSUtil.getUrlServer() + "/bot/signification/" + params[0];
            WSRequestModele requestModele = new WSRequestModele();
            return (List<Terme>) (List<?>) requestModele.get(url, new Terme());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Terme> termes) {
        System.out.println("aty am signification");
        if (termes != null) {
            System.out.println("tsy null");
            if (termes.size() == 0) {
                System.out.println("ailleur");
                rechercherAilleurs();
                System.out.println("ailleur vita");
            } else {
                BulleUI bulle1 = new BulleUI(botFragment, 0);
                bulle1.setTextInBulle("Bon !");
                BulleUI bulle2 = new BulleUI(botFragment, 0);
                bulle2.setTextInBulle(termes.get(0).getExplication());

//                Button souscrire = new Button(botFragment);
//                souscrire.setText("Rechercher ailleurs");
//                souscrire.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(botFragment, "Rechercher ailleurs", Toast.LENGTH_SHORT).show();
//                    }
//                });

                List<BulleUI> listBulle = new ArrayList<BulleUI>();
                listBulle.add(bulle1);
                listBulle.add(bulle2);

                BulleListUI bulleListUI = new BulleListUI(botFragment);
                bulleListUI.addBulles(listBulle);

                botFragment.updateBotChat(bulleListUI);

//                botFragment.addStandardComponent(souscrire);
            }
        } else {
            rechercherAilleurs();
        }
    }

    public void rechercherAilleurs() {
        BulleUI bulle1 = new BulleUI(botFragment, 0);
        bulle1.setTextInBulle("Je ne sais pas expliquer ce que vous voulez");

        Button souscrire = new Button(botFragment);
        souscrire.setText("Rechercher ailleurs");
        souscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(botFragment, "Rechercher ailleurs", Toast.LENGTH_SHORT).show();
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
