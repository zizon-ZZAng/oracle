package frame;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dto.Member;
import service.MemberService;
import service.MemberServiceImpl;
import service.WeatherService;
import service.WeatherServiceImpl;
import session.Config;

public class WeatherFrame extends JFrame {
	WeatherService w = new WeatherServiceImpl();
	MemberService m = new MemberServiceImpl();
	private JTextField textField;
	private JTextField textField_add;
	private ImageIcon[] icon = new ImageIcon[9];
	private JLabel icon_label;
	

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
		comboBox_date.setModel(new DefaultComboBoxModel(new String[] {"2023/03/13","2023/03/14","2023/03/15","2023/03/15"}));
		comboBox_date.setBounds(190, 64, 100, 23);
		getContentPane().add(comboBox_date);

		JLabel lblNewLabel_time = new JLabel("시간");
		lblNewLabel_time.setBounds(300, 68, 30, 15);
		getContentPane().add(lblNewLabel_time);
		
		
//		textField_add = new JTextField(Config.member.getAddress().toString());
		
		//변경된 주소값 받아옴
		textField_add = new JTextField(m.selectMember(Config.member.getId()).getAddress());
		textField_add.setEditable(false);
		textField_add.setBounds(64, 65, 86, 21);
		getContentPane().add(textField_add);
		textField_add.setColumns(10);
		
		JComboBox comboBox_time = new JComboBox();
		comboBox_time.setModel(new DefaultComboBoxModel(w.weatherSelectHOUR()));
		comboBox_time.setBounds(330, 64, 50, 23);

		getContentPane().add(comboBox_time);

		textField = new JTextField();
		textField.setFont(new Font("굴림", Font.BOLD, 30));
		textField.setEditable(false);
		textField.setBounds(250, 164, 73, 68);
		getContentPane().add(textField);
		textField.setColumns(10);

		icon_label = new JLabel("");
		//icon_label.setBounds(47, 145, 122, 112);
		getContentPane().add(icon_label);
		
		
		comboBox_time.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Map<String, Object> map = new HashMap<>();
				map.put("address", textField_add.getText());
				map.put("week", comboBox_date.getSelectedItem().toString());
				map.put("hour", comboBox_time.getSelectedItem().toString());

				List<Map<String, Object>> list = w.weatherSelectWVTemp(map);
				//Map<String, Object> list = w.weatherSelectWVTemp(map);
				
				Config.conMap.put("address", textField_add.getText());
				Config.conMap.put("week", comboBox_date.getSelectedItem());
				Config.conMap.put("hour", comboBox_time.getSelectedItem());
				
				
				for(int i=0; i<list.size(); i++) {
					textField.setText(list.get(i).get("TEMPERATURE").toString());
				}
				
//				textField.setText(list.get("TEMPERATURE").toString());
//				Config.weather.setTemperature(Float.parseFloat(list.get("TEMPERATURE").toString()));
				

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
						image(0);
					} else if (list.get(i).get("WEATHER").toString().equals("구름조금")) {
						image(1);
					} else if (list.get(i).get("WEATHER").toString().equals("구름많음")) {
						image(2);
					} else if (list.get(i).get("WEATHER").toString().equals("안개")) {
						image(3);
					} else if (list.get(i).get("WEATHER").toString().equals("비")) {
						image(4);
					} else if (list.get(i).get("WEATHER").toString().equals("황사")) {
						image(5);
					} else if (list.get(i).get("WEATHER").toString().equals("눈")) {
						image(6);
					} else if (list.get(i).get("WEATHER").toString().equals("맑음")) {
						image(7);
					} else if (list.get(i).get("WEATHER").toString().equals("천둥번개")) {
						image(8);
					}

				}
				
			}
		});

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
		
		this.setSize(434, 400); // 사이즈 정하기
		this.setVisible(true);
		
	}
	
	private void image(int i) {
		Image img = icon[i].getImage();
		Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);

		icon_label.setIcon(updateIcon);

		icon_label.setBounds(50, 120, 165, 150);
		icon_label.setHorizontalAlignment(JLabel.CENTER);
	}
}
