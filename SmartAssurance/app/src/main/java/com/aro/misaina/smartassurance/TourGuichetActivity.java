package com.aro.misaina.smartassurance;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class TourGuichetActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout content;
    TextView guichetId;
    Button buttonOk;
    Vibrator mVibrator;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_guichet);

        String numeroGuichet = getIntent().getExtras().getString("noGuichet");

        getSupportActionBar().setTitle("");
        getSupportActionBar().setElevation(0);

        content = (LinearLayout) findViewById(R.id.content);
        guichetId = (TextView) findViewById(R.id.guichetId);
        buttonOk = (Button) findViewById(R.id.buttonOk);



        guichetId.setText("Guichet N° "+numeroGuichet);

        buttonOk.setOnClickListener(this);

        final GifImageView gifView = new GifImageView(this);

        try {
            mediaPlayer = new MediaPlayer();
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.facebook_messenger);
            mediaPlayer.setDataSource(getApplicationContext(), uri);
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
            mediaPlayer.start();

            GifDrawable gifFromAssets = new GifDrawable(this.getAssets(), "pulse1.gif");
            gifView.setImageDrawable(gifFromAssets);
            gifView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            gifView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

            content.addView(gifView);

            mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            int short_gap = 400;
            int long_gap = 800;    // Length of Gap Between Words
            long[] pattern = {
                    0,  // Start immediately
                    short_gap, 500, short_gap,
                    1000
            };
            mVibrator.vibrate(pattern, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        mediaPlayer.stop();
        mVibrator.cancel();
        finish();
    }
}
