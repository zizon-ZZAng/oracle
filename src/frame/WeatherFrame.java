package frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class WeatherFrame extends JFrame {
	public WeatherFrame() {
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("지역");
		lblNewLabel.setBounds(29, 68, 57, 15);
		getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(89, 64, 96, 23);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("시간");
		lblNewLabel_1.setBounds(238, 68, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(292, 64, 96, 23);
		getContentPane().add(comboBox_1);
		
		JButton btnNewButton = new JButton("마이페이지");
		btnNewButton.setBounds(40, 20, 97, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("옷 추천");
		btnNewButton_1.setBounds(156, 202, 97, 23);
		getContentPane().add(btnNewButton_1);
	}

}
