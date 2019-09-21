import java.util.*;


/*
 * Node class
 */
class Node {
	
	// **** ****
	public int 		val;
	public Node 	left;
	public Node 	right;
	
	// **** constructor ****
	public Node(int val) {
		this.val	= val;
		this.left 	= null;
		this.right 	= null;
	}
}


/*
 * BST class
 */
public class BST {

	// **** ****
	Node	root;
	
	// **** BST constructor ****
	public BST() {
		this.root = null;
	}

	
	// **** insert node into BST ****
	public void insert(Node root, int val) {
		
		// **** add this node as the root of the BST ****
		if (root == null) {
			root = new Node(val);
			this.root = root;
			return;
		}
		
		// **** deal with the left child ****
		if (val <= root.val) {
			if (root.left == null) {
				root.left = new Node(val);
				return;
			} else {
				insert(root.left, val);
			}
		}
		
		// **** deal with the right child ****
		else {
			if (root.right == null) {
				root.right = new Node(val);
				return;
			}
			else {
				insert(root.right, val);
			}
		}
	}

	
	// **** find in order predecessor (max value) ****
	public Node inOrderPredecessor(Node root) {
		if (root.right == null)
			return root;
		else return inOrderPredecessor(root.right);
	}

	
	// **** find in order successor (min value) ****
	public Node inOrderSuccessor(Node root) {
		if (root.left == null)
			return root;
		else
			return inOrderSuccessor(root.left);
	}
	
	
	// **** search for node and delete it from the BST ****
	public Node delete(Node root, int val) {
	
		// **** check for a null root ****
		if (root == null) {
			return root;
		}
		
		// **** search for the node on the left side of the BST ****
		if (val < root.val) {
			root.left = delete(root.left, val);
		}
		
		// **** search for the node on the right side of the BST ****
		else if (val > root.val) {
			root.right = delete(root.right, val);
		}
		
		// **** found node to delete ****
		else {
						
			// **** node to delete has both children ****
			if ((root.left != null) && (root.right != null)) {
				
				// **** save the root (being deleted) ****
				Node temp = root;

				
//				// **** find the in order predecessor (max val on left tree) (A) ****
//				Node node = inOrderPredecessor(temp.left);			
				
				// **** find the in order successor (min val on right tree) (B) ****
				Node node = inOrderSuccessor(temp.right);

				
				// **** replace the value of the root with one from the node ****
				root.val = node.val;
								
				
//				// **** delete the node (with A) ****
//				delete(root.left, node.val);
					
				// **** delete the node (with B) ****
				root.right = delete(root.right, root.val);
			}

			// **** node to delete only has a right child ****
			else if (root.left == null) {
				return root = root.right;
			}

			// **** node to delete only has left child ****
			else if (root.right == null) {
				return root = root.left;
			}
		}
		
		// **** return root node ****
		return root;
	}
		
		
	// **** find node in BST ****
	public boolean find(Node root, int v) {

		// **** ****
		boolean found = false;
		
		// **** ****
		if (root != null) {
						
			// **** check the value of the root ****
			if (v == root.val)
				return true;
			
			// **** check left child (if needed) ****
			if (v < root.val)
				found = find(root.left, v);
			
			// **** check right child ****
			else
				found = find(root.right, v);
		} else
			return false;
		
		// **** ****
		return found;
	}
		
	
	// **** ****
	public void inOrderBST(Node root) {
		System.out.print("inOrderBST <<< ");
		inOrder(root);
		System.out.println();
	}
	
	
	// **** traverse BST in order ****
	private void inOrder(Node node) {
		if (node != null) {
			inOrder(node.left);
//			System.out.println("inOrder <<< val: " + node.val);
			System.out.print(node.val + " ");
			inOrder(node.right);
		}
	}
	

	// **** traverse BST in pre order ****
	public void preOrder(Node node) {
		if (node != null) {
			System.out.println("preOrder <<< val: " + node.val);
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	
	// **** traverse BST in post order ****
	public void postOrder(Node node) {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.println("postOrder <<< val: node.val");
		}
	}

	
	/*
	 * Test scaffolding.
	 */
	public static void main(String[] args) throws Exception {
		
		// **** value to insert or delete from BST ****
		int val = 0;
		
		// **** ****
		boolean found;
		
		// **** create the BST ****
		BST tree = new BST();
		
		// **** open scanner ****
		Scanner sc = new Scanner(System.in);
		
		// **** get number of operations ****
		int n = sc.nextInt();
		
		// **** flush rest of line ****
		sc.nextLine();
		
		// **** loop performing operations in the BST ****
		for ( ; n > 0; n--) {
			
			// **** read the next line from the input ****
			String line = sc.nextLine();
			
			// **** split the line ****
			String s[] = line.split(" ");
			
			// **** extract the val ****
			val = Integer.parseInt(s[1]);
			
			// **** perform this operation in the BST ****
			switch (s[0]) {

			// **** insert value into BST ****
			case "i":
				
				// ???? ????
				System.out.println("main <<< i " + val);

				// **** insert value into BST ****
				tree.insert(tree.root, val);
				
				// **** traverse the BST in order ****
				tree.inOrderBST(tree.root);
				
				// **** find this value in the BST ****
				found = tree.find(tree.root, val);
				if (found == false)
					throw new Exception("inserted value: " + val + " was NOT found");
				break;

			// **** delete value from BST ****
			case "d":
				
				// ???? ????
				System.out.println("main <<< d " + val);
				
				// **** delete value from BST ****
				tree.root = tree.delete(tree.root, val);

				// **** traverse the BST in order ****
				tree.inOrderBST(tree.root);

				// **** attempt to find this value in the BST ****
				found = tree.find(tree.root, val);
				if (found == true)
					throw new Exception("deleted value: " + val + " was found");
				break;
				
			// **** unexpected operation ****
			default:
				throw new Exception("invalid operation s[0] ==>" + s[0] + "<==");		
			}
		}

		// **** close scanner ****
		sc.close();
	}
}


