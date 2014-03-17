package Fellesprosjektet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class NotifikasjonListPanel extends JPanel implements PropertyChangeListener{
	
	private ProgramFrame frame;
	
	private GridBagConstraints gbc;
	private JScrollPane scrollPane;
	private JPanel list;
	protected avtaleinfo avtaleinfo;
	
	private int notifyCount;
	private String panelName = "Notifikasjoner";
	
	public NotifikasjonListPanel(ProgramFrame frame){
		this.frame = frame;
		notifyCount = 0;
		
        list = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        list.add(new JPanel(), gbc);
        list.setBackground(Color.white);
        
        scrollPane=new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(350,500));
        add(scrollPane);
        
        avtaleinfo=new avtaleinfo();
        this.add(avtaleinfo);
        
		Rom rom=new Rom("R2");
		Ansatt leder=new Ansatt("idawol");
		leder.setNavn("Per");
		DefaultListModel<Ansatt> deltagere=new DefaultListModel<Ansatt>();
		Avtale avtale=new Avtale("2014-03-12-1415","2014-03-12-1600", "Testing av prototype", rom, deltagere, leder);
		addNotifikasjonPanel(avtale);
	}
	
	public void addNotifikasjonPanel(Avtale avtale){
		NotifikasjonPanel panel=new NotifikasjonPanel(this, avtale);
		gbc=new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.addPropertyChangeListener(this);
        list.add(panel, gbc, 0);
        validate();
        repaint();
        notifyCount++;
	}
	
	public String getPanelName(){
		return panelName;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Avtale avtale=((NotifikasjonPanel)evt.getSource()).getNotifikasjon().getAvtale();
		avtaleinfo.settInfo(avtale);
	}
}