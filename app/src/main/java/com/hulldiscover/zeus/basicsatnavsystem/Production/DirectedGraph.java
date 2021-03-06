package com.hulldiscover.zeus.basicsatnavsystem.Production;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;


/**
 * Created by Zeus on 12/06/16.
 *
 * The DirectedGraph class implements an directed graph.
 *
 *
 * @author  Andre Hitchman
 * @version 1.0
 * @since   2016-06-20
 */
public class DirectedGraph {
    // graph
    private final Map<String, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges

    /**
     * A list of vertices in this graph.
     */
    private List<Vertex> vertices;

    // number of edges
    private int edgesCount;
    Edge[] edges;

    /**
     * In a directed graph,
     * an edge is an ordered pair of nodes.
     * These are the lines that connect to
     * edges.
     *
     * This class represents one edge
     * in a graph.
     *
     * The class is used to construct a graph only.
     */
    public static class Edge {
        // Member variables
        public final String vertex1;
        public final String vertex2;
        public final int distance;

        /**
         * Creates an edge in a graph
         *
         * @param vertex1
         *                  The first vertex in graph
         * @param vertex2
         *                  The first vertex in graph
         * @param distance
         *                  The distance between vertex1
         *                  and vertex 2 in graph.
         */
        public Edge(String vertex1, String vertex2, int distance) {
            // Init member variables
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.distance = distance;
        }

        /**
         * Method to return the distance of a route (edge)
         * between two vertex points.
         *
         * @return int
         *         This returns the distance of an route (edge) between
         *         two vertex points.
         */
        public int getDistance(){
            return distance;
        }


        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((vertex1 == null) ? 0 : vertex1.hashCode());
            result = prime * result + ((vertex2 == null) ? 0 : vertex2.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Edge other = (Edge) obj;
            if (vertex1 == null) {
                if (other.vertex1 != null)
                    return false;
            } else if (!vertex1.equals(other.vertex1))
                return false;
            if (vertex2 == null) {
                if (other.vertex2 != null)
                    return false;
            } else if (!vertex2.equals(other.vertex2))
                return false;
            return true;
        }

    }

    /**
     * In a directed graph,
     * a vertex is represented by a circle with a label
     * and it is also called a node.
     *
     * This class represents one vertex
     * in a graph.
     *
     * The class is used to construct a graph only.
     */
    public static class Vertex implements Comparable<Vertex> {
        // Member variables
        public final String name;
        public int distance = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
        public final Map<Vertex, Integer> neighbours = new HashMap<>();
        private List<Vertex> adjacentVertex = new ArrayList<>();
        private List<Edge> edges = new ArrayList<>();

        // implemented to test for a cycle in graph
        // currently not used in final production code
        private boolean isVisited;
        private Vertex predecessor = null;
        private boolean isOpen;


        /**
         * Creates an vertex in a graph
         *
         * @param name
         *              The name of the vertex
         */
        public Vertex(String name) {
            // Init name member variable
            this.name = name;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vertex other = (Vertex) obj;
            if (name != other.name)
                return false;
            return true;
        }

        /**
         * Method to add adjacent (neighbour) vertex points
         * of a vertex.
         *
         * @param e
         *          The Edge (route)
         * @param v
         *          The vertex point
         */
        public void addAdjacentVertex(Edge e, Vertex v){
            edges.add(e);
            adjacentVertex.add(v);
        }

        /**
         * Method to return a list of adjacent vertex
         * points in graph.
         *
         * @return
         *          List of adjacent vertexes
         */
        public List<Vertex> getAdjacentVertexes(){
            return adjacentVertex;
        }

        /**
         * Method to return list of edges
         * in graph.
         *
         * @return
         *          List of edges
         */
        public List<Edge> getEdges(){
            return edges;
        }

        // isVisited and setVisited have been
        // implemented to test for a cycle in graph
        // currently not used in final production code
        public boolean isVisited() {
            return isVisited;
        }

        public void setVisited(boolean isVisited) {
            this.isVisited = isVisited;
        }

        /**
         * Accessor for this vertex's predecessor in the search path.
         * @return Predecessor vertex
         */
        Vertex getPredecessor() {
            return predecessor;
        }

        /**
         * Mutator for this vertex's predecessor in the search path.
         * @param predecessor Predecessor vertex
         */
        void setPredecessor(Vertex predecessor) {
            this.predecessor = predecessor;
        }

