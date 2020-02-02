package ir.ac.kntu.bfs;

import javafx.util.Pair;

import java.util.ArrayList;

public class BFSNode extends Node {
    private ArrayList<BFSNode> visitedNodes = new ArrayList<>();

    public Integer getPathLength() {
        return visitedNodes.size();
    }

    public BFSNode(Integer x, Integer y) {
        super(x, y);
    }

    public BFSNode(Pair<Integer, Integer> coordinate) {
        super(coordinate);
    }

    public ArrayList<BFSNode> getVisitedNodes() {
        return (ArrayList<BFSNode>) visitedNodes.clone();
    }

    public void addToVisitedNode(BFSNode node) {
        visitedNodes.add(node);
    }

    public void setVisitedNodes(ArrayList<BFSNode> visitedNodes) {
        this.visitedNodes = visitedNodes;
    }

    public Node getSimpleNode() {
        return new Node(super.getCoordinate());
    }

}
