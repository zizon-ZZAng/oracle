package frame;

import java.util.HashMap;
import java.util.List;
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
		
		ImageIcon image = new ImageIcon("image/1006.png");
		JLabel lblNewLabel = new JLabel(image);

		lblNewLabel.setBounds(177, 75, 261, 190);
		getContentPane().add(lblNewLabel);

		this.setSize(520, 349);
		this.setLocationRelativeTo(null);

		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

	}
}