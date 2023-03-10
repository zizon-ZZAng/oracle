package frame;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import service.RecommendService;
import service.RecommendServiceImpl;
import service.WeatherService;
import service.WeatherServiceImpl;
import session.Config;

public class RecommendFrame extends JFrame {

	WeatherService wsv = new WeatherServiceImpl();
	RecommendService rsv = new RecommendServiceImpl();

	public RecommendFrame() {
		getContentPane().setLayout(null);

		JPanel clothes_panel = new JPanel();
		clothes_panel.setBounds(22, 25, 350, 237);
		getContentPane().add(clothes_panel);
		
		JLabel top_label = new JLabel("");
		top_label.setBounds(50, 50, 50, 50);
		clothes_panel.add(top_label);
		
		JLabel bottom_label = new JLabel("bottom");
		bottom_label.setBounds(50, 50, 50, 50);
		clothes_panel.add(bottom_label);
		
		JLabel shoes_label = new JLabel("shoes");
		shoes_label.setBounds(50, 50, 50, 50);
		clothes_panel.add(shoes_label);	
		
		String id = Config.member.getId();
		String add = Config.conMap.get("address").toString();
		String week = Config.conMap.get("week").toString();
		String hour = Config.conMap.get("hour").toString();

		Map<String, Object> recMap = new HashMap<>();

		recMap.put("id", id);
		recMap.put("week", week);
		recMap.put("hour", hour);
		
		List<Map<String, Object>> list = rsv.selectClothesTop(recMap);

//		for(int i=0; i<list.size(); i++)
//			System.out.println(Integer.parseInt(list.get(i).get("RANK").toString()));

		ImageIcon[] icon = new ImageIcon[17];
		for (int i = 0; i < 17; i++) {
			icon[i] = new ImageIcon(ImageFrame.class.getResource((101 + i) + ".png"));
		}
		
		//System.out.println(Integer.parseInt(list.get(0).get("CLNO").toString()));
		
		String[] reNum= new String[3];
		
		for(int j=0; j<3; j++) {
			for (int i=0; i <list.size()-j; i++) {
				if (Integer.parseInt(list.get(i).get("RANK").toString()) == 1) {
					reNum[j] = list.get(i).get("CLNO").toString();
				}
			}
		}
		
		ImageIcon[] reIcon = new ImageIcon[3];
		for (int i=0; i <3; i++) {
			for(int j=0; j<17-i; j++) {
				if (Integer.parseInt(reNum[i]) == j + 101) {	
					reIcon[i] = icon[j];
				}
			}
		}
		
//		for(int i=0;i<3;i++) {
//			System.out.println(reIcon.toString());
//		}
		
		top_label.setIcon(icon[0]);
		bottom_label.setIcon(icon[1]);
		shoes_label.setIcon(icon[2]);
						
						
//							Image updateImg0 = listImage.get(0).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//							ImageIcon updateIcon0 = new ImageIcon(updateImg0);
//							
//							top_label.setIcon(icon[j]);
							
							
//							top_label.setBounds(50, 120, 165, 150);
//							top_label.setHorizontalAlignment(JLabel.CENTER);
//							
//							Image updateImg1 = listImage.get(1).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//							ImageIcon updateIcon1 = new ImageIcon(updateImg1);
//							
//							bottom_label.setIcon(updateIcon1);
//							bottom_label.setBounds(20, 180, 165, 150);
//							bottom_label.setHorizontalAlignment(JLabel.CENTER);
//							
//							Image updateImg2 = listImage.get(2).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//							ImageIcon updateIcon2 = new ImageIcon(updateImg1);
//							
//							shoes_label.setIcon(updateIcon2);
//							shoes_label.setBounds(20, 180, 165, 150);
//							shoes_label.setHorizontalAlignment(JLabel.CENTER);
										
						
//					}
//				}	
//
//			}
		


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
		
		
		this.setSize(400, 400); // 사이즈 정하기
		this.setVisible(true);
	}
}
