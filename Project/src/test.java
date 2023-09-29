import java.io.FileWriter;
import java.io.IOException;

public class test {

	LinkedList list = new LinkedList();
	
	public void addContact(String name, String Phone, String Relation, String filename)
	{
			list.add(name, Phone, Relation);
	}
	
	public void display()
	{
		list.display();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test a = new test();
		a.addContact("Muhammad", "tahir", "brother", "file.txt");
		a.display();
	}

}
