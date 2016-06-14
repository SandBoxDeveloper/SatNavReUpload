package com.hulldiscover.zeus.basicsatnavsystem;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeus on 14/06/16.
 */
public class RouteLengthTest {

    public static final String A = "A";
    public static final String B = "B";
    public static final String C = "C";
    public static final String D = "D";
    public static final String E = "E";

    private static final Graph.Edge[] GRAPH = {
            new Graph.Edge(A, B, 5),
            new Graph.Edge(B, C, 4),
            new Graph.Edge(C, D, 7),
            new Graph.Edge(D, C, 8),
            new Graph.Edge(D, E, 6),
            new Graph.Edge(A, D, 5),
            new Graph.Edge(C, E, 2),
            new Graph.Edge(E, B, 3),
            new Graph.Edge(A, E, 7),
    };






    // Graph
    Graph directedGraph;

    /* AB5, BC4, CD7, DC8, DE6, AD5, CE2, EB3, AE7*/
    @Before
    public void setUp() {
        directedGraph = new Graph(GRAPH);
    }

    // Distance for route A-B-C
    // Expected output 9
    @Test
    public void testRouteDistanceCalculatorFirstCase() {
        // Prepare
        RoutePathLength routePathLengthCalculation = new RoutePathLength();
        List<String> routePath = new ArrayList<String>();
        // Route A-B-C
        routePath.add(A);
        routePath.add(B);
        routePath.add(C);

        int expected = 9;

        // Calculate route path distance
        int routeLength = routePathLengthCalculation.getRouteLength(directedGraph, routePath);

        // Assert
        Assert.assertEquals(expected, routeLength);
    }

    // Distance for route A-D
    // Expected output 5
    @Test
    public void testRouteDistanceCalculatorSecondCase() {
        // Prrepare
        RoutePathLength routePathLengthCalculation = new RoutePathLength();
        List<String> routePath = new ArrayList<String>();
        // Route A-D
        routePath.add(A);
        routePath.add(D);

        int expected = 5;

        // Calculate route path distance
        int routeLength = routePathLengthCalculation.getRouteLength(directedGraph, routePath);

        // Assert
        Assert.assertEquals(expected, routeLength);
    }

    // Distance for route A-D-C
    // Expected output 13
    @Test
    public void testRouteDistanceCalculatorThirdCase() {
        // Prepare
        RoutePathLength routePathLengthCalculation = new RoutePathLength();
        List<String> routePath = new ArrayList<String>();
        // Route A-D-C
        routePath.add(A);
        routePath.add(D);
        routePath.add(C);

        int expected = 13;

        // Calculate route path distance
        int routeLength = routePathLengthCalculation.getRouteLength(directedGraph, routePath);

        // Assert
        Assert.assertEquals(expected, routeLength);
    }

    // Distance for route A-E-B-C-D
    // Expected output 21
    @Test
    public void testRouteDistanceCalculatorFourthCase() {
        // Prepare
        RoutePathLength routePathLengthCalculation = new RoutePathLength();
        List<String> routePath = new ArrayList<String>();
        // Route A-E-B-C-D
        routePath.add(A);
        routePath.add(E);
        routePath.add(B);
        routePath.add(C);
        routePath.add(D);

        int expected = 21;

        // Calculate route path distance
        int routeLength = routePathLengthCalculation.getRouteLength(directedGraph, routePath);

        // Assert
        Assert.assertEquals(expected, routeLength);
    }

    // Distance for route A-E-D
    // Expected output NO SUCH ROUTE
    @Test
    public void testRouteDistanceCalculatorFifthCase() {
        // Prepare
        RoutePathLength routePathLengthCalculation = new RoutePathLength();
        List<String> routePath = new ArrayList<String>();
        // Route A-E-D
        routePath.add(A);
        routePath.add(E);
        routePath.add(D);

        String expected = "NO SUCH ROUTE";

        // Calculate route path distance
        int routeLength = routePathLengthCalculation.getRouteLength(directedGraph, routePath);

        // Assert
        Assert.assertEquals(expected, routeLength);
    }







}
