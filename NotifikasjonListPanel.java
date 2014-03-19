package Fellesprosjektet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

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
	private Database db;
	
	private int notifyCount;
	private String panelName = "Notifikasjoner";
	private String notifiks ="";
	
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
        db = new Database();
        }
	
	public void seNotifikasjoner() throws SQLException{
		fjernListe();
		Ansatt bruker = frame.getUser();
		if (bruker != null){
			notifiks = db.avtalerPersonErMed(bruker);
			String [] delt = notifiks.split("-");
			for (String verdi:delt){
				if (verdi != ""){
					int avID = Integer.parseInt(verdi);
					addNotifikasjonPanel(db.getBestemtAvtale(avID));
				}
			}
		}
	}
	public void addNotifikasjonPanel(Avtale avtale){
		NotifikasjonPanel panel=new NotifikasjonPanel(this, frame, avtale);
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
	private void fjernListe(){
		list.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        list.add(new JPanel(), gbc);
        list.setBackground(Color.white);
        notifyCount=0;
        
	}
	public String getPanelName(){
		return panelName;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() =="infoButton"){
			Avtale avtale=((NotifikasjonPanel)evt.getSource()).getNotifikasjon().getAvtale();
			avtaleinfo.settInfo(avtale);
		}
		else if (evt.getPropertyName()== "bekreftButton"){
			try {
				seNotifikasjoner();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}