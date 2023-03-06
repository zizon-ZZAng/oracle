package frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IdPwNullFrame extends JFrame {
	public IdPwNullFrame() {
		setTitle("뭐 안썼어 ㅠ");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("에잉 다시 써라!");
		lblNewLabel.setBounds(69, 71, 126, 54);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("다시 쓰기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(60, 148, 97, 23);
		getContentPane().add(btnNewButton);
		
		
		this.setSize(232, 272);
		this.setLocationRelativeTo(null); // 화면 중앙에 오게 해줌

		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

}
