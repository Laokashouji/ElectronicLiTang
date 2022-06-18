package edu.map;

import edu.datastructure.MinHeap;
import edu.datastructure.MyArrayList;
import edu.datastructure.MyList;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SchoolMap {

    private static final int MAX_SIZE = 1000;
    public static final double speed[] = {1, 5};

    private Edge[] edges = new Edge[MAX_SIZE];
    private int[] head = new int[MAX_SIZE];
    private Place[] places = new Place[MAX_SIZE];
    private Map<String, Integer> map = new HashMap<>();
    private String file1;
    private String file2;
    private String pp = "";

    public Object[] dijkstra(String source, String target) {
        Double[] d = new Double[MAX_SIZE];
        int[] v = new int[MAX_SIZE];
        Path[] path = new Path[MAX_SIZE];
        pp = "";
        for (int i = 0; i < d.length; i++) {
            d[i] = 2147483647 * 1.0;
            v[i] = 0;
            path[i] = new Path();
        }
        int s = map.get(source), t = map.get(target);
        d[s] = 0.0;
        MinHeap heap = new MinHeap(MAX_SIZE);
        heap.push(0, s);
        while (heap.isEmpty() == false) {
            Object[] xx = heap.top();
            heap.pop();
            int x = (int) xx[1];
            if (v[x] != 0) {
                continue;
            }
            v[x] = 1;
            for (int i = head[x]; i != 0; i = edges[i].next) {
                int y = edges[i].to;
                Double z = edges[i].l;
                if (d[y] == d[x] + z) {
                    path[y].add(x);
                } else if (d[y] > d[x] + z) {
                    d[y] = d[x] + z;
                    path[y].put(x);
                }
                heap.push(d[y], y);
            }
        }
        System.out.println("最短路径为: " + d[t]);

        print(path, t, s);
        Object[] ans = new Object[2];
        ans[0] = d[t];
        ans[1] = pp;
        return ans;
    }

    public Object[] dijkstra2(String source, String target) {
        Double[] d = new Double[MAX_SIZE];
        int[] v = new int[MAX_SIZE];
        Path[] path = new Path[MAX_SIZE];
        pp = "";
        for (int i = 0; i < d.length; i++) {
            d[i] = 2147483647 * 1.0;
            v[i] = 0;
            path[i] = new Path();
        }
        int s = map.get(source), t = map.get(target);
        d[s] = 0.0;
        MinHeap heap = new MinHeap(MAX_SIZE);
        heap.push(0, s);
        while (heap.isEmpty() == false) {
            Object[] xx = heap.top();
            heap.pop();
            int x = (int) xx[1];
            if (v[x] != 0) {
                continue;
            }
            v[x] = 1;
            for (int i = head[x]; i != 0; i = edges[i].next) {
                int y = edges[i].to;
                Double z = edges[i].t;
                if (d[y] == d[x] + z) {
                    path[y].add(x);
                } else if (d[y] > d[x] + z) {
                    d[y] = d[x] + z;
                    path[y].put(x);
                }
                heap.push(d[y], y);
            }
        }
        System.out.println("最短时间为: " + d[t]);

        print(path, t, s);
        Object[] ans = new Object[2];
        ans[0] = d[t];
        ans[1] = pp;
        return ans;
    }

    public Object[] dijkstra3(String source, String target) {
        Double[] d = new Double[MAX_SIZE];
        int[] v = new int[MAX_SIZE];
        Path[] path = new Path[MAX_SIZE];
        pp = "";
        for (int i = 0; i < d.length; i++) {
            d[i] = 2147483647 * 1.0;
            v[i] = 0;
            path[i] = new Path();
        }
        int s = map.get(source), t = map.get(target);
        d[s] = 0.0;
        MinHeap heap = new MinHeap(MAX_SIZE);
        heap.push(0, s);
        while (heap.isEmpty() == false) {
            Object[] xx = heap.top();
            heap.pop();
            int x = (int) xx[1];
            if (v[x] != 0) {
                continue;
            }
            v[x] = 1;
            for (int i = head[x]; i != 0; i = edges[i].next) {
                int y = edges[i].to;
                Double z = edges[i].w;
                if (d[y] == d[x] + z) {
                    path[y].add(x);
                } else if (d[y] > d[x] + z) {
                    d[y] = d[x] + z;
                    path[y].put(x);
                }
                heap.push(d[y], y);
            }
        }
        System.out.println("最短交通工具时间为: " + d[t]);

        print(path, t, s);
        Object[] ans = new Object[2];
        ans[0] = d[t];
        ans[1] = pp;
        return ans;
    }

    public void print(Path[] p, int t, int s) {
        if (t == s) {
            pp = pp + "\r\n";
            pp = pp + places[s].getName();
        } else {
            for (int i = 0; i < p[t].getP().size(); i++) {
                print(p, p[t].getP().get(i), s);
                pp = pp + "-->" + places[t].getName();
                if(i != 0)
                    pp = pp + "\r\n";
            }

        }

    }

    public void init(String file1, String file2, int school) throws FileNotFoundException {
        readSpot(school, new File(file1));
        readEdges(new File(file2));
    }

    public void readSpot(int school, File file) throws FileNotFoundException {
        int t = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String name = scanner.next();
            int type = scanner.nextInt();
            places[t] = new Place(name, school, type == 1);
            map.put(name, t++);
        }
    }

    private void readEdges(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int tot = 0;
        while (scanner.hasNext()) {
//            System.out.println(tot);
            String s = scanner.next();
            String t = scanner.next();
            double l = scanner.nextDouble();
            double c = scanner.nextDouble();
            int vehicle = scanner.nextInt();
            int school = scanner.nextInt();
            int u = map.get(s), v = map.get(t);
            add(u, v, l, vehicle, c, tot++);
            add(v, u, l, vehicle, c, tot++);
        }
    }

    public SchoolMap(String file1, String file2, int school) {
        this.file1 = file1;
        this.file2 = file2;
        try {
            init(file1, file2, school);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public SchoolMap() {
    }

    private void add(int u, int v, double l, int vehicle, double c, int tot) {
        edges[tot] = new Edge(v, l, head[u], c, vehicle);
        head[u] = tot;
    }

}

class Path {

    private MyArrayList<Integer> p = new MyArrayList<Integer>();

    public Path() {
    }

    public Path(MyArrayList<Integer> p) {
        this.p = p;
    }

    public MyArrayList<Integer> getP() {
        return p;
    }

    public void setP(MyArrayList<Integer> p) {
        this.p = p;
    }

    public void add(int x) {
        boolean flag = false;
        for (int i = 0; i < p.size(); i++) {
            if (p.get(i) == x) {
                flag = true;
                break;
            }
        }
        if (flag == false) {
            p.add(x);
        }
    }

    public void put(int x) {
        p = new MyArrayList<>();
        p.add(x);
    }
}

class Node {
    public double d;
    public int s;

    public Node(int d, int s) {
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