import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;


public class HovedVindu extends JFrame{
	
	public HovedVindu(String path, Boolean loaded) {
		startUI(path, loaded);
	}

	private void startUI(String path, Boolean loaded) {
		setTitle(path);
		setSize(800, 700);
		setBackground(Color.black);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tegneprogram draw = new Tegneprogram(path, loaded, this);
		Fargevelger fv = new Fargevelger();
		add(draw, BorderLayout.CENTER);
		add(fv,BorderLayout.WEST);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setVisible(true);
	}

}
