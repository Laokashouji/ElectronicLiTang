package edu.map;

public class Edge {

    public static final double speed[] = {1.2, 5.0};

    int to;
    double l;
    int next;
    double crowd;
    boolean[] vehicles = new boolean[2];
    double w;
    double t;

    public Edge(int to, double l, int next, double crowd, int vehicle) {
        this.to = to;
        this.l = l;
        this.next = next;
        this.crowd = crowd;
        vehicles[0] = true;
        this.t = l/speed[0] * crowd;
        if(vehicle == 0) {
            this.vehicles[1] = false;
            this.w = this.t;
        }
        else {
            this.vehicles[1] = true;
            this.w = l/speed[1] * crowd;
        }
    }

    public int getTo() {
        return to;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public double getL() {
        return l;
    }

    public void setL(double l) {
        this.l = l;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public double getCrowd() {
        return crowd;
    }

    public void setCrowd(double crowd) {
        this.crowd = crowd;
    }

    public boolean[] getVehicles() {
        return vehicles;
    }

    public void setVehicles(boolean[] vehicles) {
        this.vehicles = vehicles;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }
}
