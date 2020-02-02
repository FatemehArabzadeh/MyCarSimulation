package ir.ac.kntu.bfs;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class BreadthFirstSearch {
    private static BreadthFirstSearch bfs = new BreadthFirstSearch();
    private char[][] map;
    private char routeCharacter;
    private Pair<Integer, Integer> start, end;
    private static boolean prepared = false;
    private LinkedList<Node> route;

    public static synchronized BreadthFirstSearch prepareBFS(char[][] map,
                                                             char routeCharacter,
                                                             Pair<? extends Number, ?
                                                                     extends Number> start,
                                                             Pair<? extends Number, ? extends Number> end) {
        bfs.route = new LinkedList<>();
        prepared = true;
        bfs.map = map;
        bfs.routeCharacter = routeCharacter;
        bfs.start = new Pair<>(start.getKey().intValue(), start.getValue().intValue());
        bfs.end = new Pair<>(end.getKey().intValue(), end.getValue().intValue());
        bfs.findPath();
        return bfs;
    }

    private synchronized void findPath() throws RuntimeException {
        Queue<BFSNode> nodes = new LinkedList<>();
        ArrayList<Node> visitedNodes = new ArrayList<>();
        BFSNode startNode = new BFSNode(start);
        BFSNode curPosition = startNode;
        nodes.add(curPosition);
        while (!isFinished(curPosition)) {
            enqueueNodesAround(nodes, curPosition);
            BFSNode temp = nodes.poll();
            if (temp == null) {
                throw new NoRouteException();
            }
            if (visitedNodes.contains(temp)) {
                continue;
            }
            visitedNodes.add(curPosition.getSimpleNode());
            curPosition = temp;
        }
        List<BFSNode> finalVisitedNodes = curPosition.getVisitedNodes();
        for (int i = 0; i < finalVisitedNodes.size(); i++) {
            route.add(finalVisitedNodes.get(i).getSimpleNode());
        }
    }

    private void enqueueNodesAround(Queue<BFSNode> nodeQueue, BFSNode curPosition) {
        ArrayList<BFSNode> visitedNodes = curPosition.getVisitedNodes();
        visitedNodes.add(curPosition);
        if (isValid(curPosition.getX(), curPosition.getY() + 1)) {
            BFSNode node = new BFSNode(curPosition.getX(), curPosition.getY() + 1);
            node.setVisitedNodes(visitedNodes);
            if (!nodeQueue.contains(node)) {
                nodeQueue.add(node);
            }
        }
        if (isValid(curPosition.getX(), curPosition.getY() - 1)) {
            BFSNode node = new BFSNode(curPosition.getX(),
                    curPosition.getY() - 1);
            node.setVisitedNodes(visitedNodes);
            if (!nodeQueue.contains(node)) {
                nodeQueue.add(node);
            }
        }
        if (isValid(curPosition.getX() + 1, curPosition.getY())) {
            BFSNode node = new BFSNode(curPosition.getX() + 1,
                    curPosition.getY());
            node.setVisitedNodes(visitedNodes);
            if (!nodeQueue.contains(node)) {
                nodeQueue.add(node);
            }
        }
        if (isValid(curPosition.getX() - 1, curPosition.getY() + 1)) {
            BFSNode node = new BFSNode(curPosition.getX() - 1,
                    curPosition.getY());
            node.setVisitedNodes(visitedNodes);
            if (!nodeQueue.contains(node)) {
                nodeQueue.add(node);
            }
        }
    }

    public Pair<Integer, Integer> getNextMove() throws RuntimeException {
        if (prepared && route.size() > 0) {
            return route.get(0).getCoordinate();
        }
        throw new IllegalStateException();
    }

    public static BreadthFirstSearch getInstance() throws RuntimeException {
        if (!prepared) {
            throw new IllegalStateException("Not Prepared");
        }
        return bfs;
    }

    public List<? extends Node> getRoute() throws RuntimeException {
        if (prepared) {
            return route;
        }
        throw new IllegalStateException();
    }

    private boolean isFinished(Node currentNode) {
        return currentNode.getY().equals(end.getValue()) && currentNode.getX().equals(end.getKey());
    }

    private boolean isValid(int x, int y) {
        if (x < 0 || y < 0 || x >= map[0].length || y >= map.length) {
            return false;
        }
        if (map[y][x] != routeCharacter) {
            return false;
        }
        return true;
    }

    private BreadthFirstSearch() {
    }
}
