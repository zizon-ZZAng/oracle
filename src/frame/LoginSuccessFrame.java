package frame;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;

public class LoginSuccessFrame extends JFrame  {
	public LoginSuccessFrame() {
		setTitle("로그인");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("로그인 성공하셨슴둥~");
		lblNewLabel.setBounds(85, 67, 148, 24);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new MainFrame2();
				dispose();
			}
		});
		btnNewButton.setBounds(95, 124, 97, 23);
		getContentPane().add(btnNewButton);
		
		this.setSize(310, 242);
		this.setLocationRelativeTo(null); // 화면 중앙에 오게 해줌

		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	

}
