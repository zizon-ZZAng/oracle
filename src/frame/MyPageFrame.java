package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import common.Config;
import dto.Member;
import service.MemberService;
import service.MemberServiceImpl;

public class MyPageFrame extends JFrame {
	
	MemberService mService = new MemberServiceImpl();


	LoginFrame lf = new LoginFrame();
	
	private JTextField textField;

	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	
	
	
	public MyPageFrame() {
		setTitle("나의 정보");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("회원 정보");
		lblNewLabel.setBounds(22, 10, 71, 23);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setBounds(85, 49, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("이름");
		lblNewLabel_2.setBounds(85, 84, 57, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("성별");
		lblNewLabel_3.setBounds(85, 115, 57, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("비밀번호");
		lblNewLabel_4.setBounds(85, 146, 57, 15);
		getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton_2 = new JButton("변경");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 비밀번호 변경 버튼
				// 비밀번호 변경 Frame 이동
				
				
				
				
				
			}
		});
		btnNewButton_2.setBounds(283, 142, 67, 23);
		getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_5 = new JLabel("주소");
		lblNewLabel_5.setBounds(85, 183, 57, 15);
		getContentPane().add(lblNewLabel_5);
		

		// id
		textField = new JTextField(Config.obj.getId());
		textField.setEnabled(false);
		textField.setBounds(155, 46, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		
		// 이름
		textField_1 = new JTextField();
		textField_1.setBounds(155, 81, 116, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		// 성별
		textField_2 = new JTextField(Config.obj.getGender());
		textField_2.setEnabled(false);
		textField_2.setBounds(155, 112, 116, 21);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		// 주소
		textField_3 = new JTextField();
		textField_3.setBounds(155, 180, 116, 21);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("회원정보 수정");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 회원 정보 수정 버튼
				Member member = new Member();
				member.setName(textField_1.getText());
				member.setAddress(textField_3.getText());
				
				mService.updateMember(member);
			}
		});
		btnNewButton_3.setBounds(253, 225, 119, 23);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_1 = new JButton("회원탈퇴");
		btnNewButton_1.setBounds(31, 225, 119, 23);
		getContentPane().add(btnNewButton_1);
		
		passwordField = new JPasswordField(Config.obj.getPassword());
		passwordField.setBounds(154, 143, 117, 21);
		getContentPane().add(passwordField);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 회원 탈퇴 버튼
				new UnRegisterFrame();
				dispose();
				
			}
		});
		
		this.setSize(426, 309); // 창 사이즈 이거 없으면 창 뜰 때 걍 소멸수준임;;;
		this.setLocationRelativeTo(null); //화면 중앙에 오게 해줌
		
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
