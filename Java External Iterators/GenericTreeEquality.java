import java.util.Stack;
/* 
 * Implementation of External Iterator for inorder traversal of a generic binary search tree (BST), 
 * and use this iterator to define the equality of two BSTs.
 */
// Tree implements the binary search tree property

class Tree<T extends Comparable<T>> {
	protected T value;
	protected Tree<T> left;
	protected Tree<T> right;
	
	public Tree(T v) {
		value = v;
		left = null;
		right = null;
	}

	public void insert(T v) {
		if (value.compareTo(v) == 0)
			return;
		if (value.compareTo(v) > 0) {
			if (left == null)
				left = new Tree<T>(v);
			else
				left.insert(v);
		}
		else if (value.compareTo(v) < 0) {
			if (right == null)
				right = new Tree<T>(v);
			else
				right.insert(v);
		}	
	}
}
/*
 * external iterator for inorder traversal of a generic binary search tree (BST) 
 * 
 */
interface IIterator
{
	public boolean done();	//tests whether the iterator has any more values to yield
	public Object next();	//returns the next value if the iterator is not yet done.
}
interface IContainer
{
	public IIterator createIterator();
}

class Iter<T extends Comparable<T>> implements IIterator {
	// define here the external iterator operations, done and next
	private Stack<Tree<T>> stack = new Stack<Tree<T>>();
	private Tree<T> current;
	
	public Iter(Tree<T> root) {
		current = root;
	}
	
	@Override
	public boolean done() {		/*test whether it has more values to yield*/
		return (!stack.isEmpty() || current != null);
	}
	
	/* return the next element */
	@Override
	public Tree<T> next() {
		while(current != null) {	//to get the left most element
			stack.push(current);
			current = current.left;
		}
		current = stack.pop();
		Tree<T> next_node = current;
		current = current.right;
		return next_node;
	}	
}

public class GenericTreeEquality {
/*The equality test should repeatedly obtain one value from each tree, in alternating fashion, and compare these values for equality. 
 * It should return false if the values are not equal (mismatch). 
 * If there are no mismatches and the iterators are done at the same time, the equality test should return true.
*/
     static <T extends Comparable<T>> boolean equals(Tree<T> tree1, Tree<T> tree2) {
		
	// define here the equality test
    	 Iter<T> iterTree1 = new Iter<T>(tree1);
    	 Iter<T> iterTree2 = new Iter<T>(tree2);
    	 T valueTree1,valueTree2;
		 while(iterTree1.done() || iterTree2.done()) {
			 if(iterTree1.done() && iterTree2.done()) {		//check whether both the tree has values
    			 valueTree1 = iterTree1.next().value;
    			 valueTree2 = iterTree2.next().value;
    			 //System.out.println(valueTree1+ ":" + valueTree2);
    			 if(!valueTree1.equals(valueTree2))
    				return false;
			 }
			 else	//if only one tree has a value return false
				 return false;
		 }
//		 if(iterTree1.done()  && iterTree2.done()) {	//another method of doing it
//			 return false;
//		 }
		 return true;
	}
	
	public static void main(String[] args) {

		Tree<Double> tree1 = new Tree<Double>(6.2);
		tree1.insert(70.5);
		tree1.insert(80.2);	
		tree1.insert(90.6);
		tree1.insert(100.7);
		
		Tree<Double> tree2 = new Tree<Double>(6.2);
		tree2.insert(100.7);
		tree2.insert(90.6);	
		tree2.insert(80.2);
		tree2.insert(70.5);
		
		System.out.println(equals(tree1, tree2));
	}
}

    
 