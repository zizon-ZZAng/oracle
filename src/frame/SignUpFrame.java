package frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import service.MemberService;
import service.MemberServiceImpl;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class SignUpFrame extends JFrame{
	MemberService mService = new MemberServiceImpl();
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public SignUpFrame() {
		setTitle("회원가입");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setBounds(71, 66, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("비밀번호");
		lblNewLabel_2.setBounds(71, 105, 57, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("주소");
		lblNewLabel_3.setBounds(71, 141, 57, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("성별");
		lblNewLabel_4.setBounds(71, 178, 57, 15);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("이름");
		lblNewLabel_5.setBounds(71, 28, 57, 15);
		getContentPane().add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(134, 25, 116, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(134, 63, 116, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(134, 102, 116, 21);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(134, 137, 32, 23);
		getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"F", "M"}));
		comboBox_1.setBounds(134, 174, 57, 23);
		getContentPane().add(comboBox_1);
		
		JButton btnNewButton = new JButton("회원가입");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mService.signUpMember(Member);
				new HomeFrame();
				dispose();
			}
		});
		btnNewButton.setBounds(234, 209, 97, 23);
		getContentPane().add(btnNewButton);
		
		
		this.setSize(400, 300);
		this.setLocationRelativeTo(null); //화면 중앙에 오게 해줌
		
		this.setVisible(true);
	}
}
