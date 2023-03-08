package frame;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import service.WeatherService;
import service.WeatherServiceImpl;
import javax.swing.JTextField;

public class WeatherFrame extends JFrame {
	WeatherService mapper = new WeatherServiceImpl();
	private JTextField textField;
	public WeatherFrame() {
		
		getContentPane().setLayout(null);

		JButton myPageButton = new JButton("마이페이지");
		myPageButton.setBounds(22, 22, 97, 23);

		getContentPane().add(myPageButton);

		JLabel lblNewLabel_loca = new JLabel("지역");
		lblNewLabel_loca.setBounds(32, 68, 30, 15);
		getContentPane().add(lblNewLabel_loca);

		JComboBox comboBox_loca = new JComboBox();
		comboBox_loca
				.setModel(new DefaultComboBoxModel(new String[] { "서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시",
						"울산광역시", "세종특별자치시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도" }));
		comboBox_loca.setBounds(62, 64, 87, 23);
		getContentPane().add(comboBox_loca);

		JLabel lblNewLabel_date = new JLabel("날짜");
		lblNewLabel_date.setBounds(160, 68, 30, 15);
		getContentPane().add(lblNewLabel_date);

		JComboBox comboBox_date = new JComboBox();
		comboBox_date.setModel(new DefaultComboBoxModel(new String[] { "2023-03-06", "2023-03-07", "2023-03-08" }));
		comboBox_date.setBounds(190, 64, 100, 23);
		getContentPane().add(comboBox_date);

		JLabel lblNewLabel_time = new JLabel("시간");
		lblNewLabel_time.setBounds(300, 68, 30, 15);
		getContentPane().add(lblNewLabel_time);
		
		textField = new JTextField();
		textField.setBounds(247, 165, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		

		JComboBox comboBox_time = new JComboBox();
		comboBox_time.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {



				Map<String, Object> map = new HashMap<>();
				map.put("name", comboBox_loca.getSelectedItem().toString());
				map.put("w_date", comboBox_date.getSelectedItem().toString());
				map.put("w_hour", comboBox_time.getSelectedItem().toString());
				
				float ret = mapper.weatherSelectWVTemp(map);
				
//				textField.setText(
//				ret.get("temperature").toString();
//
//				System.out.println(ret.get("temperature").toString());
				
				ImageIcon icon = new ImageIcon();
				Image img = icon.getImage();
				Image changeImg= img.getScaledInstance(150,150, Image.SCALE_SMOOTH);
				ImageIcon changeIcon = new ImageIcon(changeImg);
				JLabel lbl = new JLabel(changeIcon);
				
				getContentPane().add(lbl);
				
	

			}

		});
		comboBox_time.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "00" }));
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

		this.setSize(400, 400); // 사이즈 정하기

		recButton.setBounds(162, 298, 97, 23);
		getContentPane().add(recButton);
		


		


		this.setSize(434, 400); // 사이즈 정하기

		this.setVisible(true);
	}
}
