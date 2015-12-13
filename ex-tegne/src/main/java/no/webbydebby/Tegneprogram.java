package no.webbydebby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.lang3.time.StopWatch;

import no.webbydebby.storagetool.StorageProviderDefinition;

public class Tegneprogram extends MPanel
		implements MouseMotionListener, MouseListener, ActionListener, Serializable, Cloneable {

	static List<Point> displayListe = new ArrayList<>();
	static List<Color> fargeListe = new ArrayList<>();
	static List<Integer> verktoyListe = new ArrayList<>();
	JButton clearBtn = new JButton("Clear");
	JButton loadBtn = new JButton("Load");
	JButton quitBtn = new JButton("Quit");
	Point start = null;
	Point slutt = null;
	StopWatch sw = new StopWatch();
	Boolean changed = false;
	Filbehandler fb;
	JFrame mainFrame;
	static int tegnet = 0;
	static int verktoy = 0;
	static Color farge = Color.BLACK;
	boolean loaded;
	private StorageProviderDefinition storage;

	public Tegneprogram(String sketchName, Boolean loaded, JFrame jf, StorageProviderDefinition storage) {
		this.storage = storage;
		addMouseMotionListener(this);
		addMouseListener(this);
		this.loaded = loaded;
		sw.start();
		mainFrame = jf;
		fb = new Filbehandler(sketchName, mainFrame, storage);

		setLayout(new BorderLayout());
		Panel pan = new Panel();
		clearBtn.addActionListener(this);
		pan.add(clearBtn);
		loadBtn.addActionListener(this);
		pan.add(loadBtn);
		quitBtn.addActionListener(this);
		pan.add(quitBtn);
		add("North", pan);
		setSize(350, 200);

		Thread a = new Thread(() -> { // Stoppeklokken kjer i sin egne,
										// asynkrone tr蘚 s� den kan kje
										// uavhengig
			saveTimer(sw);
		});

		// Starter tr蘚en
		a.start();

	}

	// N蚌 musen dras legges punktene musen dras over inn i listen DisplayListe
	public void mouseDragged(MouseEvent me) {
		changed = true;
		start = slutt;
		slutt = new Point(me.getX(), me.getY());
		// Punkt m� alltid legges inn i par, om start er null legges det inn to
		// like sluttpunkter
		if (start != null && displayListe != null) {
			displayListe.add(start);
			fargeListe.add(farge);
			verktoyListe.add(verktoy);
		} else {
			displayListe.add(slutt);
			fargeListe.add(farge);
			verktoyListe.add(verktoy);
		}
		displayListe.add(slutt);
		fargeListe.add(farge);
		verktoyListe.add(verktoy);
		repaint();
	}

	public void mouseMoved(MouseEvent me) {
		slutt = null;
	}

	public void paint(Graphics g) {

		if (tegnet == 0) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getSize().width, getSize().height);
			verktoy = 1;
		}

		g.setColor(farge);
		// prer � tegne punktene i displayListe. Kan gi error om den leser en
		// ugyldig fil

		while (tegnet < displayListe.size() - 1) {
			g.setColor(fargeListe.get(tegnet));
			verktoy = (verktoyListe.get(tegnet));
			Point p0;
			Point p1;
			p0 = displayListe.get(tegnet++);
			p1 = displayListe.get(tegnet++);

			switch (verktoy) {

			case 1:

				g.drawLine(p0.x, p0.y, p1.x, p1.y);

				break;

			case 2:

				g.setColor(Color.WHITE);

				g.fillRect(p0.x, p0.y, 50, 50);

				break;
			case 3:

				g.fillRect(p0.x, p0.y, 100, 100);

				break;
			case 4:

				g.drawRect(p0.x, p0.y, 100, 100);

				break;
			case 5:

				g.fillOval(p0.x, p0.y, 100, 100);

				break;
			case 6:

				g.drawOval(p0.x, p0.y, 100, 100);

				break;
			}
		}

	}

	public void update(Graphics g) {
		paint(g);
	}

	// knappevalg
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == clearBtn) {
			tegnet = 0;
			displayListe = new ArrayList<>();
			fargeListe = new ArrayList<>();
			verktoyListe = new ArrayList<>();
			repaint();
		} else if (e.getSource() == loadBtn) {
			fb.newPath();
			fb.loadLists();
			tegnet = 0;
			repaint();
		} else if (e.getSource() == quitBtn) {
			setVisible(false);
			System.exit(0);
		}
	}

	// Sjekker for endringer hvert 5. sekund, og evt lagrer
	public void saveTimer(StopWatch sw) {
		if (loaded) {
			fb.loadLists();
			repaint();
		}

		while (sw.isStarted()) {
			if (sw.getTime() >= 5000) {
				sw.suspend();
				sw.reset();
				sw.start();
				if (changed) {
					// lager en templiste, s� det fungerer � lagre om brukeren
					// tegner mens programmet prer � lagre
					List<Point> tempListe1 = new ArrayList<>();
					List<Color> tempListe2 = new ArrayList<>();
					List<Integer> tempListe3 = new ArrayList<>();
					tempListe1.addAll(displayListe);
					tempListe2.addAll(fargeListe);
					tempListe3.addAll(verktoyListe);
					fb.tempLists1(tempListe1);
					fb.tempLists2(tempListe2);
					fb.tempLists3(tempListe3);
					fb.saveLists();
					changed = false;
					System.out.println(sw.getTime());
				}

			}
		}
	}

	// Tegner et punkt n蚌 bruker klikker en gang
	public void mousePressed(MouseEvent e) {
		changed = true;
		Point p = new Point(e.getX(), e.getY());
		displayListe.add(p);
		displayListe.add(p);
		fargeListe.add(farge);
		fargeListe.add(farge);
		verktoyListe.add(verktoy);
		verktoyListe.add(verktoy);
		repaint();
	}

	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
