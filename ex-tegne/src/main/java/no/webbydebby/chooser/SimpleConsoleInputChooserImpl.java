package no.webbydebby.chooser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class SimpleConsoleInputChooserImpl implements ChooseFromListOrOtherDefinition {
	private InputStream in;

	public SimpleConsoleInputChooserImpl(InputStream in){
		this.in=in;
	}

	@Override
	public String chooseFromListOrOther(List<String> choices) {

		if (choices != null && choices.size() > 0) {
			System.out.println("Velg en av sketchene under ved å skrive nummeret/navnet på alternativet:");
			for (int i = 0; i < choices.size(); i++) {
				System.out.println("[" + i + "] " + choices.get(i));
			}
			System.out.println("eller...");
		}
		System.out.println("Skriv inn et nytt navn ===> ");

		// prompt the user to enter their name
		System.out.print("Ditt valg: ");

		// open up standard input
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String valg = null;

		// read the username from the command-line; need to use try/catch with
		// the
		// readLine() method
		try {
			valg = br.readLine();
		} catch (IOException ioe) {
			System.out.println("IO error trying to read your name!");
			System.exit(1);
		}
		int valgNr = -1;
		try {
			valgNr = Integer.parseInt(valg);
		} catch (NumberFormatException e) {
			// Fine, vi antar det er tekst;
			return valg;
		}
		if (valgNr >= 0 && valgNr < choices.size()) {
			return choices.get(valgNr);
		}
		throw new IllegalArgumentException("Du må enten velge ett tall eller noe tekst");
	}

}
