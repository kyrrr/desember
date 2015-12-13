package no.webtech.ex;

import java.util.List;

import no.webtech.ex.choose.AlwaysChooseFirstImpl;
import no.webtech.ex.choose.ChooseFromListOrOtherDefinition;
import no.webtech.ex.choose.SimpleConsoleInputChooserImpl;
import no.webtech.storagetool.StorageProviderDefinition;
import no.webtech.storagetool.StorageProviderLocalFilestoreImpl;
import no.webtech.storagetool.StorageProviderStoragetoolImpl;

public class Entrypoint {

	public static void main(String[] args) {
				
				StorageProviderDefinition storage = 
				//new StorageProviderStoragetoolImpl(); // Kanskje etterhvert fra "skyen"
				new StorageProviderLocalFilestoreImpl(); // Men vi bruker lokalt foerst :-)
				storage.init("sketches-3");
				ChooseFromListOrOtherDefinition choose = new SimpleConsoleInputChooserImpl(System.in);
				List<String> filenames = storage.getFilenames();
				String sketchName = choose.chooseFromListOrOther(filenames);
				boolean isExistingSketch = filenames.contains(sketchName);
				storage.setSketchname(sketchName);
				System.out.println("Sketchname: " + sketchName + " Existing?: " + isExistingSketch);
				
				boolean loaded = true;
				new HovedVindu(sketchName, loaded, storage);
	}
	
}
