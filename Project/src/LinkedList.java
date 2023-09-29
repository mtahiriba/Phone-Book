import java.io.FileWriter;
import java.io.IOException;

public class LinkedList {
	Node head;
	//Node tail;
	int size;
	
	public void addFromFirst(String name, String phone, String relation)
	{
		Node newest = new Node(name, phone, relation);
		if(this.isEmpty())
		{
			this.head = newest;
			//this.tail = newest;
			size++;
		}
		else
		{
			newest.next = head;
			head.prev = newest;
			head = newest;
			size++;
		}
	}
	
	Node lastNode(Node node)
    {
        while(node.next!=null) 
            node = node.next; 
        return node; 
    }
	
	public void add(String name, String phone, String relation)
	{
		Node newest = new Node(name, phone, relation);
		if(this.isEmpty())
		{
			this.head = newest;
			//this.tail = newest;
			size++;
		}
		else
		{
			Node tail = this.lastNode(head);
			tail.next = newest;
			newest.prev = tail;
			size++;
		}
	}
	
	public boolean isEmpty()
	{
		return((head == null));
	}
	
	public LinkedList SearchByName(String name)
	{
		LinkedList list = new LinkedList();
		Node temp = head;
		while(temp!= null)
		{
			if(temp.name.equalsIgnoreCase(name))
			{
				list.add(temp.name, temp.phone, temp.relation);
			}
			temp = temp.next;
		}
		return list;
	}

	public Node SearchByNumber(String phone)
	{
		Node temp = head;
		while(temp!= null)
		{
			if(temp.phone.equalsIgnoreCase(phone))
			{
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}
	
	public LinkedList SearchByRelation(String relation)
	{
		LinkedList list = new LinkedList();
		Node temp = head;
		while(temp!= null)
		{
			if(temp.relation.equalsIgnoreCase(relation))
			{
				list.add(temp.name, temp.phone, temp.relation);
			}
			temp = temp.next;
		}
		return list;
	}
	
	public Node delete(String number)
	{
		Node node = this.SearchByNumber(number);
		Node tail = this.lastNode(head);
		
		if(this.isEmpty() || node == null)
		{
			return node;
		}
		else if(this.head == node)
		{
			head = head.next;
			size--;
		}
		else if(tail == node)
		{
			tail = tail.prev;
			tail.next = null;
			size--;
		}
		else
		{
			Node pre = node.prev;
			pre.next = node.next;
			size--;
		}
		return node;
	}
	
	
	public Node removeFromFirst()
	{
		Node node = null;
		if(!this.isEmpty())
		{
			if(head.next == null)
			{
				node = head;
				head = null;
			}
			else
			{
				node = head;
				head = head.next;
				head.prev = null;
			}
		}
		return node;
	}

	Node partition(Node l,Node h) 
    { 
       // set pivot as h element 
        int x = h.name.charAt(0);
        if(x>=97)
        	x -= 32;
        
        
        // similar to i = l-1 for array implementation 
        Node i = l.prev; 
          
        // Similar to "for (int j = l; j <= h- 1; j++)" 
        for(Node j=l; j!=h; j=j.next) 
        { 
        	int dum = (int)(j.name.charAt(0));
        	if(dum>=97)
        		dum-=32;
        	
            if(dum <= x) 
            { 
                // Similar to i++ for array 
                i = (i==null) ? l : i.next; 
                String temp = i.name;
                String temp2 = i.phone;
                String temp3 = i.relation;
                
                i.name = j.name;
                i.phone = j.phone;
                i.relation = j.relation;
                
                j.name = temp;
                j.phone = temp2;
                j.relation = temp3;
            } 
        }
        
        i = (i==null) ? l : i.next;  // Similar to i++ 
        String temp = i.name;
        String temp2 = i.phone;
        String temp3 = i.relation;
        
        i.name = h.name;
        i.phone = h.phone;
        i.relation = h.relation;
        
        h.name = temp;
        h.phone = temp2;
        h.relation = temp3;
        return i;
    }
	
	void _quickSort(Node l,Node h) 
    { 
        if(h!=null && l!=h && l!=h.next){ 
            Node temp = partition(l,h); 
            _quickSort(l,temp.prev); 
            _quickSort(temp.next,h); 
        } 
    }
	
	public void quickSort(Node node) 
    { 
        // Find last node 
        Node head = lastNode(node); 
          
        // Call the recursive QuickSort 
        _quickSort(node,head); 
    }
	
	
	public void display()
	{
		Node temp = head;
		while(temp!=null)
		{
			System.out.print(temp.name + "\t" + temp.phone + "\t" + temp.relation + "\n");
			temp = temp.next;
		}
	}
	
	public void makeEmpty()
	{
		head = null;
		size = 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList a =  new LinkedList();
		a.add("Muhammad", "03069430816", "brother");
		a.add("Tahir\t", "03456598753", "Uncle");
		a.add("abdul\t", "03069530816", "brother");
		a.add("Samad\t", "03065430816", "brother");
		a.add("Amjad\t", "03456596753", "Uncle");
		a.add("Zahid\t", "03067430816", "brother");
		
		System.out.println("Linked List before sorting "); 
		a.display(); 
        System.out.println("\nLinked List after sorting"); 
        a.quickSort(a.head); 
        a.display();
		
		
		
		System.out.println("\n\n");
//		LinkedList search =  new LinkedList();
//		search = a.SearchByName("muhammad");
//		search.display();
//		System.out.println("\n\n");
//		
//		Node number = a.SearchByNumber("03069430816");
//		System.out.println(number.name+"\t"+number.phone+"\t"+number.relation);
//		System.out.println("\n\n");
//		
//		search = a.SearchByRelation("uncle");
//		search.display();
//		System.out.println("\n\n");
//		
//		a.delete("03069430816");
//		a.display();
//		System.out.println("\n\n");
//		
//		a.delete("03067430816");
//		a.display();
//		System.out.println("\n\n");
//		
//		a.delete("03069530816");
//		a.display();
//		System.out.println("\n\n");
		
	}

}


class Node
{
	Node next;
	Node prev;
	String name;
	String phone;
	String relation;
	
	public Node(String name, String phone, String relation)
	{
		this.name = name;
		this.phone = phone;
		this.relation = relation;
		this.next = null;
	}
}