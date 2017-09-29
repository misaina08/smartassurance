package com.aro.misaina.smartassurance;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TagFragment extends Fragment {
    LinearLayout contentGif;

    public TagFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tag, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final GifImageView gifView = new GifImageView(this.getActivity());
        contentGif = (LinearLayout) getView().findViewById(R.id.contentGif);

        try {
            GifDrawable gifFromAssets = new GifDrawable(this.getActivity().getAssets(), "animation-nfc.gif");
            gifView.setImageDrawable(gifFromAssets);
            gifView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600));
            gifView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

            contentGif.addView(gifView);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
