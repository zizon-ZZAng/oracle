package frame;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecommendFrame extends JFrame{
	public RecommendFrame() {
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(75, 290, 97, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("다시 추천받기");
		btnNewButton_1.setBounds(257, 290, 121, 23);
		getContentPane().add(btnNewButton_1);
	}

}
