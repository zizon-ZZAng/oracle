package frame;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dto.Clothes;
import dto.Clothesset;
import dto.Weather;
import service.ClothessetService;
import service.ClothessetServiceImpl;
import service.RecommendService;
import service.RecommendServiceImpl;
import service.WeatherService;
import service.WeatherServiceImpl;
import session.Config;

public class RecommendFrame extends JFrame{

	WeatherService wsv = new WeatherServiceImpl();
	RecommendService rsv = new RecommendServiceImpl();
	ClothessetService csv = new ClothessetServiceImpl();

	public RecommendFrame() {
		getContentPane().setLayout(null);			
	
		JLabel clothes_Label = new JLabel(" ");
		clothes_Label.setBounds(52, 37, 287, 228);
		getContentPane().add(clothes_Label);
		
		
		Weather weather = new Weather();
		
		float tem = Config.weather.getTemperature();
		weather.setTemperature(tem);
		Config.weather = wsv.select_tem_clothesset_final_view(weather);
		
		List<Clothesset> clothesset = csv.selectClothesset();		
		
		ImageIcon[] icon = new ImageIcon[22];
		for(int i=0; i<21; i++)
			icon[i] = new ImageIcon(ImageFrame.class.getResource( (10023 + i) + ".png"));
		
		
		for(int i=1; i<9; i++) {
			if(rsv.selectRecommendSetno(tem) == i) {
				
				String num = clothesset.get(i-1).getOutwear();
				
				Image img = icon[Integer.parseInt(num)].getImage();
				Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				ImageIcon updateIcon = new ImageIcon(updateImg);

				clothes_Label.setIcon(updateIcon);

				clothes_Label.setBounds(50, 120, 165, 150);
				clothes_Label.setHorizontalAlignment(JLabel.CENTER);
		
			}
		}
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WeatherFrame();
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
