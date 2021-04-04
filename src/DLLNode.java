public class DLLNode {
    private int data;
    private DLLNode prev;
    private DLLNode next;

    DLLNode(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    public int getData() {
        return data;
    }

    public DLLNode getPrev() {
        return prev;
    }

    public DLLNode getNext() {
        return next;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setPrev(DLLNode prev) {
        this.prev = prev;
    }

    public void setNext(DLLNode next) {
        this.next = next;
    }
}
