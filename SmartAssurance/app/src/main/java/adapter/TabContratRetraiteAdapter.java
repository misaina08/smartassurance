package adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aro.misaina.smartassurance.TabDepotsRetraiteFragment;
import com.aro.misaina.smartassurance.TabInfoSousRetraiteFragment;

/**
 * Created by LENOVO on 8/6/2017.
 */

public class TabContratRetraiteAdapter extends FragmentPagerAdapter {
    private Integer idSouscription;
    public TabContratRetraiteAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch(position){
            case 0:
                TabInfoSousRetraiteFragment fragment = new TabInfoSousRetraiteFragment();
                Bundle b = new Bundle();
                b.putInt("idSouscription", idSouscription);
                fragment.setArguments(b);
                return fragment;
            case 1:
                TabDepotsRetraiteFragment fragDepot = new TabDepotsRetraiteFragment();
                Bundle b1 = new Bundle();
                b1.putInt("idSouscription", idSouscription);
                fragDepot.setArguments(b1);
                return fragDepot;
            case 2:
                TabInfoSousRetraiteFragment fragment2 = new TabInfoSousRetraiteFragment();
                Bundle b2 = new Bundle();
                b2.putInt("idSouscription", idSouscription);
                fragment2.setArguments(b2);
                return fragment2;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public Integer getIdSouscription() {
        return idSouscription;
    }

    public void setIdSouscription(Integer idSouscription) {
        this.idSouscription = idSouscription;
    }
}
