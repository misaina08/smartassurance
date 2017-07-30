package ai;

import android.view.View;
import android.widget.Button;

import com.aro.misaina.smartassurance.BotActivity;
import com.aro.misaina.smartassurance.SRAutoMotoPopFragment;

import java.util.ArrayList;
import java.util.List;

import ai.ui.BulleListUI;
import ai.ui.BulleUI;
import ai.ui.CardListUI;
import ai.ui.CardUI;
import ai.ui.UIElement;
import modeles.produit.Produit;

/**
 * Created by Misaina on 27/07/2017.
 */

public class Actions {
    private BotActivity context;

    public Actions(BotActivity context) {
        this.context = context;
    }

    /**
     * Liste tous les produits
     * @return
     */
    public UIElement listeProduit() {
        final List<Produit> produits = new ArrayList<Produit>();
        for (int i = 0; i < 10; i++) {
            produits.add(new Produit(i, "produit " + i));
        }
        List<CardUI> cards = new ArrayList<CardUI>(produits.size());
        for (final Produit p : produits) {
            CardUI card = new CardUI(getContext());
            card.setText(p.getNom());

            // ajout du bouton info pour le card
            Button buttonInfo = new Button(getContext());
            buttonInfo.setText("Info");

            // ajout du bouton souscrire
            Button buttonSouscrire = new Button((getContext()));
            buttonSouscrire.setText("Souscrire");

            buttonInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        infoProduit(p);
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            });
            buttonSouscrire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SRAutoMotoPopFragment amPopFragment = new SRAutoMotoPopFragment();
                    amPopFragment.setBotActivity(context);
                    amPopFragment.show(context.getFragmentManager(), "Automoto popup");
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
     * @return
     */
    public UIElement agenceProche() {
        CardUI cardUI = new CardUI(getContext());
        cardUI.setText("agence proche");

        Button b1 = new Button(getContext());
        b1.setText("Consulter");

        Button b2 = new Button(getContext());
        b2.setText("Voir dans le map");

        cardUI.getOptionsCardLayout().addView(b1);
        cardUI.getOptionsCardLayout().addView(b2);

        return cardUI;
    }

    /**
     * Informations sur un produit
     * @param produit
     * @throws Exception
     */
    public void infoProduit(Produit produit) throws Exception{
        BulleUI bulleUI = new BulleUI(getContext(), 0);
        bulleUI.setTextInBulle("Informations sur le produit " + produit.getNom());

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



    public BotActivity getContext() {
        return context;
    }

    public void setContext(BotActivity context) {
        this.context = context;
    }
}
