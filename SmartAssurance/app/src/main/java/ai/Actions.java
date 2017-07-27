package ai;

import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ai.ui.BulleUI;
import ai.ui.CardListUI;
import ai.ui.CardUI;
import ai.ui.UIElement;
import modeles.produit.ProduitView;

/**
 * Created by Misaina on 27/07/2017.
 */

public class Actions {
    private Context context;

    public Actions(Context context) {
        this.context = context;
    }

    public UIElement listeProduit() {
        List<ProduitView> produits = new ArrayList<ProduitView>();
        for (int i = 0; i < 10; i++) {
            produits.add(new ProduitView(i, "produit " + i));
        }
        List<CardUI> cards = new ArrayList<CardUI>(produits.size());
        for (int i = 0; i < produits.size(); i++) {
            CardUI card = new CardUI(getContext());
            card.setText(produits.get(i).getNom());
            TextView t = new TextView(getContext());
            t.setText(produits.get(i).getNom());
            card.getOptionsCardLayout().addView(t);
            cards.add(card);
        }
        CardListUI cardListUI = new CardListUI(getContext());
        cardListUI.addCards(cards);
        return cardListUI;
    }

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

    public UIElement infoProduit() {
        CardUI cardUI = new CardUI(getContext());
        cardUI.setText("info produit");

        Button b1 = new Button(getContext());
        b1.setText("Souscrire");

        Button b2 = new Button(getContext());
        b2.setText("Info");

        cardUI.getOptionsCardLayout().addView(b1);
        cardUI.getOptionsCardLayout().addView(b2);

        return cardUI;
    }

    public UIElement name() {
        BulleUI bulleUi = new BulleUI(getContext(), 0);
        bulleUi.setTextInBulle("my name is jarvis");
        return bulleUi;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
