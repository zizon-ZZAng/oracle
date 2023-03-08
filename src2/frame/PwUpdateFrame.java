package frame;

import javax.swing.JFrame;

import service.MemberService;
import service.MemberServiceImpl;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class PwUpdateFrame extends JFrame{
	
	MemberService mService = new MemberServiceImpl();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public PwUpdateFrame() {
		setTitle("비밀번호 변경");

	getContentPane().setLayout(null);
	
	JLabel lblNewLabel = new JLabel("현재 비밀번호");
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setBounds(62, 54, 91, 15);
	getContentPane().add(lblNewLabel);
	
	JLabel lblNewLabel_1 = new JLabel("새 비밀번호");
	lblNewLabel_1.setBounds(68, 95, 85, 15);
	getContentPane().add(lblNewLabel_1);
	
	textField = new JTextField();
	textField.setBounds(191, 51, 116, 21);
	getContentPane().add(textField);
	textField.setColumns(10);
	
	textField_1 = new JTextField();
	textField_1.setBounds(191, 92, 116, 21);
	getContentPane().add(textField_1);
	textField_1.setColumns(10);
	
	JButton btnNewButton = new JButton("변경하기");
	btnNewButton.setBounds(74, 190, 97, 23);
	getContentPane().add(btnNewButton);
	
	JButton btnNewButton_1 = new JButton("취소");
	btnNewButton_1.setBounds(210, 190, 97, 23);
	getContentPane().add(btnNewButton_1);
	
	textField_2 = new JTextField();
	textField_2.setBounds(191, 133, 116, 21);
	getContentPane().add(textField_2);
	textField_2.setColumns(10);
	
	JLabel lblNewLabel_3 = new JLabel("새 비밀번호 확인");
	lblNewLabel_3.setBounds(68, 136, 103, 15);
	getContentPane().add(lblNewLabel_3);
	
	this.setSize(400, 300);
	this.setLocationRelativeTo(null); // 화면 중앙에 오게 해줌

	this.setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
