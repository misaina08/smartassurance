package ai;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.aro.misaina.smartassurance.BotFragment;
import com.aro.misaina.smartassurance.MapAgenceActivity;
import com.aro.misaina.smartassurance.SRAutoMotoPopFragment;
import com.aro.misaina.smartassurance.SRRetraitePopFragment;

import java.util.ArrayList;
import java.util.List;

import ai.ui.BulleListUI;
import ai.ui.BulleUI;
import ai.ui.CardListUI;
import ai.ui.CardUI;
import ai.ui.UIElement;
import modeles.Agence;
import modeles.produit.Produit;
import services.ObjetsStatique;
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
    public UIElement listeProduit() {

        List<CardUI> cards = new ArrayList<CardUI>(ObjetsStatique.getProduits().size());
        for (final Produit p : ObjetsStatique.getProduits()) {
            CardUI card = new CardUI(getContext().getActivity());
            card.setText(p.getNom());

            // ajout du bouton info pour le card
            Button buttonInfo = new Button(getContext().getActivity());
            buttonInfo.setText("Info");

            // ajout du bouton souscrire
            Button buttonSouscrire = new Button((getContext().getActivity()));
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
            buttonSouscrire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (p.getId()) {
                        case 2:

                            SRAutoMotoPopFragment amPopFragment = new SRAutoMotoPopFragment();
                            amPopFragment.setBotFragment(context);
                            amPopFragment.show(context.getFragmentManager(), "Automoto popup");
                            break;

                        case 3:
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
        CardListUI cardListUI = new CardListUI(getContext().getActivity());
        cardListUI.addCards(cards);
        return cardListUI;
    }

    /**
     * Agence la plus proche
     *
     * @return
     */
    public UIElement agenceProche() {
        Coordonnee currPos = new Coordonnee(new Double(-18.879202), new Double(47.507904));
        CoordonneeUtil coordonneeUtil = new CoordonneeUtil();

        Agence agenceProche = coordonneeUtil.getAgenceProche(currPos);
//        CurrentPositionAsync async = new CurrentPositionAsync();
//        async.setActions(this);
//        async.setActivity(getContext().getActivity());
//        async.execute();
//        System.out.println(agenceProche());

        CardUI cardUI = new CardUI(getContext().getActivity());
        cardUI.setText(agenceProche.getNom());

        Button b1 = new Button(getContext().getActivity());
        b1.setText("Voir dans le map");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext().getActivity(), MapAgenceActivity.class);
                getContext().getActivity().startActivity(intent);
            }
        });

        Button b2 = new Button(getContext().getActivity());
        b2.setText("Envoyer un mail");

        Button b3 = new Button(getContext().getActivity());
        b3.setText("Appeler");

        cardUI.getOptionsCardLayout().addView(b1);
        cardUI.getOptionsCardLayout().addView(b2);
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
        BulleUI bulleUI = new BulleUI(getContext().getActivity(), 0);
        bulleUI.setTextInBulle("Informations sur le produit " + produit.getNom());

        BulleUI bulleUI1 = new BulleUI(getContext().getActivity(), 0);
        bulleUI1.setTextInBulle("wawa ");

        BulleUI bulleUI2 = new BulleUI(getContext().getActivity(), 0);
        bulleUI2.setTextInBulle("huhu");

        List<BulleUI> list = new ArrayList<BulleUI>();
        list.add(bulleUI);
        list.add(bulleUI1);
        list.add(bulleUI2);

        BulleListUI bulleListUI = new BulleListUI(getContext().getActivity());
        bulleListUI.addBulles(list);

        getContext().sendFromUI(bulleListUI, "Informations sur le produit");
    }

    public UIElement name() {
        BulleUI bulleUi = new BulleUI(getContext().getActivity(), 0);
        bulleUi.setTextInBulle("my name is jarvis");
        return bulleUi;
    }


    public BotFragment getContext() {
        return context;
    }

    public void setContext(BotFragment context) {
        this.context = context;
    }
}
