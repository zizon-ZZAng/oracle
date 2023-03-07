package frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class SignUpFrame extends JFrame{
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_pw;
	private JTextField textField_add;
	public SignUpFrame() {
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(46, 40, 57, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setBounds(46, 84, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("비밀번호");
		lblNewLabel_2.setBounds(46, 129, 57, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("주소");
		lblNewLabel_3.setBounds(46, 169, 57, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("성별");
		lblNewLabel_4.setBounds(46, 208, 57, 15);
		getContentPane().add(lblNewLabel_4);
		
		textField_id = new JTextField();
		textField_id.setBounds(155, 37, 116, 21);
		getContentPane().add(textField_id);
		textField_id.setColumns(10);
		
		textField_name = new JTextField();
		textField_name.setBounds(155, 81, 116, 21);
		getContentPane().add(textField_name);
		textField_name.setColumns(10);
		
		textField_pw = new JTextField();
		textField_pw.setBounds(155, 126, 116, 21);
		getContentPane().add(textField_pw);
		textField_pw.setColumns(10);
		
		textField_add = new JTextField();
		textField_add.setBounds(155, 166, 116, 21);
		getContentPane().add(textField_add);
		textField_add.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(155, 204, 116, 23);
		getContentPane().add(comboBox);
		
		JButton comButton = new JButton("완료");
		comButton.setBounds(118, 276, 97, 23);
		getContentPane().add(comButton);
	}
}
