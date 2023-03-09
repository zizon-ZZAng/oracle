package frame;

import java.awt.Font;
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
import javax.swing.JTextField;

import service.WeatherService;
import service.WeatherServiceImpl;
import session.Config;

public class WeatherFrame extends JFrame {
	WeatherService w = new WeatherServiceImpl();
	private JTextField textField;
	private JTextField textField_add;

	public WeatherFrame() {

		getContentPane().setLayout(null);

		JButton myPageButton = new JButton("마이페이지");
		myPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MyPageFrame();
				dispose();
			}
		});
		myPageButton.setBounds(22, 22, 97, 23);

		getContentPane().add(myPageButton);

		JLabel lblNewLabel_loca = new JLabel("지역");
		lblNewLabel_loca.setBounds(22, 68, 30, 15);
		getContentPane().add(lblNewLabel_loca);

		JLabel lblNewLabel_date = new JLabel("날짜");
		lblNewLabel_date.setBounds(160, 68, 30, 15);
		getContentPane().add(lblNewLabel_date);

		JComboBox comboBox_date = new JComboBox();
		comboBox_date.setModel(new DefaultComboBoxModel(w.weatherSelectDATE()));
		comboBox_date.setBounds(190, 64, 100, 23);
		getContentPane().add(comboBox_date);

		JLabel lblNewLabel_time = new JLabel("시간");
		lblNewLabel_time.setBounds(300, 68, 30, 15);
		getContentPane().add(lblNewLabel_time);
		
		
		textField_add = new JTextField(Config.member.getAddress().toString());
		textField_add.setEditable(false);
		textField_add.setBounds(64, 65, 86, 21);
		getContentPane().add(textField_add);
		textField_add.setColumns(10);
		

		textField = new JTextField();
		textField.setFont(new Font("굴림", Font.BOLD, 30));
		textField.setEditable(false);
		textField.setBounds(250, 164, 73, 68);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel icon_label = new JLabel("");
		icon_label.setBounds(47, 145, 122, 112);
		getContentPane().add(icon_label);

		JComboBox comboBox_time = new JComboBox();
		comboBox_time.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Map<String, Object> map = new HashMap<>();
				map.put("name", textField_add.getText());
				map.put("w_date", comboBox_date.getSelectedItem().toString());
				map.put("w_hour", comboBox_time.getSelectedItem().toString());

				//List<Map<String, Object>> list = mapper.weatherSelectWVTemp(map);
				Map<String, Object> list = w.weatherSelectWVTemp(map);
				
				//textField.setText(list.get(i).get("TEMPERATURE").toString());
				
				textField.setText(list.get(i).get("TEMPERATURE").toString());
					
				//Config.weather.setTemperature(Float.parseFloat(list.get(i).get("TEMPERATURE").toString()));
				

				ImageIcon[] icon = new ImageIcon[9];

				icon[0] = new ImageIcon(ImageFrame.class.getResource("cloudy.png"));
				icon[1] = new ImageIcon(ImageFrame.class.getResource("cloudy1.png"));
				icon[2] = new ImageIcon(ImageFrame.class.getResource("cloudy2.png"));
				icon[3] = new ImageIcon(ImageFrame.class.getResource("fog.png"));
				icon[4] = new ImageIcon(ImageFrame.class.getResource("rain.png"));
				icon[5] = new ImageIcon(ImageFrame.class.getResource("sand.png"));
				icon[6] = new ImageIcon(ImageFrame.class.getResource("snow.png"));
				icon[7] = new ImageIcon(ImageFrame.class.getResource("sun.png"));
				icon[8] = new ImageIcon(ImageFrame.class.getResource("thunder.png"));

				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).get("WEATHER").toString().equals("흐림")) {

						Image img = icon[0].getImage();
						Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
						ImageIcon updateIcon = new ImageIcon(updateImg);

						icon_label.setIcon(updateIcon);

						icon_label.setBounds(50, 120, 165, 150);
						icon_label.setHorizontalAlignment(JLabel.CENTER);

					} else if (list.get(i).get("WEATHER").toString().equals("구름조금")) {

						Image img = icon[1].getImage();
						Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
						ImageIcon updateIcon = new ImageIcon(updateImg);

						icon_label.setIcon(updateIcon);

						icon_label.setBounds(50, 120, 165, 150);
						icon_label.setHorizontalAlignment(JLabel.CENTER);
					} else if (list.get(i).get("WEATHER").toString().equals("구름많음")) {

						Image img = icon[2].getImage();
						Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
						ImageIcon updateIcon = new ImageIcon(updateImg);

						icon_label.setIcon(updateIcon);

						icon_label.setBounds(50, 120, 165, 150);
						icon_label.setHorizontalAlignment(JLabel.CENTER);
					} else if (list.get(i).get("WEATHER").toString().equals("안개")) {

						Image img = icon[3].getImage();
						Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
						ImageIcon updateIcon = new ImageIcon(updateImg);

						icon_label.setIcon(updateIcon);

						icon_label.setBounds(50, 120, 165, 150);
						icon_label.setHorizontalAlignment(JLabel.CENTER);
					} else if (list.get(i).get("WEATHER").toString().equals("비")) {

						Image img = icon[4].getImage();
						Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
						ImageIcon updateIcon = new ImageIcon(updateImg);

						icon_label.setIcon(updateIcon);

						icon_label.setBounds(50, 120, 165, 150);
						icon_label.setHorizontalAlignment(JLabel.CENTER);
					} else if (list.get(i).get("WEATHER").toString().equals("황사")) {

						Image img = icon[5].getImage();
						Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
						ImageIcon updateIcon = new ImageIcon(updateImg);

						icon_label.setIcon(updateIcon);

						icon_label.setBounds(50, 120, 165, 150);
						icon_label.setHorizontalAlignment(JLabel.CENTER);
					} else if (list.get(i).get("WEATHER").toString().equals("눈")) {

						Image img = icon[6].getImage();
						Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
						ImageIcon updateIcon = new ImageIcon(updateImg);

						icon_label.setIcon(updateIcon);

						icon_label.setBounds(50, 120, 165, 150);
						icon_label.setHorizontalAlignment(JLabel.CENTER);
					} else if (list.get(i).get("WEATHER").toString().equals("맑음")) {

						Image img = icon[7].getImage();
						Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
						ImageIcon updateIcon = new ImageIcon(updateImg);

						icon_label.setIcon(updateIcon);

						icon_label.setBounds(50, 120, 165, 150);
						icon_label.setHorizontalAlignment(JLabel.CENTER);
					} else if (list.get(i).get("WEATHER").toString().equals("천둥번개")) {

						Image img = icon[8].getImage();
						Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
						ImageIcon updateIcon = new ImageIcon(updateImg);

						icon_label.setIcon(updateIcon);

						icon_label.setBounds(50, 120, 165, 150);
						icon_label.setHorizontalAlignment(JLabel.CENTER);
					}

				}

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

		JLabel lblNewLabel = new JLabel("ºC");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel.setBounds(330, 182, 50, 48);
		getContentPane().add(lblNewLabel);
		


//		JPanel panel_Pic = new JPanel();
//		panel_Pic.setBounds(32, 118, 150, 150);
//		getContentPane().add(panel_Pic);
//		ImageIcon icon = new ImageIcon(
//				WeatherFrame.class.getResource("C:/Users/Administrator/Desktop/weather_icon.png"));

		this.setSize(434, 400); // 사이즈 정하기
		this.setVisible(true);
	}
}
