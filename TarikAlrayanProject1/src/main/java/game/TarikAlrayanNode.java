package game;

/** 
* @file Project1
* @description game to deal with liked list
* @assignment 1. 
* @date  31/03/2022 
* @author yazar TARIK ALRAYAN tarik.alrayan@fsm.stu.edu.tr
*/

public class TarikAlrayanNode<T> {

    private T value;
    private int columnIndex;
    private TarikAlrayanNode<T> next;
    private TarikAlrayanNode<T> up;

    public TarikAlrayanNode(T value, int column) {
        this.value = value;
        this.columnIndex = column;
    }

    public TarikAlrayanNode<T> getNext() {
        return next;
    }

    public void setNext(TarikAlrayanNode<T> next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public TarikAlrayanNode<T> getUp() {
        return up;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public void setUp(TarikAlrayanNode<T> up) {
        this.up = up;
    }

}
