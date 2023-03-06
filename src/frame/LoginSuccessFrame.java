package frame;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class LoginSuccessFrame extends JFrame  {
	public LoginSuccessFrame() {
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnNewButton = new JButton("로그아웃");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		menuBar.add(btnNewButton);
		
		JMenu mnNewMenu = new JMenu("내정보");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("정보변경");
		mnNewMenu.add(mntmNewMenuItem);
	}
	
	

}
