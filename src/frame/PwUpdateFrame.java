package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import common.Config;
import service.MemberService;
import service.MemberServiceImpl;

public class PwUpdateFrame extends JFrame {

	MemberService mService = new MemberServiceImpl();

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		textField = new JTextField();
		textField.setBounds(214, 49, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		// 새 비밀번호
		textField_1 = new JTextField();
		textField_1.setBounds(214, 86, 116, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		// 새 비밀번호(확인)
		textField_2 = new JTextField();
		textField_2.setBounds(214, 126, 116, 21);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton = new JButton("변경하기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText() == Config.obj.getPassword()) {
					
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