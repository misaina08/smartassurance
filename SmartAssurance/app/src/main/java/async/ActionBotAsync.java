package async;

import android.os.AsyncTask;

import com.aro.misaina.smartassurance.BotFragment;

import org.json.JSONObject;

import ai.Bot;
import ai.ui.UIElement;
import utilitaire.WSUtil;
import ws.WSRequestModele;

/**
 * Created by misa on 8/20/2017.
 */

public class ActionBotAsync extends AsyncTask<String, Void, String> {
    private BotFragment botFragment;
    @Override
    protected String doInBackground(String... params) {
        try{
            String url = WSUtil.getUrlServer()+"/bot/action/"+params[0];
            WSRequestModele request = new WSRequestModele();
            return request.getContent(url);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String json){
        try{
            JSONObject jsonObject = new JSONObject(json);

            Bot bot = new Bot();
            UIElement result = bot.execute(jsonObject.getString("action"), botFragment);
//            botFragment.updateMyChat(jsonObject.getString("action"));
            botFragment.updateBotChat(result);
        }
        catch(Exception ex){
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
