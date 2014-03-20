package Fellesprosjektet;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private Database db;
	private ProgramFrame frame;
	
	public AvtalePanel(final Avtale avtale, final ProgramFrame frame){
		db=new Database();
		this.avtale=avtale;
		this.frame=frame;
		
		gbc=new GridBagConstraints();
		setLayout(new GridBagLayout());
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.insets=new Insets(5,5,5,5);
		this.setPreferredSize(new Dimension(150,150));
		
		String [] ST = avtale.getStartTid().split("-");
		String [] SL = avtale.getSluttTid().split("-");
		tid=new JLabel(ST[3]+" - "+SL[3]);
		gbc.gridwidth=2;
		gbc.gridx=0;
		gbc.gridy=0;
		add(tid,gbc);
		
		romLabel=new JLabel("Rom: ");
		gbc.gridx=0;
		gbc.gridy=1;
		add(romLabel,gbc);
		
		if(!avtale.getRom().getNavn().equals("0")){
			rom=new JLabel(avtale.getRom().getNavn());
		}else{
			rom=new JLabel(avtale.getSted());
		}
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
		
		if(frame.getUser().getBrukernavn().equals(avtale.getLeder().getBrukernavn())){
			status=new JLabel(getStatusVert());
		}else{
			status=new JLabel(getStatusInvitert());
		}
		gbc.gridwidth=2;
		gbc.gridx=0;
		gbc.gridy=3;
		add(status,gbc);
		
		sepaaAvtaleButton=new JButton("Info");
		gbc.gridx=0;
		gbc.gridy=4;
		add(sepaaAvtaleButton,gbc);
		sepaaAvtaleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				avtaleview avtaleview;
				try {
					avtaleview = new avtaleview(avtale, frame);
					avtaleview.pack();
					avtaleview.setVisible(true);
					avtaleview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String getStatusVert(){
		try {
			DefaultListModel deltagere=db.alleDeltagere(avtale.getId());
			int avslaatt=0;
			int venter=0;
			for (int i=0; i < deltagere.getSize(); i++){
				Ansatt deltager=(Ansatt)deltagere.get(i);
				int status=db.getStatus(avtale.getId(), deltager.getBrukernavn());
				if(status==0){
					avslaatt++;
				}else if(status==-1){
					venter++;
				}
			}
			if(avslaatt!=0){
				if(avslaatt==1){
					return "1 peroson har avslått";
				}
				return avslaatt+" personer har avslått";
			}else if(venter!=0){
				if(avslaatt==1){
					return "1 person har ikke svart";
				}
				return venter+" personer har ikke svart";
			}else{
				return "Alle har bekreftet";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getStatusInvitert(){
		try {
			int status = db.getStatus(avtale.getId(), frame.getUser().getBrukernavn());
			if(status==-1){
				return "Du har ikke svart";
			}else if(status==1){
				return "Du har bekreftet";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public Avtale getAvtale(){
		return this.avtale;
	}
}
