package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import dto.Location;
import service.WeatherServiceImpl;


public class WeatherFrame extends JFrame {
	public WeatherFrame() {
		getContentPane().setLayout(null);
		

		
		JButton myPageButton = new JButton("마이페이지");
		myPageButton.setBounds(22, 22, 97, 23);

		getContentPane().add(myPageButton);
		
		
		JLabel lblNewLabel_loca = new JLabel("지역");
		lblNewLabel_loca.setBounds(32, 68, 30, 15);
		getContentPane().add(lblNewLabel_loca);

		JComboBox comboBox_loca = new JComboBox();
		comboBox_loca.setModel(new DefaultComboBoxModel(new String[] {"서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "세종특별자치시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도"}));
		comboBox_loca.setBounds(62, 64, 87, 23);
		getContentPane().add(comboBox_loca);
		
		JLabel lblNewLabel_date = new JLabel("날짜");
		lblNewLabel_date.setBounds(180, 68, 30, 15);
		getContentPane().add(lblNewLabel_date);

		JComboBox comboBox_date = new JComboBox();
		comboBox_date.setModel(new DefaultComboBoxModel(new String[] {"2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-07"}));
		comboBox_date.setBounds(210, 64, 87, 23);
		getContentPane().add(comboBox_date);
		
		JLabel lblNewLabel_time = new JLabel("시간");
		lblNewLabel_time.setBounds(300, 68, 30, 15);
		getContentPane().add(lblNewLabel_time);
		
		JComboBox comboBox_time = new JComboBox();
		comboBox_time.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				 if (e.getSource() == comboBox_time) {
//		                System.out.println("CENTER 클릭됨");
//		            } else if (e.getSource() ==  ) {
//		                System.out.println("NORTH 클릭됨");
//		            } else if (e.getSource() == btnSouth) {
//		                System.out.println("South 클릭됨");
//		            } else if (e.getSource() == btnEast) {
//		                System.out.println("East 클릭됨");
//		            } else if (e.getSource() == btnWest) {
//		                System.out.println("West 클릭됨");
//		            }
		        }
			
		});
		comboBox_time.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00"}));
		comboBox_time.setBounds(330, 64, 50, 23);
		getContentPane().add(comboBox_time);
		

		JButton recButton = new JButton("옷 추천");

		recButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RecommendFrame();
				dispose();
			}
		});
		recButton.setBounds(153, 257, 97, 23);
		getContentPane().add(recButton);
		
		this.setSize(400, 400);	// 사이즈 정하기

		recButton.setBounds(162, 298, 97, 23);
		getContentPane().add(recButton);
	
		//
		
		recButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
			
			//네 ?
		});
		
		
		
		
		this.setSize(434, 400);	// 사이즈 정하기

		this.setVisible(true);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
