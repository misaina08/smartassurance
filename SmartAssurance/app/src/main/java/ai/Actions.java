package ai;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;

import com.aro.misaina.smartassurance.BotFragment;
import com.aro.misaina.smartassurance.DevisAutoActivity;
import com.aro.misaina.smartassurance.MapAgenceActivity;
import com.aro.misaina.smartassurance.SRAutoMotoPopFragment;
import com.aro.misaina.smartassurance.SRRetraitePopFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import ai.ui.BulleListUI;
import ai.ui.BulleUI;
import ai.ui.CardListUI;
import ai.ui.CardUI;
import ai.ui.UIElement;
import async.bot.SignificationTermeAsync;
import async.retraite.EstimationAsync;
import async.retraite.SituationCompteAsync;
import modeles.Agence;
import modeles.produit.Produit;
import services.ObjetsStatique;
import sqlite.bot.CurrentQuestionDao;
import sqlite.bot.QuestionCotisationDao;
import utilitaire.Coordonnee;
import utilitaire.CoordonneeUtil;

/**
 * Created by Misaina on 27/07/2017.
 */

public class Actions {
    private BotFragment context;


    public Actions(BotFragment context) {
        this.context = context;
    }

    /**
     * Liste tous les produits
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public UIElement listeProduit() {

        List<CardUI> cards = new ArrayList<CardUI>(ObjetsStatique.getProduits().size());
        for (final Produit p : ObjetsStatique.getProduits()) {
            CardUI card = new CardUI(getContext());
            card.setText(p.getIntitule());

            // ajout du bouton info pour le card
            Button buttonInfo = new Button(getContext());
            buttonInfo.setText("Info");

            // ajout du bouton souscrire
            Button buttonSouscrire = new Button((getContext()));
            buttonSouscrire.setText("Souscrire");

            buttonInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        infoProduit(p);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

//            buttonSouscrire.setBackground(getContext().getResources().getDrawable(R.drawable.shape_blue_strock));

            buttonSouscrire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (p.getId()) {
                        case 2:
                            getContext().updateMyChat("Souscription au produit assurance auto-moto");
                            SRAutoMotoPopFragment amPopFragment = new SRAutoMotoPopFragment();
                            amPopFragment.setBotFragment(context);
                            amPopFragment.show(context.getFragmentManager(), "Automoto popup");
                            break;

                        case 3:
                            getContext().updateMyChat("Souscription au produit assurance retraite");
                            SRRetraitePopFragment retraitePopFragment = new SRRetraitePopFragment();
                            retraitePopFragment.setBotFragment(context);
                            retraitePopFragment.show(context.getFragmentManager(), "Retraite popup");
                            break;
                    }

                }
            });

            card.getOptionsCardLayout().addView(buttonInfo);
            card.getOptionsCardLayout().addView(buttonSouscrire);
            cards.add(card);
        }
        CardListUI cardListUI = new CardListUI(getContext());
        cardListUI.addCards(cards);
        return cardListUI;
    }

    /**
     * Agence la plus proche
     *
     * @return
     */
    public UIElement agenceProche() {

        final Coordonnee currPos = new Coordonnee(new Double(-18.868577), new Double(47.515464));
        CoordonneeUtil coordonneeUtil = new CoordonneeUtil();

        final Agence agenceProche = coordonneeUtil.getAgenceProche(currPos);
//        CurrentPositionAsync async = new CurrentPositionAsync();
//        async.setActions(this);
//        async.setActivity(getContext());
//        async.execute();
//        System.out.println(agenceProche());

        CardUI cardUI = new CardUI(getContext());
        cardUI.setText(agenceProche.getNom());

        Button b1 = new Button(getContext());
        b1.setText("Voir dans le map");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapAgenceActivity.class);
                Gson gson = new Gson();
                intent.putExtra("currPos", gson.toJson(currPos));
                intent.putExtra("agenceDest", gson.toJson(agenceProche));
                getContext().startActivity(intent);
            }
        });


        Button b3 = new Button(getContext());
        b3.setText("Appeler");
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + agenceProche.getTel()));
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                getContext().startActivity(callIntent);
            }
        });

        cardUI.getOptionsCardLayout().addView(b1);
        cardUI.getOptionsCardLayout().addView(b3);

        return cardUI;
    }

    /**
     * Informations sur un produit
     *
     * @param produit
     * @throws Exception
     */
    public void infoProduit(Produit produit) throws Exception {
        BulleUI bulleUI = new BulleUI(getContext(), 0);
        bulleUI.setTextInBulle("Informations sur le produit " + produit.getIntitule());

        BulleUI bulleUI1 = new BulleUI(getContext(), 0);
        bulleUI1.setTextInBulle("wawa ");

        BulleUI bulleUI2 = new BulleUI(getContext(), 0);
        bulleUI2.setTextInBulle("huhu");

        List<BulleUI> list = new ArrayList<BulleUI>();
        list.add(bulleUI);
        list.add(bulleUI1);
        list.add(bulleUI2);

        BulleListUI bulleListUI = new BulleListUI(getContext());
        bulleListUI.addBulles(list);

        getContext().sendFromUI(bulleListUI, "Informations sur le produit");
    }

    public UIElement name() {
        BulleUI bulleUi = new BulleUI(getContext(), 0);
        bulleUi.setTextInBulle("my name is jarvis");
        return bulleUi;
    }

    public UIElement aideCotisation() throws Exception {
        BulleUI bulle1 = new BulleUI(getContext(), 0);
        bulle1.setTextInBulle("D'accord.");
        BulleUI bulle2 = new BulleUI(getContext(), 0);
        bulle2.setTextInBulle("Quelle age avez vous actuellement ?");

        List<BulleUI> list = new ArrayList<BulleUI>();
        list.add(bulle1);
        list.add(bulle2);

        BulleListUI bulleListUI = new BulleListUI(getContext());
        bulleListUI.addBulles(list);

        // question suivante
        CurrentQuestionDao currentQuestionDao = new CurrentQuestionDao(getContext());
        currentQuestionDao.setCurrentQuestion("saveAgeCotisation");
        return bulleListUI;
    }

    public UIElement saveAgeCotisation(Integer age) throws Exception {
        if (age < 18 || age > 60) {
            BulleUI bulle = new BulleUI(getContext(), 0);
            bulle.setTextInBulle("Saisissez entre 18 et 60 ans");
            return bulle;
        } else {
            // enregistrement de l'age saisie
            QuestionCotisationDao questionCotisationDao = new QuestionCotisationDao(getContext());
            questionCotisationDao.setAge(age);
            CurrentQuestionDao currentQuestionDao = new CurrentQuestionDao(getContext());
            currentQuestionDao.setCurrentQuestion("estimationCotisation");
            String messageAvant = "";
            if (age < 25) {
                messageAvant = age + " ans ? Vous etes encore jeune, vous en tirerez davantage a la retraite";
            } else if (age > 25 && age < 40) {
                messageAvant = "Ahh !!! C'est bien le moment ideal d'y souscrire";
            } else if (age > 40 && age < 50) {
                messageAvant = "C'est bien ... ";
            } else if (age > 50 && age < 56) {
                messageAvant = "C'est pas encore tard. Il vous reste bien " + (60 - age) + " ans";
            } else if (age > 50 && age < 61) {
                messageAvant = "Mieux vaut tard que jamais. Souscrivez vite et deposez plus !!!!!!";
            }
            BulleUI bulle = new BulleUI(getContext(), 0);
            bulle.setTextInBulle(messageAvant);
            BulleUI bulle1 = new BulleUI(getContext(), 0);
            bulle1.setTextInBulle("Combien vous désirez avoir à l'age de retraite ?");
            List<BulleUI> listeBulle = new ArrayList<BulleUI>();
            listeBulle.add(bulle);
            listeBulle.add(bulle1);

            BulleListUI bulleListUI = new BulleListUI(getContext());
            bulleListUI.addBulles(listeBulle);

            return bulleListUI;
        }

    }


    public void estimationCotisation(Integer age, Double mtDesire, BotFragment botFragment) throws Exception {
        CurrentQuestionDao currentQuestionDao = new CurrentQuestionDao(botFragment);
        currentQuestionDao.setCurrentQuestion("aucun");

        Double[] params = {new Double(age), mtDesire};
        EstimationAsync estimationAsync = new EstimationAsync();
        estimationAsync.setBotFragment(botFragment);
        estimationAsync.execute(params);

    }

    public UIElement aideSituationRetraite() throws Exception {
        BulleUI bulle1 = new BulleUI(getContext(), 0);
        bulle1.setTextInBulle("D'accord.");
        BulleUI bulle2 = new BulleUI(getContext(), 0);
        bulle2.setTextInBulle("Saisissez votre numéro client ?");

        List<BulleUI> list = new ArrayList<BulleUI>();
        list.add(bulle1);
        list.add(bulle2);

        BulleListUI bulleListUI = new BulleListUI(getContext());
        bulleListUI.addBulles(list);

        // question suivante
        CurrentQuestionDao currentQuestionDao = new CurrentQuestionDao(getContext());
        currentQuestionDao.setCurrentQuestion("situationCompteRetraite");
        return bulleListUI;
    }

    public void situationCompteRetraite(String noclient, BotFragment botFragment) throws Exception {
        CurrentQuestionDao currentQuestionDao = new CurrentQuestionDao(botFragment);
        currentQuestionDao.setCurrentQuestion("aucun");

        String[] noClient = {noclient};
        SituationCompteAsync situationCompteAsync = new SituationCompteAsync();
        situationCompteAsync.setBotFragment(botFragment);
        situationCompteAsync.execute(noClient);

    }

    public UIElement textDevis() {
        Button buttonDevis = new Button((getContext()));
        buttonDevis.setText("Prêt");
        buttonDevis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getContext().updateMyChat("Prêt");

                    DevisAutoActivity amPopDevis = new DevisAutoActivity();
                    amPopDevis.setBotFragment(context);
                    amPopDevis.show(context.getFragmentManager(), "Automoto devis");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        CardUI card = new CardUI(getContext());
        card.setText("Ok. Je vais vous envoyer un formulaire à remplir");

        card.getOptionsCardLayout().addView(buttonDevis);
        return card;
    }

    public UIElement defaultActions() {
        List<CardUI> cards = new ArrayList<CardUI>();

        List<String> options = new ArrayList<String>();
        options.add("Agence la plus proche");
        options.add("Actualités");
        options.add("Produits");
        options.add("Devis");
        options.add("Compte retraite");
        options.add("Cotisation retraite");

        for (final String option : options) {
            CardUI card = new CardUI(getContext());
            card.setText(option);

            Button buttonOk = new Button(getContext());
            buttonOk.setText("Aller");
            buttonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        getContext().updateMyChat(option);
                        getContext().sendFromRequest(option);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            card.getOptionsCardLayout().addView(buttonOk);
            cards.add(card);
        }

        CardListUI cardListUI = new CardListUI(getContext());
        cardListUI.addCards(cards);
        return cardListUI;
    }

    public UIElement proceduresProduit(Integer idproduit) {
        Produit produit = new Produit();
        for (Produit p : ObjetsStatique.getProduits()) {
            if (p.getId().equals(idproduit)) {
                produit = p;
                break;
            }
        }

        BulleUI bulleUI = new BulleUI(getContext(), 0);
        bulleUI.setTextInBulle("Voici les procedures de souscription : " + produit.getProcedures());

        BulleUI bulleUI1 = new BulleUI(getContext(), 0);
        bulleUI1.setTextInBulle("Vous devez avoir les pieces suivantes : " + produit.getPiecesNecessaires());

        List<BulleUI> list = new ArrayList<BulleUI>();
        list.add(bulleUI);
        list.add(bulleUI1);

        BulleListUI bulleListUI = new BulleListUI(getContext());
        bulleListUI.addBulles(list);

        return bulleListUI;
    }

    public void explicationMotCle(String motcle) throws Exception {
        String[] params = {motcle};
        SignificationTermeAsync significationTermeAsync = new SignificationTermeAsync();
        significationTermeAsync.setBotFragment(getContext());
        significationTermeAsync.execute(params);

    }

    public BotFragment getContext() {
        return context;
    }

    public void setContext(BotFragment context) {
        this.context = context;
    }
}
