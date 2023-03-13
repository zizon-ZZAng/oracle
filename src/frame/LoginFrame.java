package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dto.Member;
import service.MemberService;
import service.MemberServiceImpl;
import session.Config;

public class LoginFrame extends JFrame {
	private JTextField textField_id;
	private JPasswordField passwordField;
	MemberService m = new MemberServiceImpl();

	public LoginFrame() {

		MemberService s = new MemberServiceImpl();
		setTitle("로그인");
		getContentPane().setLayout(null);

		textField_id = new JTextField();
		textField_id.setBounds(175, 85, 116, 21);
		getContentPane().add(textField_id);
		textField_id.setColumns(10);

		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(66, 91, 57, 15);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(66, 139, 57, 15);
		getContentPane().add(lblNewLabel_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(175, 136, 116, 21);
		getContentPane().add(passwordField);
		
		JButton loginButton = new JButton("로그인");
		loginButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
					Member mem = new Member();

					// 아이디, 비밀번호 정보 가져오기
					String id = textField_id.getText();
					String pw = passwordField.getText();
					
					String hash = m.hashPW(pw, id);
					
					mem.setId(id);
					mem.setPassword(hash);
					
					Config.member = s.loginMember(mem);
				
					if (Config.member != null) {
						JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
						new WeatherFrame(); // 화면 종료
						dispose();
						
					} else if (id.length()==0 || pw.length()==0){
						JOptionPane.showMessageDialog(null, "입력해주세요.");
						
					}else {
						JOptionPane.showMessageDialog(null, "아이디, 비밀번호를 확인해주세요.");
					}

			}
		});
		loginButton.setBounds(61, 220, 97, 23);
		getContentPane().add(loginButton);

		JButton signUpButton = new JButton("회원가입");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SignUpFrame();
				dispose();
			}
		});
		signUpButton.setBounds(219, 220, 97, 23);
		getContentPane().add(signUpButton);
		

		this.setSize(400, 400); // 사이즈 정하기
		this.setVisible(true);
	}
}
