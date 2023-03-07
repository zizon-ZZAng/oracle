package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextField;

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
import common.Config;

import dto.Member;
import service.MemberService;
import service.MemberServiceImpl;

public class MyPageFrame extends JFrame {
	
	MemberService mService = new MemberServiceImpl();
<<<<<<< Updated upstream
	
=======

	LoginFrame lf = new LoginFrame();
	
	private JTextField textField;

>>>>>>> Stashed changes
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	
	
	public MyPageFrame() {
		setTitle("나의 정보");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnNewButton = new JButton("회원 정보");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//어쩌지
				
				
			}
		});
		menuBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("회원탈퇴");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new UnRegisterFrame();
				dispose();
				
			}
		});
		menuBar.add(btnNewButton_1);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("필수정보");
		lblNewLabel.setBounds(12, 10, 57, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setBounds(31, 48, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("이름");
		lblNewLabel_2.setBounds(31, 83, 57, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("성별");
		lblNewLabel_3.setBounds(31, 114, 57, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("비밀번호");
		lblNewLabel_4.setBounds(31, 145, 57, 15);
		getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton_2 = new JButton("비밀번호 변경");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//비밀번호 변경 Frame 이동
				
				
				
				
				
			}
		});
		btnNewButton_2.setBounds(100, 141, 117, 23);
		getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_5 = new JLabel("주소");
		lblNewLabel_5.setBounds(31, 182, 57, 15);
		getContentPane().add(lblNewLabel_5);
		

		// id
		textField = new JTextField(Config.obj.getId());
		textField.setEnabled(false);
		textField.setBounds(101, 45, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
<<<<<<< Updated upstream
=======


>>>>>>> Stashed changes
		
		// 이름
		textField_1 = new JTextField();
		textField_1.setBounds(101, 80, 116, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		// 성별
		textField_2 = new JTextField(Config.obj.getGender());
		textField_2.setEnabled(false);
		textField_2.setBounds(101, 111, 116, 21);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		// 주소
		textField_3 = new JTextField();
		textField_3.setBounds(101, 179, 116, 21);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("회원정보 수정");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				//회원 정보 수정
				
			}
		});
		btnNewButton_3.setBounds(255, 205, 119, 23);
		getContentPane().add(btnNewButton_3);
		
		this.setSize(426, 309); // 창 사이즈 이거 없으면 창 뜰 때 걍 소멸수준임;;;
		this.setLocationRelativeTo(null); //화면 중앙에 오게 해줌
		
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
