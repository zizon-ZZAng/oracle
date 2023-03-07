package frame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import dto.Weather;
import service.WeatherService;
import service.WeatherServiceImpl;

public class WeatherFrame extends JDialog implements ActionListener {
	public WeatherFrame() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{46, 52, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		
		
		JLabel lblNewLabel = new JLabel("지역");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 1;
		getContentPane().add(comboBox, gbc_comboBox);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("날짜");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JComboBox comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 3;
		gbc_comboBox_1.gridy = 2;
		getContentPane().add(comboBox_1, gbc_comboBox_1);
		
		
		
		JLabel lblNewLabel_1_1 = new JLabel("시간");
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_1.gridx = 2;
		gbc_lblNewLabel_1_1.gridy = 3;
		getContentPane().add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		JComboBox comboBox_1_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1_1 = new GridBagConstraints();
		gbc_comboBox_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1_1.gridx = 3;
		gbc_comboBox_1_1.gridy = 3;
		getContentPane().add(comboBox_1_1, gbc_comboBox_1_1);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		
		if(e.getActionCommand().equals("지역<")) {
			WeatherService s = new WeatherServiceImpl();
			String a = new s.weatherSelect(null);
			JComboBox combo = new JComboBox();
			combo.addItem(a);
			
		}
			
			String title = comboBox.getText();
			String author = comboBox_1.getText();
			String price = comboBox_1_1.getText();
		
					
			Weather book = new Book();
			book.setTitle(title);
			book.setAuthor(author);
			book.setPrice( Long.parseLong(price));
			book.setCate(cate.charAt(0));
			// 4개의 항목의 값을 가져와서 Book타입 DB에 저장하는 소스 구현
			
			int ret = bookDB.insertBook(book);
		
	}

}
