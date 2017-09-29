package adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aro.misaina.smartassurance.RapportsFragment;
import com.aro.misaina.smartassurance.TabCirconstanceSinistreFragment;
import com.aro.misaina.smartassurance.TabDommagesSinistreFragment;
import com.aro.misaina.smartassurance.TabInfoSinistreFragment;
import com.aro.misaina.smartassurance.TabPhotosSinistreFragment;
import com.google.gson.Gson;

import modeles.automoto.AmSinistreView;

/**
 * Created by misa on 8/12/2017.
 */

public class TabSinistreAdapter extends FragmentPagerAdapter {
    public TabSinistreAdapter(FragmentManager fm) {
        super(fm);
    }
    AmSinistreView sinistre;
    @Override
    public Fragment getItem(int position) {
        Gson gson = new Gson();
        switch (position){
            case 0:
                TabInfoSinistreFragment infoSinistreFragment = new TabInfoSinistreFragment();
                Bundle b = new Bundle();
                b.putString("dataJson", gson.toJson(sinistre));
                infoSinistreFragment.setArguments(b);
                return infoSinistreFragment;
            case 1:
                TabDommagesSinistreFragment dommagesSinistreFragment = new TabDommagesSinistreFragment();
                Bundle b1 = new Bundle();
                b1.putString("dataJson", gson.toJson(sinistre));
                dommagesSinistreFragment.setArguments(b1);
                return dommagesSinistreFragment;
            case 2:
                TabCirconstanceSinistreFragment tabCirconstanceSinistreFragment = new TabCirconstanceSinistreFragment();
                Bundle b2 = new Bundle();
                b2.putString("dataJson", gson.toJson(sinistre));
                tabCirconstanceSinistreFragment.setArguments(b2);
                return tabCirconstanceSinistreFragment;
            case 3:
                TabPhotosSinistreFragment photosSinistre = new TabPhotosSinistreFragment();
                Bundle b3 = new Bundle();
                b3.putString("dataJson", gson.toJson(sinistre));
                photosSinistre.setArguments(b3);
                return photosSinistre;
            case 4:
                RapportsFragment rapportsFragment = new RapportsFragment();
                Bundle b4 = new Bundle();
                b4.putString("dataJson", gson.toJson(sinistre));
                rapportsFragment.setArguments(b4);
                return rapportsFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

    public AmSinistreView getSinistre() {
        return sinistre;
    }

    public void setSinistre(AmSinistreView sinistre) {
        this.sinistre = sinistre;
    }
}
