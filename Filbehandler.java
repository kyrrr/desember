import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Filbehandler {
	
	String pathname;
    JFrame mainFrame;
	private List<Point> p;
	private List<Color> c;
	private List<Integer> v;


	public Filbehandler(String path, JFrame jf){
		pathname = path;
		mainFrame = jf;
	};
	
	public void setPath(String s){
		pathname = s;
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
	public void saveLists (){
		try {
			FileOutputStream fos = new FileOutputStream(pathname);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(p);
			oos.writeObject(c);
			oos.writeObject(v);
			oos.flush();
			oos.close();
			fos.close();
			System.out.println("SavedPoints");

		} catch (Exception ex) {
			System.out.println("Feil ved skriving av vektor");
			ex.printStackTrace();
		}

	}


//Returnerer en liste over alle punktene i angitt fil
	public void loadLists(){
		try {
			FileInputStream fis = new FileInputStream(pathname);
			ObjectInputStream ois = new ObjectInputStream(fis);
			p = (List<Point>) ois.readObject();
			c = (List<Color>) ois.readObject();
			v = (List<Integer>) ois.readObject();
			ois.close();
			fis.close();
			Tegneprogram.displayListe = p;
			Tegneprogram.fargeListe= c;
			Tegneprogram.verktoyListe= v;

		} catch (Exception ex) {
			System.out.println("Feil ved lesing av vektor");
		}
    }
	//forandrer hvilken fil som er aktiv, dvs forandrer pathname til valgt fil
	public void newPath(){
	JFileChooser f = new JFileChooser();
	//Gj�r at filvelgeren �pnes i den aktive mappen (som allerede er �pen)
    f.setCurrentDirectory(new File(System.getProperty("user.dir")));
    try{
    int result = f.showOpenDialog(null);
    //sjekker om en fil er valgt
    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = f.getSelectedFile();
        pathname = selectedFile.getPath();
        mainFrame.setTitle(pathname);
    }
    }
    catch(Exception e){
    	JOptionPane.showMessageDialog(mainFrame, "Feil ved lesing av fil");
    }

	}
}
