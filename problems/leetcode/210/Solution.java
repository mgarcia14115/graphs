
import java.util.*;
public class Solution{
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Graph g = new Graph();
        for(int i = 0 ;i < numCourses;i++){
            g.addVertex(i);
        }
        
        for(int i = 0;i < prerequisites.length;i++){
            int [] eges = prerequisites[i];
            g.addEdge(eges);
        }

        g.DFS();

        if(g.acyclic){
            
            mylist list = new mylist();

            for(int id :g.keys){
                Node u = g.vertices.get(id);
                    list.insert(u);
            }
            int [] a = new int[list.size];
            Node j = list.head;
            for(int i = 0 ;i < a.length;i++){
                a[i] = j.id;
                
                j = j.next;
            }
            return a;
        }
        int [] emp = {};
       return emp; 
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
        int time;
        public void DFS(){
            time = 0;
            for(int id: this.keys){
                Node temp = this.vertices.get(id);
                if(temp.color.equals("white")){
                    DFSVisit(temp);
                }
              

            }
        }

        boolean acyclic;
        public void DFSVisit(Node u){
            time += 1;
            u.dist = time;
            u.color = "gray";

            for(int id: this.adjlist.get(u).keySet()){
                Node v = this.vertices.get(id);
                if( v.color.equals("white")){
                    v.pred = u;
                    DFSVisit(v);
                }

                if(v.color.equals("gray")){
                    this.acyclic = false; 
                }
            }

            time +=1;
            u.color = "black";
            u.finishtime = time; 

            
        }

     
        

    }

    public static class Node{
        int id;
        String color;
        Node pred;
        int dist;
        int finishtime;
        Node next;
        public Node(int id){
            this.id = id;
            color = "white";
            pred = null;
            dist = 0;
            finishtime = 0;
            next = null; 
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


    public static class mylist{

        Node head;
        int size;
        public mylist(){
            this.size = 0;
            this.head = null;
        }
        //sort from greatest to least finish time.
        public void insert(Node u){
            
            if(this.head == null){
                //no head
                this.head = u; 
                size++;
                return;

            }else{
                if(u.finishtime > this.head.finishtime){
                   // new node is head.
                    u.next = this.head;
                    this.head = u; 
                    size++;
                    return;
                }

                
                Node i = this.head;
                while(i != null){
                    if(i.next != null){
                        if(u.finishtime > i.next.finishtime){
                            
                            u.next = i.next;
                            i.next = u; 
                            size++;
                            return;
                        }
                    }else{

                        i.next = u;
                        size++;
                        return;
                    }
                    
                    
                    i = i.next;
                } 


               
            }

           
        }



    }

}