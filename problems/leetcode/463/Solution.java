
import java.util.*;
class Solution {


	public int islandPerimeter(int[][] grid) {
        Graph<String> g = buildGraph(grid);
		g.DFS();
		return g.perimeter;
    }

	public static Graph<String> buildGraph( int [][] grid ){
		Graph<String> g = new Graph<>();
		int rows = grid.length;
		int cols = grid[0].length;
		
		for(int i = 0 ;i < rows;i++){

			for(int j = 0 ;j < cols;j++){

				if(grid[i][j]  == 1){
					Node<String> u = new Node<String>(i + " " + j);
					g.addVertex(u);
					checkLeft(u, grid, g, i, j);
					checkRight(u, grid, g, i, j);
					checkDown(u, grid, g, i, j);
					checkUp(u, grid, g, i, j);
					
				}
			}
		}
		return g;

	}

	

	public static void checkRight(Node<String> u, int [][] grid,Graph<String> g, int i , int j){
		try {
			if (grid[i][j+1] == 1) {
				Node<String> v = new Node<String>(i + " " + (j+1));
				g.addVertex(v);
				g.addEdge(u, v);
				
			}
			
		} catch (Exception e) {
			//System.out.println("Out of bounds. Skipping");
		}
		
	}

	public static void checkLeft(Node<String> u, int [][] grid,Graph<String> g, int i , int j){
		try {
			if (grid[i][j-1] == 1) {
				Node<String> v = new Node<String>(i + " " + (j-1));
				g.addVertex(v);
				g.addEdge(u, v);
				
			}
			
		} catch (Exception e) {
			//System.out.println("Out of bounds. Skipping");
		}
		
	}

	public static void checkDown(Node<String> u, int [][] grid,Graph<String> g, int i , int j){
		try {
			if (grid[i+1][j] == 1) {
				Node<String> v = new Node<String>((i+1) + " " + j);
				g.addVertex(v);
				g.addEdge(u, v);
				
			}
			
		} catch (Exception e) {
			//System.out.println("Out of bounds. Skipping");
		}
		
	}

	public static void checkUp(Node<String> u, int [][] grid,Graph<String> g, int i , int j){
		try {
			if (grid[i-1][j] == 1) {
				Node<String> v = new Node<String>((i-1) + " " + j);
				g.addVertex(v);
				g.addEdge(u, v);
				
			}
			
		} catch (Exception e) {
			//System.out.println("Out of bounds. Skipping");
		}
		
	}

	public static class Graph<T>{
		HashMap<Node<T>,HashMap<T,Node<T>>> adjlist; 
		HashMap<T,Node<T>> vertices;
		HashSet<T> keys;
		int perimeter; 
		public Graph(){
			this.adjlist = new HashMap<>();
			this.vertices = new HashMap<>();
			this.keys = new HashSet<>();
		}

		public boolean addVertex(Node<T> u){

			if(!this.keys.contains(u.key)){
				this.keys.add(u.key);
				this.vertices.put(u.key, u);
				this.adjlist.put(u, new HashMap<>());
				return true;
			}
			return false; 
		}

		public void addEdge(Node<T> u, Node<T> v){

			//undirected graph

			HashMap<T,Node<T>> ualist = this.adjlist.get(u);
			HashMap<T,Node<T>> valist = this.adjlist.get(v);
			if(!ualist.containsKey(v.key)){
				ualist.put(v.key, v);
			}
			if(!valist.containsKey(u.key)){
				valist.put(u.key, u);
			}
			

		}

	
		public void displayGraph(){

			for(T i: this.vertices.keySet()){
				Node<T> u = this.vertices.get(i);
				System.out.print("Node " + u.key + " adjlist: ");
				for(T j: this.adjlist.get(u).keySet()){	
					Node<T> v = this.vertices.get(j);
					System.out.print( v.key + " |");

				}

				System.out.println();

			}
		}


		public void DFS(){
			this.perimeter = 0; 
			for( T key: this.vertices.keySet()){
				Node<T> u = this.vertices.get(key);
				u.color = "white";
				u.pred = null; 
			}
			int time = 0;
			
			for ( T key: this.vertices.keySet()){
				Node<T> u = this.vertices.get(key);
				if( u.color.equals("white")){
					this.perimeter += (4 - this.adjlist.get(u).size());
					time += DFSVisit(u,time);
				}
			}
		}

		public int DFSVisit(Node<T> u,int time){

			time += 1; 
			u.distance = time;
			u.color = "gray";

			for( T key: this.adjlist.get(u).keySet()){
				Node<T> v = this.vertices.get(key);

				if( v.color.equals("white")){
					this.perimeter += (4 - this.adjlist.get(v).size());
					v.pred = u; 
					DFSVisit(v, time);
				}

			}

			u.color = "black";
			time +=1;
			u.finishTime = time; 
			return time; 
		}
	
	}

	public static class Node<T>{

		T key;
		String color;
		Node<T> pred;
		int distance;
		int finishTime;
		public Node(T key){
			this.key = key;
		}

		@Override
		public boolean equals(Object obj){

			if( obj == null){
				return false;
			}

			if( this == obj){
				return true;
			}

			Node<T> temp = (Node) obj;
			if(this.key.equals(temp.key)){
				return true;
			}

			return false;


		}

		@Override
		public int hashCode(){
			return Objects.hashCode(this.key);
		}
		

	}
}