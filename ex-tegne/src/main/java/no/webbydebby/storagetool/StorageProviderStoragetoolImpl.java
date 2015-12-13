package no.webbydebby.storagetool;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

import com.microsoft.azure.storage.StorageException;

import storagetool.Storage;

public class StorageProviderStoragetoolImpl implements StorageProviderDefinition{

	Storage storage;
	@Override
	public void init(String teamcontainer) {
		if (storage!=null) throw new IllegalThreadStateException("Cannot call init more than once...");
		storage= new Storage(teamcontainer);
	}

	@Override
	public void upload(InputStream data) {
		try {
			storage.upload(data);
		} catch (URISyntaxException | StorageException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setSketchname(String otherSketch) {
		if (storage==null) throw new IllegalThreadStateException("Must frst call init ...");
		storage.setSketchname(otherSketch);
	}

	@Override
	public InputStream download() {
		try {
			return storage.download();
		} catch (URISyntaxException | StorageException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String> getFilenames() {
		
		return storage.getFilenames();
	}
	
	

}
