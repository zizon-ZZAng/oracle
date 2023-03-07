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
		
		JComboBox comboBox_loca = new JComboBox();
		comboBox_loca.setBounds(89, 64, 96, 23);
		getContentPane().add(comboBox_loca);
		
		JLabel lblNewLabel_1 = new JLabel("시간");
		lblNewLabel_1.setBounds(238, 68, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBox_date = new JComboBox();
		comboBox_date.setBounds(292, 64, 96, 23);
		getContentPane().add(comboBox_date);
		
		JButton myPageButton = new JButton("마이페이지");
		myPageButton.setBounds(40, 20, 97, 23);
		getContentPane().add(myPageButton);
		
		JButton recButton = new JButton("옷 추천");
		recButton.setBounds(156, 202, 97, 23);
		getContentPane().add(recButton);
	}

}
