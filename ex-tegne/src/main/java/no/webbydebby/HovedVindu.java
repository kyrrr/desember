package no.webbydebby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import no.webbydebby.storagetool.StorageProviderDefinition;


public class HovedVindu /*extends JFrame*/{
	
	private StorageProviderDefinition storage;
	private JFrame jframe;

	public HovedVindu(String sketchName, Boolean loaded, StorageProviderDefinition storage, JFrame jframe) {
		this.jframe=jframe;
		this.storage = storage;
		startUI(sketchName, loaded);
	}

	private void startUI(String sketchName, Boolean loaded) {
		jframe.setTitle(sketchName);
		jframe.setSize(800, 700);
		jframe.setBackground(Color.black);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tegneprogram draw = new Tegneprogram(sketchName, loaded, jframe, storage, jframe);
		Fargevelger fv = new Fargevelger();
		jframe.add(draw, BorderLayout.CENTER);
		jframe.add(fv,BorderLayout.WEST);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe.setLocation(dim.width/2-jframe.getSize().width/2, dim.height/2-jframe.getSize().height/2);
		jframe.setVisible(true);
	}

}
