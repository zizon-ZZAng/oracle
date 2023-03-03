package frame;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
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
			public void actionPerformed(ActionEvent e) {
				//로그인 성공시 홈 화면
				// 로그인 실패시 실패 문구 띄우기
				
			}
		});
		btnNewButton.setBounds(192, 175, 97, 23);
		getContentPane().add(btnNewButton);
		
		this.setSize(400, 300);
		this.setLocationRelativeTo(null); //화면 중앙에 오게 해줌
		
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//기존 창 없애줌
		
	}
}
