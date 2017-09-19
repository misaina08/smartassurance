package async;

import android.os.AsyncTask;

import com.aro.misaina.smartassurance.BotFragment;

import org.json.JSONObject;

import ai.Bot;
import ai.model.Chat;
import ai.ui.UIElement;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 8/20/2017.
 */

public class ActionBotAsync extends AsyncTask<String, Void, String> {
    private BotFragment botFragment;
    private String motcle;

    @Override
    protected String doInBackground(String... params) {
        try {
            String url = WSUtil.getUrlServer() + "/bot/action/" + params[0];
            motcle = params[0];
            WSRequestModele request = new WSRequestModele();
            return request.getContent(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            Bot bot = new Bot();
            UIElement result = null;
            if (jsonObject.getString("action").compareToIgnoreCase("proceduresProduit") == 0) {
                Chat chat = new Chat();
                chat.setParam("4");
                chat.setTypesparams("java.lang.Integer");
                chat.setAction(jsonObject.getString("action"));
                result = bot.executeActionWithSpecificParam(chat, "4", botFragment);
                botFragment.updateBotChat(result);

            } else if (jsonObject.getString("action").compareToIgnoreCase("explicationMotCle") == 0) {
                Chat chat = new Chat();
                chat.setParam(motcle);
                chat.setTypesparams("java.lang.String");
                chat.setAction(jsonObject.getString("action"));
                result = bot.executeActionWithSpecificParam(chat, motcle, botFragment);
            } else {
                result = bot.execute(jsonObject.getString("action"), botFragment);
                botFragment.updateBotChat(result);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public BotFragment getBotFragment() {
        return botFragment;
    }

    public void setBotFragment(BotFragment botFragment) {
        this.botFragment = botFragment;
    }
}
