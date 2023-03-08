package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dto.Location;

public class WeatherFrame extends JFrame {
	public WeatherFrame() {
		getContentPane().setLayout(null);
		
		
		JButton myPageButton = new JButton("마이페이지");
		myPageButton.setBounds(22, 22, 97, 23);
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
		recButton.setBounds(162, 298, 97, 23);
		getContentPane().add(recButton);
		
		
		recButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
			
			//네 ?
		});
		
		
		
		
		this.setSize(434, 400);	// 사이즈 정하기
		this.setVisible(true);
	}
}
