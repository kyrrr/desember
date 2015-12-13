package no.webbydebby;

import java.util.List;

import javax.swing.JFrame;

import no.webbydebby.chooser.SwingSketchChooserImpl;
import no.webbydebby.storagetool.StorageProviderDefinition;
import no.webbydebby.storagetool.StorageProviderLocalFilestoreImpl;

public class Entrypoint {

	public static void main(String[] args) {
				
				StorageProviderDefinition storage = 
				//new StorageProviderStoragetoolImpl(); // Kanskje etterhvert fra "skyen"
				new StorageProviderLocalFilestoreImpl(); // Men vi bruker lokalt foerst :-)
				storage.init("sketches-3");
				List<String> filenames = storage.getFilenames();
				String sketchName = new SwingSketchChooserImpl().chooseFromListOrOther(filenames);
				
			
				boolean isExistingSketch = filenames.contains(sketchName);
				storage.setSketchname(sketchName);
				System.out.println("Sketchname: " + sketchName + " Existing?: " + isExistingSketch);
				JFrame jframe = new JFrame();
				boolean loaded = true;
				new HovedVindu(sketchName, loaded, storage, jframe);
				
	}
	
}
