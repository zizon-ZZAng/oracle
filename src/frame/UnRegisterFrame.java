package frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import common.Config;
import dto.Member;
import service.MemberService;
import service.MemberServiceImpl;

public class UnRegisterFrame extends JFrame {

	MemberService mService = new MemberServiceImpl();

	private JTextField textField;
	private JTextField textField_1;

	public UnRegisterFrame() {
		setTitle("회원탈퇴");
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(95, 68, 57, 15);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(95, 120, 57, 15);
		getContentPane().add(lblNewLabel_1);

		textField = new JTextField(Config.obj.getId());
		textField.setEnabled(false);
		textField.setBounds(186, 65, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(186, 117, 116, 21);
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
				

				String id = Config.obj.getId();
				String pw = textField_1.getText();

				String hash = this.hashPW(pw, id);

				Member member = new Member();
				
				member.setId(id);
				member.setPassword(hash);

				int ret = mService.unMember(member);

				// 탈퇴 성공시
				if (ret == 1) {
					JOptionPane.showMessageDialog(null, "회원 탈퇴 완료");
					new HomeFrame();
					dispose();
				}
				else if (textField_1.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
				}
				// 탈퇴 실패 (비밀번호 틀림)
				else {
					JOptionPane.showMessageDialog(null, "비밀번호를 정확하게 입력해주세요.", "비밀번호 오류", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnNewButton.setBounds(139, 191, 97, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MyPageFrame();
				dispose();
			}
		});
		btnNewButton_1.setBackground(new Color(240, 240, 240));
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBounds(0, 0, 81, 23);
		getContentPane().add(btnNewButton_1);
		
		this.setSize(400, 300);
		this.setLocationRelativeTo(null); // 화면 중앙에 오게 해줌

		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
