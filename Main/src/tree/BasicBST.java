package tree;

/**
 * Basic Binary Search Tree(Basic BST)
 * @author shichaodong
 *
 */
public class BasicBST {
	
	public static class BSTNode{
		private int val;
		private BSTNode left = null;
		private BSTNode right = null;
		
		public BSTNode(int value){val = value;}
		public int getVal(){return val;}
	}
	
	private BSTNode tree = null;
	
	
	public BasicBST(){}
	
	public void insert(int value){
		tree = insert(tree, value);
	}
	
	private BSTNode insert(BSTNode tree, int val){
		if(tree == null){
			return new BSTNode(val);
		}
		
		if(val < tree.val){
			tree.left = insert(tree.left, val);
		}else if(val > tree.val){
			tree.right = insert(tree.right, val);
		}
		
		return tree;
	}
	
	public void delete(int value){
		tree = delete1(tree, value);
	}
	
	public BSTNode delete1(BSTNode tree, int value){
		if(tree == null)
			return null;
		else{
			if(value < tree.val)
				tree.left = delete1(tree.left, value);
			else if(value > tree.val)
				tree.right = delete1(tree.right, value);
			else{
				if(tree.left != null && tree.right != null){
					BSTNode leftMost = tree.left;
					while(leftMost.right != null)
						leftMost = leftMost.right;
					tree.val = leftMost.val;
					tree.left = delete1(tree.left, leftMost.val);
				}else{
					tree = tree.left == null ? tree.right: tree.left;
				}
			}
			return tree;
		}
	}
	
	private BSTNode delete(BSTNode tree, int value){
		if(tree == null){
			return null;
		}else{
			if(value < tree.val)
				tree.left = delete(tree.left, value);
			else if(value > tree.val)
				tree.right =  delete(tree.right, value);
			else{
				// 2 possibilities.
				// (1) target node has no left child.
				if(tree.left == null)
					tree = tree.right;
				// (2) target node has left child.
				else{
					BSTNode leftParent = tree.left;
					BSTNode leftMost = tree.left;
					while(leftMost.right != null){
						leftParent = leftMost;
						leftMost = leftMost.right;
					}
					// (2-1) left child has no right child.
					if(leftParent == leftMost){
						leftParent.right = tree.right;
						tree = leftParent;
					}
					// (2-2) left child has right-most child.
					else{
						leftParent.right = leftMost.left;
						tree.val = leftMost.val;
					}
				}
			}
			return tree;
		}
	}
	
	public void inOrder(){
		inOrder(tree);
	}
	
	private void inOrder(BSTNode tree){
		if(tree != null){
			inOrder(tree.left);
			System.out.print(tree.val + " ");
			inOrder(tree.right);
		}
	}
	
	public int height(){
		return height(tree);
	}
	
	private int height(BSTNode tree){
		if(tree == null)
			return 0;
		else{
			int leftHeight = height(tree.left);
			int rightHeight = height(tree.right);
			return leftHeight > rightHeight ?leftHeight + 1: rightHeight + 1;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasicBST b = new BasicBST();
		b.insert(6);
		b.insert(2);
		b.insert(1);
		b.insert(4);
		b.insert(3);
		b.insert(5);
		b.insert(7);
		b.insert(8);
		b.inOrder();
		System.out.println("\nTree height:" + b.height());
		
		
		System.out.println("deleting 4:");
		b.delete(4);
		b.inOrder();
		System.out.println();
		
		System.out.println("deleting 3:");
		b.delete(3);
		b.inOrder();
		System.out.println();
		
		System.out.println("deleting 7:");
		b.delete(7);
		b.inOrder();
		System.out.println();
		
		System.out.println("deleting 5:");
		b.delete(5);
		b.inOrder();
		System.out.println();
		
		System.out.println("deleting 6:");
		b.delete(6);
		b.inOrder();
		System.out.println();
		
		System.out.println("deleting 1:");
		b.delete(1);
		b.inOrder();
		System.out.println();
		
		System.out.println("deleting 2:");
		b.delete(2);
		b.inOrder();
		System.out.println();
		
		System.out.println("deleting 8:");
		b.delete(8);
		b.inOrder();
		System.out.println("\n=========Above for testing delete of BST=======");
		BasicBST b1 = new BasicBST();
		b1.insert(1);
		b1.insert(2);
		b1.insert(3);
		b1.insert(4);
		b1.insert(5);
		b1.inOrder();
		System.out.println("\nTree height: " + b1.height());
	}

}
