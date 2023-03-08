package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dto.Member2;
import service.MemberService;
import service.Member2ServiceImpl;

public class UnRegisterFrame extends JFrame {

	MemberService mService = new Member2ServiceImpl();

	private JTextField textField;
	private JTextField textField_1;

	public UnRegisterFrame() {
		setTitle("회원탈퇴");
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(100, 68, 57, 15);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(100, 120, 57, 15);
		getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(191, 65, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(191, 117, 116, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("회원탈퇴");
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

				Member2 member = new Member2();
				
				member.setId(id);
				member.setPassword(hash);

				mService.unMember(member);

				JOptionPane.showMessageDialog(null, "회원 탈퇴 완료");
				

				new HomeFrame();
				dispose();

			}
		});
		btnNewButton.setBounds(167, 192, 97, 23);
		getContentPane().add(btnNewButton);
		
		this.setSize(400, 300);
		this.setLocationRelativeTo(null); // 화면 중앙에 오게 해줌

		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
