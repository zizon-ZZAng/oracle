package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
<<<<<<< HEAD
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
=======

import dto.Location;
>>>>>>> f024e3827967d7aac1c15410b16339ca6006b66b

public class WeatherFrame extends JFrame {
	private JTextField textField;
	public WeatherFrame() {
		getContentPane().setLayout(null);
		
<<<<<<< HEAD
		JLabel lblNewLabel = new JLabel("지역");
		lblNewLabel.setBounds(29, 68, 57, 15);
		getContentPane().add(lblNewLabel);
		
		JComboBox comboBox_loca = new JComboBox();
		comboBox_loca.setModel(new DefaultComboBoxModel(new String[] {"서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "세종특별자치시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도"}));
		comboBox_loca.setBounds(89, 64, 96, 23);
		getContentPane().add(comboBox_loca);
		
		JLabel lblNewLabel_1 = new JLabel("시간");
		lblNewLabel_1.setBounds(29, 147, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBox_date = new JComboBox();
		comboBox_date.setModel(new DefaultComboBoxModel(new String[] {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"}));
		comboBox_date.setBounds(89, 143, 96, 23);
		getContentPane().add(comboBox_date);
		
		JButton myPageButton = new JButton("마이페이지");
		myPageButton.setBounds(12, 10, 97, 23);
=======
		
		JButton myPageButton = new JButton("마이페이지");
		myPageButton.setBounds(22, 22, 97, 23);
>>>>>>> f024e3827967d7aac1c15410b16339ca6006b66b
		getContentPane().add(myPageButton);
		
		
		JLabel lblNewLabel = new JLabel("지역");
		lblNewLabel.setBounds(32, 68, 30, 15);
		getContentPane().add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "세종특별자치시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도"}));
		comboBox.setBounds(62, 64, 87, 23);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("날짜");
		lblNewLabel_1.setBounds(180, 68, 30, 15);
		getContentPane().add(lblNewLabel_1);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-06", "2023-03-07"}));
		comboBox_1.setBounds(210, 64, 87, 23);
		getContentPane().add(comboBox_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("시간");
		lblNewLabel_1_1.setBounds(300, 68, 30, 15);
		getContentPane().add(lblNewLabel_1_1);
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00"}));
		comboBox_1_1.setBounds(330, 64, 50, 23);
		getContentPane().add(comboBox_1_1);
		

		JButton recButton = new JButton("옷 추천");
<<<<<<< HEAD
		recButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RecommendFrame();
				dispose();
			}
		});
		recButton.setBounds(252, 103, 97, 23);
		getContentPane().add(recButton);
		
		JLabel lblNewLabel_2 = new JLabel("날짜");
		lblNewLabel_2.setBounds(29, 107, 57, 15);
		getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(89, 104, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		this.setSize(400, 400);	// 사이즈 정하기
=======
		recButton.setBounds(162, 298, 97, 23);
		getContentPane().add(recButton);
		
		
		recButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
			
			//네 ?
		});
		
		
		
		
		this.setSize(434, 400);	// 사이즈 정하기
>>>>>>> f024e3827967d7aac1c15410b16339ca6006b66b
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
