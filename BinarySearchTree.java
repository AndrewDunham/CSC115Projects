//Andrew Dunham
//V00908230
import java.util.*;

//
// An implementation of a binary search tree.
//
// This tree stores both keys and values associated with those keys.
//
// More information about binary search trees can be found here:
//
// http://en.wikipedia.org/wiki/Binary_search_tree
//
// Note: Wikipedia is using a different definition of
//       depth and height than we are using.  Be sure
//       to read the comments in this file for the
//	 	 height function.
//
class BinarySearchTree <K extends Comparable<K>, V>  {

	public static final int BST_PREORDER  = 1;
	public static final int BST_POSTORDER = 2;
	public static final int BST_INORDER   = 3;

	// These are package friendly for the TreeView class
	BSTNode<K,V>	root;
	int		count;

	int		findLoops;
	int		insertLoops;

	public BinarySearchTree () {
		root = null;
		count = 0;
		resetFindLoops();
		resetInsertLoops();
	}

	public int getFindLoopCount() {
		return findLoops;
	}

	public int getInsertLoopCount() {
		return insertLoops;
	}

	public void resetFindLoops() {
		findLoops = 0;
	}
	public void resetInsertLoops() {
		insertLoops = 0;
	}

	//
	// Purpose:
	//
	// Insert a new Key:Value Entry into the tree.  If the Key
	// already exists in the tree, update the value stored at
	// that node with the new value.
	//
	// Pre-Conditions:
	// 	the tree is a valid binary search tree
	//
	public void insert (K k, V v) {
		BSTNode<K, V> tempCurrent = root;//create a pointer to the root node, this node will update to the current node.
		BSTNode<K, V> dataNode = new BSTNode <K, V> (k, v);//this node has the data in it.

		if (root == null){//If there is no node at the root then set root = the dataNode
			root = dataNode;
			count ++;
		}

		while(tempCurrent != null){

			insertLoops ++;

			if(k.compareTo(tempCurrent.key)==0){
				tempCurrent.value = v;
				return;
			}
			if(0 > k.compareTo(tempCurrent.key)){//Set to left
				if(tempCurrent.left != null){
					tempCurrent = tempCurrent.left;
				}
				else{
					//if the tempCurrent.left == null then then set this position to be the dataNode and increment the counter
					tempCurrent.left = dataNode;
					count ++;
					break;
				}
			}
			if(0 < k.compareTo(tempCurrent.key)){
				if(tempCurrent.right != null){
					tempCurrent = tempCurrent.right;
				}
				else{
					//if the tempcurent.right == null then then set this position to be the dataNode and increment the counter
					tempCurrent.right = dataNode;
					count ++;
					break;
				}
			}	
		}
	}

	//
	// Purpose:
	//
	// Return the value stored at key.  Throw a KeyNotFoundException
	// if the key isn't in the tree.
	//
	// Pre-conditions:
	//	the tree is a valid binary search tree
	//
	// Returns:
	//	the value stored at key
	//
	// Throws:
	//	KeyNotFoundException if key isn't in the tree
	//
	public V find (K key) throws KeyNotFoundException {
		BSTNode<K,V> temp = root;

		if(count == 0){
			throw new KeyNotFoundException();
		}
		while(temp != null){
			findLoops ++;

			if(key.compareTo(temp.key)==0){
				return temp.value;
			}
			else{
				if(key.compareTo(temp.key) > 0){
					temp = temp.right;
				}
				else{
					temp = temp.left;
				}
			}
		}
		throw new KeyNotFoundException();
	}

	//
	// Purpose:
	//
	// Return the number of nodes in the tree.
	//
	// Returns:
	//	the number of nodes in the tree.
	public int size() {
		System.out.println(count);

		return count;
	}

	//
	// Purpose:
	//	Remove all nodes from the tree.
	//
	public void clear() {
		count = 0;
		root = null;
	}

	//
	// Purpose:
	//
	// Return the height of the tree.  We define height
	// as being the number of nodes on the path from the root
	// to the deepest node.
	//
	// This means that a tree with one node has height 1.
	//
	// Examples:
	//	See the assignment PDF and the test program for
	//	examples of height.
	//
	public int height() {
		//if the height is 0 return 0
		if (count == 0){
			return 0;
		}//Else go through the new function to find the heigh recusively.
		return heightFinder(root);
	}

	public int heightFinder(BSTNode<K, V> recurNode){
		if(recurNode == null){//Has reached the end
			return 0;
		}
		return (1 + (Math.max(heightFinder(recurNode.right), heightFinder(recurNode.left))));
	}

	//
	// Purpose:
	//
	// Return a list of all the key/value Entrys stored in the tree
	// The list will be constructed by performing a level-order
	// traversal of the tree.
	//
	// Level order is most commonly implemented using a queue of nodes.
	//
	//  From wikipedia (they call it breadth-first), the algorithm for level order is:
	//
	//	levelorder()
	//		q = empty queue
	//		q.enqueue(root)
	//		while not q.empty do
	//			node := q.dequeue()
	//			visit(node)
	//			if node.left != null then
	//			      q.enqueue(node.left)
	//			if node.right != null then
	//			      q.enqueue(node.right)
	//
	// Note that we will use the Java LinkedList as a Queue by using
	// only the removeFirst() and addLast() methods.
	//
	public List<Entry<K,V>> entryList() {
		List<Entry<K, V>> 		l = new LinkedList<Entry<K,V> >();
		Queue<BSTNode<K,V>> queue = new LinkedList<>();
		queue.add(root);
		while(queue.isEmpty() != true){
			BSTNode<K,V> temporaryNode  = queue.poll();
			l.add(new Entry<>(temporaryNode.key,temporaryNode.value));
			if(temporaryNode.left != null){
				queue.add(temporaryNode.left);
			}
			if(temporaryNode.right != null){
				queue.add(temporaryNode.right);
			}
		}
		return l;
	}

	//
	// Purpose:
	//
	// Return a list of all the key/value Entrys stored in the tree
	// The list will be constructed by performing a traversal 
	// specified by the parameter which.
	//
	// If which is:
	//	BST_PREORDER	perform a pre-order traversal
	//	BST_POSTORDER	perform a post-order traversal
	//	BST_INORDER	perform an in-order traversal
	//
	public List<Entry<K,V> > entryList (int which) {
		List<Entry<K,V> > l = new LinkedList<Entry<K,V> >();
		if(which == BST_PREORDER){
			doPreOrder(root,l);
		}
		else if(which == BST_POSTORDER){
			doPostOrder(root,l);
		}
		else{
			doInOrder(root,l);
		}
		return l;
	}


	private void doInOrder (BSTNode<K,V> n, List <Entry<K,V> > l){
		if(n == null){
			return;
		}
		doInOrder(n.left, l);
		l.add(new Entry<K,V>(n.key,n.value));
		doInOrder(n.right,l);
	}
	private void doPreOrder (BSTNode<K,V> n, List <Entry<K,V> > l){
		if(n == null){
			return;
		}
		l.add(new Entry<K,V>(n.key,n.value));
		doPreOrder(n.left, l);
		doPreOrder(n.right, l);
	}
	private void doPostOrder (BSTNode<K,V> n, List <Entry<K,V> > l){
		if(n == null){
			return;
		}
		doPostOrder(n.left, l);
		doPostOrder(n.right, l);
		l.add(new Entry<K,V>(n.key,n.value));
	}
	//private int doHeight (BSTNode<K,V> t){

	//}
}
