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

	private WeatherService wsv = new WeatherServiceImpl();
	private RecommendService rsv = new RecommendServiceImpl();
	private ImageIcon[] icon;
	private ImageIcon[] reIcon;
	private JLabel[] rec_label = new JLabel[3];

	public RecommendFrame() {
		getContentPane().setLayout(null);

		JPanel clothes_panel = new JPanel();
		clothes_panel.setBounds(22, 25, 350, 237);
		getContentPane().add(clothes_panel);
		
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

		for(int i=0; i<3; i++) {
			rec_label[i] = new JLabel("");
			rec_label[i].setBounds(50+i*20, 50+i*20, 50+i*20, 50+i*20);
			clothes_panel.add(rec_label[i]);
		}
		
		icon = new ImageIcon[17];
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
		
		// 이미지 아이콘 배열에 각각 옷 사진 넣기
		reIcon = new ImageIcon[3];
		for (int i=0; i <3; i++) {
			for(int j=0; j<17-i; j++) {
				if (Integer.parseInt(reNum[i]) == j + 101) {	
					reIcon[i] = icon[j];
				}
			}
		}
		
//		for(int i=0;i<3;i++) {
//			System.out.println(i);
//			System.out.println(reIcon[i]);
//		}
		
		image();
		
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
	
	
	private void image() {
		for(int j =0; j<3; j++) {
			Image img = reIcon[j].getImage();
			Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			ImageIcon updateIcon = new ImageIcon(updateImg);
	
			rec_label[j].setIcon(updateIcon);
			rec_label[j].setBounds(50+j*20, 120+j*40, 165+j*20, 150+j*40);
			rec_label[j].setHorizontalAlignment(JLabel.CENTER);
		}
	}
	
}
