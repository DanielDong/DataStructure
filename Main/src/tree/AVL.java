package tree;

public class AVL {
	
	public static class AVLNode{
		private int val;
		private int height;
		public AVLNode left, right;
		
		public AVLNode(){}
		public AVLNode(int value){
			val = value;
		}
		
		public int getVal(){
			return val;
		}
		public void setVal(int value){
			val = value;
		}
	}
	
	private AVLNode tree;
	public AVL(){
		tree = null;
	}
	
	public void insert(int value){
		tree = insert(tree, value);
	}
	
	private AVLNode insert(AVLNode tree, int value){

		if(tree == null)
			tree = new AVLNode(value);
		else{
			if(value < tree.getVal()){
				tree.left = insert(tree.left, value);
				if(height(tree.left) - height(tree.right) >= 2){
					if(value < tree.left.getVal()){
						tree = rotateLL(tree);
					}else{
						tree = rotateLR(tree);
					}
				}
				
			}else if(value > tree.getVal()){
				tree.right = insert(tree.right, value);
				if(height(tree.right) - height(tree.left) >= 2){
					if(value > tree.right.getVal()){
						tree = rotateRR(tree);
					}else{
						tree = rotateRL(tree);
					}

				}
			}	
		}
		tree.height = Math.max(height(tree.left), height(tree.right)) + 1;
		return tree;
	}
	
	public void delete(int value){
		tree = delete(tree, value);
	}
	
	private AVLNode delete(AVLNode tree, int value){
		if(tree == null)
			return null;
		else{
			if(value < tree.getVal()){
				tree.left = delete(tree.left, value);
			}else if(value > tree.getVal()){
				tree.right = delete(tree.right, value);
			}else{
				if(tree.left != null && tree.right != null){
					AVLNode leftMost = tree.left;
					while(leftMost.right != null)
						leftMost = leftMost.right;
					
					tree.setVal(leftMost.getVal());
					tree.left = delete(tree.left, tree.getVal());
					
				}else{
					tree = tree.left == null ? tree.right: tree.left;
				}
			}
			if(tree != null)
				tree.height = Math.max(height(tree.left), height(tree.right)) + 1;
			tree = rotate(tree);
			return tree;
		}
	}
	// Rotate operation used in delete operation
	private AVLNode rotate(AVLNode tree){
		if(tree == null)
			return null;
		else{
			if(height(tree.left) - height(tree.right) >= 2){
				if(height(tree.left.left) > height(tree.left.right)){
					tree = rotateLL(tree);
				}else{
					tree = rotateLR(tree);
				}
			}else if(height(tree.right) - height(tree.left) >= 2){
				if(height(tree.right.right) > height(tree.right.left)){
					tree = rotateRR(tree);
				}else{
					tree = rotateRL(tree);
				}
			}
			return tree;
		}
	}
	
	private AVLNode rotateLL(AVLNode tree){
		AVLNode top = tree.left;
		tree.left = top.right;
		top.right = tree;
		
		tree.height = Math.max(height(tree.left), height(tree.right)) + 1;
		top.height = Math.max(height(top.left), height(top.right)) + 1;
		return top;
	}
	
	private AVLNode rotateRR(AVLNode tree){
		AVLNode top = tree.right;
		tree.right = top.left;
		top.left = tree;
		
		tree.height = Math.max(height(tree.left), height(tree.right)) + 1;
		top.height = Math.max(height(top.left), height(top.right)) + 1;
		
		return top;
	}
	
	private AVLNode rotateLR(AVLNode tree){
		tree.left = rotateRR(tree.left);
		return rotateLL(tree);
	}
	
	private AVLNode rotateRL(AVLNode tree){
		tree.right = rotateLL(tree.right);
		return rotateRR(tree);
	}
	
	public int height(){
		return height(tree);
	}
	private int height(AVLNode tree){
		return tree == null ? -1 : tree.height;
	}
	
	public void inOrder(){
		System.out.println("=====in-order=====");
		inOrder(tree);
		System.out.println();
	}
	
	private void inOrder(AVLNode tree){
		if(tree != null){
			inOrder(tree.left);
			System.out.print(tree.getVal() + " ");
			inOrder(tree.right);
		}
	}
	
	public void postOrder(){
		System.out.println("=====post-order=====");
		postOrder(tree);
		System.out.println();
	}
	
	private void postOrder(AVLNode tree){
		if(tree != null){
			postOrder(tree.left);
			postOrder(tree.right);
			System.out.print(tree.getVal() + " ");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AVL a = new AVL();
//		a.insert(1);
//		a.inOrder();
//		a.postOrder();
//		
//		a.insert(2);
//		a.inOrder();
//		a.postOrder();
//		
//		a.insert(3);
//		a.inOrder();
//		a.postOrder();
		
		a.insert(1);
		a.inOrder();
		a.postOrder();
		
		a.insert(2);
		a.inOrder();
		a.postOrder();
		
		a.insert(3);
		a.inOrder();
		a.postOrder();
		
		a.insert(4);
		a.inOrder();
		a.postOrder();
		
		a.insert(5);
		a.inOrder();
		a.postOrder();
		
		a.insert(6);
		a.inOrder();
		a.postOrder();
		
		a.insert(7);
		a.inOrder();
		a.postOrder();
		
		a.insert(8);
		a.inOrder();
		a.postOrder();
		System.out.println("=============start delete==========");
		a.delete(5);
		a.inOrder();
		a.postOrder();
		
		a.delete(4);
		a.inOrder();
		a.postOrder();
		
		a.delete(3);
		a.inOrder();
		a.postOrder();
		
		a.delete(1);
		a.inOrder();
		a.postOrder();
		
		a.delete(2);
		a.inOrder();
		a.postOrder();
		
		a.delete(7);
		a.inOrder();
		a.postOrder();
		
		a.delete(8);
		a.inOrder();
		a.postOrder();
		
		a.delete(6);
		a.inOrder();
		a.postOrder();
	}

}
