package frame;

import javax.swing.JFrame;

import service.MemberService;
import service.MemberServiceImpl;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class LogoutFrame extends JFrame{
	
	MemberService mService = new MemberServiceImpl();
	
	public LogoutFrame() {
		setTitle("메세지");
		getContentPane().setLayout(null);
		
			
		JButton btnNewButton = new JButton("YES");
		btnNewButton.setBounds(43, 88, 97, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("NO");
		btnNewButton_1.setBounds(165, 88, 97, 23);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("로그아웃 하시겠습니까?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel.setBounds(68, 42, 173, 23);
		getContentPane().add(lblNewLabel);
	
		
		
		
		this.setLocationRelativeTo(null);
	}
	
	
}
