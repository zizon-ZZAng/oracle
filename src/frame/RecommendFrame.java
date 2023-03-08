package frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import common.Config;
import service.RecommendService;
import service.RecommendServiceImpl;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;

public class RecommendFrame extends JFrame {
	RecommendService rservice = new RecommendServiceImpl();
	
	private JTextField textField;
	private JTextField textField_1;

	public RecommendFrame() {
		setTitle("옷 추천");
		getContentPane().setLayout(null);
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("wdate", textField_1.getText());
		map.put("id", Config.obj.getId());

		List<Map<String, Object>> list = rservice.clothesRecommend(map);
		for (Map<String, Object> ret : list) {

		}

		this.setSize(462, 300);
		this.setLocationRelativeTo(null);

		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}