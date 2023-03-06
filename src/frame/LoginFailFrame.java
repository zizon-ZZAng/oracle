package frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class LoginFailFrame extends JFrame {
	public LoginFailFrame() {
		setTitle("로그인 실패");
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		btnNewButton.setBounds(99, 119, 97, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("힝 틀렸쥬?");
		lblNewLabel.setBounds(114, 67, 72, 15);
		getContentPane().add(lblNewLabel);
	}
}
