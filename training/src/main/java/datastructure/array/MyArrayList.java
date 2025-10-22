package datastructure.array;


import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 * @author luolinyuan
 */
public class MyArrayList<E> {
    /**
     * 数据数组
     */
    private E[] data;
    
    /**
     * 数组大小
     */
    private int size = 0;
    
    /**
     * 初始数组大小
     */
    private static final int INIT_CAP = 10;
    
    public MyArrayList() {
        this(INIT_CAP);
    }

    public MyArrayList(int capacity) {
        this.data = (E[]) new Object[capacity];
    }
    
    /**
     * 在末尾加一个元素
     * @param e     待加入元素
     */
    public void addLast(E e) {
        int cap = this.data.length;
        if (this.size == cap) {
            resize(2 * cap);
        }
        
        HashTable table=  new Hashtable<>()
        this.data[size] = e;
        this.size++;
    }
    
    public void add(int index, E e) {
        checkPositionIndex(index);
        
        int cap = this.data.length;
        if (this.size == cap) {
            resize(cap * 2);
        }
        
        // 从index位置开始 往后挪
        for (int i = size - 1; i >= index; i--) {
            this.data[i + 1] = data[i];
        }
        this.data[index]  = e;
        this.size++;
    }
    
    public void addAtFirst(E e) {
        this.add(0, e);
    }
    
    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        
        int cap = this.data.length;
        if (size == cap / 4) {
            resize(cap / 2);
        }
        E deletedVal = this.data[size - 1];
        //  置为null 防止内存泄漏
        this.data[size - 1] = null;
        this.size--;
        return deletedVal;
    }
    
    public E remove(int index) {
        checkElementIndex(index);
        
        int cap = this.data.length;
        if (this.size == cap / 4) {
            resize(cap / 2);
        }
        E deletedVal = this.data[index];
        for (int i = index + 1; i < size; i++) {
            this.data[i - 1] = this.data[i];
        }
        this.data[size] = null;
        this.size--;
        return deletedVal;
    }
    
    
    public E getFirst() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        return this.data[0];
    }
    
    public E get(int index) {
        checkElementIndex(index);
        return this.data[index];
    }
    
    public int size() {
        return this.size;
    }
    
    
    /**
     * 调整数组大小
     *
     * @param newCap    新的容量
     */
    public void resize(int newCap) {
        E[] newData = (E[]) new Object[newCap];
        for (int i = 0 ; i < data.length; i++) {
            newData[i] = data[i];
        }
        this.data = newData;
    }
    
    
    public void checkElementIndex(int index) {
        if (index < 0 || index >= data.length) {
            throw new ArrayIndexOutOfBoundsException("index: "+ index + " out of bounds of array");
        }
    }
    
    public void checkPositionIndex(int index) {
        if (index < 0 || index > data.length) {
            throw new ArrayIndexOutOfBoundsException("position : " + index + " out of bounds of array");
        }
    }
    
    public static void main(String[] args) {
        // 初始容量设置为 3
        MyArrayList<Integer> arr = new MyArrayList<>(3);
        
        // 添加 5 个元素
        for (int i = 1; i <= 5; i++) {
            arr.addLast(i);
        }
        
        arr.remove(3);
        arr.add(1, 9);
        arr.addAtFirst(100);
        int val = arr.removeLast();
        
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }
    }
}