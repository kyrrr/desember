package no.webbydebby.chooser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

import no.webbydebby.JPanelFixForOSX;

public class RunChooserStandalone implements ChooseFromListOrOtherDefinition {

	JDialog jdialog = null;
	private String valgtFile;

	private void setValgtFil(String valgtFile) {
		this.valgtFile = valgtFile;
	}

	@Override
	public String chooseFromListOrOther(List<String> filListe) {
		jdialog = new JDialog();
		JPanelFixForOSX chooserPanel = new JPanelFixForOSX();
		JButton go = new JButton("Velg");

		chooserPanel.setLayout(new BorderLayout());

		chooserPanel.add("North", go);

		String[] fileListArray = new String[filListe.size()];
		filListe.toArray(fileListArray);

		JComboBox<String> jcombobox = new JComboBox<>(fileListArray);
		
		
		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(valgtFile==null){
					valgtFile= (String)jcombobox.getSelectedItem();
				}
				System.out.println("Button pressed, valget ditt er " + valgtFile);
				jdialog.setVisible(false);
				//drep jdialog
				jdialog.dispose();
			}
		});
		
		
		jcombobox.setEditable(true);
		jdialog.add("North", jcombobox);
		jdialog.add("South", chooserPanel);
		// container.setSize(80, 140);
		jdialog.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		jdialog.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		jdialog.setLocation(dim.width / 2 - jdialog.getSize().width / 2,
				dim.height / 2 - jdialog.getSize().height / 2);
		jdialog.setVisible(true);
		
		
		
		

		jcombobox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setValgtFil((String) ((JComboBox) e.getSource()).getSelectedItem());
				
			}

		});
		return valgtFile;
	}

}
