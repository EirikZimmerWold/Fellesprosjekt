package Fellesprosjektet;

import java.awt.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;


public class Gruppe {
	
	private static int count = 0;
	
	private int gruppeId;
	private String navn;
	private JList medlemmer;
	private DefaultListModel medlemmerModell;

	public Gruppe(String n) {
		medlemmerModell = new DefaultListModel();
		medlemmer = new JList(medlemmerModell);
		count += 1;
		gruppeId = count;
		navn = n;
	}
	
	public void setMedlem(Ansatt ansatt) {
		medlemmerModell.addElement(ansatt);
	}
	
	public JList getMedlemmer() {
		return medlemmer;
	}
	
	public String getNavn() {
		return navn;
	}
	
	public void setNavn(String n) {
		navn = n;
	}
	
	public DefaultListModel getModel() {
		return medlemmerModell;
	}
	
	public Ansatt getAnsatt(int i) {
		return (Ansatt) medlemmerModell.getElementAt(i);
	}
	
	public int getCount() {
		return medlemmerModell.getSize();
	}
	
	public String toString() {
		return this.gruppeId + ", " + navn;
		
	}
}
