package Fellesprosjektet;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	private String brukernavn="Ida";
	private char[] passord={'P','a','s','s','o','r','d'};
	final JFrame popUpWithMessage = new JFrame();
	private String message="Feil passord eller brukernavn";
	
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		LoggInnPanel loggInn=new LoggInnPanel();
		frame.getContentPane().add(loggInn);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
	
	public LoggInnPanel(){
		gbc=new GridBagConstraints();
		setLayout(new GridBagLayout());
		gbc.insets=new Insets(5,5,5,5);
		
		
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
	
	private boolean loggInn(){
		if(brukernavnTextField.getText().equals(brukernavn)){
			if(riktigPassord()){
				return true;
			}
		}
		return false;
	}
	
	private boolean riktigPassord(){
		char[] input=passordField.getPassword();
		if (input.length != passord.length) {
			return false;
		} else {
			return Arrays.equals (input, passord);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(loggInn()){
			System.out.println("Du er logget inn");
		}else{
			JOptionPane.showMessageDialog(popUpWithMessage, message);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER){ 
			if(loggInn()){
				System.out.println("Du er logget inn");
			}else{
				JOptionPane.showMessageDialog(popUpWithMessage, message);
			}
        }
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
