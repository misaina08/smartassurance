package async;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import ai.Actions;
import modeles.Agence;
import utilitaire.Coordonnee;
import utilitaire.CoordonneeUtil;

/**
 * Created by misa on 8/14/2017.
 */

public class CurrentPositionAsync extends AsyncTask<Void, Void, Coordonnee> {
    Actions actions;
    Activity activity;

    @Override
    protected Coordonnee doInBackground(Void... params) {
        final Coordonnee currPos = new Coordonnee();
        FusedLocationProviderClient mFusedLocationClient;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("no permissions");
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(activity, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
//                                    Coordonnees de la position actuelle
                            currPos.setLatitude(location.getLatitude());
                            currPos.setLongitude(location.getLongitude());
                        }
                    }
                });
        return currPos;
    }

    @Override
    protected void onPostExecute(Coordonnee res) {
        System.out.println("_________latitude "+res.getLatitude());
        CoordonneeUtil coordonneeUtil = new CoordonneeUtil();
        Agence agenceProche = coordonneeUtil.getAgenceProche(res);

    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Actions getActions() {
        return actions;
    }

    public void setActions(Actions actions) {
        this.actions = actions;
    }
}
