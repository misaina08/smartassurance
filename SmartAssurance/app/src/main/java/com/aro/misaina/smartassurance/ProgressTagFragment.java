package com.aro.misaina.smartassurance;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressTagFragment extends Fragment {


    public ProgressTagFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress_tag, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        GifImageView gifView = new GifImageView(getActivity());
        LinearLayout content = (LinearLayout) getView().findViewById(R.id.container);
        try {
            GifDrawable gifFromAssets = new GifDrawable(getActivity().getAssets(), "checkmark.gif");
            gifView.setImageDrawable(gifFromAssets);
            gifView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400));
            gifView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

            content.addView(gifView);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
