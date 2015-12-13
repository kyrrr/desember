package com.tutorialspoint;

import javax.swing.JFrame;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import no.webbydebby.HovedVindu;
import no.webbydebby.chooser.ChooseFromListOrOtherDefinition;
import no.webbydebby.chooser.SimpleConsoleInputChooserImpl;
import no.webbydebby.chooser.SwingSketchChooserImpl;
import no.webbydebby.storagetool.StorageProviderDefinition;
import no.webbydebby.storagetool.StorageProviderLocalFilestoreImpl;

@Configuration
public class TegneprogramConfig {

	@Bean
	public StorageProviderDefinition storage() {
		StorageProviderDefinition r = new StorageProviderLocalFilestoreImpl();
		r.init("sketches-3");
		return r;
	}

	
	@Bean 
	public ChooseFromListOrOtherDefinition chooser(){
		return new SwingSketchChooserImpl();
				
				//impleConsoleInputChooserImpl(System.in);
	
	}
	
	@Bean 
	public HovedVindu hovedvindu(ChooseFromListOrOtherDefinition choose, StorageProviderDefinition storage ){
		String sketchName = choose.chooseFromListOrOther(storage.getFilenames());
		storage.setSketchname(sketchName);
		System.out.println("Sketchname: " + sketchName);
		return new HovedVindu(sketchName, true, storage, new JFrame());
	}
}