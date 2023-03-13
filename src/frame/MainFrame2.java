package frame;

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
import dto.Weather;
import service.MemberService;
import service.MemberServiceImpl;
import service.WeatherService;
import service.WeatherServiceImpl;

public class MainFrame2 extends JFrame {
	WeatherService wService = new WeatherServiceImpl();
	MemberService mService = new MemberServiceImpl();

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	//홈화면(2) - 옷 추천을 위한 설정
	public MainFrame2() {
		
		setTitle("지존짱");
		getContentPane().setLayout(null);
		

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		
		JMenu mnNewMenu = new JMenu("마이페이지");
		menuBar.add(mnNewMenu);

		
		//회원정보 눌렀을 때
		JMenuItem mntmNewMenuItem = new JMenuItem("회원정보");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mnNewMenu.add(mntmNewMenuItem);

				dispose();
				new MyPageFrame();
			}

		});

		mnNewMenu.add(mntmNewMenuItem);

		
		// 로그아웃 눌렀을 때
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("로그아웃");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);

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

		
		
		// 지역
		JLabel lblNewLabel = new JLabel("지역");
		lblNewLabel.setBounds(33, 39, 57, 15);
		getContentPane().add(lblNewLabel);

		// 지역 (회원 주소를 받아와서 고정)
		// 로그인된 아이디를 회원정보 수정 후 한번 더 조회 해서 MainFrame2로 갔을 때 값이 변경되어 있게 했음
		// (Config.obj.getAddress() 였을 땐 회원정보에서 변경했는데 MainFrame2에서 변경 안됐었음. 예전값이 유지됐었음)
		// 그래서 mService.selectMemberOne(Config.obj.getId()) 여기서 조회하고 주소를 뽑아왔음
		textField = new JTextField(mService.selectMemberOne(Config.obj.getId()).getAddress());
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(91, 36, 116, 21);
		getContentPane().add(textField);
		

		// 날짜
		JLabel lblNewLabel_1 = new JLabel("날짜");
		lblNewLabel_1.setBounds(33, 85, 57, 15);
		getContentPane().add(lblNewLabel_1);		
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(91, 81, 116, 23);
		getContentPane().add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(wService.selectDayYMD()));
		

		// 온도
		JLabel lblNewLabel_3 = new JLabel("온도");
		lblNewLabel_3.setBounds(33, 173, 57, 15);
		getContentPane().add(lblNewLabel_3);

		// 온도 회원 거주지역 + 날짜 + 시간에 맞는 온도 띄움 (고정)
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(91, 170, 116, 21);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		
		

		// 시간
		JLabel lblNewLabel_2 = new JLabel("시간");
		lblNewLabel_2.setBounds(33, 129, 57, 15);
		getContentPane().add(lblNewLabel_2);

		JComboBox comboBox = new JComboBox();

		// 시간 설정하면 그 날 그 시간대의 기온이 뜸
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//
				Config.wdate = comboBox_1.getSelectedItem().toString() + " " + comboBox.getSelectedItem().toString().substring(0, 2);

				Weather weather = new Weather();
				weather.setLocname(mService.selectMemberOne(Config.obj.getId()).getAddress());
				weather.setWdate(Config.wdate);

				textField_2.setText(Integer.toString(wService.selectWeatherTemp(weather)));
			}
		});

		comboBox.setModel(new DefaultComboBoxModel(wService.selectDayTime()));
		comboBox.setToolTipText("");
		comboBox.setBounds(91, 125, 116, 23);
		getContentPane().add(comboBox);

		
		
		// 옷 추천시작 버튼
		JButton btnNewButton = new JButton("추천 시작");
		btnNewButton.setFocusPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String tmp = textField_2.getText();

				if (tmp.length() == 0) { // 날짜와 시간 선택 안됐을 경우
					JOptionPane.showMessageDialog(null, "날짜와 시간을 선택해주세요");
				}

				else {

					new RecommendFrame();

				}

			}
		});
		btnNewButton.setBounds(263, 81, 97, 68);
		getContentPane().add(btnNewButton);

		// 화면 설정
		this.setSize(400, 300); // 창 사이즈 이거 없으면 창 뜰 때 사이즈가 말도안되게 줄어들어있음
		this.setLocationRelativeTo(null); // 화면 중앙에 오게 해줌

		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 화면 완전히 닫기
														
	}
}