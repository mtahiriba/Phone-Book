
public class Stack {

	LinkedList list;
	
	public Stack()
	{
		list = new LinkedList();
	}
	
	public void push(String name, String phone, String relation)
	{
		list.addFromFirst(name, phone, relation);
	}
	
	public Node pop()
	{
		Node node = list.removeFromFirst();
		return node;
	}
	
	public void display()
	{
		list.display();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Stack a = new Stack();
		a.push("Muhammad Tahir", "03069430816", "brother");
		a.push("amjad", "03069430816", "brother");
		a.display();
	}

}
