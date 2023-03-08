package frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import common.Config;

public class MainFrame2 extends JFrame {
	private JTextField textField;
	private JTextField textField_1;

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

		setTitle("옷 추천");
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("지역");
		lblNewLabel.setBounds(31, 62, 57, 15);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("날짜");
		lblNewLabel_1.setBounds(31, 108, 57, 15);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("시간");
		lblNewLabel_2.setBounds(31, 152, 57, 15);
		getContentPane().add(lblNewLabel_2);

		textField = new JTextField(Config.obj.getAddress());
		textField.setEnabled(false);
		textField.setColumns(10);
		textField.setBounds(89, 59, 116, 21);
		getContentPane().add(textField);

		textField_1 = new JTextField("yyyy-mm-dd");
		textField_1.setColumns(10);
		textField_1.setBounds(89, 105, 116, 21);
		getContentPane().add(textField_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"00시", "01시", "02시", "03시", "04시", "05시", "06시", "07시", "08시", "09시", "10시", "11시", "12시", "13시", "14시", "15시", "16시", "17시", "18시", "19시", "20시", "21시", "22시", "23시"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(89, 148, 116, 23);
		getContentPane().add(comboBox);

		JButton btnNewButton = new JButton("추천 시작");
		btnNewButton.setFocusPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(263, 81, 97, 68);
		getContentPane().add(btnNewButton);
		textField_1.setColumns(10);

		this.setSize(400, 300); // 창 사이즈 이거 없으면 창 뜰 때 걍 소멸수준임;;;
		this.setLocationRelativeTo(null); // 화면 중앙에 오게 해줌

		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 이건 윈도우 창 종료시 프로세스까지 깔끔하게 닫는거래
		// 우리는 창을 껐는데 작업관리자에서는 가동중이래
	}
}