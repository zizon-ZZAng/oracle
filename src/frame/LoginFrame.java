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

public class LoginFrame extends JFrame {

	MemberService mService = new MemberServiceImpl();

	private JTextField textField;
	private JPasswordField passwordField;

	
	//로그인 화면
	public LoginFrame() {
		setTitle("로그인");
		getContentPane().setLayout(null);

		
		//아이디
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(74, 60, 57, 15);
		getContentPane().add(lblNewLabel);
		
		//아이디 입력칸
		textField = new JTextField();
		textField.setBounds(176, 57, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		
		//비밀번호
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(74, 115, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		//비밀번호 입력칸
		passwordField = new JPasswordField();
		passwordField.setBounds(176, 112, 116, 21);
		getContentPane().add(passwordField);
		
		
		//로그인 버튼
		JButton btnNewButton = new JButton("로그인");
		btnNewButton.setFocusPainted(false);
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

			
			//로그인 버튼 눌렀을 때 실행
			public void actionPerformed(ActionEvent e) {

				Member member = new Member();

				String id = textField.getText();
				String pw = passwordField.getText();

				String hash = this.hashPW(pw, id);

				member.setId(id);
				member.setPassword(hash);
				
				
				Config.obj = mService.loginMember(member);	// 로그인 정보 저장
				
				// 아이디나 비밀번호 성공했을 때의 경우
				if (Config.obj != null) {

					// JOptionPane 이용시 사용자 입력창, 확인창, 알림창 만들 수 있음
					// showMessageDialog 알림창 띄우는 함수
					JOptionPane.showMessageDialog(null, "로그인 성공");

					new MainFrame2();

					dispose(); // 기존 창 종료

				} else if (id.length() == 0 || pw.length() == 0) { // 둘 다 입력 안했을 경우

					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 입력해주세요.");
				}

				else { // 실패했을 경우

					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀렸습니다.");

				}

			}
		});

		btnNewButton.setBounds(208, 175, 97, 23);
		getContentPane().add(btnNewButton);

		
		//초기화 버튼 - 입력 했던거 지우기
		JButton btnNewButton_1 = new JButton("초기화");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textField.setText("");
				passwordField.setText("");

			}
		});
		btnNewButton_1.setBounds(67, 175, 97, 23);
		getContentPane().add(btnNewButton_1);

		
		//뒤로가기 버튼
		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.setBackground(new Color(240, 240, 240)); // 프레임 색을 버튼에 적용(투명해진 척~)
		btnNewButton_2.setFocusPainted(false); // 선택되었을 때 생기는 테두리 사용안함
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HomeFrame(); 
				dispose();
			}
		});
		btnNewButton_2.setBounds(0, 0, 81, 23);
		getContentPane().add(btnNewButton_2);

		
		//화면 설정
		this.setSize(400, 300);
		this.setLocationRelativeTo(null); // 화면 중앙에 오게 해줌

		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
