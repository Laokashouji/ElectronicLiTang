package edu.datastructure;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class MyArrayList<E> implements MyList<E>, Serializable {

    @Serial
    private static final long serialVersionUID = 1234452581122892189L;

    private static final int MIN_SIZE = 100;
    private static final int EXPAND_SIZE = 50;

    private int size;
    private Object[] arr;

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean checkRange(int index) {
        if(index < 0 || index >= size)
            return false;
        else return true;
    }

    public MyArrayList() {
        arr = new Object[this.MIN_SIZE];
        size = 0;
    }

    public MyArrayList(ArrayList<E> arrayList) {
        arr = new Object[this.MIN_SIZE];
        for (int i = 0; i < arrayList.size(); i++) {
            add(arrayList.get(i));
        }
    }

    public MyArrayList(int length) {
        if(length < 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(length < MIN_SIZE) {
            length = MIN_SIZE;
        }
        arr = new Object[length];
        size = 0;
    }

    public MyArrayList(Object[] arr) {
        this.size = arr.length;
        this.arr = new Object[this.size];
        for (int i = 0; i < this.size; i++) {
            this.arr[i] = arr[i];
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    public void expand(){
        Object[] newArr = new Object[this.size + EXPAND_SIZE];
        for (int i = 0; i < this.size; i++) {
            newArr[i] = this.arr[i];
        }
        arr = newArr;
    }

    @Override
    public void add(E value) {
        if(size >= arr.length) {
            expand();
        }
        arr[size++] = value;
    }

    @Override
    public void addAll(MyList list) {
        while (size + list.size() >= arr.length) {
            expand();
        }
        for (int i = 0; i < list.size(); i++) {
            arr[size++] = list.get(i);
        }
    }

    @Override
    public E get(int index) {
        if(this.checkRange(index) != false)
            return (E)arr[index];
        else return null;
    }

    @Override
    public int[] get(E value) {
        int[] ret = new int[size];
        int index = 0;
        for(int i = 0; i < size; i++) {
            if(arr[i] == value) {
                ret[index] = i;
                index++;
            }
        }
        int[] ans = new int[index];
        for (int i = 0; i < index; i++) {
            ans[i] = ret[i];
        }
        return ans;
    }

    @Override
    public Object[] getAll(){
        Object[] ret = new Object[size];
        for (int i = 0; i < size; i++) {
            ret[i] = arr[i];
        }
        return ret;
    }

    @Override
    public void remove(int index) {
        if(this.checkRange(index) == true) {
            Object[] newArr = new Object[arr.length-1];
            int cnt = 0;
            for (int i = 0; i < arr.length; i++) {
                if (i == index) {
                    continue;
                }
                newArr[cnt++] = arr[i];
            }
            this.size -= 1;
            arr = newArr;
        }
    }

    @Override
    public void remove(E value) {
        for(int i = 0; i < this.size; i++) {
            if(arr[i].equals(value)) {
                this.remove(i);
            }
        }
    }

    @Override
    public void removeAll() {
        arr = new Object[0];
        this.size = 0;
    }

    @Override
    public void set(int index, E value) {
        if(this.checkRange(index) == true) {
            arr[index] = value;
        }
    }

    @Override
    public void set(E repobj, E value) {
        for(int i = 0 ; i < this.size; i++) {
            if(arr[i].equals(repobj)) {
                arr[i] = value;
            }
        }
    }

    @Override
    public MyArrayList<E> slices(int a, int b) {
        if(a < 0 || b > size)
            return null;
        Object[] newArr = new Object[b - a];
        for (int i = 0; i < b - a; i++) {
            newArr[i] = arr[a + i];
        }
        return new MyArrayList<E>(newArr);
    }

    public void delete(E name) {
        if(size > 0){
            for (int i = 0; i < size; i++) {
                if(((E)arr[i]).equals(name)){
                    Object[] newArr = new Object[size - 1];
                    int t = 0;
                    for (int j = 0; j < size; j++) {
                        if(i != j)
                            newArr[t++] = arr[j];
                    }
                    size--;
                    break;
                }
            }
        }
    }
}