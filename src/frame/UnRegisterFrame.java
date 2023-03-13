package frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import common.Config;
import dto.Member;
import service.MemberService;
import service.MemberServiceImpl;

public class UnRegisterFrame extends JFrame {

	MemberService mService = new MemberServiceImpl();

	private JTextField textField;
	private JPasswordField passwordField;

	
	//회원탈퇴 화면
	public UnRegisterFrame() {
		setTitle("회원탈퇴");
		getContentPane().setLayout(null);

		
		//아이디
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(95, 68, 57, 15);
		getContentPane().add(lblNewLabel);

		// 아이디 입력칸
		textField = new JTextField(Config.obj.getId());	// 로그인한 아이디를 불러왔음
		textField.setEnabled(false);	//직접 수정 못하게 했음
		textField.setBounds(186, 65, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		
		// 비밀번호
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(95, 120, 57, 15);
		getContentPane().add(lblNewLabel_1);

		// 비밀번호 입력칸
		passwordField = new JPasswordField();
		passwordField.setBounds(186, 117, 116, 21);
		getContentPane().add(passwordField);

		
		// 회원탈퇴
		JButton btnNewButton = new JButton("회원탈퇴");
		btnNewButton.setFocusPainted(false);

		// 회원탈퇴 버튼을 눌렀을 경우
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

			// 회원탈퇴 진짜 실행
			public void actionPerformed(ActionEvent e) {

				// 탈퇴하려고 버튼 누른게 맞는지 확인
				int result = JOptionPane.showConfirmDialog(null, "회원탈퇴 하시겠습니까?", "회원탈퇴", JOptionPane.YES_NO_OPTION);

				
				if (result == JOptionPane.YES_OPTION) { // 회원탈퇴 하려고 YES 누른게 맞다면 실행

					String id = Config.obj.getId();
					String pw = passwordField.getText();

					String hash = this.hashPW(pw, id);

					Member member = new Member();

					member.setId(id);
					member.setPassword(hash);

					int ret = mService.unMember(member);

					if (ret == 1) { // 탈퇴 성공시

						JOptionPane.showMessageDialog(null, "회원 탈퇴 완료");
						new HomeFrame();
						dispose();

					} else if (passwordField.getText().length() == 0) { // 탈퇴 실패 (비밀번호 입력을 안했음)

						JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.", "비밀번호 오류", JOptionPane.WARNING_MESSAGE);
					}

					else { // 탈퇴 실패 (비밀번호 틀림)

						JOptionPane.showMessageDialog(null, "비밀번호를 정확하게 입력해주세요.", "비밀번호 오류", JOptionPane.ERROR_MESSAGE);
					}

				}
			}

		});

		btnNewButton.setBounds(139, 191, 97, 23);
		getContentPane().add(btnNewButton);
		

		// 뒤로가기
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
		

		// 화면 설정
		this.setSize(400, 300);
		this.setLocationRelativeTo(null); // 화면 실행 했을 때 중앙에 위치

		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
