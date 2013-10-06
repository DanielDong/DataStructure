package graph;

// Calculate the shortest path between any two vertex in a graph.
public class FloydWarshall {
	
	private int[][] graph;
	private int[][] dist;
	private int[][] path;
	private int vernum;
	public static final int INF = Integer.MAX_VALUE;
	
	public void setGraph(int[][] graph){this.graph = graph;}
	public int[][] getPath(){return path;}
	public void setVernum(int num){this.vernum = num;}
	public int getVernum(){return vernum;}
	
	public int[][] floyd(){
		vernum = graph.length;
		dist = new int[vernum][vernum];
		path = new int[vernum][vernum];
		
		for(int i = 0; i < vernum; i ++){
			for(int j = 0; j < vernum; j ++){
				dist[i][j] = graph[i][j];
				path[i][j] = -1;
			}
		}
		
		for(int k = 0; k < vernum; k ++)
			for(int i = 0; i < vernum; i ++)
				for(int j = 0; j < vernum; j ++){
					if(dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]){
						dist[i][j] = dist[i][k] + dist[k][j];
						path[i][j] = k;
					}
				}
		return dist;
	}
	
	private void printInnerPath(int from, int to){
		int k = path[from][to];
		if(k == -1)
			return;
		printInnerPath(from, k);
		System.out.print(k + " -> ");
		printInnerPath(k, to);
		
	}
	public void printPath(int from, int to){
		System.out.print(from + " -> ");
		printInnerPath(from, to);
		System.out.println(to);
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
		
		FloydWarshall f = new FloydWarshall();
		f.setGraph(graph);
		int[][] dist = f.floyd();
		for(int i = 0; i < 1; i ++)
			for(int j = 0; j < f.getVernum(); j ++){
				System.out.println(i + " -> " + j + ": " + dist[i][j]);
			}
		
		int[][] path = f.getPath();
		for(int i = 0; i < 1; i ++)
			for(int j = 0; j < f.getVernum(); j ++){
				f.printPath(i, j);
			}
	}

}