        /**
         * Returns whether this vertex is still open for the purposes
         * of graph search
         * @return Whether the vertex is open
         */
        boolean isOpen() {
            return isOpen;
        }

        /**
         * Mutator for whether this vertex is still open for the purposes
         * of graph search
         * @param open Whether the vertex is open
         */
        void setOpen(boolean open) {
            this.isOpen = open;
        }

        /**
         * This method prints
         * the path of a vertex
         *
         * @return void
         */
        private void printPath() {
            if (this == this.predecessor) {
                System.out.printf("%s", this.name);
            } else if (this.predecessor == null) {
                System.out.printf("%s(is not unreachable)", this.name);
            } else {
                this.predecessor.printPath();
                System.out.printf(" -> %s(%d)", this.name, this.distance);
            }
        }

        /**
         * Method compares the distances
         * between two vertices.
         *
         * @param other the vertex to compare with
         * @return the distance between two vertices
         */
        public int compareTo(Vertex other) {
            return Integer.compare(distance, other.distance);
        }

    }

    /**
     * Constructs a new graph given a set of edges.
     *
     * @param edges an array of edges
     */
    public DirectedGraph(Edge[] edges) {
        // Init graph to length of edges array
        graph = new HashMap<>(edges.length);
        edgesCount = edges.length;
        this.edges = edges;

        //one pass to find all vertices
        /* Conditional statement with
         * ternary operator support
         * looping through all edges in the graph
         * to find all vertices.
         */
        for (Edge e : edges) {
            // if graph (HashMap) doesn't contain vertex1
            if (!graph.containsKey(e.vertex1)) {
                graph.put(e.vertex1, new Vertex(e.vertex1));
            }
            // if graph (HashMap) doesn't contain vertex2
            if (!graph.containsKey(e.vertex2)) {
                graph.put(e.vertex2, new Vertex(e.vertex2));
            }
        }

        //another pass to set neighbouring vertices
        /* Conditional statement with
         * ternary operator support
         */
        for (Edge e : edges) {
            // neighbours of vertices
            graph.get(e.vertex1).neighbours.put(graph.get(e.vertex2), e.distance);
            graph.get(e.vertex1).addAdjacentVertex(e, getV(e.vertex2));
            //graph.get(e.vertex2).neighbours.put(graph.get(e.vertex1), e.distance); // also do this for an undirected graph
        }
    }

    /**
     * A string representation of a vertex with end-of-lines before and after.
     * @param v A vertex
     * @return The vertex's string representation
     */
    private String vertexString(Vertex v) {
        return "\n" + v.name + "\n";
    }

    /**
     * Method to get vertex by name.
     *
     * @param v - The name of the vertex point.
     * @return Vertex
     */
    public Vertex getV(String v) {
        return graph.get(v);
    }

    /**
     * Method throw an exception if
     * parameter is not a vertex
     * in graph.
     *
     * @param vertex the vertex
     */
    private void validateVertex(String vertex) {
        if (!graphHasVertex(vertex)) {
            System.out.print(vertex + " is not a vertex");
        }
    }

    /**
     * Method returns the
     * vertices in graph.
     *
     * @return the set of vertices in this graph
     */
    public Iterable<String> vertices () {
        return graph.keySet();
    }

    public Iterable<Vertex> verticesList () {
        return graph.values();
    }

    /**
     * Accessor for a list of vertices
     * making up this graph.
     *
     * @return A list of vertices
     */
    public List<Vertex> listOfVertices () {
        vertices = new ArrayList<Vertex>(graph.values().size());
        vertices.addAll(graph.values());
        //List<Vertex> vertexList = new ArrayList<Vertex>(graph.values());

        return vertices;
    }

    /**
     * Returns the iterator that travels the vertexs of a graph.
     *
     * @return an iterator that travels the vertexs of a graph.
     */
    public Iterator<String> iterator() {
        return graph.keySet().iterator();
    }

    /**
     * In graph theory, an adjacent vertex
     * of a vertex v in a graph is a vertex
     * that is connected to v by an edge.
     *
     * This method prints
     * the neighbour to a vertex
     *
     * @param  vertex the vertex
     */
    public void printVerticesAdjacentTo(String vertex) {
        // first validate this input is
        // an vertex in graph
        validateVertex(vertex);

        // Set of keys from HashMap
        // that contain the neighbours to
        // @param vertex
        Set<Vertex> vertexSet = graph.get(vertex).neighbours.keySet();

        // Loop through set
        // and print values
        for (Vertex vertex1 : vertexSet) {
            System.out.println(vertex + " is Adjacent to: " + vertex1.name);
        }
    }

