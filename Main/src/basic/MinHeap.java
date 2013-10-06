package basic;

import java.util.ArrayList;

public class MinHeap {
	
	private ArrayList<Integer> heap = null;
	public MinHeap(){
		heap = new ArrayList<Integer>();
	}
	
	public void insert(int value){
		if(heap.size() == 0){
			heap.add(0);
		}
		heap.add(value);
		// Heap up
		heapUp(heap.size() - 1);
	}
	
	private void heapUp(int pos){
		if(pos > 1){
			int parent = pos / 2;
			if(heap.get(parent) > heap.get(pos)){
				swap(pos, parent);
				heapUp(parent);
			}
		}
	} 
	
	private void swap(int from, int to){
		int temp = heap.get(from);
		heap.set(from, heap.get(to));
		heap.set(to, temp);
	}
	
	public int deleteMin(){
		if(heap.size() == 1)
			return 0;
		else{
			int ret = heap.get(1);
			swap(1, heap.size() - 1);
			heapDown(1);
			heap.remove(heap.size() - 1);
			return ret;
		}
	}
	
	private void heapDown(int pos){
		int len = heap.size() - 1;
		if(len > 2){
			
			int child = pos * 2;
			if(child < len){
				if(child + 1 < len && heap.get(child + 1) < heap.get(child)){
					child += 1;
				}
				if(heap.get(child) < heap.get(pos)){
					swap(pos, child);
					heapDown(child);
				}
				
			}
		}
	}
	
	public void printHeap(){
		System.out.println("======print heap======");
		for(int i = 1; i < heap.size(); i ++){
			System.out.print(heap.get(i) + " ");
		}
		System.out.println();
	}
	
	public int size(){
		return heap.size() - 1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinHeap h = new MinHeap();
		h.insert(14);h.insert(13);h.insert(15);h.insert(9);
		h.insert(17);h.insert(7);h.insert(6);
		h.printHeap();
		System.out.println("========start deleting======");
		int len = h.size(); 
		for(int i = 0; i < len; i ++){
			h.deleteMin();
			h.printHeap();
		}
	}

}
