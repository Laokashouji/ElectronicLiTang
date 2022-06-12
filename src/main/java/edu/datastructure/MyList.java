package edu.datastructure;

public interface MyList<E> {

    int size();

    void add(E value);

    void addAll(MyList list);

    Object get(int index);

    int[] get(E value);

    Object[] getAll();

    void remove(int index);

    void remove(E value);

    void removeAll();

    void set(int index,E value);

    void set(E repobj,E value);

    MyArrayList<E> slices(int a, int b);
}
