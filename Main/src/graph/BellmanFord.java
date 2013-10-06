package graph;

// Single source shortest path
public class BellmanFord {

	private int[] prev;
	private int[] dist;
	private int[][] graph;
	private int vernum;
	public static final int INF = Integer.MAX_VALUE;
	
	public boolean bellmanFord(int u){
		vernum = graph.length;
		dist = new int[vernum];
		prev = new int[vernum];
		for(int i = 0; i < vernum; i ++){
			dist[i] = graph[u][i];
			// Consider situation where two sub-graphs disconnect.
//			if(graph[u][i] < INF)
//				prev[i] = u;
//			else
//				prev[i] = -1;
			prev[i] = u;
		}
		
		for(int k = 2; k < vernum; k ++){
			for(int i = 0; i < vernum; i ++){
				if(i != u){
					for(int j = 0; j < vernum; j ++){
						if(j != i && j != u){
							if(graph[j][i] != INF && dist[j] != INF && dist[i] > graph[j][i] + dist[j]){
								dist[i] = graph[j][i] + dist[j];
								prev[i] = j;
							}
						}// end if
					}// end for: j
				}// end if
			}// end for: i
		}// end for: k
		// Check if there is any minus circle
		boolean isExist = false;
		for(int i = 0; i < vernum; i ++)
			for(int j = 0; j < vernum; j ++){
				if(graph[j][i] != INF && dist[i] > graph[j][i] + dist[j]){
					isExist = true;
					break;
				}
			}
		return isExist;
	}
	
	public void printPath(int u){
		for(int i = 0; i < graph.length; i ++){
			int tmp = i;
			do{
				System.out.print(tmp + " -> ");
				tmp = prev[tmp];
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
		BellmanFord b = new BellmanFord();
		b.setGraph(graph);
		System.out.println(b.bellmanFord(0));
		int[] dist = b.getDist();
		for(int i = 0; i < dist.length; i ++){
			System.out.println("0 -> " + i + ": " + dist[i]);
		}
		b.printPath(0);
		
	}

	public void setGraph(int[][] graph) {
		this.graph = graph;
	}

	public int[][] getGraph() {
		return graph;
	}
	
	public int[] getDist(){
		return dist;
	}

}
