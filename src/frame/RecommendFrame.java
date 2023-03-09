package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import dto.Weather;
import service.WeatherService;
import service.WeatherServiceImpl;
import session.Config;

public class RecommendFrame extends JFrame{
	
	WeatherService wsv = new WeatherServiceImpl();
	
	public RecommendFrame() {
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WeatherFrame();
				Weather weather = new Weather();
				
				//String date = Config.weather.getW_date().toString();
				float tem = Config.weather.getTemperature();
				//String hour = Config.weather.getW_hour().toString();
				
				//weather.setW_date(date);
				//weather.setW_hour(hour);
				weather.setTemperature(tem);
				
				Config.weather = 	
				dispose();
			}
		});
		btnNewButton.setBounds(52, 290, 97, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("다시 추천받기");
		btnNewButton_1.setBounds(218, 290, 121, 23);
		getContentPane().add(btnNewButton_1);
		
		this.setSize(400, 400);	// 사이즈 정하기
		this.setVisible(true);
	}

}
