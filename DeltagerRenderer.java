package Fellesprosjektet;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

public class DeltagerRenderer extends JLabel implements ListCellRenderer{
	
	//1=bekreftet, 0=avslatt, -1 = ventersvar
	private Color bekreftetFarge;
	private Color avslattFarge;
	private Color ventersvarFarge;
	private ImageIcon bekreftet;
	private ImageIcon avslatt;
	private ImageIcon ventersvar;


	public DeltagerRenderer() {
		setOpaque(true);
		bekreftetFarge = Color.green;
		avslattFarge = Color.red;
		ventersvarFarge = Color.orange;
		bekreftet = createIcon(1);
		avslatt = createIcon(0);
		ventersvar = createIcon(-1);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,int index, boolean isSelected, boolean cellHasFocus) {
		Ansatt ansatt = (Ansatt) value;
		setIcon(getIcon(ansatt.getStatus()));
		String s = value.toString();
        setText(s);
        
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
	}

	private ImageIcon createIcon(int status){
		BufferedImage bimage = new BufferedImage(10,10, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = bimage.getGraphics();
		if(status == 1){
			g.setColor(bekreftetFarge);
		}else if(status == 0){
			g.setColor(avslattFarge);
		}else if(status == -1){
			g.setColor(ventersvarFarge);
		}
		g.fillRect(0,0,20,20);
		g.dispose();
		return new ImageIcon(bimage);
	}

	private ImageIcon getIcon(int status){
		if(status == 1){
			return bekreftet;
		}else if(status == 0){
			return avslatt;
		}else if(status == -1){
			return ventersvar;
		}
		return null;
	}
}