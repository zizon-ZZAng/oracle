package frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class UnRegisterSuccessFrame extends JFrame {
	public UnRegisterSuccessFrame() {
		setTitle("회원탈퇴");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("회원탈퇴가 완료되었습니다");
		lblNewLabel.setBounds(81, 57, 163, 15);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.setBounds(107, 102, 97, 23);
		getContentPane().add(btnNewButton);
	}
	
	

}
