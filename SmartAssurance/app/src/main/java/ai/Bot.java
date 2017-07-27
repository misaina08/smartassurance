package ai;

import android.content.Context;

import java.lang.reflect.Method;

import ai.ui.UIElement;

/**
 * Created by Misaina on 27/07/2017.
 */

public class Bot {
    public UIElement execute(String request, Context context) throws Exception {
        Actions actions = new Actions(context);
        Method method = actions.getClass().getMethod(request, null);
        return (UIElement) method.invoke(actions, null);
    }
}
