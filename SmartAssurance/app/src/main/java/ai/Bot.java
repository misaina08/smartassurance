package ai;

import com.aro.misaina.smartassurance.BotFragment;

import java.lang.reflect.Method;

import ai.model.Chat;
import ai.ui.UIElement;

/**
 * Created by Misaina on 27/07/2017.
 */

public class Bot {
    public UIElement execute(String request, BotFragment context) throws Exception {
        Actions actions = new Actions(context);
        Method method = actions.getClass().getMethod(request, null);
        System.out.println("mandalo aty am actions execute");
        return (UIElement) method.invoke(actions, null);
    }

    /**
     * Execute la méthode spécifiée *action, depuis la classe Actions Retourne
     * un string contenant l'affichage avec les données
     *
     * @param
     * @throws Exception
     */
    public UIElement executeActionWithSpecificParam(Chat chat, String param, BotFragment context) throws Exception {
        Actions act = new Actions(context);
        if (chat.getTypesparams().compareToIgnoreCase("null") == 0) {
            Method method = act.getClass().getMethod(chat.getAction(), null);
            return (UIElement) method.invoke(act, null);
        }
        else if (chat.getTypesparams().compareToIgnoreCase("java.lang.Integer") == 0) {
            Method method = act.getClass().getMethod(chat.getAction(), java.lang.Integer.class);
            return (UIElement) method.invoke(act, new Integer(param));
        }
        else if (chat.getTypesparams().compareToIgnoreCase("java.lang.String") == 0) {
            Method method = act.getClass().getMethod(chat.getAction(), java.lang.String.class);
            return (UIElement) method.invoke(act, param);
        }
        return null;
    }

}
