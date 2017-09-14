package com.aro.misaina.smartassurance;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import async.guichet.NouveauClientAsync;
import fcm.FirebaseInitializer;
import services.ObjetsStatique;
import services.SessionManager;
import sqlite.GuichetDao;
import utilitaire.LocaleHelper;

public class AccueilActivity extends AppCompatActivity {
    private AccueilActivity accueilActivity;
    BottomBar bottomBar;

    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;

    public LinearLayout contentAccueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        try {
            // init language
            LocaleHelper localeHelper = new LocaleHelper(this);
            localeHelper.setLocale();

            // initialisation de la session
            SessionManager sessionManager = new SessionManager(this.getApplicationContext());

            contentAccueil = (LinearLayout) findViewById(R.id.contentAccueil);

            // init sur guichet
            GuichetDao guichetDao = new GuichetDao(this.getApplicationContext());
            ObjetsStatique.setEstSurGuichet(guichetDao.estSurGuichet());

            //        initialisation de l'adapteur nfc
            nfcAdapter = NfcAdapter.getDefaultAdapter(this);
            if (!nfcAdapter.isEnabled()) {
                //Demander à l’utilisateur d’activer l’option NFC
                Toast.makeText(getApplicationContext(), "Veuillez activer votre NFC dans le paramètre", Toast.LENGTH_LONG);
            }
//        initialiser l'intent pour la detection de tag
            pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass())
                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

            accueilActivity = this;
            initBottomBar();

            ObjetsStatique objetsStatique = new ObjetsStatique();
            objetsStatique.init();
            Bundle bundle = getIntent().getExtras();
            if (new Integer(bundle.getInt("tabid")) != null) {
                bottomBar.selectTabWithId(bundle.getInt("tabid"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Initialisation du bottom bar
     */
    public void initBottomBar() {
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment fragment = new BotFragment();
                if (tabId == R.id.tab_acueil) {

                }
                if (tabId == R.id.tab_guichet) {
                    if (ObjetsStatique.isEstSurGuichet()) {
                        fragment = new GuichetFragment();
                    } else {
                        fragment = new TagFragment();
                    }
                }
                if (tabId == R.id.tab_ai) {
                    fragment = new BotFragment();
                }
                if (tabId == R.id.tab_parametres) {
                    fragment = new ParametresFragment();
                }
                if (tabId == R.id.tab_contrats) {
                    fragment = new ListeContratsFragment();
                }
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.contentContainer, fragment)
                        .addToBackStack(fragment.getClass().getName())
                        .commit();
            }
        });
    }

    public void changeContent() {
        Fragment fragment = new BotFragment();
        if (ObjetsStatique.isEstSurGuichet()) {
            fragment = new GuichetFragment();
        } else {
            fragment = new TagFragment();
        }
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentContainer, fragment)
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }

    /**
     * Initialisation du menu sur la barre d'action en haut
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_accueil, menu);

        MenuItem userAction = (MenuItem) menu.findItem(R.id.user_action);
        MenuItem userSpace = (MenuItem) menu.findItem(R.id.user_space);

        if (SessionManager.estConnecte()) {
            userAction.setVisible(false);
            userSpace.setVisible(true);
        } else {
            userAction.setVisible(true);
            userSpace.setVisible(false);
        }
        return true;
    }

    /**
     * Evenement à appeler lorsqu'on clique les menus en haut
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.user_signup) {
            Intent intent = new Intent(AccueilActivity.this, SaisiInfoClientActivity.class);
            this.startActivity(intent);
            return true;
        } else if (id == R.id.user_connect) {
            Intent intent = new Intent(AccueilActivity.this, ConnexionActivity.class);
            this.startActivity(intent);
            return true;
        } else if (id == R.id.user_space) {
            Intent intent = new Intent(AccueilActivity.this, EspaceClientActivity.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, null,
                null);
//        if (SessionManager.estConnecte()) {
//            Intent intent = new Intent(this, VerrouillageActivity.class) ;
//            startActivity(intent);
//        }
        System.out.println("on resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
        System.out.println("on pause");
    }

    @Override
    public void onNewIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action) ||
                NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action) ||
                NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
            //Méthode qui va traiter le tag NFC
            processNfcIntent(intent);
        }
        System.out.println("on new intent");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public void processNfcIntent(Intent intent) {
        try {
            //Infos sur le tag
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            byte[] id = tag.getId();
            String[] technologies = tag.getTechList();
            int content = tag.describeContents();
            Ndef ndef = Ndef.get(tag);
            boolean isWritable = ndef.isWritable();
            boolean canMakeReadOnly = ndef.canMakeReadOnly();
            //Récupération des messages
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                    NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgs;
            String messageDansTag = "";
            //Boucle sur les enregistrements
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                    NdefRecord record = msgs[i].getRecords()[i];
                    byte[] idRec = record.getId();
                    short tnf = record.getTnf();
                    byte[] type = record.getType();
                    byte[] payload = record.getPayload();
                    messageDansTag += readText(record);

                    //Utiliser ?
                    //Laisser Android choisir l’appli par défaut si type URI ?
                    if (Arrays.equals(type, NdefRecord.RTD_URI)) {
                        Uri uri = record.toUri();
                        Intent inte = new Intent(Intent.ACTION_VIEW);
                        inte.setData(uri);
                        startActivity(inte);
                    }
                }
            } else {
                //Tag de type inconnu, tester une récupération du contenu hexadécimal ?
                byte[] empty = new byte[]{};
                NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN,
                        empty, empty, empty);
                NdefMessage msg = new NdefMessage(new NdefRecord[]{record});
                msgs = new NdefMessage[]{msg};

            }

            // message : idAgence/nomAgence

            // initialisation du token
            FirebaseInitializer fcmInitializer = new FirebaseInitializer();
            fcmInitializer.initFcm();

            //traitement des informations dans le tag
            NouveauClientAsync nouveauClientAsync = new NouveauClientAsync();
            nouveauClientAsync.setAccueilActivity(this);
            // message dans tag : idAgence
            String[] params = {messageDansTag, FirebaseInitializer.getCurrentToken()};
            nouveauClientAsync.execute(params);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private String readText(NdefRecord record) throws UnsupportedEncodingException {
        /*
         * See NFC forum specification for "Text Record Type Definition" at 3.2.1
         *
         * http://www.nfc-forum.org/specs/
         *
         * bit_7 defines encoding
         * bit_6 reserved for future use, must be 0
         * bit_5..0 length of IANA language code
         */

        byte[] payload = record.getPayload();

        // Get the Text Encoding
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";

        // Get the Language Code
        int languageCodeLength = payload[0] & 0063;

        // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");
        // e.g. "en"

        // Get the Text
        return new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
    }


}
