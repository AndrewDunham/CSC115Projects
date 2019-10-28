/*
 * Name: Andrew Dunham
 * Student Number:
 */

public class IntegerLinkedList implements IntegerList{
	private int count; 
	IntegerNode head;
	IntegerNode tail;

	public IntegerLinkedList(){
		count = 0;
		head = null;
		tail = null;
	}

	/*
	 * PURPOSE:
	 *   Add the element x to the front of the list.
	 *
	 * PRECONDITIONS:
	 *   None.
	 *
	 * Examples:
	 *
	 * If l is {1,2,3} and l.addFront(9) returns, then l is {9,1,2,3}.
	 * If l is {} and l.addFront(3) returns, then l is {3}.
	 */
	public void addFront (int x){
		IntegerNode n = new IntegerNode(x);
		if (count == 0){
			head = n;
			tail = n;
		}
		else{
			n.next = head;
			head.prev = n;
			head = n;
		}
		count += 1;
	}


	/*
	 * PURPOSE:
	 *   Add the element x to the back of the list.
	 *
	 * PRECONDITIONS:
	 *   None.
	 *
	 * Examples:
	 *
	 * If l is {1,2,3} and l.addBack(9) returns, then l is {1,2,3,9}.
	 * If l is {} and l.addBack(9) returns, then l is {9}.
	 */
	public void addBack (int x){
		IntegerNode n = new IntegerNode(x);
		if (count == 0){
			head = n;
			tail = n;
		}
		else{
			n.prev = tail;
			tail.next = n;
			tail = n;

		} 
		count += 1;
	}

	/*
	 * PURPOSE:
	 *   Add the element x at position pos in the list.
	 *
	 * Note:
	 *   In a list with 3 elements, the valid positions for addAt are
	 *   0, 1, 2, 3.
	 *
	 * PRECONDITIONS:
	 *   pos >= 0 and pos <= l.size()
	 *
	 * Examples:
	 *
	 * If l is {} and l.addAt(9,0) returns, then l is {9}.
	 * If l is {1} and l.addAt(9,0) returns, then l is {9,1}.
	 * If l is {1,2} and l.addAt(9,1) returns, then l is {1,9,2}
	 * If l is {1,2} and l.addAt(9,2) returns, then l is {1,2,9}
	 */
	public void addAt (int x, int pos){
		IntegerNode n = new IntegerNode(x);
		IntegerNode temp = head;
		if (count == 0){
			head = n;
			tail = n;
			count +=1;
		}
		else if(count ==1){
			IntegerNode p = new IntegerNode(x);
			if (pos == 0){
				addFront(x);
			}
			else{
				addBack(x);
			}
		}
		else{
			if(pos != 0 && pos != count){

				for(int i=0; i< pos; i++){
					temp = temp.next;
				}
				n.prev = temp.prev;
				n.next = temp;
				temp.next.prev = n;
				temp.prev.next = n;
				count += 1;
			}else if (pos == 0){
				addFront(x);
			}else{
				addBack(x);
			}		
		}
	}
	/*
	 * PURPOSE:
	 *	Return the number of elements in the list
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {7,13,22} l.size() returns 3
	 *	If l is {} l.size() returns 0
	 */
	public int size(){
		return count;
	}

	/*
	 * PURPOSE:
	 *   Return the element at position pos in the list.
	 *
	 * PRECONDITIONS:
	 *	pos >= 0 and pos < l.size()
	 *
	 * Examples:
	 *	If l is {67,12,13} then l.get(0) returns 67
	 *	If l is	{67,12,13} then l.get(2) returns 13
	 *	If l is {92} then the result of l.get(2) is undefined.
	 *
	 */
	public int  get (int pos){
		IntegerNode temp = head;
		for (int i = 0; i < pos; i++){
			temp = temp.next;
		}
		return temp.getValue();
	}

	/*
	 * PURPOSE:
	 *   Remove all elements from the list.  After calling this
	 *   method on a list l, l.size() will return 0
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {67,12,13} then after l.clear(), l is {}
	 *	If l is {} then after l.clear(), l is {}
	 *
	 */
	public void clear(){
		head = null;
		tail = null;
		count = 0;
	}

	/*
	 * PURPOSE:
	 *   Remove all instances of value from the list.
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {67,12,13,12} then after l.remove(12), l is {67,13}
	 *	If l is {1,2,3} then after l.remove(2), l is {1,3}
	 *	If l is {1,2,3} then after l.remove(99), l is {1,2,3}
	 */
	public void remove (int value){
		IntegerNode dudette = head;
		int counter;
		counter = 0;
		IntegerNode nothingDude = null;
		if(counter != 1){

			while (dudette != nothingDude){
				if(dudette.value == value){
					if(count == 1){
						count = 0;
						head = nothingDude;
						tail = nothingDude;
						break;
					}else{
						count --;
					}if(dudette.prev != nothingDude){
						dudette.prev.next = dudette.next;
						if(tail.equals(dudette)){
							tail = dudette.prev;
						}
					}if (dudette.next != nothingDude){
						dudette.next.prev = dudette.prev;
						if(head.equals(dudette)){
							head = dudette.next;
						}
					}
				}
				dudette = dudette.next;
			}
			
		}else{
			count = 0;
			head = nothingDude;
			tail = nothingDude;
		}
		/*	
		for (int i = 0; i< count; i++){
			if (temp.equals (value)){
				removeAt(i);
				remove(value);
			}
			temp = temp.next;
		}*/
	}

	/*
	 * PURPOSE:
	 *   Remove the element at position pos in the list.
	 *
	 * Note:
	 *   In a list with 3 elements, the valid positions for removeAt are
	 *   0, 1, 2.
	 *
	 * PRECONDITIONS:
	 *   pos >= 0 and pos < l.size()
	 *
	 * Examples:
	 *
	 * If l is {1} and l.removeAt(0) returns, then l is {}.
	 * If l is {1,2,3} and l.removeAt(1) returns, then l is {1,3}
	 * If l is {1,2,3} and l.removeAt(2) returns, then l is {1,2}
	 */
	public void removeAt (int pos){
		
		if (count == 1){
			head = null;
			tail = null;
			count = 0;
		}
		else if (count > 1){
			if (pos == 0){
				head = head.next;
				head.prev = null;
			}
			else{
				IntegerNode madude = head;
				for(int i = 0; i< pos; i++){
					madude = madude.next;
				}
				if (madude.next != null){
					madude.next.prev = madude.prev;
				}if (madude.prev != null){
					madude.prev.next = madude.next;
				}
			}
			count--;
		}
		
	}

	/*
	 * PURPOSE:
	 *	Return a string representation of the list
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {1,2,3,4} then l.toString() returns "{1,2,3,4}"
	 *	If l is {} then l.toString() returns "{}"
	 *
	 */
	public String toString()
	{
		IntegerNode temp = head;
	    String s = "{";
		for (int i=0;i<count;i++)
		{
			s+= temp.value;
			temp = temp.next;
			if (i != (count - 1))
			{
				s+= ",";
			}
		}
		s+= "}";
		return s;
	}
}
