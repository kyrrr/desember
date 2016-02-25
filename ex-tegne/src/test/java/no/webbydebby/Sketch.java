package no.webbydebby;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Sketch {
	static List<Point> punktListe = null;
	static List<Color> fargeListe = null;
    static List<Integer> verktoyListe = null;
    
    public Sketch(){
    	punktListe = new ArrayList<>();
    	fargeListe = new ArrayList<>();
        verktoyListe = new ArrayList<>();
    }
    
    public void addPunktFargeVerktoy(Point point, Color color, Integer verktoy){
    	punktListe.add(point);
    	fargeListe.add(color);
    	verktoyListe.add(verktoy);
    }
	
}
