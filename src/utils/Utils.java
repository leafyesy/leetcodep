package src.utils;

import java.util.List;

public class Utils {

    public static <T> void printList(List<T> list) {
        System.out.print("[");
        int count = 0;
        for (T obj : list) {
            System.out.print("" + obj);
            count++;
            if (count < list.size())
                System.out.print(",");
        }
        System.out.println("]");
    }



}
