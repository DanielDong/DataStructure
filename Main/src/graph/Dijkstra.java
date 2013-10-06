package graph;

import java.util.Arrays;

// Single source shortest path
public class Dijkstra {
	
	private int[][] graph;
	private boolean[] s;
	private int[] dist;
	private int[] pre;
	public static final int INF = Integer.MAX_VALUE;
	
	public int[] dijkstra(int u){
		// Initialize dist array and s array.
		pre = new int[graph.length];
		dist = new int[graph.length];
		s = new boolean[graph.length];
		Arrays.fill(s, false);
		for(int i = 0; i < dist.length; i ++){
			dist[i] = graph[u][i];
			pre[i] = u;
		}
		// Start dijkstra process.
		s[u] = true;
		while(true){
			int min = INF, v = -1;
			for(int i = 0; i < graph.length; i ++){
				if(!s[i] && dist[i] < min){
					min = dist[i];
					v = i;
				}
			}
			// NO vertex in V-S set.
			if(v == -1)
				break;
			
			s[v] = true;
			for(int i = 0; i < graph.length; i ++){
				if(!s[i] && graph[v][i] != INF && dist[i] > graph[v][i] + dist[v]){
					dist[i] = graph[v][i] + dist[v];
					pre[i]= v;
				}
			}
		}
		return dist;
	}
	
	public void printPath(int u){
		for(int i = 0; i < graph.length; i ++){
			int tmp = i;
			do{
				System.out.print(tmp + " -> ");
				tmp = pre[tmp];
			}while(tmp != u);
			System.out.println(tmp);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] graph = {
				{0, 10, INF, 30, 100},
				{10, 0, 50, INF, INF},
				{INF, 50, 0, 20, 10},
				{30, INF, 20, 0, 60},
				{100, INF, 10, 60, 0}
		};
		Dijkstra d = new Dijkstra();
		d.setGraph(graph);
		int[] dist = d.dijkstra(0);
		for(int i = 0; i < dist.length; i ++){
			System.out.println("0 -> " + i + ": " + dist[i]);
		}
		d.printPath(0);
	}



	public void setGraph(int[][] graph) {
		this.graph = graph;
	}



	public int[][] getGraph() {
		return graph;
	}



	public void setS(boolean[] s) {
		this.s = s;
	}



	public boolean[] getS() {
		return s;
	}



	public void setDist(int[] dist) {
		this.dist = dist;
	}



	public int[] getDist() {
		return dist;
	}

}
