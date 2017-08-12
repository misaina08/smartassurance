package adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aro.misaina.smartassurance.TabGarantiesSousAutoFragment;
import com.aro.misaina.smartassurance.TabInfoSousAutoFragment;
import com.aro.misaina.smartassurance.TabSinistreFragment;
import com.aro.misaina.smartassurance.TabVehiculeSousAutoFragment;

/**
 * Created by LENOVO on 8/4/2017.
 */

public class TabContratAutoAdapter extends FragmentPagerAdapter {
    private Integer idSouscription;
    private String dataJson;
    public TabContratAutoAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Bundle bundle = new Bundle();
                bundle.putInt("idSouscription", idSouscription);

                TabInfoSousAutoFragment tabInfoSousAutoFragment = new TabInfoSousAutoFragment();
                tabInfoSousAutoFragment.setArguments(bundle);
                return tabInfoSousAutoFragment;
            case 1:
                TabVehiculeSousAutoFragment vehiculeFragment = new TabVehiculeSousAutoFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putInt("idSouscription", idSouscription);
                vehiculeFragment.setArguments(bundle1);
                return vehiculeFragment;
            case 2:
                TabGarantiesSousAutoFragment garantiesFragment = new TabGarantiesSousAutoFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putInt("idSouscription", idSouscription);
                garantiesFragment.setArguments(bundle2);
                return garantiesFragment;
            case 3:
                TabSinistreFragment sinistreFragment = new TabSinistreFragment();
                Bundle bundle3 = new Bundle();
                bundle3.putInt("idSouscription", idSouscription);
                bundle3.putString("dataJson", dataJson);
                sinistreFragment.setArguments(bundle3);
                return sinistreFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    public Integer getIdSouscription() {
        return idSouscription;
    }

    public void setIdSouscription(Integer idSouscription) {
        this.idSouscription = idSouscription;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }
}
