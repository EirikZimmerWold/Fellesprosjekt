package Fellesprosjektet;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoggInnPanel extends JPanel implements ActionListener, KeyListener{
	private JButton loggInnButton;
	private JLabel brukernavnLabel, passordLabel;
	private JTextField brukernavnTextField;
	private JPasswordField passordField;
	private GridBagConstraints gbc;
	private Database db;
	final JFrame popUpWithMessage = new JFrame();
	private String message="Feil passord eller brukernavn";
	private ProgramFrame frame;
	private JFrame jframe;
	private String brukernavn;

	public LoggInnPanel(ProgramFrame frame, JFrame jframe){
		gbc=new GridBagConstraints();
		setLayout(new GridBagLayout());
		gbc.insets=new Insets(5,5,5,5);
		
		this.frame=frame;
		this.jframe=jframe;
		db=new Database();
		
		brukernavnLabel=new JLabel("Brukernavn: ");
		gbc.gridx=0;
		gbc.gridy=0;
		this.add(brukernavnLabel,gbc);
		
		brukernavnTextField=new JTextField(20);
		gbc.gridx=1;
		gbc.gridy=0;
		this.add(brukernavnTextField,gbc);
		brukernavnTextField.addKeyListener(this);
		
		passordLabel=new JLabel("Passord: ");
		gbc.gridx=0;
		gbc.gridy=1;
		this.add(passordLabel,gbc);
		
		passordField=new JPasswordField(20);
		gbc.gridx=1;
		gbc.gridy=1;
		this.add(passordField,gbc);
		passordField.addKeyListener(this);
		
		loggInnButton=new JButton("Logg inn");
		gbc.gridwidth=2;
		gbc.gridx=0;
		gbc.gridy=3;
		this.add(loggInnButton,gbc);
		loggInnButton.addActionListener((ActionListener) this);
	}
	
	private boolean loggInn()throws SQLException{
		String brukernavn= brukernavnTextField.getText();
		if(!db.checkUsername(brukernavn)){
			return false;
		}
		String Passord = db.getPassord(brukernavn);
		char [] passord=Passord.toCharArray();
		if(riktigPassord(passord)){
			this.brukernavn=brukernavn;
			return true;
		}
		return false;
	}
	
	private boolean riktigPassord(char[] passord){
		char[] input=passordField.getPassword();
		if (input.length != passord.length) {
			return false;
		} else {
			return Arrays.equals (input, passord);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0){
		try {
			if(loggInn()){
				frame.getMainPanel().setCurrUser(brukernavn);
				frame.setUser(brukernavn);
				frame.update();
				frame.loggedIn(true);
				frame.enableComponents();
				jframe.dispose();
			}else{
				JOptionPane.showMessageDialog(popUpWithMessage, message);
			}
		} catch (HeadlessException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER){ 
			try {
				if(loggInn()){
					frame.enableComponents();
					jframe.dispose();
					frame.getMainPanel().setCurrUser(brukernavn);
					frame.setUser(brukernavn);
					frame.update();
					
				}else{
					JOptionPane.showMessageDialog(popUpWithMessage, message);
				}
			} catch (HeadlessException | SQLException e) {
				e.printStackTrace();
			}
        }
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
