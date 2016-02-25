package no.webbydebby.storagetool;

import java.io.InputStream;
import java.util.List;

/**
 * Dette interfacet definerer kjernefunksjonaliteten til en StorageProvider.
 * 
 * Kjente implementasjoner er:
 * StorageProviderLocalFilestoreImpl
 * StorageProviderStoragetoolImpl.java
 
 */
public interface StorageProviderDefinition {
	void init(String teamcontainer);
	void upload(InputStream data);
	void setSketchname(String sketchName);
	InputStream download();
	List<String> getFilenames();
}