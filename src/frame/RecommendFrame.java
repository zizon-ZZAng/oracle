package frame;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import common.Config;
import service.RecommendService;
import service.RecommendServiceImpl;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class RecommendFrame extends JFrame {
	RecommendService rservice = new RecommendServiceImpl();

	private JTextField textField;
	private JTextField textField_1;

	public RecommendFrame() {
		setTitle("옷 추천");

		Map<String, Object> map = new HashMap<>();
		map.put("wdate", Config.wdate);
		map.put("id", Config.obj.getId());

		// 상의
		Map<String, Object> rett = rservice.clothesRecommendTop(map);
		String imagecodet = "image/" + rett.get("CLONO").toString() + ".png";

		ImageIcon imaget = new ImageIcon(imagecodet);

		Image imagetop = imaget.getImage();
		Image updateImgt = imagetop.getScaledInstance(500, 500, Image.SCALE_SMOOTH);

		ImageIcon updatetop = new ImageIcon(updateImgt);
		JLabel lblNewLabel_1_1 = new JLabel(updatetop);

		JLabel lblNewLabel_1 = new JLabel("상의");
		
		// --------------------------------------------

		JLabel lblNewLabel_2 = new JLabel("하의");

		// 하의
		Map<String, Object> retb = rservice.clothesRecommendBottom(map);
		String imagecodeb = "image/" + retb.get("CLONO").toString() + ".png";

		ImageIcon imageb = new ImageIcon(imagecodeb);

		Image imagebot = imageb.getImage();
		Image updateImgb = imagebot.getScaledInstance(500, 500, Image.SCALE_SMOOTH);

		ImageIcon updatebot = new ImageIcon(updateImgb);
		JLabel lblNewLabel_2_1 = new JLabel(updatebot);
		
		// --------------------------------------------

		JLabel lblNewLabel_3 = new JLabel("신발");

		// 신발
		Map<String, Object> rets = rservice.clothesRecommendShoes(map);
		String imagecodes = "image/" + rets.get("CLONO").toString() + ".png";

		ImageIcon images = new ImageIcon(imagecodes);

		Image imagesho = images.getImage();
		Image updateImgs = imagesho.getScaledInstance(150, 150, Image.SCALE_SMOOTH);

		ImageIcon updatesho = new ImageIcon(updateImgs);
		JLabel lblNewLabel_3_1 = new JLabel(updatesho);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
							.addGap(94)
							.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
							.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
							.addGap(147))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addGap(322)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 386, Short.MAX_VALUE)
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addGap(263))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1)
							.addComponent(lblNewLabel_2))
						.addComponent(lblNewLabel_3))
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))))
		);
		getContentPane().setLayout(groupLayout);
		
		// --------------------------------------------
		
		this.setSize(1170, 814);
		this.setLocationRelativeTo(null);

		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}