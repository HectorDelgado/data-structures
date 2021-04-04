public class SLLNode {
    private int data;
    private SLLNode next;

    SLLNode(int data) {
        this.data = data;
        next = null;
    }

    public int getData() {
        return data;
    }

    public SLLNode getNext() {
        return next;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setNext(SLLNode next) {
        this.next = next;
    }
}
