
import java.util.*;
public class Solution{
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph g = new Graph();
        for(int i = 0 ;i < numCourses;i++){
            g.addVertex(i);
        }
        
        for(int i = 0;i < prerequisites.length;i++){
            int [] eges = prerequisites[i];
            g.addEdge(eges);
        }

        g.DFS();

        return g.acyclic;
    }
    

    public static class Graph{

        HashMap<Integer, Node> vertices;
        HashSet<Integer> keys;
        HashMap<Node, HashMap<Integer,Node>> adjlist;

        public Graph(){
            this.vertices = new HashMap<>();
            this.keys = new HashSet<>();
            this.adjlist = new HashMap<>();
            acyclic = true;
        }

        public void addVertex(int key){

            if(!this.keys.contains(key)){
                Node temp = new Node(key);
                this.keys.add(key);
                this.vertices.put(key, temp);

                this.adjlist.put(temp, new HashMap<>());
            }
         

        }

        public void addEdge(int [] keys){
            int keyu = keys[1];
            int keyv = keys[0];
            addVertex(keyu);
            addVertex(keyv);

            Node u = this.vertices.get(keyu);
            Node v = this.vertices.get(keyv);

            if(!this.adjlist.get(u).containsKey(keyv)){
                this.adjlist.get(u).put(keyv, v);
            }

            



        }

        public void display(){

            for(int key: this.keys){

                Node u = this.vertices.get(key);

                System.out.print("Vertex " + u.id + " adjlist:");

                for(int vkey: this.adjlist.get(u).keySet()){
                    Node v = new Node(vkey);
                    System.out.print(v.id + " ");
                }
                System.out.println();

            }
        }

        public void DFS(){
            int time = 0;
            for(int id: this.keys){
                Node temp = this.vertices.get(id);
                if(temp.color.equals("white")){
                    time+=DFSVisit(temp, time);
                }
              

            }
        }

        boolean acyclic;
        public int DFSVisit(Node u, int time){
            time += 1;
            u.dist = time;
            u.color = "gray";

            for(int id: this.adjlist.get(u).keySet()){
                Node v = this.vertices.get(id);
                if( v.color.equals("white")){
                    v.pred = u;
                    DFSVisit(v, time);
                }

                if(v.color.equals("gray")){
                    this.acyclic = false; 
                }
            }

            time +=1;
            u.color = "black";
            u.finishtime = time; 

            return time;
        }

   
        

    }

    public static class Node{
        int id;
        String color;
        Node pred;
        int dist;
        int finishtime;

        public Node(int id){
            this.id = id;
            color = "white";
            pred = null;
            dist = 0;
            finishtime = 0; 
        }

        public boolean equals(Object obj){

            if(obj == null){
                return false;
            }
            Node temp = (Node)obj;
            if(this.id == temp.id) {
                return true; 
            }

            return false;
        }

        public int hashCode(){

            return Objects.hashCode(this.id);
        }

    }


}