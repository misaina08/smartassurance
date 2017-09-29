package com.aro.misaina.smartassurance;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import async.guichet.DevantAsync;
import sqlite.GuichetDao;


/**
 * A simple {@link Fragment} subclass.
 */
public class GuichetFragment extends Fragment {
    //    VideoView videoPub;
    Handler mHandler;

    public GuichetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guichet, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.mHandler = new Handler();

//        MediaController mediacontroller = new MediaController(
//                getActivity());
//        videoPub = (VideoView) getView().findViewById(videoPub);
//
//        mediacontroller.setAnchorView(videoPub);
//
//        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.v1);
//        videoPub.setVideoURI(uri);
//        videoPub.start();
//
//        videoPub.setMediaController(mediacontroller);
//        videoPub.requestFocus();
//        videoPub.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

//            public void onPrepared(MediaPlayer mp) {
//                videoPub.start();
//            }
//
//        });

        initData();
        initSlider();

        this.mHandler.postDelayed(m_Runnable, 1000);
    }

    public void initSlider() {
        CarouselView carouselView;

//        final int[] sampleImages = {R.drawable.s1, R.drawable.s2,
//                R.drawable.s3, R.drawable.s4, R.drawable.s6, R.drawable.s7,
//                R.drawable.s8,
//                R.drawable.s9, R.drawable.s10,
//                R.drawable.s11,
//                R.drawable.s12,
//                R.drawable.s13,
//                R.drawable.s14,
//                R.drawable.s15,
//                R.drawable.s16,
//                R.drawable.s17,
//                R.drawable.s18,
//                R.drawable.s19,
//                R.drawable.s20,
//                R.drawable.s21,
//                R.drawable.s22,
//                R.drawable.s23,
//                R.drawable.s24,
//                R.drawable.s25,
//                R.drawable.s26,
//                R.drawable.s27,
//                R.drawable.s28,
//                R.drawable.s29
//        };
        final String[] urlImagesSlide = new String[29];
        for (int i = 1; i < 30; i++) {
            urlImagesSlide[i - 1] = "http://pixelmada.com/aro/images/sampledata/slide/" + i + ".jpg";
        }

        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
//                imageView.setImageResource(sampleImages[position]);
                Picasso.with(getActivity()).load(urlImagesSlide[position]).into(imageView);
            }
        };

        carouselView = (CarouselView) getView().findViewById(R.id.carouselView);
        carouselView.setImageListener(imageListener);
        carouselView.setPageCount(urlImagesSlide.length);
    }

    public void initData() {

        try {

            GuichetDao guichetDao = new GuichetDao(getActivity());
            Integer[] param = {guichetDao.getNumeroEnCours()};
            DevantAsync devantAsync = new DevantAsync();
            devantAsync.setGuichetFragment(this);
            devantAsync.execute(param);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private final Runnable m_Runnable = new Runnable() {
        public void run() {
            initData();
            mHandler.postDelayed(m_Runnable, 1000);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(m_Runnable);
    }

    @Override
    public void onStop() {
        super.onStop();
        mHandler.removeCallbacks(m_Runnable);
    }
}
