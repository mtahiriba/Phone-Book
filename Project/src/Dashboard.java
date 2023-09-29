import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Button;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField phone;
	private JTextField search;
	private JTextField relation;
	static LinkedList list;
	Stack rDelete;
	Stack rSearch;
	private static JTable table;
	static String editname;
	static String editphone;
	static String editrelation;
	
	/**
	 * Create the frame.
	 */
	public Dashboard() {
		rDelete = new Stack();
		list = new LinkedList();
		rSearch = new Stack();
		
		setBackground(new Color(51, 153, 204));
		setTitle("PhoneBook");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(70, 130, 180));
		contentPane.setToolTipText("PhoneBook");
		contentPane.setForeground(Color.BLUE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		System.out.println(login.data[4]);
		makeLinkedList(login.data[4]);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(166, 246, 534, 188);
		contentPane.add(scrollPane);
	
		table = new JTable();
		
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {
					"FULL NAME", "CONATCT", "RELATION"
				}
			));
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(179);
		table.getColumnModel().getColumn(2).setPreferredWidth(190);
		scrollPane.setViewportView(table);
		
		JButton recentdeletebuddon = new JButton("Recent Delete");
		recentdeletebuddon.setBackground(Color.WHITE);
		recentdeletebuddon.setFont(new Font("Arial Black", Font.PLAIN, 11));
		recentdeletebuddon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addrecord(rDelete.list);
			}
		});
		recentdeletebuddon.setBounds(10, 350, 125, 23);
		contentPane.add(recentdeletebuddon);
		
		JButton editbutton = new JButton("Edit");
		editbutton.setBackground(Color.WHITE);
		editbutton.setFont(new Font("Arial Black", Font.PLAIN, 11));
		editbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				if(selectedRowIndex>=0)
				{
					editname = model.getValueAt(selectedRowIndex, 0).toString();
					editphone = model.getValueAt(selectedRowIndex, 1).toString();
					editrelation = model.getValueAt(selectedRowIndex, 2).toString();
					try {
						EditContact frame = new EditContact();
						frame.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(contentPane, "Data NOT Selected! Please Select Data...", "MESSAGE", 2);
				}
			}
		});
		editbutton.setBounds(10, 214, 125, 23);
		contentPane.add(editbutton);
		
		JButton recentsearchbutton = new JButton("Recent Search");
		recentsearchbutton.setBackground(Color.WHITE);
		recentsearchbutton.setFont(new Font("Arial Black", Font.PLAIN, 11));
		recentsearchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addrecord(rSearch.list);
			}
		});
		recentsearchbutton.setBounds(10, 316, 125, 23);
		contentPane.add(recentsearchbutton);
		
		JButton deletebutton = new JButton("Delete");
		deletebutton.setBackground(Color.WHITE);
		deletebutton.setFont(new Font("Arial Black", Font.PLAIN, 11));
		deletebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				if(selectedRowIndex>=0)
				{
					Node node = delete(model.getValueAt(selectedRowIndex, 1).toString());
					rDelete.push(node.name, node.phone, node.relation);
				}
				else
				{
					JOptionPane.showMessageDialog(contentPane, "Please Select Data...", "Data NOT Selected!", 2);
				}
						
			}
		});
		deletebutton.setBounds(10, 248, 125, 23);
		contentPane.add(deletebutton);
		
		JButton ascendingsortbuttton = new JButton("Sort A-Z");
		ascendingsortbuttton.setBackground(Color.WHITE);
		ascendingsortbuttton.setFont(new Font("Arial Black", Font.PLAIN, 11));
		ascendingsortbuttton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.quickSort(list.head);
				addrecord(list);
			}
		});
		ascendingsortbuttton.setBounds(10, 282, 125, 23);
		contentPane.add(ascendingsortbuttton);
		
		JButton logoutbutton = new JButton("LOGOUT");
		logoutbutton.setBackground(Color.WHITE);
		logoutbutton.setFont(new Font("Arial Black", Font.PLAIN, 11));
		logoutbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});
		logoutbutton.setBounds(10, 384, 125, 23);
		contentPane.add(logoutbutton);
		
		firstname = new JTextField();
		firstname.setBounds(336, 73, 112, 20);
		contentPane.add(firstname);
		firstname.setColumns(10);
		
		lastname = new JTextField();
		lastname.setColumns(10);
		lastname.setBounds(336, 107, 112, 20);
		contentPane.add(lastname);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(336, 141, 112, 20);
		contentPane.add(phone);
		
		JLabel lblNewLabel = new JLabel("First Name : ");
		lblNewLabel.setForeground(new Color(248, 248, 255));
		lblNewLabel.setBackground(new Color(240, 255, 255));
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 14));
		lblNewLabel.setBounds(203, 75, 112, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("PhoneBook");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Agency FB", Font.BOLD, 24));
		lblNewLabel_3.setBounds(0, 11, 734, 23);
		contentPane.add(lblNewLabel_3);
		
		JTextArea errorfield = new JTextArea();
		errorfield.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
		errorfield.setForeground(new Color(255, 255, 255));
		errorfield.setBackground(new Color(70, 130, 180));
		errorfield.setBounds(467, 178, 231, 53);
		contentPane.add(errorfield);
		
		Button savebutton = new Button("Save");
		savebutton.setFont(new Font("Cambria Math", Font.BOLD, 12));
		savebutton.setForeground(Color.DARK_GRAY);
		savebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = 1;
				String error = "";
				if(firstname.getText().equals(""))	{	error += "First Name"; if(index%2==1) {error += "\t";} else {error += "\n";}	index++;}
				if(lastname.getText().equals(""))	{	error += "Last Name"; if(index%2==1) {error += "\t";} else {error += "\n";}	index++;	}
				if(relation.getText().equals(""))	{	error += "Relation"; if(index%2==1) {error += "\t";} else {error += "\n";}	index++;	}
				if(phone.getText().equals(""))		{	error += "Phone Number"; if(index%2==1) {error += "\t";} else {error += "\n";}	index++;}
				if(error.equals(""))
				{	
					if(!checkName(firstname.getText()))	{	error += "First Name"; if(index%2==1) {error += "\t";} else {error += "\n";}	index++;}
					if(!checkName(lastname.getText()))	{	error += "Last Name"; if(index%2==1) {error += "\t";} else {error += "\n";}	index++;	}
					if(!checkName(relation.getText()))	{	error += "Relation"; if(index%2==1) {error += "\t";} else {error += "\n";}	index++;	}
					if(!checkPhone(phone.getText()))	{	error += "Phone Number"; if(index%2==1) {error += "\t";} else {error += "\n";}	index++;}
					
					if(error.equals(""))
					{
						addContact(firstname.getText() + " " + lastname.getText(), phone.getText(), relation.getText(), login.data[4]);
						firstname.setText("");
						lastname.setText("");
						relation.setText("");
						phone.setText("");
						makeLinkedList(login.data[4]);
						addrecord(list);
						JOptionPane.showMessageDialog(contentPane, "Data Added Successfully...", "Message", 1);
					}
					else
					{	errorfield.setText("Please Enter Correct the Following:\n" + error);	}
				}
				else
				{	errorfield.setText("Please Fill the following fields:\n" + error);	}
				
			}
		});
		savebutton.setBounds(389, 209, 59, 22);
		contentPane.add(savebutton);
		
		search = new JTextField();
		search.setBounds(560, 73, 138, 20);
		contentPane.add(search);
		search.setColumns(10);
		
		JComboBox catagory = new JComboBox();
		catagory.setBounds(560, 141, 138, 20);
		contentPane.add(catagory);
		
		catagory.addItem("Name");
		catagory.addItem("Phone");
		catagory.addItem("Relation");
		
		
		Button searchbutton = new Button("Search");
		searchbutton.setFont(new Font("Cambria Math", Font.BOLD, 14));
		searchbutton.setForeground(Color.DARK_GRAY);
		searchbutton.setBackground(new Color(204, 204, 204));
		searchbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(search.getText().equals("")))
				{
					makeLinkedList(login.data[4]);
					if(catagory.getSelectedItem().equals("Phone"))
					{
						Node newest = list.SearchByNumber(search.getText());
						if(newest != null)
						{
							list.makeEmpty();
							list.add(newest.name, newest.phone, newest.relation);
							addrecord(list);
							search.setText("");
							rSearch.push(newest.name, newest.phone, newest.relation);
						}
						else
						{
							JOptionPane.showMessageDialog(contentPane, "Record NOT Found...", "Alert", 1);
							search.setText("");
						}
						
					}
					else if(catagory.getSelectedItem().equals("Relation"))
					{
						list = list.SearchByRelation(search.getText());
						if(!list.isEmpty())
						{
							addrecord(list);
							search.setText("");
							Node temp = list.head;
							for(int i=0; i<list.size; i++)
							{
								rSearch.push(temp.name, temp.phone, temp.relation);
								temp = temp.next;
							}
						}
						else
						{
							JOptionPane.showMessageDialog(contentPane, "Record NOT Found...", "Alert", 1);
							search.setText("");
						}
					}
					else
					{
						list = list.SearchByName(search.getText());
						if(!list.isEmpty())
						{
							addrecord(list);
							search.setText("");
							Node temp = list.head;
							for(int i=0; i<list.size; i++)
							{
								rSearch.push(temp.name, temp.phone, temp.relation);
								temp = temp.next;
							}
						}
						else
						{
							JOptionPane.showMessageDialog(contentPane, "Record NOT Found...", "Alert", 1);
							search.setText("");
						}
						
					}	
				}
			}
		});
		searchbutton.setBounds(632, 105, 66, 22);
		contentPane.add(searchbutton);
		
		JLabel lblLastName = new JLabel("Last Name : ");
		lblLastName.setForeground(new Color(248, 248, 255));
		lblLastName.setFont(new Font("Serif", Font.BOLD, 14));
		lblLastName.setBounds(203, 111, 112, 14);
		contentPane.add(lblLastName);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number :");
		lblPhoneNumber.setForeground(new Color(248, 248, 255));
		lblPhoneNumber.setFont(new Font("Serif", Font.BOLD, 14));
		lblPhoneNumber.setBounds(203, 143, 112, 14);
		contentPane.add(lblPhoneNumber);
		
		JLabel lbrelation = new JLabel("Relation:");
		lbrelation.setForeground(new Color(248, 248, 255));
		lbrelation.setFont(new Font("Serif", Font.BOLD, 14));
		lbrelation.setBounds(203, 177, 112, 14);
		contentPane.add(lbrelation);
		
		relation = new JTextField();
		relation.setColumns(10);
		relation.setBounds(336, 176, 112, 20);
		contentPane.add(relation);
		
		JLabel lblFeatures = new JLabel("FEATURES");
		lblFeatures.setHorizontalAlignment(SwingConstants.CENTER);
		lblFeatures.setForeground(new Color(248, 248, 255));
		lblFeatures.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 15));
		lblFeatures.setBackground(new Color(240, 255, 255));
		lblFeatures.setBounds(10, 142, 125, 14);
		contentPane.add(lblFeatures);
		
		JLabel lbsearch = new JLabel("Search:");
		lbsearch.setForeground(new Color(248, 248, 255));
		lbsearch.setFont(new Font("Serif", Font.BOLD, 14));
		lbsearch.setBackground(new Color(240, 255, 255));
		lbsearch.setBounds(467, 74, 77, 14);
		contentPane.add(lbsearch);
		
		
		
		JLabel catagories = new JLabel("Catagories:");
		catagories.setForeground(new Color(248, 248, 255));
		catagories.setFont(new Font("Serif", Font.BOLD, 14));
		catagories.setBackground(new Color(240, 255, 255));
		catagories.setBounds(467, 139, 77, 20);
		contentPane.add(catagories);
		
		JButton btnViewAll = new JButton("View All");
		btnViewAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeLinkedList(login.data[4]);
				addrecord(list);
			}
		});
		btnViewAll.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnViewAll.setFont(new Font("Arial Black", Font.PLAIN, 11));
		btnViewAll.setBackground(Color.WHITE);
		btnViewAll.setBounds(10, 180, 125, 23);
		contentPane.add(btnViewAll);
			
	}
	
	public static String[][] makeArray(LinkedList list)
	{
		String[][] records = new String[list.size][3];
		Node temp = list.head;
		
		for(int i=0; i<list.size; i++)
		{
			records[i][0] = temp.name;
			records[i][1] = temp.phone;
			records[i][2] = temp.relation;
			temp = temp.next;
		}
		return records;
	}
	
	public Node delete(String number)
	{
		Node node = list.delete(number);
		addrecord(list);
		
		makeLinkedList(login.data[4]);
		list.delete(node.phone);
		filewriter();
		return node;
	}
	
	public static void filewriter()
	{
		try {
			FileWriter fw = new FileWriter("E:\\"+login.data[4]);
			Node temp = list.head;
			for(int i=0; i<list.size; i++)
			{
				fw.write(temp.name + ", " + temp.phone + ", " + temp.relation + "\n");
				temp = temp.next;
			}
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1);
		}
	}
	
	public static void makeLinkedList(String filename)
	{
		list.makeEmpty();
		
		File file = new File("E:\\"+filename);
		try {
			Scanner reader = new Scanner(file);
			while(reader.hasNextLine())
			{
				String line = reader.nextLine();
				String[] arr = line.split(", ");
				if(arr.length>1)
				{
					list.add(arr[0], arr[1], arr[2]);
				}
					
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addContact(String name, String Phone, String Relation, String filename)
	{
		try {
			FileWriter fw = new FileWriter("E:\\"+filename, true);
			fw.write(name + ", " + Phone + ", " + Relation + "\n");
			list.add(name, Phone, Relation);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
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
	
	public static void addrecord(LinkedList list)
	{
		String[][] records = makeArray(list);
		table.setModel(new DefaultTableModel(
			records,
			new String[] {
				"FULL NAME", "CONATCT", "RELATION"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(179);
		table.getColumnModel().getColumn(2).setPreferredWidth(190);
	}
	
	public static void Edit(String name, String phone, String relation)
	{
		
		Node node = list.SearchByNumber(Dashboard.editphone);
		node.name = name;
		node.phone = phone;
		node.relation = relation;
		addrecord(list);
		makeLinkedList(login.data[4]);
		node = list.SearchByNumber(Dashboard.editphone);
		node.name = name;
		node.phone = phone;
		node.relation = relation;
		filewriter();
	}
}