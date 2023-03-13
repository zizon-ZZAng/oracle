package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dto.Member;
import service.LocationService;
import service.LocationServiceImpl;
import service.MemberService;
import service.MemberServiceImpl;

public class SignUpFrame extends JFrame{
	
	
	
	LocationService l = new LocationServiceImpl();
	MemberService s = new MemberServiceImpl();
	private JTextField textField_id;
	private JTextField textField_name;
	private JPasswordField passwordField;
	public SignUpFrame() {
	
		
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(79, 51, 57, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setBounds(79, 95, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("비밀번호");
		lblNewLabel_2.setBounds(79, 140, 57, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("주소");
		lblNewLabel_3.setBounds(79, 180, 57, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("성별");
		lblNewLabel_4.setBounds(79, 219, 57, 15);
		getContentPane().add(lblNewLabel_4);
		
		textField_id = new JTextField();
		textField_id.setBounds(188, 48, 116, 21);
		getContentPane().add(textField_id);
		textField_id.setColumns(10);
		
		textField_name = new JTextField();
		textField_name.setBounds(188, 92, 116, 21);
		getContentPane().add(textField_name);
		textField_name.setColumns(10);
		
		JComboBox comboBox_Sex = new JComboBox();
		comboBox_Sex.setModel(new DefaultComboBoxModel(new String[] {"F", "M"}));
		comboBox_Sex.setBounds(188, 215, 116, 23);
		getContentPane().add(comboBox_Sex);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(188, 137, 116, 21);
		getContentPane().add(passwordField);
		
		JComboBox comboBox_Add = new JComboBox();
		comboBox_Add.setModel(new DefaultComboBoxModel(l.locationSelect()));
		comboBox_Add.setBounds(188, 176, 116, 23);
		getContentPane().add(comboBox_Add);
		
		JButton comButton = new JButton("완료");
		//insertMember
		comButton.addActionListener(new ActionListener() {	
			
			public void actionPerformed(ActionEvent e) {
				String id = textField_id.getText();
				String pw = passwordField.getText();
				
				String hash = s.hashPW(pw, id);
				
				Member m = new Member();
				
				m.setId(textField_id.getText());
				m.setName(textField_name.getText()); 
				m.setPassword(hash);
				m.setAddress(comboBox_Add.getSelectedItem().toString());
				m.setSex(comboBox_Sex.getSelectedItem().toString());
				m.setChk(1);
				
				int ret = s.insertMember(m);
				
				if(ret ==1 ) {
					JOptionPane.showMessageDialog(null, "가입되었습니다.");
					new LoginFrame();
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "입력해주세요.");
				}

			}
		});
		comButton.setBounds(151, 287, 97, 23);
		getContentPane().add(comButton);
		
		this.setSize(400, 400);	// 사이즈 정하기
		this.setVisible(true);
		
	}
}
