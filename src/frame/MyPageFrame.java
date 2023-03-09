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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class MyPageFrame extends JFrame {

	MemberService msv = new MemberServiceImpl();

	private JTextField textField_id;
	private JPasswordField passwordField;
	private JTextField textField_name;

	public MyPageFrame() {


		this.setSize(400, 400); // 사이즈 정하기
		
		
		this.setSize(400, 400);	// 사이즈 정하기

		getContentPane().setLayout(null);

		textField_id = new JTextField();
		textField_id.setEditable(false);
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
		passwordField.setEditable(false);

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
		
		
		JComboBox comboBox_add = new JComboBox();
		comboBox_add.setModel(new DefaultComboBoxModel(new String[] { "서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시",
				"울산광역시", "세종특별자치시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도" }));
		comboBox_add.setBounds(192, 183, 96, 23);
		getContentPane().add(comboBox_add);
		this.setVisible(true);

		// id , password 기본값
		textField_id.setText(Config.member.getId().toString());
		passwordField.setText(Config.member.getPassword().toString());

		JButton btnNewButton = new JButton("변경하기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (e.getActionCommand().equals("변경하기")) {

					Member mem = new Member();

//				config session 만들기(id, password)
//				mem.setId(textField_id.getText());

				Member mem = new Member();
				
				mem.setId(Config.member.getId());
				mem.setPassword(Config.member.getPassword());
				mem.setName(textField_name.getText());
				mem.setAddress(comboBox_add.getSelectedItem().toString());


					
					String id = Config.member.getId().toString();
					String password = Config.member.getPassword().toString();
					String name = textField_name.getText();
					String address = comboBox_add.getSelectedItem().toString();
					
					mem.setId(id);
					mem.setPassword(password);
					mem.setName(name);
					mem.setAddress(address);

					Config.member = msv.loginMember(mem);

					if (msv.member1UpdateOne(mem) == 1) {
						msv.member1UpdateOne(mem);
						JOptionPane.showMessageDialog(null, "변경되었습니다.");
						new WeatherFrame(); // 화면 종료
						dispose();

					} else if (textField_name.getText().length() == 0 || 
								comboBox_add.getSelectedItem().toString().length() == 0) {
						JOptionPane.showMessageDialog(null, "입력해주세요.");
					}

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


	}
}
