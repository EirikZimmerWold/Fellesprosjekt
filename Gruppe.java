package Fellesprosjektet;

import java.awt.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;


public class Gruppe {
	private int gruppeId;
	private String navn;
	private JList medlemmer;
	private DefaultListModel medlemmerModell;

	public Gruppe(int id, String navn, DefaultListModel gruppeModell) {
		this.medlemmerModell = gruppeModell;
		this.medlemmer = new JList(medlemmerModell);
		this.navn = navn;
		this.gruppeId = id;
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
