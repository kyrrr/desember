/**
 * Created by Odin on 11/18/2015.
 */
package no.webbydebby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Random;

import javax.swing.JButton;

class ColorSlider extends JPanelFixForOSX {




    public ColorSlider() {
        setSize(400,300);
        ColorPicker cp = new ColorPicker(this);
        add(cp, BorderLayout.SOUTH);
        setVisible(true);
    }

}

class ColorPicker extends JPanelFixForOSX implements AdjustmentListener, ActionListener {
    private int scrollVerdi1;
    private int scrollVerdi2;
    private int scrollVerdi3;
    // ------------------------------------------------------------
    Scrollbar scr = new Scrollbar(Scrollbar.HORIZONTAL,255,0,0,255 );
    Scrollbar scg = new Scrollbar(Scrollbar.HORIZONTAL,255,0,0,255 );
    Scrollbar scb = new Scrollbar(Scrollbar.HORIZONTAL,255,0,0,255 );
    JPanelFixForOSX c = null;
    //JButton VelgFarge = new JButton("Velg Farge");
    JButton Tilfeldig = new JButton("Tilfeldig");


    public ColorPicker(JPanelFixForOSX ic) {
        c = ic;
        setLayout(new GridBagLayout());
        //add(scr);
        //add(scg);
        //add(scb);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        scr.setBackground(Color.WHITE);
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(scr,gbc);

        scg.setBackground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(scg,gbc); 
        
        scb.setBackground(Color.WHITE);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(scb,gbc);
        
        scr.addAdjustmentListener(this);
        scg.addAdjustmentListener(this);
        scb.addAdjustmentListener(this);
        //add(VelgFarge);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(Tilfeldig,gbc);
        //add(Tilfeldig);

  
        //VelgFarge.addActionListener(this);
        Tilfeldig.addActionListener(this);

    }


    public void adjustmentValueChanged(AdjustmentEvent a) {
        c.setBackground(new Color(scr.getValue(),scg.getValue(),scb.getValue()));
        Tegneprogram.farge=new Color(scr.getValue(),scg.getValue(),scb.getValue());
        Tegneprogram.tegnet=0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //if (e.getSource() == VelgFarge) {

          //  Tegneprogram.tegnet=0;

        //}
        /*else*/ if (e.getSource() == Tilfeldig) {
            Random r = new Random();
            Tegneprogram.farge=new Color(scrollVerdi1=r.nextInt(256), scrollVerdi2=r.nextInt(256), scrollVerdi3=r.nextInt(256));
            c.setBackground(Tegneprogram.farge);
            Tegneprogram.tegnet=0;
            scr.setValue(scrollVerdi1);
            scg.setValue(scrollVerdi2);
            scb.setValue(scrollVerdi3);

        }

    }
}