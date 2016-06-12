package com.hulldiscover.zeus.basicsatnavsystem;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zeus on 12/06/16.
 */
public class Graph {
    // graph
    private final Map<String, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges
    // number of edges
    private int edgesCount;

    /*
     * In a directed graph,
     * an edge is an ordered pair of nodes.
     * These are the lines that connect to
     * edges.
     *
     * This class represents one edge
     * in a graph.
     *
     * The class is used to construct a graph.
     */
    public static class Edge {
        // Member variables
        public final String vertex1;
        public final String vertex2;
        public final int distance;


        /* Constructor
         * Creates an edge in a graph
         *
         * @param vertex1 the first vertex in graph
         * @param vertex2 the first vertex in graph
         * @param distance the distance between vertex1
         *        and vertex 2 in graph.
         */
        public Edge(String vertex1, String vertex2, int distance) {
            // Init member variables
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.distance = distance;
        }
    }

    /*
     * In a directed graph,
     * is represented by a circle with a label.
     *
     * This class represents one vertex
     * in a graph.
     *
     * The class is used to construct a graph.
     */
    public static class Vertex implements Comparable<Vertex> {
        // Member variables
        public final String name;
        public int distance = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
        public Vertex previous = null;
        public final Map<Vertex, Integer> neighbours = new HashMap<>();

        /* Constructor
         * Creates an vertex in a graph
         *
         * @param name the name of the vertex
         */
        public Vertex(String name) {
            // Init name member variable
            this.name = name;
        }

        /*
         * Print path of vertex
         * @param null
         * @return void
         */
        private void printPath() {
            if (this == this.previous) {
                System.out.printf("%s", this.name);
            } else if (this.previous == null) {
                System.out.printf("%s(unreached)", this.name);
            } else {
                this.previous.printPath();
                System.out.printf(" -> %s(%d)", this.name, this.distance);
            }
        }

        /* Method compares the distances
         * between two vertices.
         *
         * @param other the vertex to compare with
         * @return the distance between two vertices
         * @see int
         */
        public int compareTo(Vertex other) {
            return Integer.compare(distance, other.distance);
        }
    }


    /*
     * This class constructs a graph
     * based off a set of edges.
     *
     * @param edges an array of edges
     */
    public Graph(Edge[] edges) {
        // Init graph to length of edges array
        graph = new HashMap<>(edges.length);
        edgesCount = edges.length;

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
                //edgesCount++; // increment number of edges
            }
            // if graph (HashMap) doesn't contain vertex2
            if (!graph.containsKey(e.vertex2)) {
                graph.put(e.vertex2, new Vertex(e.vertex2));
                //edgesCount++; // increment number of edges
            }
        }

        //another pass to set neighbouring vertices
        /* Conditional statement with
         * ternary operator support
         * TODO: Understand
         */
        for (Edge e : edges) {
            // neighbours of vertices
            graph.get(e.vertex1).neighbours.put(graph.get(e.vertex1), e.distance);
            //graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for an undirected graph
        }

    }

    /* Method returns the
     * number of vertices graph.
     *
     * @return the number of vertices in graph
     * @see int
     */
    public int numberOfVertices() {
        return graph.size();
    }

    /* Method returns the
     * number of edges graph.
     *
     * @return the number of edges in graph
     * @see int
     */
    public int numberOfEdges() {
        return edgesCount;
    }



    /** Prints a path from the source to the specified vertex */
    public void printPath(String endName) {
        if (!graph.containsKey(endName)) {
            System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
            return;
        }

        graph.get(endName).printPath();
        System.out.println();
    }

    public void printAllPaths() {
        for (Vertex v : graph.values()) {
            v.printPath();
            System.out.println();
        }
    }

    public int getDistance(String startName, String endName) {
        int distance = 0;
        if(graph.containsKey(startName)) {
             distance = graph.get(startName).distance;
        } else {
            System.err.printf("Graph doesn't contain vertex");
        }
        return distance;
    }

    //TODO: Class called PathTO using BreadthFirstSearch Algorithim
    // PathFinder(Graph G, String s) constructor
    // int distanceTo(String v) length of shortest path of s to v in G
    // Interable<String> pathTo(String v) shortest path from s to v in G

}
