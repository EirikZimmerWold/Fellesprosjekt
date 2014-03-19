package Fellesprosjektet;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

import java.awt.GridBagLayout;
import java.sql.SQLException;


public class weekdayPanel extends JPanel{
	private JLabel dayName;
	private JLabel dayDate;
	private JPanel list;
	private GridBagConstraints gbc;
	private ProgramFrame frame;
	private Database db;
	private String Dag;
	private String Maaned;
	private String aar;

	public weekdayPanel(String day, ProgramFrame frame){
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		this.frame=frame;
		db = frame.getDB();
		
		dayName = new JLabel(day);
		dayDate = new JLabel(" ");
		
		list=new JPanel(new GridBagLayout());
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.weighty = 1;
        gc.weighty=1;
        gc.gridx=0;
        gc.gridy=2;
        list.add(new JPanel(), gc);
        list.setBackground(Color.white);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(175,450));
		add(scrollPane, gc);
		
		gc.gridx=0;
		gc.gridy=0;
		add(dayName, gc);
		gc.gridx=0;
		gc.gridy=1;
		add(dayDate, gc);
	}

	public void setDate(String date){
		dayDate.setText(date);
		String [] del = date.split("/");
		Dag = del[0];
		Maaned = del[1];
		aar = del[2];
		
	}

	private boolean maanedsjekk(String tekst, String dato){
		if (tekst.equals("Januar") && dato.equals("1")){
			return true;
		}
		else if (tekst.equals("Feburar") && dato.equals("2")){
			return true;
		}
		else if (tekst.equals("Mars") && dato.equals("3")){
			return true;
		}
		else if (tekst.equals("April") && dato.equals("4")){
			return true;
		}
		else if (tekst.equals("Mai") && dato.equals("5")){
			return true;
		}
		else if (tekst.equals("Juni") && dato.equals("6")){
			return true;
		}
		else if (tekst.equals("Juli") && dato.equals("7")){
			return true;
		}
		else if (tekst.equals("August") && dato.equals("8")){
			return true;
		}
		else if (tekst.equals("September") && dato.equals("9")){
			return true;
		}
		else if (tekst.equals("Oktober") && dato.equals("10")){
			return true;
		}
		else if (tekst.equals("November") && dato.equals("11")){
			return true;
		}
		else if (tekst.equals("Desember") && dato.equals("12")){
			return true;
		}
		else{ return false;}
	}
	
	private void fjerneFraListe(){
		list.removeAll();
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.weighty = 1;
        gc.weighty= 1;
        gc.gridx=0;
        gc.gridy=2;
		
	}
	public void leggeTilAvtale(Ansatt eier) throws SQLException{
		fjerneFraListe();
		String avtaler;
		if (eier != null){
			avtaler = db.avtalerPersonErMed2(eier);
			String [] delt = avtaler.split("-");
			for (String verdi:delt){
				if (verdi != ""){
					int avID = Integer.parseInt(verdi);
					Avtale avtalen=db.getBestemtAvtale(avID);
					String [] avtaledag = avtalen.getStartTid().split("-");
					if (Dag.equals(avtaledag[2])){
						if(aar.equals(avtaledag[0])){
							if (maanedsjekk(avtaledag[1],Maaned) == true){
								addAvtalePanel(avtalen);
							}	
						}
					}
				}
			}
		}
	}
	public void addAvtalePanel(Avtale avtale){
		AvtalePanel panel=new AvtalePanel(avtale, frame);
		gbc=new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx=1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        list.add(panel, gbc, 0);
        validate();
        repaint();
	}
}
