package frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class RecommendFrame extends JFrame{
   private JTextField textField;
   private JTextField textField_1;
   public RecommendFrame() {
      setTitle("옷 추천");
      getContentPane().setLayout(null);
      
      this.setSize(462, 300);
      this.setLocationRelativeTo(null); 

      this.setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}