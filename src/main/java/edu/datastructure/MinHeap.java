package edu.datastructure;

import org.junit.Test;

class Node{
    public double d;
    public int s;

    public Node(double d, int s) {
        this.d = d;
        this.s = s;
    }

    public double getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }
}

public class MinHeap {
    private Node[] heapArray;
    private int maxSize;
    private int currentSize;

    public static void main(String[] args) {
        MinHeap heap = new MinHeap(100);
        heap.push(5);
        heap.push(6);
        heap.push(2);
        heap.push(4);
        heap.push(3);
        heap.push(1);
        heap.push(7);
        heap.displayHeap();
        System.out.println(heap.pop().getD());
        System.out.println(heap.pop().getD());
        System.out.println(heap.pop().getD());
        System.out.println(heap.pop().getD());
        System.out.println(heap.pop().getD());
        System.out.println(heap.pop().getD());
        System.out.println(heap.pop().getD());
    }


    public MinHeap(int Size) {
        maxSize = Size;
        heapArray = new Node[maxSize];
        currentSize = 0;
    }

    public void filterDown(int start, int end) {
        int i = start;
        int j = 2 * i + 1;
        Node temp = heapArray[i];
        while (j <= end) {
            if (j < end
                    && heapArray[j].getD() > heapArray[j + 1].getD()) {
                j++;
            }
            if (temp.getD() <= heapArray[j].getD()) {
                break;
            } else {
                heapArray[i] = heapArray[j];
                i = j;
                j = 2 * j + 1;
            }
        }
        heapArray[i] = temp;
    }

    public void filterUp(int start) {
        int j = start;
        int i = (j - 1) / 2;
        Node temp = heapArray[j];

        while (j > 0) {
            if (heapArray[i].getD() <= temp.getD()) {
                break;
            } else {
                heapArray[j] = heapArray[i];
                j = i;
                i = (i - 1) / 2;
            }
            heapArray[j] = temp;
        }
    }

    public void push(int key){
        Node newNode = new Node(key, 0);
        heapArray[currentSize] = newNode;
        filterUp(currentSize);
        currentSize++;
    }
    public void push(int key, int s){
        Node newNode = new Node(key, s);
        heapArray[currentSize] = newNode;
        filterUp(currentSize);
        currentSize++;
    }
    public void push(Double key, int s){
        Node newNode = new Node(key, s);
        heapArray[currentSize] = newNode;
        filterUp(currentSize);
        currentSize++;
    }
    public Node pop(){
        Node root = heapArray[0];
        if(currentSize - 1 < 0)
            return null;
        heapArray[0] = heapArray[currentSize - 1];
        currentSize--;
        filterDown(0, currentSize - 1);
        return root;
    }
    public Object[] top(){
        Node root = heapArray[0];
        Object[] t = new Object[2];
        t[0] = root.getD();
        t[1] = root.getS();
        return t;
    }

    public void displayHeap() {
        System.out.print("heapArray: ");
        for (int i = 0; i < currentSize; i++) {
            if (heapArray[i] != null) {
                System.out.print(heapArray[i].getD() + " ");
            } else {
                System.out.print("-- ");
            }
        }
        System.out.println();

        int nBlanks = 32; // heap format
        int itemsPerRow = 1;
        int column = 0;
        int j = 0; // current item
        String dots = "...............................";
        System.out.println(dots + dots); // dotted top line

        while (currentSize > 0) { // for each heap item
            if (column == 0) { // first item in row
                for (int k = 0; k < nBlanks; k++) { // preceding blanks
                    System.out.print(" ");
                }
            }
            System.out.print(heapArray[j].getD()); // display item

            if (++j == currentSize) { // done?
                break;
            }

            if (++column == itemsPerRow) { // end of row?
                nBlanks /= 2; // half the blanks
                itemsPerRow *= 2; // twice the items
                column = 0; // start over on
                System.out.println(); // next row
            } else { // next item on row
                for (int k = 0; k < nBlanks * 2 - 2; k++) {
                    System.out.print(' '); // interim blanks
                }
            }
        }
        System.out.println("\n" + dots + dots);
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    public void makeEmpty() {
        currentSize = 0;
    }
}