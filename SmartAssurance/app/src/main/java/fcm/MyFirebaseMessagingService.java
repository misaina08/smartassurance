package fcm;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.aro.misaina.smartassurance.AccueilActivity;
import com.aro.misaina.smartassurance.R;
import com.aro.misaina.smartassurance.TourGuichetActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import modeles.Notification;
import sqlite.GuichetDao;
import utilitaire.AndroidUtil;

/**
 * Created by misa on 9/6/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        try {

            //La notification à envoyer
            Notification notification = new Notification(remoteMessage.getData().get("title"),
                    remoteMessage.getData().get("body"), remoteMessage.getData().get("target"));

            if (notification.getTarget().compareToIgnoreCase("target_client_suivant") == 0) {
//                sendNotifGuichetTour(notification);

                Intent intentVibrate = new Intent(getBaseContext(), TourGuichetActivity.class);
                intentVibrate.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intentVibrate.putExtra("noGuichet", notification.getContenu());
                getBaseContext().startActivity(intentVibrate);
            }
            if (notification.getTarget().compareToIgnoreCase("target_client_termine") == 0) {
                notification.setTitre("Terminée");
                sendNotifGuichetTour(notification);
                GuichetDao guichetDao = new GuichetDao(this);
                guichetDao.setNumeroEnCours(0);
                guichetDao.setSurGuichet(0);
            }
            else{
                sendNotifGuichetTour(notification);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendNotifGuichetTour(Notification notification) {
        Intent intent = new Intent(this, AccueilActivity.class);
        intent.putExtra("tabid", R.id.tab_guichet);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        AndroidUtil androidUtil = new AndroidUtil();
        Bitmap icon = androidUtil.drawableToBitmap(getResources().getDrawable(R.mipmap.ic_launcher));

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(icon)
                .setContentTitle(notification.getTitre())
                .setContentText(notification.getContenu())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
