import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Filvelger extends JFrame implements ActionListener{

	JButton nyKnapp = new JButton("Ny");
	JButton eksistKnapp = new JButton("Eksisterende");
	String path;
	Boolean loaded;

	public Filvelger(){
		setLayout(new BorderLayout());
		Panel pan = new Panel();
		nyKnapp.addActionListener(this);
		pan.add(nyKnapp);
		eksistKnapp.addActionListener(this);
		pan.add(eksistKnapp);
		add("North", pan);
		setSize(350, 200);
		setBackground(Color.black);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setVisible(true);


	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nyKnapp) {
			lagNyFil();
		}
		else if (e.getSource() == eksistKnapp) {
			lesTegning();
		}
	}
	
	public String getPath(){
		return path;
	}
	
	public void lagNyFil(){
		//TODO Kanskje ikke overskrive eksisterende filer?
		 path = JOptionPane.showInputDialog("Filnavn:");
		 if (new File(path).isFile()){
		    	JOptionPane.showMessageDialog(this, "Filen eksisterer allerede");
		    	lagNyFil();

		 }
		 else{ 
		 loaded = false;
		 new HovedVindu(path, loaded);
		 dispose();
		 }

	}
	
	//Velger en eksisterende fil
	public void lesTegning(){
		
        JFileChooser f = new JFileChooser();
        f.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int result = f.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = f.getSelectedFile();
            path = selectedFile.getPath();
        }
        loaded = true;
        new HovedVindu(path, loaded);
        dispose();
		
	}

}
