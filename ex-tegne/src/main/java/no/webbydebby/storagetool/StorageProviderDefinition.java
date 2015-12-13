package no.webbydebby.storagetool;

import java.io.InputStream;
import java.util.List;

/**
 * Dette interfacet definerer kjernefunksjonaliteten til en StorageProvider.
 * 
 * Kjente implementasjoner er:
 * StorageProviderLocalFilestoreImpl
 * StorageProviderStoragetoolImpl.java
 * 
 * Det er imidlertid et problem med Storagetool pt, men dette kan endre seg ...
 * 
 * 
 * NB: Dette interfacet er ikke optimalt, men er diktert av hvordan storagetool/Storage.class 
 * er implementert  - dessverre
 * 
 * Moral: Write to interfaces, not to implementations!!!
 *
 */
public interface StorageProviderDefinition {
	void init(String teamcontainer);
	void upload(InputStream data);
	void setSketchname(String sketchName);
	InputStream download();
	List<String> getFilenames();
}