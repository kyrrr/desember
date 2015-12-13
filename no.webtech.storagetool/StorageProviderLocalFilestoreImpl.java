package no.webtech.storagetool;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StorageProviderLocalFilestoreImpl implements StorageProviderDefinition {

	private String filePrefix;
	private String sketchName;

	@Override
	public void init(String teamcontainer) {
		if (filePrefix != null)
			throw new IllegalThreadStateException("Cannot call init more than once...");
		filePrefix = teamcontainer;
	}

	@Override
	public void upload(InputStream data) {
		try {
			FileOutputStream fos = new FileOutputStream(filePrefix + "-" + sketchName);
			byte[] ba = new byte[102400];
			int i;
			while ((i = data.read(ba)) != -1) {
				fos.write(ba, 0, i);
			}
			fos.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void setSketchname(String sketchName) {
		if (filePrefix == null)
			throw new IllegalThreadStateException("Must first call init ...");
		this.sketchName = sketchName;
	}

	@Override
	public InputStream download() {

		try {
			File file = new File(filePrefix + "-" + sketchName);
			if (file.exists() && file.isFile() && file.canRead()) {
				byte[] buffer = new byte[(int) file.length()];
				FileInputStream fis = new FileInputStream(file);
				int numbrOfBytesRead = fis.read(buffer);
				fis.close();
				if (numbrOfBytesRead == buffer.length) {
					return new ByteArrayInputStream(buffer); // Fine :-) Return this as an InputStream
				} else {
					// Error during read
					throw new IllegalStateException("Wrong number of bytes. We did read " + numbrOfBytesRead
							+ " bytes but expected " + buffer.length);
				}
			} else {
				// Error accessability
				throw new IllegalStateException("Cannot access file " + filePrefix + "-" + sketchName);
			}

		} catch (IOException e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}
	}
/*
	@Override
	public List<String> getFilenames() {
		List<String> ret = new ArrayList<String>();
		ret.add("kyro");
		ret.add("kyro2");
		
		return ret;
	}
*/	
	public List<String> getFilenames() {
		List<String> ret = new ArrayList<String>();
	
		File folder = new File("./");
		File[] listOfFiles = folder.listFiles();
		
		for(int i = 0; i < listOfFiles.length; i++){
			if(listOfFiles[i].isFile()){
			String name = listOfFiles[i].getName();
				if(name.startsWith(filePrefix+"-")){
					ret.add(name.substring(filePrefix.length() + 1));
				}
			}
		}
		
		return ret;
	}

}
