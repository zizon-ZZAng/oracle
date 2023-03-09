package frame;

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
import javax.swing.JPasswordField;

public class PwUpdateFrame extends JFrame {

	MemberService mService = new MemberServiceImpl();
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	public PwUpdateFrame() {
		setTitle("비밀번호 변경");
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("현재 비밀번호");
		lblNewLabel.setBounds(69, 52, 84, 15);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("새 비밀번호");
		lblNewLabel_1.setBounds(69, 89, 84, 15);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("새 비밀번호 (확인)");
		lblNewLabel_2.setBounds(69, 129, 104, 15);
		getContentPane().add(lblNewLabel_2);
		
		// 현재 비밀번호
		passwordField = new JPasswordField();
		passwordField.setBounds(214, 49, 116, 21);
		getContentPane().add(passwordField);
		
		// 새 비밀번호
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(214, 86, 116, 21);
		getContentPane().add(passwordField_1);
		
		// 새 비밀번호 (확인)
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(214, 126, 116, 21);
		getContentPane().add(passwordField_2);

		JButton btnNewButton = new JButton("변경하기");
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
				String pw = passwordField.getText();
				String newpw = passwordField_1.getText();
				String newpwchk = passwordField_2.getText();

				String hash = this.hashPW(pw, id);
				String newhash = this.hashPW(newpw, id);
				String newhashchk = this.hashPW(newpwchk, id);

				Member member = new Member();
				member.setId(id);
				member.setPassword(hash);
				member.setNewpw(newhash);

				if (!hash.equals(Config.obj.getPassword())) {
					JOptionPane.showMessageDialog(null, "현재 비밀번호가 틀렸습니다", "비밀번호 변경", JOptionPane.ERROR_MESSAGE);
				}
				else if (hash.equals(newhash)) {
					JOptionPane.showMessageDialog(null, "새 비밀번호를 현재 비밀번호와 다르게 입력해주세요", "비밀번호 변경", JOptionPane.ERROR_MESSAGE);
				}
				else if (!newhash.equals(newhashchk)) {
					JOptionPane.showMessageDialog(null, "새 비밀번호를 확인해주세요", "비밀번호 변경", JOptionPane.ERROR_MESSAGE);
				}
				else {
					mService.updatePWMember(member); //성공할 곳에 얘가 존재해야함 그래야 sql 실행되니깐
					JOptionPane.showMessageDialog(null, "성공", "비밀번호 변경", JOptionPane.PLAIN_MESSAGE);
					new MyPageFrame();
					dispose();
				}

			}
		});
		btnNewButton.setBounds(83, 183, 97, 23);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MyPageFrame();
				dispose();
			}
		});
		btnNewButton_1.setBounds(207, 183, 97, 23);
		getContentPane().add(btnNewButton_1);

		this.setSize(400, 300);
		this.setLocationRelativeTo(null);

		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}