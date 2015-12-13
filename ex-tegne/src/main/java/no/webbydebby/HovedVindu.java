package no.webbydebby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import no.webbydebby.storagetool.StorageProviderDefinition;


public class HovedVindu /*extends JFrame*/{
	//får inn jframe i stedet for å være en jframe
	private StorageProviderDefinition storage;
	private JFrame jframe;
	private String sketchName;
	private Boolean loaded;

	public HovedVindu(String sketchName, Boolean loaded, StorageProviderDefinition storage, JFrame jframe) {
		this.jframe=jframe;
		this.storage = storage;
		this.sketchName = sketchName;
		this.loaded = loaded;
		// startUI(sketchName, loaded);
	}

	public void startUI() {
		jframe.setTitle(sketchName);
		jframe.setSize(800, 700);
		jframe.setBackground(Color.black);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tegneprogram draw = new Tegneprogram(sketchName, loaded, storage, jframe);
		Fargevelger fv = new Fargevelger();
		jframe.add(draw, BorderLayout.CENTER);
		jframe.add(fv,BorderLayout.WEST);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe.setLocation(dim.width/2-jframe.getSize().width/2, dim.height/2-jframe.getSize().height/2);
		jframe.setVisible(true);
	}

}
