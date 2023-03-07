package frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dto.Member;
import service.MemberService;
import service.MemberServiceImpl;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class SignUpFrame extends JFrame{
	
	MemberService s = new MemberServiceImpl();
	private JTextField textField_id;
	private JTextField textField_name;
	private JPasswordField passwordField;
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
		
		JComboBox comboBox_Sex = new JComboBox();
		comboBox_Sex.setModel(new DefaultComboBoxModel(new String[] {"F", "M"}));
		comboBox_Sex.setBounds(155, 204, 116, 23);
		getContentPane().add(comboBox_Sex);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(155, 126, 116, 21);
		getContentPane().add(passwordField);
		
		JComboBox comboBox_Add = new JComboBox();
		comboBox_Add.setModel(new DefaultComboBoxModel(new String[] {"서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "세종특별자치시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도"}));
		comboBox_Add.setBounds(155, 165, 116, 23);
		getContentPane().add(comboBox_Add);
		
		JButton comButton = new JButton("완료");
		//insertMember
		comButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Member m = new Member();
				
				m.setId(textField_id.getText());
				m.setName(textField_name.getText()); 
				m.setPassword(passwordField.getText());
				m.setAddress(comboBox_Add.getSelectedItem().toString());
				m.setSex(comboBox_Sex.getSelectedItem().toString());
				
				int ret = s.insertMember(m);
				
				if(ret ==1 ) {
					new LoginFrame();
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "입력해주세요.");
				}

			}
		});
		comButton.setBounds(118, 276, 97, 23);
		getContentPane().add(comButton);
		

		
		this.setSize(400, 400);	// 사이즈 정하기
		this.setVisible(true);
		
	}
}
