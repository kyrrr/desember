package no.webbydebby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import no.webbydebby.storagetool.StorageProviderDefinition;


public class HovedVindu extends JFrame{
	
	private StorageProviderDefinition storage;

	public HovedVindu(String sketchName, Boolean loaded, StorageProviderDefinition storage) {
		this.storage = storage;
		startUI(sketchName, loaded);
	}

	private void startUI(String sketchName, Boolean loaded) {
		setTitle(sketchName);
		setSize(800, 700);
		setBackground(Color.black);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tegneprogram draw = new Tegneprogram(sketchName, loaded, this, storage);
		Fargevelger fv = new Fargevelger();
		add(draw, BorderLayout.CENTER);
		add(fv,BorderLayout.WEST);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setVisible(true);
	}

}
