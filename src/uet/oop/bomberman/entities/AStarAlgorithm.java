package uet.oop.bomberman.entities;

import uet.oop.bomberman.GameManagement;

import java.util.*;

public class AStarAlgorithm {
    private Node[][] searchArea;
    private PriorityQueue<Node> openList;
    private Set<Node> closedSet;
    private Node initialNode;
    private Node finalNode;

    /**
     * hàm khởi tạo có tham số
     */
    public AStarAlgorithm(char[][] Map, int RowInitial, int ColInitial, int RowFinal, int ColFinal) {
        this.initialNode = new Node(RowInitial, ColInitial);
        this.finalNode = new Node(RowFinal, ColFinal);
        searchArea = new Node[Map.length][Map[0].length];
        setNodes(Map);
        openList = new PriorityQueue<>(Comparator.comparingInt(Node::getF)); // uu tien theo f nho
        closedSet = new HashSet<>();
    }

    /**
     * update cac Node trong searchArea
     * Trong do: row, col, h, isBlocked la nhung cai tim luon duoc
     * g, f, parent la nhung cai update trong qua trinh tim kiem
     */

    /**
     * update các Node trong searchArea
     * với row, col, h, isBlocked tìm luôn được
     * g, f, parent cần update trong quá trình tìm
     */
    public void setNodes(char[][] Map) {
        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {
                searchArea[i][j] = new Node(i, j);
                searchArea[i][j].calculateH(finalNode);
                searchArea[i][j].setBlocked(GameManagement.getMapMatrix()[i][j] == '#' || GameManagement.getMapMatrix()[i][j] == '*' || GameManagement.getBombMap()[i][j] == '@');
            }
        }
    }

    /**
     * A*
     */
    public List<Node> aStarSearch() {
        openList.add(initialNode);
        while (!isEmpty(openList)) {
            Node currentNode = openList.poll();
            if (closedSet.contains(currentNode)) {
                continue;
            }
            closedSet.add(currentNode);
            if (isFinalNode(currentNode)) {
                return getPath(currentNode);
            } else {
                updateClosedNode(currentNode);
            }
        }
        return null;
    }

    public List<Node> getPath(Node currentNode) {
        List<Node> path = new ArrayList<>();
        while (currentNode != null) {
            path.add(currentNode); // mang luu se theo thu tu nguoc :>
            currentNode = currentNode.getParent();
        }
        return path;
    }

    public void updateClosedNode(Node currentNode) {
        int[] X = {0, 0, -1, 1}; // X[i] va Y[i] tương ứng 4 phía xung quanh
        int[] Y = {-1, 1, 0, 0};
        int rows = searchArea.length;
        int cols = searchArea[0].length;
        for (int i = 0; i < X.length; i++) {
            int RowClosedNode = currentNode.getRow() + X[i];
            int ColClosedNode = currentNode.getCol() + Y[i];
            if (RowClosedNode >= 0 && RowClosedNode < rows
                    && ColClosedNode >= 0 && ColClosedNode < cols) {
                Node ClosedNode = searchArea[RowClosedNode][ColClosedNode];
                if (!ClosedNode.isBlocked() && ClosedNode.checkIsChanged(currentNode)) {
                    openList.add(ClosedNode);
                }
            }
        }
    }

    private boolean isFinalNode(Node currentNode) {
        return currentNode.equals(finalNode);
    }

    private boolean isEmpty(PriorityQueue<Node> openList) {
        return openList.size() == 0;
    }
}
