package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import service.MemberService;
import service.MemberServiceImpl;

public class LoginFrame extends JFrame implements ActionListener{
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
if(e.getActionCommand().equals("등록하기")) {
			
			// 아이디, 비밀번호 정보 가져오기
			String id = textField_id.getText();
			String pw = textField_pw.getText();
						
			MemberService s = new MemberServiceImpl();
							
			int ret = s.loginMember(id, pw);
			
			System.out.println(ret);
			if(ret ==1) {
				JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
				System.exit(0);	// 화면 종료
			}
			else {
				JOptionPane.showMessageDialog(null, "아이디,비번이 잘못되었습니다.");
			}
		
	}
}
