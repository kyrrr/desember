package no.webbydebby;

import java.awt.Color;
import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JFrame;

import no.webbydebby.chooser.ChooseFromListOrOtherDefinition;
import no.webbydebby.chooser.SimpleConsoleInputChooserImpl;
import no.webbydebby.storagetool.StorageProviderDefinition;

public class Filbehandler {
	
	String sketchname;
    JFrame mainFrame;
	private List<Point> p;
	private List<Color> c;
	private List<Integer> v;
	private StorageProviderDefinition storage;

	public Filbehandler(String path, JFrame jf, StorageProviderDefinition storage){
		this.storage = storage;
		sketchname = path;
		mainFrame = jf;
	};
	
	public void setPath(String s){
		sketchname = s;
		mainFrame.setTitle(s);
	}
	public void tempLists1 (List<Point> tempList1){
		p = tempList1;
	}
	public void tempLists2 (List<Color> tempList2){
		c = tempList2;
	}
	public void tempLists3 (List<Integer> tempList3){
		v = tempList3;
	}
	
	//Lagrer til valgt "pathname"
	public void saveLists(){
		try {
			
			//storage.setSketchname(pathname); må flyttes til filvelger-klasse, enten om ny eller eksitsterende
			
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(buffer);
			oos.writeObject(p);
			oos.writeObject(c);
			oos.writeObject(v);
			oos.flush();
			oos.close();
			buffer.close();
			InputStream in = new ByteArrayInputStream(buffer.toByteArray());
			
			storage.upload(in);
			
			
			
			System.out.println("SavedPoints");

		} catch (Exception ex) {
			System.out.println("Feil ved skriving av vektor");
			ex.printStackTrace();
		}

	}


//Returnerer en liste over alle punktene i angitt fil
	public void loadLists(){
		try {
			
			//storage.setSketchname(pathname); må flyttes til filvelger-klasse, enten om ny eller
			
			
			storage.setSketchname(sketchname);
			ObjectInputStream data = new ObjectInputStream(storage.download());
			p = (List<Point>) data.readObject();
			c = (List<Color>) data.readObject();
			v = (List<Integer>) data.readObject();
			Tegneprogram.displayListe = p;
			Tegneprogram.fargeListe = c;
			Tegneprogram.verktoyListe = v;
			
			data.close();
			
	

		} catch (Exception ex) {
			System.out.println("Feil ved lesing av vektor");
		}
    }
	//forandrer hvilken fil som er aktiv, dvs forandrer pathname til valgt fil
	public void newPath(){
		//vil lukke nåværende vindu, ny dialog. halfway there
		//HovedVindu.close();
		ChooseFromListOrOtherDefinition choose = new SimpleConsoleInputChooserImpl(System.in);
		List<String> filenames = storage.getFilenames();
		String sketchName = choose.chooseFromListOrOther(filenames);
		boolean isExistingSketch = filenames.contains(sketchName);
		storage.setSketchname(sketchName);
		System.out.println("Sketchname: " + sketchName + " Existing?: " + isExistingSketch);
		
		boolean loaded = true;
		String path = sketchName;
		new HovedVindu(path, loaded, storage);
	}
}
