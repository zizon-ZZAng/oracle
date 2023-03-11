package frame;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class RecommendFrame extends JFrame{

	private WeatherService wsv = new WeatherServiceImpl();
	private RecommendService rsv = new RecommendServiceImpl();
	private ImageIcon[] icon;
	private ImageIcon[] reIcon;
	private JLabel[] rec_label = new JLabel[3];
	private JButton btnNewButton_1;
	private List<Map<String, Object>> list;
	private String[] reNum = new String[3];
	//private int k=1;
	
	public RecommendFrame() {
		getContentPane().setLayout(null);

		JPanel clothes_panel = new JPanel();
		clothes_panel.setBounds(22, 25, 350, 237);
		getContentPane().add(clothes_panel);
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WeatherFrame();
				dispose();
			}
		});

		btnNewButton.setBounds(52, 290, 97, 23);
		getContentPane().add(btnNewButton);

		btnNewButton_1 = new JButton("다시 추천받기");
		btnNewButton_1.setBounds(218, 290, 121, 23);
		getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new MyListener());
		
		String id = Config.member.getId();
		String add = Config.conMap.get("address").toString();
		String week = Config.conMap.get("week").toString();
		String hour = Config.conMap.get("hour").toString();

		Map<String, Object> recMap = new HashMap<>();

		recMap.put("id", id);
		recMap.put("week", week);
		recMap.put("hour", hour);
		recMap.put("address", add);
		
		list = rsv.selectClothesTop(recMap);	
		
//		for(Map<String, Object> map:list) {
//			System.out.println(map.toString());
//		}
		
//		for(int i=0; i<list.size(); i++)
//			System.out.println(Integer.parseInt(list.get(i).get("RANK").toString()));

		for(int i=0; i<3; i++) {
			rec_label[i] = new JLabel("");
			rec_label[i].setBounds(50+i*20, 50+i*20, 50+i*20, 50+i*20);
			clothes_panel.add(rec_label[i]);
		}
		
		// 사진 집어넣기
		icon = new ImageIcon[29];
		for (int i = 0; i < 29; i++) {
			icon[i] = new ImageIcon(ImageFrame.class.getResource((101 + i) + ".png"));
			//System.out.println(icon[i]);
		}
		
		reNum = reRecommend(1);
		//System.out.println(k);
		
		
		for(int j=0; j<3; j++)
		System.out.println(reNum[j]);
		
		// 이미지 아이콘 배열에 각각 옷 사진 넣기
		reIcon = new ImageIcon[3];
		for (int i=0; i <3; i++) {
			for(int j=0; j<29-j; j++) {
				if (Integer.parseInt(reNum[i]) == j + 101) {	
					reIcon[i] = icon[j];
					//System.out.println(reIcon[i]);
				}
			}
		}
		
//		for(int i=0;i<3;i++) {
//			System.out.println(i);
//			System.out.println(reIcon[i]);
//		}
//		
		image();
		
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


	// 문제가 있음
	private String[] reRecommend(int num) {
		
		for (int i=0; i <list.size(); i++) {
			if (Integer.parseInt(list.get(i).get("RANK").toString()) == num) {
				for(int j=0; j<3; j++) {
					reNum[j] = list.get(i).get("CLNO").toString();
//					if(reNum[j]!=null)
//						continue;
				}
			}
		}	
		return reNum;
	}
	
	private class MyListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnNewButton_1) {
				//System.out.println(k);
				new RecommendFrame();
				dispose();
			}		
		}	
	}
	
}
