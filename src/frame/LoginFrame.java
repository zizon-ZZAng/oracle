package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dto.Member;
import service.MemberService;
import service.MemberServiceImpl;

public class LoginFrame extends JFrame {
	private JTextField textField_id;
	private JTextField textField_pw;

	public LoginFrame() {

		MemberService s = new MemberServiceImpl();
		setTitle("로그인");
		getContentPane().setLayout(null);

		textField_id = new JTextField();
		textField_id.setBounds(175, 85, 116, 21);
		getContentPane().add(textField_id);
		textField_id.setColumns(10);

		textField_pw = new JTextField();
		textField_pw.setBounds(175, 133, 116, 21);
		getContentPane().add(textField_pw);
		textField_pw.setColumns(10);

		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(66, 91, 57, 15);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(66, 139, 57, 15);
		getContentPane().add(lblNewLabel_1);

		JButton loginButton = new JButton("로그인");
		loginButton.addActionListener(new ActionListener() {
			
			
			public String hashPW(String pw, String id) {
				try {
					// 1. Hash 알고리즘 SHA-256, 단방향 aa => dlkfjafkajl
					MessageDigest md = MessageDigest.getInstance("SHA-256");
					
					// ex) 1234(암호) + salt(고유한 값, userid)
					md.update((pw + id).getBytes());
					
					// byte to String 으로 변경
					byte[] pwdSalt = md.digest();
					
					StringBuffer sb = new StringBuffer();
					for (byte b : pwdSalt) {
						sb.append(String.format("%02x", b));
					}
					
					String result = sb.toString();
					
					return result;
				}
				catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
			
			public void actionPerformed(ActionEvent e) {
					Member mem = new Member();
//				if(e.getActionCommand().equals("로그인")) {
					

					// 아이디, 비밀번호 정보 가져오기
					String id = textField_id.getText();
					String pw = textField_pw.getText();
					
					String hash = this.hashPW(pw, id);
					
					mem.setId(id);
					mem.setPassword(hash);
					
					mem = s.loginMember(id, pw);
					
					if (mem != null) {
						JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
						new WeatherFrame(); // 화면 종료
						dispose();
						
					} else if (id.length()==0 || pw.length()==0){
						JOptionPane.showMessageDialog(null, "입력해주세요.");
						
					}else {
						JOptionPane.showMessageDialog(null, "아이디, 비밀번호를 확인해주세요.");
					}

//				}
				
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