    /**
     * Returns the set of vertices adjacent to vertex in graph.
     *
     * @param  vertex the vertex
     * @return the set of vertices adjacent to vertex <tt>v</tt> in this graph
     * @throws IllegalArgumentException if <tt>v</tt> is not a vertex in this graph
     */
    public Set<Vertex> setOfVerticesAdjacentTo(String vertex) {
        validateVertex(vertex);
        return graph.get(vertex).neighbours.keySet();
    }

    /**
     * Method to return adjacent vertices.
     *
     * @param v - The vertex name
     * @return List of vertex names adjacent to vertex
     */
    public List<String> listOfVerticesAdjacentTo(String v) {
        List<String> adjacent = new ArrayList<String>();
        Set<Vertex> setOfAdjacentVertex;

        // Validates and returns set of vertex adjacent to v
        setOfAdjacentVertex = setOfVerticesAdjacentTo(v);

        for(Vertex vertexName : setOfAdjacentVertex) {
            // Add adjacent vertex
            adjacent.add(vertexName.name);
        }
        return adjacent;
    }

    /**
     * Method to return adjacent vertices.
     *
     * @param v - The vertex
     * @return List of vertex adjacent to vertex
     */
    public List<Vertex> verticesAdjacentTo(Vertex v) {
        List<Vertex> adjacent = new ArrayList<Vertex>();
        Set<Vertex> setOfAdjacentVertex;

        // Validates and returns set of vertex adjacent to v
        setOfAdjacentVertex = setOfVerticesAdjacentTo(v.name);

        for(Vertex vertexName : setOfAdjacentVertex) {
            // Add adjacent vertex
            adjacent.add(vertexName);
        }
        return adjacent;
    }

    /**
     * Method to return a set of vertices
     * adjacent to a vertex point.
     *
     * @param vertex - The vertex point
     * @return Iterable - Adjacent vertices
     */
    public Iterable<Vertex> adjacentTo(String vertex) {
        return setOfVerticesAdjacentTo(vertex);
    }

    /**
     * Method check if vertex is adjacent
     * to another vertex.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return true of false whether vertex is adjacent to
     */
    public boolean isAdjacentTo(String vertex1, String vertex2) {
        boolean isAdjacent = false;
        // Set that contains
        // keys of @param vertex1
        // neighbours
        Set<Vertex> vertexSet = graph.get(vertex1).neighbours.keySet();

        // Check vertex has neighbour
        // of @param vertex2
        for(Vertex vertex : vertexSet) {
            if(vertex.name.equals(vertex2)) {
                isAdjacent = true;
                return isAdjacent;
            }
        }

        return isAdjacent;
    }

    /**
     *  Method returns true if
     * input @param String v is
     * a vertex in graph.
     *
     * @param  vertex the vertex
     * @return <tt>true</tt> if <tt>vertex</tt> is a vertex in graph,
     *         <tt>false</tt> otherwise
     */
    public boolean graphHasVertex(String vertex) {
        return graph.containsKey(vertex);
    }

    /**
     *  Method returns the
     * number of vertices graph.
     *
     * @return the number of vertices in graph
     * @see int
     */
    public int numberOfVertices() {
        return graph.size();
    }

    /**
     *  Method returns the
     * number of edges graph.
     *
     * @return the number of edges in graph
     * @see int
     */
    public int numberOfEdges() {
        return edgesCount;
    }

    /**
     * Method to return the number of edges
     * in graph as a array return type.
     *
     * @return Edge - The number of edges in graph
     */
    public Edge[] edges() { return edges; }

    /**
     * Method to return the distance
     * between two vertices.
     *
     * @param startName
     *                  vertex
     * @param endName
     *                  vertex
     * @return int
     *          This returns the distance between vertices
     */
    public int getDistance(String startName, String endName) {
        int distance = 0;

        for (Edge edge : edges) {
            if (edge.vertex1.equals(startName)
                    && edge.vertex2.equals(endName)) {
                distance = edge.distance;
            }
            if (edge.vertex1.equals(startName)
                    && edge.vertex2.equals(endName)) {
                distance = edge.distance;
            }
        }
        return distance;
    }

