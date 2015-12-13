package no.webbydebby;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Odin on 11/22/2015.
 */
public class Fargevelger extends MPanel implements ActionListener {
    MPanel cards;
    final static String knapper = "Velg Tegneverktoy";
    final static String slides = "Endre farge og st�rrelser";
    JButton jbt8 = new JButton("Velg Farge");
    JButton Avbryt = new JButton("Velg Farge");

    public Fargevelger(){
        kortSamling();
    }

    public void kortSamling() {

        setLayout(new GridBagLayout());    
        GridBagConstraints gbc = new GridBagConstraints();

        KnappeBoks kb = new KnappeBoks();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(kb,gbc);
        //card1.add(jbt8);
        //jbt8.setBackground(Tegneprogram.farge);
        //jbt8.addActionListener(this);


        ColorSlider cs = new ColorSlider();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(cs,gbc);

        //card2.add(Avbryt);
        //Avbryt.addActionListener(this);

        //Create the panel that contains the "cards".
        //cards = new JPanel(new CardLayout());
        //card1.add(card1, knapper);
        //add(gl);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        if (evt.getSource() == jbt8) {
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, String.valueOf(slides));
            Tegneprogram.tegnet=0;

        }
        else if (evt.getSource() == Avbryt) {
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, String.valueOf(knapper));
            Tegneprogram.tegnet=0;

        }

    }
}
