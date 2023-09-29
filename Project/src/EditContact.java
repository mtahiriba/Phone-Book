import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Button;
import javax.swing.JTextArea;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditContact extends JFrame {

	private JPanel contentPane;
	private JTextField relation;
	private JTextField phone;
	private JTextField lastname;
	private JTextField firstname;
	static String editname;
	static String editphone;
	static String editrelation;
	
	
	/**
	 * Create the frame.
	 */
	public EditContact() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(70, 130, 180));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea errorfield = new JTextArea();
		errorfield.setForeground(new Color(255, 255, 255));
		errorfield.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
		errorfield.setBackground(new Color(70, 130, 180));
		errorfield.setBounds(291, 96, 133, 153);
		contentPane.add(errorfield);
		
		
		JLabel lblContact = new JLabel("CONTACT");
		lblContact.setFont(new Font("Agency FB", Font.BOLD, 26));
		lblContact.setForeground(new Color(255, 255, 255));
		lblContact.setHorizontalAlignment(SwingConstants.CENTER);
		lblContact.setBounds(0, 11, 434, 32);
		contentPane.add(lblContact);
		
		JLabel label = new JLabel("First Name : ");
		label.setForeground(new Color(248, 248, 255));
		label.setFont(new Font("Serif", Font.BOLD, 14));
		label.setBackground(new Color(240, 255, 255));
		label.setBounds(21, 67, 112, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Last Name : ");
		label_1.setForeground(new Color(248, 248, 255));
		label_1.setFont(new Font("Serif", Font.BOLD, 14));
		label_1.setBounds(21, 103, 112, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Phone Number :");
		label_2.setForeground(new Color(248, 248, 255));
		label_2.setFont(new Font("Serif", Font.BOLD, 14));
		label_2.setBounds(21, 135, 112, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Relation:");
		label_3.setForeground(new Color(248, 248, 255));
		label_3.setFont(new Font("Serif", Font.BOLD, 14));
		label_3.setBounds(21, 169, 112, 14);
		contentPane.add(label_3);
		
		relation = new JTextField();
		relation.setColumns(10);
		relation.setBounds(137, 168, 129, 20);
		contentPane.add(relation);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(137, 133, 129, 20);
		contentPane.add(phone);
		
		lastname = new JTextField();
		lastname.setColumns(10);
		lastname.setBounds(137, 99, 129, 20);
		contentPane.add(lastname);
		
		firstname = new JTextField();
		firstname.setColumns(10);
		firstname.setBounds(137, 65, 129, 20);
		contentPane.add(firstname);
		
		String[] str = split(Dashboard.editname);
		firstname.setText(str[0]);
		lastname.setText(str[1]);
		phone.setText(Dashboard.editphone);
		relation.setText(Dashboard.editrelation);
		
		
		Button savebutton = new Button("Save");
		savebutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = 1;
				String error = "";
				if(firstname.getText().equals(""))	{	error += "First Name\n";}
				if(lastname.getText().equals(""))	{	error += "Last Name\n";}
				if(relation.getText().equals(""))	{	error += "Relation\n";}
				if(phone.getText().equals(""))		{	error += "Phone Number\n";}
				if(error.equals(""))
				{	
					if(!checkName(firstname.getText()))	{	error += "First Name\n";}
					if(!checkName(lastname.getText()))	{	error += "Last Name\n";}
					if(!checkName(relation.getText()))	{	error += "Relation\n";}
					if(!checkPhone(phone.getText()))	{	error += "Phone Number\n";}
					
					if(error.equals(""))
					{
						editname = firstname.getText() + " " + lastname.getText();
						editphone = phone.getText();
						editrelation = relation.getText();
						editname = firstname.getText()+ " " + lastname.getText();
						editphone = phone.getText();
						editrelation = relation.getText();
						
						JOptionPane.showMessageDialog(contentPane, "CONTACT SAVE SUCCESSFULLY...", "MESSAGE", 1);
						Dashboard.Edit(editname, editphone, editrelation);
						setVisible(false);
					}
					else
					{	errorfield.setText("Please Enter the\nCorrect of Following:\n================\n" + error);	}
				}
				else
				{	errorfield.setText("Please Fill the\nfollowing fields:\n--------------------------\n" + error);	}
				
			}
		});
		savebutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		savebutton.setForeground(Color.DARK_GRAY);
		savebutton.setFont(new Font("Cambria Math", Font.BOLD, 12));
		savebutton.setBounds(207, 213, 59, 22);
		contentPane.add(savebutton);
		
		JTextArea txtrInformation = new JTextArea();
		txtrInformation.setText("    INFORMATION\r\n================");
		txtrInformation.setForeground(Color.WHITE);
		txtrInformation.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
		txtrInformation.setBackground(new Color(70, 130, 180));
		txtrInformation.setBounds(291, 54, 133, 42);
		contentPane.add(txtrInformation);
		
		
	}
	
	public boolean checkName(String name)
	{
		for(int i=0; i<name.length(); i++)
		{
			if(!((name.charAt(i) >= 'a' && name.charAt(i)<='z') || (name.charAt(i) >= 'A' && name.charAt(i)<='Z') || name.charAt(i) == ' '))
				return false;
		}
		return true;
	}
	
	public boolean checkPhone(String phone)
	{
		if(phone.length() == 11)
		{
			for(int i=0; i<phone.length(); i++)
			{
				if(!(phone.charAt(i)>=48 && phone.charAt(i)<=57))
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
		
		return true;
	}
	
	public String[] split(String name)
	{
		String[] str = name.split(" ");
		return str;
	}
}