    /** Prints a path from the source to the specified vertex */
    public void printPath(String endName) {
        if (!graph.containsKey(endName)) {
            System.err.printf("DirectedGraph doesn't contain end vertex \"%s\"\n", endName);
            return;
        }

        graph.get(endName).printPath();
        System.out.println();
    }






    /**
     * TEST IMPLEMENTATIONS/Experimentation
     * Not used to run main application
     */

    /**
     * Method to print all paths
     * in graph.
     */
    public void printAllPaths() {
        for (Vertex v : graph.values()) {
            v.printPath();
            System.out.println();
        }
    }

    /**
     * Method to check if graph is cyclic?
     *
     * @param v - the source vertex
     * @return boolean
     */
    public boolean isCyclicDirected(Vertex v){
        if (v.isVisited){
            return true;
        }
        v.setVisited(true);
        Iterator<Vertex> e = setOfVerticesAdjacentTo(v.name).iterator();
        while (e.hasNext()) {
            Vertex t = e.next();
            // quick test:
            if (t.isVisited) {
                return true;
            }
            // elaborate, recursive test:
            if(isCyclicDirected(t)) {
                return true;
            }
        }
        // none of our adjacent vertices flag as cyclic
        return false;
    }

    /** Runs dijkstra using a specified source vertex */
    public void dijkstra(String startName) {
        if (!graph.containsKey(startName)) {
            System.err.printf("DirectedGraph doesn't contain start vertex \"%s\"\n", startName);
            return;
        }
        final Vertex source = graph.get(startName);
        NavigableSet<Vertex> q = new TreeSet<>();

        // set-up vertices
        for (Vertex v : graph.values()) {
            v.predecessor = v == source ? source : null;
            v.distance = v == source ? 0 : Integer.MAX_VALUE;
            q.add(v);
        }

        dijkstra(q);
    }

    /** Implementation of dijkstra's algorithm using a binary heap. */
    private NavigableSet<DirectedGraph.Vertex>dijkstra(final NavigableSet<DirectedGraph.Vertex> q) {
        DirectedGraph.Vertex u, v;
        while (!q.isEmpty()) {

            u = q.pollFirst(); // vertex with shortest distance (first iteration will return source)
            if (u.distance == Integer.MAX_VALUE) break; // we can ignore u (and any other remaining vertices) since they are unreachable

            //look at distances to each neighbour
            for (Map.Entry<DirectedGraph.Vertex, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey(); //the neighbour in this iteration

                final int alternateDist = u.distance + a.getValue();
                if (alternateDist < v.distance) { // shorter path to neighbour found
                    q.remove(v);
                    v.distance = alternateDist;
                    v.predecessor = u;
                    q.add(v);
                }
            }
        }
        return q;
    }

    private Stack<String> cycle;
    // does this graph have a self loop?
    // side effect: initialize cycle to be self loop
    public boolean hasSelfLoop(String vertex) {
        for (int v = 0; v < numberOfVertices(); v++) {
            Set<Vertex> adjacent = setOfVerticesAdjacentTo(vertex);
            for(Vertex s : adjacent ) {
                //String vertexName = graph.get(v).name;
                if(vertex == s.name) {
                    cycle = new Stack<String>();
                    cycle.push(vertex);
                    cycle.push(vertex);
                    return true;
                }
            }
        }
        return false;
    }

    // get distance between vertex points
    public int getDistance2(String startName, String middleName, String endName) {
        int distance = 0;
        for (Edge edge : edges) {
            if (edge.vertex1.equals(startName)
                    && edge.vertex2.equals(middleName)
                    && edge.vertex1.equals(endName)) {
                distance = edge.distance;
            }
            if (edge.vertex1.equals(startName)
                    && edge.vertex2.equals(middleName)
                    && edge.vertex1.equals(endName)) {
                distance = edge.distance;
            }
            //throw new RuntimeException("Should not happen");
        }
        return distance;
    }

    /**
     * Method to print string representation
     * of graph.
     *
     * @param start vertex
     * @param end vertex
     * @return the graph as a string
     */
    public String pathString(Vertex start, Vertex end) {
        if ( start == end ) {
            return vertexString(start);
        }
        else if ( end.getPredecessor() == null ) {
            return "";
        }
        else {
            return pathString(start, end.getPredecessor()) + vertexString(end);
        }
    }

}
