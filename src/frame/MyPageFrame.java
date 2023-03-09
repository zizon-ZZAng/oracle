package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dto.Member;
import service.MemberService;
import service.MemberServiceImpl;
import session.Config;

public class MyPageFrame extends JFrame {
	
	MemberService msv = new MemberServiceImpl();
	
	private JTextField textField_id;
	private JPasswordField passwordField;
	private JTextField textField_name;
	private JTextField textField_add;
	public MyPageFrame() {
		
		
		this.setSize(400, 400);	// 사이즈 정하기
		getContentPane().setLayout(null);
		
		textField_id = new JTextField();
		textField_id.setBounds(192, 49, 96, 21);
		getContentPane().add(textField_id);
		textField_id.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(78, 52, 50, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(78, 97, 50, 15);
		getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(192, 94, 96, 21);
		getContentPane().add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("이름");
		lblNewLabel_2.setBounds(78, 140, 50, 15);
		getContentPane().add(lblNewLabel_2);
		
		textField_name = new JTextField();
		textField_name.setBounds(192, 137, 96, 21);
		getContentPane().add(textField_name);
		textField_name.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("주소");
		lblNewLabel_3.setBounds(78, 187, 50, 15);
		getContentPane().add(lblNewLabel_3);
		
		textField_add = new JTextField();
		textField_add.setBounds(192, 184, 96, 21);
		getContentPane().add(textField_add);
		textField_add.setColumns(10);
		
		JButton btnNewButton = new JButton("변경하기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Member mem = new Member();
				
				mem.setId(Config.member.getId());
				mem.setPassword(Config.member.getPassword());
				mem.setName(textField_name.getText());
				mem.setAddress(textField_add.getText());

				int ret = msv.member1UpdateOne(mem);
				
				
				if ( ret == 1) {
					JOptionPane.showMessageDialog(null, "변경되었습니다.");
					new WeatherFrame(); // 화면 종료
					dispose();
					
				} else if (textField_name.getText().length()==0 || textField_add.getText().length()==0){
					JOptionPane.showMessageDialog(null, "입력해주세요.");
						}
				
			}
		});
		btnNewButton.setBounds(57, 253, 117, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("되돌아가기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WeatherFrame();
				dispose();
				
			}
		});
		btnNewButton_1.setBounds(215, 253, 109, 23);
		getContentPane().add(btnNewButton_1);
		this.setVisible(true);
	}
}
