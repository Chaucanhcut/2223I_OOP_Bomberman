package uet.oop.bomberman.entities;

public class Node {
    private int row;
    private int col;
    private int g = 0; // Khoảng cách từ Node xuất phát đến curentNode
    private int h = 0; // Khoảng cách từ currentNode đến targetNode
    private int f = 0; // f = g + h
    private boolean isBlocked = false; // bi chan
    private Node parent = null;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void calculateH(Node finalNode) {
        this.h = Math.abs(finalNode.getRow() - row) + Math.abs(finalNode.getCol() - col);
    }

    public void calculateF() {
        f = h + g;
    }

    @Override
    public boolean equals(Object arg0) {
        Node other = (Node) arg0;
        return this.row == other.getRow() && this.col == other.getCol();
    }

    public boolean checkIsChanged(Node currentNode) {
        if (g == 0 || g > currentNode.getG() + 1) {
            this.changeNodeData(currentNode);
            return true;
        }
        return false;
    }

    public void changeNodeData(Node currentNode) {
        parent = currentNode;
        g = currentNode.getG() + 1;
        calculateF();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlock) {
        this.isBlocked = isBlock;
    }
}

