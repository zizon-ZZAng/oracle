package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class HomeFrame extends JFrame{	//JFrame 실행되려면 상속되어 있어야함 ! extends JFrame 꼭 써주기

	
	public HomeFrame() {
		setTitle("지존짱");
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnNewButton = new JButton("로그인");
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("회원가입");
		getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SignUpFrame(); //회원가입 버튼 누르면 회원가입 창으로 이동
				dispose(); // 새 창 띄우면 기존 창 사라짐 
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();	//로그인 버튼 누르면 로그인 창으로 이동 꺄륶!
				dispose(); // 새 창 띄우면 기존 창 사라짐 
			}
		});
		
		this.setSize(400, 300); // 창 사이즈 이거 없으면 창 뜰 때 걍 소멸수준임;;;
		this.setLocationRelativeTo(null); //화면 중앙에 오게 해줌
		
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 이건 윈도우 창 종료시 프로세스까지 깔끔하게 닫는거래
														// 우리는 창을 껐는데 작업관리자에서는 가동중이래 
	}
}
