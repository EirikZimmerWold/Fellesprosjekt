package Fellesprosjektet;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AvtalePanel extends JPanel{
	private JLabel tid, rom, leder, status, romLabel, lederLabel;
	private JButton sepaaAvtaleButton;
	private Avtale avtale;
	private GridBagConstraints gbc;
	private PropertyChangeSupport pcs;
	public final static String SEPAAAVTALE_PROPERTY="sepaaAvtaleButton";
	
	public static void main(String[] args) throws SQLException {
		Rom rom=new Rom("r2");
		DefaultListModel deltagere=new DefaultListModel();
		Ansatt leder=new Ansatt("ida");
		Avtale avtale=new Avtale("13:00", "15:00", "dfsgd", rom , deltagere, leder);
		AvtalePanel avtalePanel=new AvtalePanel(avtale);
		
		JFrame frame=new JFrame();
		frame.getContentPane().add(avtalePanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
	
	public AvtalePanel(Avtale avtale){
		pcs=new PropertyChangeSupport(this);
		this.avtale=avtale;
		
		gbc=new GridBagConstraints();
		setLayout(new GridBagLayout());
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.insets=new Insets(5,5,5,5);
		this.setPreferredSize(new Dimension(150,150));
		
		tid=new JLabel(avtale.getStartTid()+" - "+avtale.getSluttTid());
		gbc.gridwidth=2;
		gbc.gridx=0;
		gbc.gridy=0;
		add(tid,gbc);
		
		romLabel=new JLabel("Rom: ");
		gbc.gridx=0;
		gbc.gridy=1;
		add(romLabel,gbc);
		
		rom=new JLabel(avtale.getRom().getNavn());
		gbc.gridwidth=1;
		gbc.gridx=1;
		gbc.gridy=1;
		add(rom,gbc);
		
		lederLabel=new JLabel("Leder: ");
		gbc.gridx=0;
		gbc.gridy=2;
		add(lederLabel,gbc);
		
		leder=new JLabel(avtale.getLeder().getNavn());
		gbc.gridx=1;
		gbc.gridy=2;
		add(leder,gbc);
		
		status=new JLabel("FSSHg");
		gbc.gridx=0;
		gbc.gridy=3;
		add(status,gbc);
		
		sepaaAvtaleButton=new JButton("Info");
		gbc.gridx=1;
		gbc.gridy=3;
		add(sepaaAvtaleButton,gbc);
		sepaaAvtaleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				firePropertyChange(SEPAAAVTALE_PROPERTY, "oldValue", "newValue");
			}
		});
	}
	
	public Avtale getAvtale(){
		return this.avtale;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);		
	}

}
