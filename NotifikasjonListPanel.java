package Fellesprosjektet;

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
	private GridBagConstraints gbc;
	private JScrollPane scrollPane;
	private JPanel list;
	protected avtaleinfo avtaleinfo;
	
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		NotifikasjonListPanel notifikasjonListPanel=new NotifikasjonListPanel();
		frame.getContentPane().add(notifikasjonListPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		
		Rom rom=new Rom("R2");
		Ansatt leder=new Ansatt("idawol");
		leder.setNavn("Per");
		DefaultListModel deltagere=new DefaultListModel();
		Avtale avtale=new Avtale("2014-03-12-1415","2014-03-12-1600", "Testing av prototype", rom, deltagere, leder);
		notifikasjonListPanel.addNotifikasjonPanel(avtale);
		//NotifikasjonPanel notifikasjonPanel2=new NotifikasjonPanel();
		//notifikasjonListPanel.addNotifikasjonPanel(notifikasjonPanel2);
	}
	
	public NotifikasjonListPanel(){
        list = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        list.add(new JPanel(), gbc);
        
        scrollPane=new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(350,500));
        add(scrollPane);
        
        avtaleinfo=new avtaleinfo();
        this.add(avtaleinfo);
	}
	
	public void addNotifikasjonPanel(Avtale avtale){
		NotifikasjonPanel panel=new NotifikasjonPanel(avtale);
		gbc=new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.addPropertyChangeListener(this);
        list.add(panel, gbc, 0);
        validate();
        repaint();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Avtale avtale=((NotifikasjonPanel)evt.getSource()).getNotifikasjon().getAvtale();
		avtaleinfo.settInfo(avtale);
	}
}