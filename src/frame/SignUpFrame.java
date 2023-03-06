package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dto.Member;
import service.MemberService;
import service.MemberServiceImpl;


public class SignUpFrame extends JFrame{

	MemberService mService = new MemberServiceImpl();

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	private JTextField textField_3;


	public SignUpFrame() {
		setTitle("회원가입");
		getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setBounds(98, 68, 57, 15);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("비밀번호");
		lblNewLabel_2.setBounds(98, 107, 57, 15);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("주소");
		lblNewLabel_3.setBounds(98, 143, 57, 15);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("성별");
		lblNewLabel_4.setBounds(98, 180, 57, 15);
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("이름");
		lblNewLabel_5.setBounds(98, 30, 57, 15);
		getContentPane().add(lblNewLabel_5);

		textField = new JTextField();
		textField.setBounds(161, 27, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(161, 65, 116, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(161, 104, 116, 21);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);


		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(161, 140, 116, 21);
		getContentPane().add(textField_3);
		

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "F", "M" }));
		comboBox_1.setBounds(161, 176, 57, 23);
		getContentPane().add(comboBox_1);

		JButton btnNewButton = new JButton("회원가입");
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
			
			
			//입력하면 데이터저장
			public void actionPerformed(ActionEvent e) {
				String id = textField_1.getText();
				String pw = textField_2.getText();
				
				String hash = this.hashPW(pw, id);
				
				Member member = new Member();
				member.setName(textField.getText());
				member.setId(id);
				member.setPassword(hash);
				member.setAddress(textField_3.getText());
				member.setGender(comboBox_1.getSelectedItem().toString());

				mService.signUpMember(member);
				
				new HomeFrame();

				dispose();
			}
		});
		btnNewButton.setBounds(140, 217, 97, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("←");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new HomeFrame();
				dispose();
			}
		});
		btnNewButton_1.setBounds(0, 0, 45, 23);
		getContentPane().add(btnNewButton_1);

		this.setSize(400, 300);
		this.setLocationRelativeTo(null); // 화면 중앙에 오게 해줌

		this.setVisible(true);
	}
}
