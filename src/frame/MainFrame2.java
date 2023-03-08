package frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MainFrame2 extends JFrame {

	public MainFrame2() {
		setTitle("지존짱");
		getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		;

		JMenu mnNewMenu = new JMenu("마이페이지");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("회원정보");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mnNewMenu.add(mntmNewMenuItem);
//            menuBar.add(btnNewButton);

				dispose();
				new MyPageFrame();
			}

		});

		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("로그아웃");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
				new LogoutFrame();
				if (result == JOptionPane.YES_OPTION) {
					new HomeFrame();
					dispose();
				} else {
					new MainFrame2();
					dispose();
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		this.setSize(400, 300); // 창 사이즈 이거 없으면 창 뜰 때 걍 소멸수준임;;;
		this.setLocationRelativeTo(null); // 화면 중앙에 오게 해줌

		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 이건 윈도우 창 종료시 프로세스까지 깔끔하게 닫는거래
		// 우리는 창을 껐는데 작업관리자에서는 가동중이래
	}
}