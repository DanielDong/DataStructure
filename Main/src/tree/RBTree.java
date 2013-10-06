package tree;

public class RBTree {
	
	public static class RBNode{
		private int val;
		private int leaf; // flag to indicate leaf or not. 1 for leaf, 0 for internal node
		private int color; // 0 for red, 1 for black
		private RBNode left;
		private RBNode right;
		private RBNode parent;
		
		public RBNode(){
			color = BLACK;
			leaf = LEAF;
		}
		public RBNode(int value){
			val = value;
			color = BLACK;
			leaf = LEAF;
		}
		
		public RBNode getGrandpa(){
			return parent.parent;
		}
		
		public RBNode getUncle(){
			if(parent == getGrandpa().left){
				if(getGrandpa().right.leaf == LEAF){
					return null;
				}else{
					return getGrandpa().right;
				}
			}else{
				if(getGrandpa().left.leaf == LEAF)
					return null;
				else
					return getGrandpa().left;
			}
		}
	}
	
	// In red-black tree, all data nodes are internal nodes.
	// Leaf nodes have no data and only exist to indicate leaf has been reached.
	private RBNode tree;
	private static final int RED = 0;
	private static final int BLACK = 1; 
	private static final int LEAF = 2;
	private static final int NOT_LEAF = 3;
	
	public RBTree(){tree = null;}
	
	public RBNode insert(RBNode node){
		if(tree == null){
			tree = node;
			node.parent = null;
			node.color = BLACK;
			
			RBNode leftLeaf = new RBNode();
			leftLeaf.parent = tree;
			tree.left = leftLeaf;
			
			RBNode rightLeaf = new RBNode();
			rightLeaf.parent = tree;
			tree.right = rightLeaf;
			
			return tree;
			
		}else{
			RBNode tmp = tree;
			while(tmp.leaf != LEAF){
				if(tmp.val > node.val){
					tmp = tmp.left;
				}else{
					tmp = tmp.right;
				}
			}
			
			tmp.val = node.val;
			tmp.leaf = NOT_LEAF;
			tmp.color = node.color;
			
			RBNode leftLeaf = new RBNode();
			leftLeaf.parent = tmp;
			tmp.left = leftLeaf;
			
			RBNode rightLeaf = new RBNode();
			rightLeaf.parent = tmp;
			tmp.right = rightLeaf;
			
			return tmp;
		}
	}
	
	public void adjustInsert(RBNode node){
		insertCase1(node);
	}
	
	// New inserted node is root node.
	private void insertCase1(RBNode node){
		if(node.parent == null){
			node.color = BLACK;
		}else{
			insertCase2(node);
		}
	}
	
	// If parent's color is black, nothing to do.
	private void insertCase2(RBNode node){
		if(node.parent.color == BLACK)
			return;
		else
			insertCase3(node);
	}
	
	// Both parent and uncle are red. Turn their colors into black and grandparent to red.
	private void insertCase3(RBNode node){
		if(node.getUncle() != null && node.getUncle().color == RED){
			node.getUncle().color = BLACK;
			node.parent.color = BLACK;
			node.getGrandpa().color = RED;
			insertCase1(node.getGrandpa());
		}else{
			insertCase4(node);
		}
	}
	
	// parent is red, uncle is black or null. 
	private void insertCase4(RBNode node){
		RBNode tmp = node;
		if(node == node.parent.left && node.parent == node.getGrandpa().right){
			tmp = rotateRR(node.parent);
		}else if(node == node.parent.right && node.parent == node.getGrandpa().left){
			tmp = rotateLL(node.parent);
		}
		insertCase5(tmp);
	}
	
	// parent is red, uncle is black or null. Do LL or RR rotation on grandpa node
	private void insertCase5(RBNode node){
		node.parent.color = BLACK;
		node.getGrandpa().color = RED;
		if(node == node.parent.left && node.parent == node.getGrandpa().left){
			rotateRR(node.getGrandpa());
		}else if(node == node.parent.right && node.parent == node.getGrandpa().right){
			rotateLL(node.getGrandpa());
		}
	}
	
	private RBNode rotateLL(RBNode node){
		RBNode top = node.right;
		RBNode tmp = top.left;
		if(node.parent != null){
			top.parent = node.parent;
			if(node == node.parent.left)
				node.parent.left = top;
			else
				node.parent.right = top;
		}else{
			top.parent= null;
			tree = top;
		}
		
		top.left = node;
		node.parent = top;
		node.right = tmp;
		tmp.parent = node;
		return top.left;
	}
	
	private RBNode rotateRR(RBNode node){
		RBNode top = node.left;
		RBNode tmp = top.right;
		if(node.parent != null){
			top.parent = node.parent;
			if(node == node.parent.left){
				node.parent.left = top;
			}else
				node.parent.right = top;
		}else{
			top.parent = null;
			tree = top;
		}
		
		top.right = node;
		node.parent = top;
		node.left = tmp;
		tmp.parent = node;
		return top.right;
	}
	// 0 for red, 1 for black
	public void inOrder(){
		System.out.println("========in-order========");
		inOrder(tree);
		System.out.println();
	}
	
	private void inOrder(RBNode tree){
		if(tree.leaf != LEAF){
			inOrder(tree.left);
			System.out.println("val: " + tree.val + " color: " + tree.color );
			inOrder(tree.right);
		}
	}
	// 0 for red, 1 for black
	public void postOrder(){
		System.out.println("========post-order========");
		postOrder(tree);
		System.out.println();
	}
	
	private void postOrder(RBNode tree){
		if(tree.leaf != LEAF){
			postOrder(tree.left);
			postOrder(tree.right);
			System.out.println("val: " + tree.val + " color: " + tree.color);
		}
	}
	/**
	 * @param args
	 */
	// 0 for red, 1 for black
	public static void main(String[] args) {
		RBTree rbt = new RBTree();
//		int[] intArr = {10, 2, 3, 40, 15, 0 , 8, 9, 99, 7, 5};
		int[] intArr = {4,3,2,1,8,7,5,0};
		for(int i = 0; i < intArr.length; i ++){
			RBNode node = new RBNode();
			node.color = RED;
			node.leaf = NOT_LEAF;
			node.val = intArr[i];
			rbt.adjustInsert(rbt.insert(node));
		}
		rbt.inOrder();
		rbt.postOrder();
	}

}
