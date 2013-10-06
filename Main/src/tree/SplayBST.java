package tree;

public class SplayBST {
	
	public static class SplayBSTNode{
		private int val;
		private SplayBSTNode left;
		private SplayBSTNode right;
		
		public SplayBSTNode(){}
		public SplayBSTNode (int value){val = value;}
	}
	
	private SplayBSTNode tree;
	private SplayBSTNode nullNode = null;
	private SplayBSTNode newNode = null;
	private SplayBSTNode leftTreeMax = null;
	private SplayBSTNode rightTreeMin = null;
	
	public SplayBST(){
		nullNode = new SplayBSTNode();
		nullNode.left = nullNode.right = nullNode;
		tree = nullNode;
	}
	
	public void insert(int value){
		if(newNode == null){
			newNode = new SplayBSTNode();
			newNode.left = newNode.right = nullNode;
		}
			
		newNode.val = value;
		if(tree == nullNode){
			tree = newNode;
			
		}else{
			tree = splay(value, tree);
			if(value < tree.val){
				newNode.left = tree.left;
				newNode.right = tree;
				tree.left = nullNode;
				tree = newNode;
			}else if(value > tree.val){
				newNode.left = tree;
				newNode.right = tree.right;
				tree.right = nullNode;
				tree = newNode;
			}
		}
		newNode = null;		
	}
	
	// Top-down splay operation
	public SplayBSTNode splay(int value, SplayBSTNode tree){
		SplayBSTNode header = new SplayBSTNode();
		header.left = header.right = nullNode;
		leftTreeMax = rightTreeMin = header;
		nullNode.val = value;
		while(true){
			if(value < tree.val){
				if(value < tree.left.val){
					tree = rotateLL(value, tree);
				}
				
				if(tree.left == nullNode)
					break;
				
				rightTreeMin.left = tree;
				rightTreeMin = tree;
				tree = tree.left;
			}else if(value > tree.val){
				if(value > tree.right.val){
					tree = rotateRR(value, tree);
				}
				
				if(tree.right == nullNode)
					break;
				
				leftTreeMax.right = tree;
				leftTreeMax = tree;
				tree = tree.right;
			}else
				break;
		}// end while
		
		leftTreeMax.right = tree.left;
		rightTreeMin.left = tree.right;
		tree.left = header.right;
		tree.right = header.left;
		
		return tree;
	}
	// Right rotate
	private SplayBSTNode rotateLL(int value, SplayBSTNode tree){
		SplayBSTNode top = tree.left;
		tree.left = top.right;
		top.right = tree;
		return top;
	}
	
	// Left rotate
	private SplayBSTNode rotateRR(int value, SplayBSTNode tree){
		SplayBSTNode top = tree.right;
		tree.right = top.left;
		top.left = tree;
		return top;
	}
	
	public void inOrder(){
		System.out.println("=====in-order traverse=====");
		inOrder(tree);
		System.out.println();
	}
	
	private void inOrder(SplayBSTNode tree){
		if(tree != nullNode){
			inOrder(tree.left);
			System.out.print(tree.val + " ");
			inOrder(tree.right);
		}
	}
	
	public void postOrder(){
		System.out.println("=====post-order traverse=====");
		postOrder(tree);
		System.out.println();
	}
	
	private void postOrder(SplayBSTNode tree){
		if(tree != nullNode){
			postOrder(tree.left);
			postOrder(tree.right);
			System.out.print(tree.val + " ");
		}
	}
	
	public int height(){
		return height(tree);
	}
	
	private int height(SplayBSTNode tree){
		if(tree == nullNode)
			return 0;
		else{
			int leftHeight = height(tree.left);
			int rightHeight = height(tree.right);
			return leftHeight > rightHeight ?leftHeight + 1 :rightHeight + 1 ;
		}
	}
	
	public int count(){
		return count(tree);
	}
	
	private int count(SplayBSTNode tree){
		if(tree == nullNode)
			return 0;
		else{
			return count(tree.left) + count(tree.right) + 1;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SplayBST s = new SplayBST();
		s.insert(7);
		s.insert(6);
		s.insert(5);
		s.insert(11);
		s.insert(8); 
		s.inOrder();
		s.postOrder();
		System.out.println("Height: " + s.height());
		System.out.println("Count : " + s.count());
	}

}
