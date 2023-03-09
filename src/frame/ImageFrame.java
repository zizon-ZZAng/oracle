package frame;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageFrame extends JFrame {

	public ImageFrame() {
		
		JLabel imgLabel = new JLabel();
		
		ImageIcon icon = new ImageIcon(ImageFrame.class.getResource("sun.png"));
		
		Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		
		imgLabel.setIcon(updateIcon);
		
		imgLabel.setBounds(210,30,165,150);
		imgLabel.setHorizontalAlignment(JLabel.CENTER);
		
		getContentPane().add(imgLabel);
		
		setSize(400,400);
		setVisible(true);	
	}
	
	
}
