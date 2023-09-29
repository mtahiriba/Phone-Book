import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistrationForm extends JFrame {

	private JPanel contentPane;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField email;
	private JTextField phone;
	private JPasswordField password;

	
	/**
	 * Create the frame.
	 */
	public RegistrationForm() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 313);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBackground(new Color(70, 130, 180));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel var1 = new JLabel("Full Name:");
		var1.setFont(new Font("Serif", Font.BOLD, 14));
		var1.setForeground(new Color(255, 255, 255));
		var1.setBackground(new Color(255, 255, 255));
		var1.setBounds(47, 78, 114, 14);
		contentPane.add(var1);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Serif", Font.BOLD, 14));
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setBounds(47, 131, 46, 14);
		contentPane.add(lblEmail);
		
		firstname = new JTextField();
		firstname.setToolTipText("FirstName");
		firstname.setBounds(147, 75, 159, 20);
		contentPane.add(firstname);
		firstname.setColumns(10);
		
		lastname = new JTextField();
		lastname.setToolTipText("Last Name");
		lastname.setBounds(331, 75, 159, 20);
		contentPane.add(lastname);
		lastname.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 11));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(147, 103, 159, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name");
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 11));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(331, 106, 159, 14);
		contentPane.add(lblNewLabel_1);
		
		email = new JTextField();
		email.setToolTipText("Email");
		email.setBounds(147, 131, 343, 20);
		contentPane.add(email);
		email.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Serif", Font.BOLD, 14));
		lblPhone.setForeground(new Color(255, 255, 255));
		lblPhone.setBounds(47, 194, 90, 14);
		contentPane.add(lblPhone);
		
		phone = new JTextField();
		phone.setToolTipText("Phone Number");
		phone.setBounds(147, 193, 343, 20);
		contentPane.add(phone);
		phone.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Serif", Font.BOLD, 14));
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setBounds(47, 165, 90, 14);
		contentPane.add(lblPassword);
		
		JButton register = new JButton("Register");
		register.setFont(new Font("Agency FB", Font.BOLD, 15));
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String error = "";
				if(firstname.getText().equals("") || !(checkName(firstname.getText())))	{	error += "Please Check the FirstName...\n";	}
				
				if(email.getText().equals("") || !(checkEmail(email.getText())))		{	error += "Please Check the Email...\n";	}
				else
				{
					if(!checkDuplicateEmail(email.getText()))
					{
						error = "EMAIL ALREADY IN USE...";
					}
				}
				
				if (lastname.getText().equals("") || !(checkName(lastname.getText()))) 	{	error += "Please Check the LastName.\n";	}
				
				if (phone.getText().equals("")) {	error += "Please Check the Phone Number.\n";	}
				
				if (password.getText().equals("")) 
				{	error += "Please enter password.\n";	}
				
				
				if (error.equals("")) 
				{
					register(firstname.getText(), lastname.getText(), email.getText(), password.getText(), phone.getText());
					firstname.setText("");
					lastname.setText("");
					password.setText("");
					phone.setText("");
					email.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(contentPane, error, "MESSAGE", 2);
				}
			}
		});
		register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		register.setBounds(399, 240, 89, 23);
		contentPane.add(register);
		
		password = new JPasswordField();
		password.setToolTipText("Password");
		password.setBounds(147, 162, 343, 20);
		contentPane.add(password);
		
		JLabel lblRegistrationForm = new JLabel("----- REGISTRATION FORM -----");
		lblRegistrationForm.setFont(new Font("Agency FB", Font.BOLD, 22));
		lblRegistrationForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrationForm.setForeground(new Color(255, 255, 255));
		lblRegistrationForm.setBounds(0, 11, 524, 43);
		contentPane.add(lblRegistrationForm);
	}
	
	
	public boolean checkDuplicateEmail(String email)
	{
		File file = new File("E:\\register.txt");
		try {
			Scanner reader = new Scanner(file);
			while(reader.hasNextLine())
			{
				String[] arr = reader.nextLine().split(", ");
				if(email.equalsIgnoreCase(arr[1]))
				{
					return false;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	
	public boolean checkEmail(String email)
	{
		for(int i=0; i<email.length(); i++)
		{
			if(email.charAt(i) == '@')
				return true;
		}
		return false;
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
	
	public void register(String firstname, String lastname, String email, String password, String phone)
	{
		try {
			FileWriter fw = new FileWriter("E:\\register.txt", true);
			fw.write(firstname+" "+lastname+", "+email+", "+password+", "+phone+", "+email+"contacts.txt\n");
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

	public static void main(System[] args)
	{
	    new RegistrationForm();
	    
	    
	}
}
