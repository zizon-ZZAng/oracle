package frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame{
	private JTextField textField_id;
	private JTextField textField_pw;
	public LoginFrame() {
		getContentPane().setLayout(null);
		
		textField_id = new JTextField();
		textField_id.setBounds(201, 52, 116, 21);
		getContentPane().add(textField_id);
		textField_id.setColumns(10);
		
		textField_pw = new JTextField();
		textField_pw.setBounds(201, 100, 116, 21);
		getContentPane().add(textField_pw);
		textField_pw.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(96, 55, 57, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(96, 103, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		JButton loginButton = new JButton("로그인");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		loginButton.setBounds(91, 184, 97, 23);
		getContentPane().add(loginButton);
		
		JButton signUpButton = new JButton("회원가입");
		signUpButton.setBounds(249, 184, 97, 23);
		getContentPane().add(signUpButton);
		
		this.setSize(400, 400);	// 사이즈 정하기
		this.setVisible(true);
	}
}
