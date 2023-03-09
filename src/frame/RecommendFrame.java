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

		lblNewLabel_1_1.setBounds(12, 94, 250, 274);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1 = new JLabel("상의");
		lblNewLabel_1.setBounds(12, 26, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		// --------------------------------------------

		JLabel lblNewLabel_2 = new JLabel("하의");
		lblNewLabel_2.setBounds(356, 26, 57, 15);
		getContentPane().add(lblNewLabel_2);

		// 하의
		Map<String, Object> retb = rservice.clothesRecommendBottom(map);
		String imagecodeb = "image/" + retb.get("CLONO").toString() + ".png";

		ImageIcon imageb = new ImageIcon(imagecodeb);

		Image imagebot = imageb.getImage();
		Image updateImgb = imagebot.getScaledInstance(500, 500, Image.SCALE_SMOOTH);

		ImageIcon updatebot = new ImageIcon(updateImgb);
		JLabel lblNewLabel_2_1 = new JLabel(updatebot);

		lblNewLabel_2_1.setBounds(356, 110, 250, 274);
		getContentPane().add(lblNewLabel_2_1);
		
		// --------------------------------------------

		JLabel lblNewLabel_3 = new JLabel("신발");
		lblNewLabel_3.setBounds(680, 26, 57, 15);
		getContentPane().add(lblNewLabel_3);

		// 신발
		Map<String, Object> rets = rservice.clothesRecommendShoes(map);
		String imagecodes = "image/" + rets.get("CLONO").toString() + ".png";

		ImageIcon images = new ImageIcon(imagecodes);

		Image imagesho = images.getImage();
		Image updateImgs = imagesho.getScaledInstance(300, 300, Image.SCALE_SMOOTH);

		ImageIcon updatesho = new ImageIcon(updateImgs);
		JLabel lblNewLabel_3_1 = new JLabel(updatesho);

		lblNewLabel_3_1.setBounds(680, 94, 250, 274);
		getContentPane().add(lblNewLabel_3_1);
		
		// --------------------------------------------
		
		this.setSize(1118, 814);
		this.setLocationRelativeTo(null);

		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

	}
}