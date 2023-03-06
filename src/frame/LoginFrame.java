package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dto.Member;
import service.MemberService;
import service.MemberServiceImpl;

public class LoginFrame extends JFrame {

	MemberService mService = new MemberServiceImpl();

	private JTextField textField;
	private JTextField textField_1;

	public LoginFrame() {
		setTitle("로그인");
		getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(176, 57, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(74, 60, 57, 15);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(74, 115, 57, 15);
		getContentPane().add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(176, 112, 116, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("로그인");
		btnNewButton.addActionListener(new ActionListener() {

			// 비밀번호 암호화
			public String hashPW(String pw, String id) {
				try {

					MessageDigest md = MessageDigest.getInstance("SHA-256");

					md.update((pw + id).getBytes());

					byte[] pwdSalt = md.digest();

					StringBuffer sb = new StringBuffer();
					for (byte b : pwdSalt) {
						sb.append(String.format("%02x", b));
					}

					String result = sb.toString();
					return result;

				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}

			}

			public void actionPerformed(ActionEvent e) {
				
				
				String id = textField.getText();
				String pw = textField_1.getText();
				
				String hash = this.hashPW(pw, id);
				
				Member member = new Member();
				member.setId(id);
				member.setPassword(hash);
				
				mService.loginMember(member);
				
			
				
				
				

//				if()
//				//로그인 성공시 홈 화면
//				else { //로그인 실패시
//					new LoginFailFrame();
//				}

			}
		});
		btnNewButton.setBounds(192, 175, 97, 23);
		getContentPane().add(btnNewButton);

		this.setSize(400, 300);
		this.setLocationRelativeTo(null); // 화면 중앙에 오게 해줌

		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
