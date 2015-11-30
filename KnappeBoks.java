import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KnappeBoks extends JPanel implements ActionListener {
	
    JButton jbt1 = new JButton("Tegne");
    JButton jbt2 = new JButton("Viskelær");
    JButton jbt3 = new JButton("Fylt Kvadrat");
    JButton jbt4 = new JButton("Hul kvadrat");
    JButton jbt5 = new JButton("Fylt Sirkel");
    JButton jbt6 = new JButton("Hul Sirkel");

    Box VerktoyBoks = Box.createVerticalBox();
    Color std;
    


    public KnappeBoks() {
        TegneVerktoyBoks();
    }



    private void TegneVerktoyBoks() {

    	VerktoyBoks.add(jbt1);
        jbt1.addActionListener(this);

        VerktoyBoks.add(jbt2);
        jbt2.addActionListener(this);

        VerktoyBoks.add(jbt3);
        jbt3.addActionListener(this);

        VerktoyBoks.add(jbt4);
        jbt4.addActionListener(this);

        VerktoyBoks.add(jbt5);
        jbt5.addActionListener(this);

        VerktoyBoks.add(jbt6);
        jbt6.addActionListener(this);
        
        std = jbt6.getBackground();


        add(VerktoyBoks);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbt1) {Tegneprogram.verktoy=1; setColor(jbt1);}
        else if (e.getSource() == jbt2) {Tegneprogram.verktoy=2;setColor(jbt2);}
        else if (e.getSource() == jbt3) {Tegneprogram.verktoy=3;setColor(jbt3);}
        else if (e.getSource() == jbt4) {Tegneprogram.verktoy=4;setColor(jbt4);}
        else if (e.getSource() == jbt5) {Tegneprogram.verktoy=5;setColor(jbt5);}
        else if (e.getSource() == jbt6) {Tegneprogram.verktoy=6;setColor(jbt6);}


    }
    
    void resetAllColors(){
    	jbt1.setBackground(std);
    	jbt2.setBackground(std);
    	jbt3.setBackground(std);
    	jbt4.setBackground(std);
    	jbt5.setBackground(std);
    	jbt6.setBackground(std);
    }
    
    public void setColor(JButton b){
    	resetAllColors();
    	b.setBackground(Color.GRAY);
    }
}

