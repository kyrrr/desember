package no.webbydebby;

import java.awt.Panel;

import javax.swing.JPanel;

public class JPanelFixForOSX extends Panel {
//unngår et kjent problem med JPanel for linux og mac osx.
	//Denne klassen endres 
	
	
	//i dette tilfellet forsvinner lerettet under tegning.
//extends Panel for mac, JPanel for windows
}
