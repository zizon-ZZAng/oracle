package frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class Test extends JFrame {

	

	public Test() {

		JLabel lblNewLabel = new JLabel("                   15°C");
		lblNewLabel.setFont(new Font("Impact", Font.BOLD, 60));
		getContentPane().add(lblNewLabel, BorderLayout.SOUTH);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setLabelFor(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Administrator\\Desktop\\weather_icon\\sun.png"));
		getContentPane().add(lblNewLabel_1, BorderLayout.CENTER);

//		Image img = icon.getImage();
//		Image updateImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//		ImageIcon updateIcon = new ImageIcon(updateImg);
//		JLabel lbl = new JLabel(lblNewLabel_1);
//		getContentPane().add(lbl);
//		setTitle("weather");
//		setSize(568, 352);
//		setVisible(true);

//		getContentPane().add(imgLabel, BorderLayout.NORTH);
		setVisible(true);
	}

//	private Image getScaledImage(Image lblNewLabel_1, int w, int h) {
//
//		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//
//		Graphics2D g2 = resizedImg.createGraphics();
//
//		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//
//		g2.drawImage(lblNewLabel_1, 0, 0, 50, 50, null);
//
//		g2.dispose();
//
//		return resizedImg;
//
//	DefaultListModel ww = new DefaultListModel();
//
//	Image img = new ImageIcon(getClass().getResource("C:\\Users\\Administrator\\Desktop\\weather_icon\\sun.png"))
//			.getImage();
//	Image imgResize = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
//
//	ww.addElement(new ImageIcon(imgResize,"sun.png"));
//
//	JList skinJList = new JList();
//
//	skinJList.setModel(ww);
//
//	skinJList.setCellRenderer(new Test());
//
//	public class Test implements ListCellRenderer {
//
//		@Override
//		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
//				boolean cellHasFocus) {
//
//			JLabel skinLabel = new JLabel();
//			// 라벨 이미지아이콘 셋팅
//			skinLabel.setIcon((ImageIcon) value);
//			skinLabel.setText("back.jpg");
//			// 라벨 텍스트 색상 조정
//			skinLabel.setForeground(new Color(255, 0, 1));
//			// 라벨 텍스트 하단으로 위치 조정
//			skinLabel.setHorizontalTextPosition(JLabel.CENTER);
//			skinLabel.setVerticalTextPosition(JLabel.BOTTOM);
//			// 만든 이미지라벨 반환
//			return skinLabel;
//
//			return null;
//		}

//	}	

}
	



