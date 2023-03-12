package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class NoMoreRecommendFrame extends JFrame implements ActionListener{
	private JPanel panel;
	private JLabel label;
	private JButton button;
	
	
	public NoMoreRecommendFrame() {
		this.setTitle("I AM REALLY SORRY...");
		this.setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		label = new JLabel("더이상 추천목록이 없습니다.");
		
		button = new JButton("SEE YOU NEXT TIME");
		button.addActionListener(this);
		
		panel.add(label);
		panel.add(button);
		
		this.add(panel);
		this.setVisible(true);		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();		
	}
}

