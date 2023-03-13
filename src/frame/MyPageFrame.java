package frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import common.Config;
import dto.Member;
import service.LocationService;
import service.LocationServiceImpl;
import service.MemberService;
import service.MemberServiceImpl;

public class MyPageFrame extends JFrame {
   
   MemberService mService = new MemberServiceImpl();
   LocationService lService = new LocationServiceImpl();
   
   private JTextField textField;

   private JTextField textField_1;
   private JTextField textField_2;
   private JPasswordField passwordField;
   
   
   
   public MyPageFrame() {
      setTitle("나의 정보");
      getContentPane().setLayout(null);
      
      JLabel lblNewLabel = new JLabel("회원 정보");
      lblNewLabel.setBounds(22, 10, 71, 23);
      getContentPane().add(lblNewLabel);
      
      
      //아이디
      JLabel lblNewLabel_1 = new JLabel("아이디");
      lblNewLabel_1.setBounds(85, 49, 57, 15);
      getContentPane().add(lblNewLabel_1);
      
      //아이디 입력칸 (고정)
      textField = new JTextField(Config.obj.getId());
      textField.setEnabled(false);
      textField.setBounds(155, 46, 116, 21);
      getContentPane().add(textField);
      textField.setColumns(10);
      
      
      
      //이름
      JLabel lblNewLabel_2 = new JLabel("이름");
      lblNewLabel_2.setBounds(85, 84, 57, 15);
      getContentPane().add(lblNewLabel_2);
      
      //이름 입력칸
      textField_1 = new JTextField(mService.selectMemberOne(Config.obj.getId()).getName());
      textField_1.setBounds(155, 81, 116, 21);
      getContentPane().add(textField_1);
      textField_1.setColumns(10);
      
      
      
      // 성별
      JLabel lblNewLabel_3 = new JLabel("성별");
      lblNewLabel_3.setBounds(85, 115, 57, 15);
      getContentPane().add(lblNewLabel_3);
      
      //성별 입력칸 (고정)
      textField_2 = new JTextField(Config.obj.getGender());
      textField_2.setEnabled(false);
      textField_2.setBounds(155, 112, 116, 21);
      getContentPane().add(textField_2);
      textField_2.setColumns(10);
      
      
      
      //비밀번호
      JLabel lblNewLabel_4 = new JLabel("비밀번호");
      lblNewLabel_4.setBounds(85, 146, 57, 15);
      getContentPane().add(lblNewLabel_4);
      
      //비밀번호 입력칸 (고정) -- 따로 변경 가능하게 
      passwordField = new JPasswordField(mService.selectMemberOne(Config.obj.getId()).getPassword());
      passwordField.setEnabled(false);
      passwordField.setBounds(154, 143, 117, 21);
      getContentPane().add(passwordField);
      
     
      // 비밀번호 변경 버튼
      JButton btnNewButton_2 = new JButton("변경");
      btnNewButton_2.setFocusPainted(false);
      btnNewButton_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // 비밀번호 변경 Frame 이동
            new PwUpdateFrame();
            dispose();
         }
      });
      btnNewButton_2.setBounds(283, 142, 67, 23);
      getContentPane().add(btnNewButton_2);
     
      
      
      
      //주소
      JLabel lblNewLabel_5 = new JLabel("주소");
      lblNewLabel_5.setBounds(85, 183, 57, 15);
      getContentPane().add(lblNewLabel_5);
      
      //주소 입력칸
      JComboBox comboBox = new JComboBox();
      comboBox.setModel(new DefaultComboBoxModel(lService.selectLocation()));
      comboBox.setSelectedItem(mService.selectMemberOne(Config.obj.getId()).getAddress());
      comboBox.setBounds(155, 179, 116, 23);
      getContentPane().add(comboBox);
      
      
      
      // 회원정보 수정 버튼
      JButton btnNewButton_3 = new JButton("회원정보 수정");
      btnNewButton_3.setFocusPainted(false);
      btnNewButton_3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
           
            Member member = new Member();
            member.setName(textField_1.getText());
            member.setAddress(comboBox.getSelectedItem().toString());
            
            member.setId(Config.obj.getId());
            member.setPassword(Config.obj.getPassword());
            
            int ret = mService.updateMember(member);
            
            if (ret==1) {
            	JOptionPane.showMessageDialog(null, "회원정보 수정 성공");
            }
            else if (textField_1.getText().length() == 0) {
               JOptionPane.showMessageDialog(null, "이름을 입력하세요.");
            }
         }
      });
      btnNewButton_3.setBounds(253, 225, 119, 23);
      getContentPane().add(btnNewButton_3);
      
    
    
      
      //뒤로가기 버튼
      JButton btnNewButton = new JButton("Back");
      btnNewButton.setBackground(new Color(240, 240, 240)); // 프레임 색을 버튼에 적용(투명해진 척~)
      btnNewButton.setFocusPainted(false); // 선택되었을 때 생기는 테두리 사용안함
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
            new MainFrame2();
            dispose();
         }
      });
      btnNewButton.setBounds(329, 0, 81, 23);
      getContentPane().add(btnNewButton);
      
      
      
      
      //회원 탈퇴 버튼
      JButton btnNewButton_1 = new JButton("회원탈퇴");
      btnNewButton_1.setFocusPainted(false);
      btnNewButton_1.setBounds(31, 225, 119, 23);
      getContentPane().add(btnNewButton_1);
      
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // 회원 탈퇴 버튼
            new UnRegisterFrame();
            dispose();
            
         }
      });
      
      
      //화면 설정
      this.setSize(426, 309); // 창 사이즈 이거 없으면 창 뜰 때 걍 소멸수준임;;;
      this.setLocationRelativeTo(null); //화면 중앙에 오게 해줌
      
      this.setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}