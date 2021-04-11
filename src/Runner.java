import arraylist.MyArrayList;

import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        MyArrayList arrayList = new MyArrayList();
        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(-4);
        arrayList.printList();

        MyArrayList reversed = arrayList.reverse();
        reversed.printList();


    }
}
