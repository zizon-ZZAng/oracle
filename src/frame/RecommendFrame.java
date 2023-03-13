package frame;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import common.Config;
import service.RecommendService;
import service.RecommendServiceImpl;

public class RecommendFrame extends JFrame {
	RecommendService rservice = new RecommendServiceImpl();

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	
	//옷 추천 화면
	public RecommendFrame() {
		setTitle("옷 추천");

		Map<String, Object> map = new HashMap<>();
		map.put("wdate", Config.wdate);
		map.put("id", Config.obj.getId());

		
		// 상의
		JLabel lblNewLabel_1 = new JLabel("상의");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		Map<String, Object> rett = rservice.clothesRecommendTop(map);
		String imagecodet = "image/" + rett.get("CLONO").toString() + ".png";

		ImageIcon imaget = new ImageIcon(imagecodet);

		Image imagetop = imaget.getImage();
		Image updateImgt = imagetop.getScaledInstance(250, 250, Image.SCALE_SMOOTH);

		ImageIcon updatetop = new ImageIcon(updateImgt);
		JLabel lblNewLabel_1_1 = new JLabel(updatetop);

		

// --------------------------------------------------------------------

		// 하의
		JLabel lblNewLabel_2 = new JLabel("하의");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

		
		Map<String, Object> retb = rservice.clothesRecommendBottom(map);
		String imagecodeb = "image/" + retb.get("CLONO").toString() + ".png";

		ImageIcon imageb = new ImageIcon(imagecodeb);

		Image imagebot = imageb.getImage();
		Image updateImgb = imagebot.getScaledInstance(250, 300, Image.SCALE_SMOOTH);

		ImageIcon updatebot = new ImageIcon(updateImgb);
		JLabel lblNewLabel_2_1 = new JLabel(updatebot);

		
// --------------------------------------------------------------------

		// 신발
		JLabel lblNewLabel_3 = new JLabel("신발");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);

		
		Map<String, Object> rets = rservice.clothesRecommendShoes(map);
		String imagecodes = "image/" + rets.get("CLONO").toString() + ".png";

		ImageIcon images = new ImageIcon(imagecodes);

		Image imagesho = images.getImage();
		Image updateImgs = imagesho.getScaledInstance(250, 250, Image.SCALE_SMOOTH);

		ImageIcon updatesho = new ImageIcon(updateImgs);
		JLabel lblNewLabel_3_1 = new JLabel(updatesho);
		
// --------------------------------------------------------------------
		
		textField = new JTextField(rett.get("CLONAME").toString());
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setColumns(10);
		
		textField_1 = new JTextField(retb.get("CLONAME").toString());
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(rets.get("CLONAME").toString());
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField(rett.get("PRICE").toString());
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField(retb.get("PRICE").toString());
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField(rets.get("PRICE").toString());
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		
		
		//추천 완료 됐을 때 확인 버튼 눌러서 창을 끔
		JButton btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); 
			}
		});
		
		
		//그룹 레이아웃 사용
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
								.addComponent(textField)
								.addComponent(textField_3))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2_1, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
								.addComponent(textField_1)
								.addComponent(textField_4))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_3_1, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
								.addComponent(textField_2)
								.addComponent(textField_5)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(481)
							.addComponent(btnNewButton)))
					.addContainerGap(81, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3))
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel_3_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(48))
		);
		getContentPane().setLayout(groupLayout);

// --------------------------------------------------------------------

		//화면설정
		this.setSize(1076, 700);
		this.setLocationRelativeTo(null);

		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}