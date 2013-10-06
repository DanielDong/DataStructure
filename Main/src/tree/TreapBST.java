package tree;

import java.util.Random;

/**
 * Treap = Tree + Heap
 * @author shichaodong
 *
 */
public class TreapBST {

	public static class TreapBSTNode{
		private int val;  // Key of the node
		private int prio; // Priority of the node.
		private TreapBSTNode left;
		private TreapBSTNode right;
		
		public TreapBSTNode(int value){
			val = value;
			prio = new Random(System.currentTimeMillis()).nextInt(50);
		}
	}
	
	private TreapBSTNode tree;
	
	private TreapBSTNode rotateLL(TreapBSTNode tree){
		TreapBSTNode top = tree.left;
		tree.left = top.right;
		top.right = tree;
		return top;
	}
	
	private TreapBSTNode rotateRR(TreapBSTNode tree){
		TreapBSTNode top = tree.right;
		tree.right = top.left;
		top.left = tree;
		return top;
	}
	
	public void insert(int value){
		tree = insert(tree, value);
	}
	
	private TreapBSTNode insert(TreapBSTNode tree, int value){
		if(tree == null)
			return new TreapBSTNode(value);
		else{
			if(value < tree.val){
				tree.left = insert(tree.left, value);
				if(tree.left.prio < tree.prio){
					tree = rotateLL(tree);
				}
			}else if(value > tree.val){
				tree.right = insert(tree.right, value);
				if(tree.right.prio < tree.prio){
					tree = rotateRR(tree);
				}
			}
			return tree;
		}
	}
	
	public void delete(int value){
		tree = delete(tree, value);
	}
	
	private TreapBSTNode delete(TreapBSTNode tree, int value){
		if(tree == null)
			return null;
		else{
			if(value < tree.val)
				tree.left = delete(tree.left, value);
			else if(value > tree.val)
				tree.right = delete(tree.right, value);
			else{
				if(tree.left != null && tree.right != null){
					if(tree.left.prio < tree.right.prio){
						tree = rotateLL(tree);
					}else{
						tree = rotateRR(tree);
					}
					tree = delete(tree, value);
				}else{
					tree = tree.left == null? tree.right: tree.left;
				}
			}
			return tree;
		}
	}
	
	public void inOrder(){
		inOrder(tree);
	}

	private void inOrder(TreapBSTNode tree){
		if(tree != null){
			inOrder(tree.left);
			System.out.print(tree.val + " ");
			inOrder(tree.right);
		}
	}
	
	public int height(){
		return height(tree);
	}
	
	private int height(TreapBSTNode tree){
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
		TreapBST t = new TreapBST();
		t.insert(1);
		t.insert(2);
		t.insert(3);
		t.insert(4);
		t.insert(5);
//		t.insert(6);
//		t.insert(7);
//		t.insert(8);
//		t.insert(9);
//		t.insert(10);
//		t.insert(11);
//		t.insert(12);
//		t.insert(13);
//		t.insert(14);
//		t.insert(15);
//		t.insert(16);
//		t.insert(17);
//		t.insert(18);
//		t.insert(19);
//		t.insert(20);
//		t.insert(21);
//		t.insert(22);
//		t.insert(23);
//		t.insert(24);
//		t.insert(25);
//		t.insert(26);
//		t.insert(27);
//		t.insert(28);
//		t.insert(29);
//		t.insert(30);
//		t.insert(31);
//		t.insert(32);
//		t.insert(33);
//		t.insert(34);
//		t.insert(35);
//		t.insert(36);
//		t.insert(37);
//		t.insert(38);
//		t.insert(39);
//		t.insert(40);
		t.inOrder();
		System.out.println("\nTree height: " + t.height());
		
		System.out.println("\ndeleting 5:");
		t.delete(5);
		t.inOrder();
		System.out.println("\nTree height: " + t.height());
		
		System.out.println("\ndeleting 2:");
		t.delete(2);
		t.inOrder();
		System.out.println("\nTree height: " + t.height());
		
		System.out.println("\ndeleting 1:");
		t.delete(1);
		t.inOrder();
		System.out.println("\nTree height: " + t.height());
		
		System.out.println("\ndeleting 3:");
		t.delete(3);
		t.inOrder();
		System.out.println("\nTree height: " + t.height());
		
		System.out.println("\ndeleting 4:");
		t.delete(4);
		t.inOrder();
		System.out.println("\nTree height: " + t.height());
	}

}
