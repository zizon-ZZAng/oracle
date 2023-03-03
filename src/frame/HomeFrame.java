package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class HomeFrame extends JFrame{
	public HomeFrame() {
		setTitle("지존짱");
		getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnNewButton = new JButton("로그인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();
			}
		});
		menuBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("회원가입");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SignUpFrame();
			}
		});
		menuBar.add(btnNewButton_1);
		
		this.setSize(400, 300);
		this.setLocationRelativeTo(null); //화면 중앙에 오게 해줌
		
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
