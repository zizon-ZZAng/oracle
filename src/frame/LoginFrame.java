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

				Member member = new Member();

				String id = textField.getText();
				String pw = textField_1.getText();

				String hash = this.hashPW(pw, id);

				member.setId(id);
				member.setPassword(hash);

				// 아이디나 비밀번호 성공했을 때의 경우
				// ""자리에 뭐가 들어가야하는데 싹 다 디져주세요 ㅠㅠ
				if (id.equals("a") && pw.equals("a")) {

					mService.loginMember(member);

					// JOptionPane 이용시 사용자 입력창, 확인창, 알림창 만들 수 있음
					// showMessageDialog 알림창 띄우는 함수
					JOptionPane.showMessageDialog(null, "로그인 성공");

					new MainFrame2();

					dispose(); // 기존 창 종료

					// System.exit(0); //화면을 종료 시키기. 0이 정상종료임 // 아무래도 모든 창이 종료되는 듯

				} else if (id.length() == 0 || pw.length() == 0) { // 둘 다 입력 안했을 경우

					JOptionPane.showMessageDialog(null, "다시 입력해");
				}

				else { // 실패했을 경우

					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀렸습니다. ^3<");

				}

			}
		});

		btnNewButton.setBounds(208, 175, 97, 23);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("초기화");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textField.setText("");
				textField_1.setText("");

			}
		});
		btnNewButton_1.setBounds(67, 175, 97, 23);
		getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("＜");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HomeFrame();
				dispose();
			}
		});
		btnNewButton_2.setBounds(0, 0, 57, 23);
		getContentPane().add(btnNewButton_2);

		this.setSize(400, 300);
		this.setLocationRelativeTo(null); // 화면 중앙에 오게 해줌

		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
