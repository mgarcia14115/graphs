package Git;

import java.util.*;

public class Graph<T> {

   

    private HashMap<Node<T>, HashMap<T,Node<T>>> adjlist;
    private HashMap<T,Node<T>> vertices;
    private HashSet<T> keys;
    
    
    
    public HashMap<Node<T>, HashMap<T, Node<T>>> getAdjlist() {
        return adjlist;
    }

    public void setAdjlist(HashMap<Node<T>, HashMap<T, Node<T>>> adjlist) {
        this.adjlist = adjlist;
    }
    
    public HashSet<T> getKeys() {
        return keys;
    }

    public void setKeys(HashSet<T> keys) {
        this.keys = keys;
    }

    public HashMap<T, Node<T>> getVertices() {
        return vertices;
    }

    public void setVertices(HashMap<T, Node<T>> vertices) {
        this.vertices = vertices;
    }

    
    public Graph(){
        this.adjlist = new HashMap<>();
        this.vertices = new HashMap<>();
        this.keys = new HashSet<>();
    }

    public void addVertex(Node<T> vertex){

        if( !this.keys.contains(vertex.id)){
            this.keys.add(vertex.id);
            this.adjlist.put(vertex, new HashMap<>());
            this.vertices.put(vertex.id  , vertex);
        }


    }

    public void addEdge(T u, T v){

        Node<T> nodeU = this.vertices.get(u);
        Node<T> nodeV = this.vertices.get(v);

        if( nodeU == null || nodeV == null){
            return;
        }

        if ( this.adjlist.get(nodeU) == null){
            this.adjlist.put(nodeU, new HashMap<>());
        }

        this.adjlist.get(nodeU).put(v,nodeV);

        if(this.adjlist.get(nodeV) == null){
            this.adjlist.put(nodeV, new HashMap<>());
        }
        this.adjlist.get(nodeV).put(u,nodeU); 

    }


    public void BFS(Graph<T> G, T s){
        Node<T> source = this.vertices.get(s);
        for(T key: G.getKeys()){
            Node<T> u = G.getVertices().get(key);
            if(!u.equals(source)){
                u.setColor("white");
                u.setDistance(Integer.MIN_VALUE);
                u.setPred(null);
            }

        }

        source.setColor("gray");
        source.setDistance(0);
        source.setPred(null);


        Queue<Node<T>> x = new LinkedList<>();
        x.add(source);
        while (!x.isEmpty()){

            Node<T> u = x.remove();



            for (T vkey: G.getAdjlist().get(u).keySet()){
                Node<T> v = G.getVertices().get(vkey);
                if( v.color.equals("white")){
                    v.color = "gray";
                    v.distance = u.distance + 1;
                    v.pred = u;
                    x.add(v);
                }
                
            }

            u.color = "black";
        }

   }
 
    
    public static class Node<T> {

        private T id;
        private String color;
        private Node<T> pred;
        public Node<T> getPred() {
            return pred;
        }

        public void setPred(Node<T> pred) {
            this.pred = pred;
        }

        private int distance;
        
     

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public T getId() {
            return id;
        }
        
        public void setId(T id) {
            this.id = id;
        }
        
        
        public String getColor() {
            return color;
        }
        
        public void setColor(String color) {
            this.color = color;
        }
               
        


        @Override
        public boolean equals(Object obj){
            if(this == obj){
                return true; 
            }

            if(obj.getClass() != this.getClass()){
                return false; 
            }
            Node<?> temp = (Node<?>)obj;

            if(this.id == temp.id){
                return true;
            }
            return false;
        }

        @Override
        public int  hashCode(){
            return Objects.hashCode(id);
        }
    }

}
