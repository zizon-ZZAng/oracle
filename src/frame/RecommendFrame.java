package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import dto.Clothesset;
import dto.Weather;
import service.ClothessetService;
import service.ClothessetServiceImpl;
import service.RecommendService;
import service.RecommendServiceImpl;
import service.WeatherService;
import service.WeatherServiceImpl;
import session.Config;
import javax.swing.JLabel;

public class RecommendFrame extends JFrame implements ActionListener{

	WeatherService wsv = new WeatherServiceImpl();
	RecommendService rsv = new RecommendServiceImpl();
	ClothessetService csv = new ClothessetServiceImpl();

	public RecommendFrame() {
		getContentPane().setLayout(null);			
	
		JLabel clothes_Label = new JLabel("New label");
		clothes_Label.setBounds(52, 37, 287, 228);
		getContentPane().add(clothes_Label);
		
		
		Weather weather = new Weather();
		
		float tem = Config.weather.getTemperature();
		
		weather.setTemperature(tem);
		
		Config.weather = wsv.select_tem_clothesset_final_view(weather);
		
		List<Clothesset> clothes = new ArrayList();
		
		for(int i=1; i<9; i++) {
			if( rsv.selectRecommendSetno(tem) == i) {
				clothes = csv.selectClothesset(i);
				System.out.println(clothes.toString());
			}
		}
		
		ImageIcon[] icon = new ImageIcon[21];
		
		icon[0] = new ImageIcon(ImageFrame.class.getResource("10023.png"));
		icon[1] = new ImageIcon(ImageFrame.class.getResource("10024.png"));
		icon[2] = new ImageIcon(ImageFrame.class.getResource("10025.png"));
		icon[3] = new ImageIcon(ImageFrame.class.getResource("10026.png"));
		icon[4] = new ImageIcon(ImageFrame.class.getResource("10027.png"));
		icon[5] = new ImageIcon(ImageFrame.class.getResource("10028.png"));
		icon[6] = new ImageIcon(ImageFrame.class.getResource("10029.png"));
		icon[7] = new ImageIcon(ImageFrame.class.getResource("10030.png"));
		icon[0] = new ImageIcon(ImageFrame.class.getResource("10031.png"));
		icon[0] = new ImageIcon(ImageFrame.class.getResource("10032.png"));
		icon[0] = new ImageIcon(ImageFrame.class.getResource("10033.png"));
		icon[0] = new ImageIcon(ImageFrame.class.getResource("10034.png"));
		icon[0] = new ImageIcon(ImageFrame.class.getResource("10035.png"));
		icon[0] = new ImageIcon(ImageFrame.class.getResource("10036.png"));
		icon[0] = new ImageIcon(ImageFrame.class.getResource("10037.png"));
		
		
		
		
		
		
		
		
		
		
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WeatherFrame();
//				Weather weather = new Weather();
//				
//				//String date = Config.weather.getW_date().toString();
//				float tem = Config.weather.getTemperature();
//				//String hour = Config.weather.getW_hour().toString();
//				
//				weather.setTemperature(tem);
//				
//				Config.weather = wsv.select_tem_clothesset_final_view(weather);
//				
//				List<Clothesset> clothes = new ArrayList();
//				
//				for(int i=1; i<9; i++) {
//					if( rsv.selectRecommendSetno(tem) == i) {
//						clothes = csv.selectClothesset(i);
//						System.out.println(clothes.toString());
//					}
//				}
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
