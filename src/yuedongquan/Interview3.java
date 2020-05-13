package yuedongquan;

import java.io.File;
import java.util.Stack;

public class Interview3 {

    private static volatile Stack<File> stack = new Stack<>();

    public static void main(String[] args) {

    }


    private static void findAllDir(File file) {
        File[] files = file.listFiles();
        if (files == null) return;
        for (File nextFile : files) {
            if (nextFile.isDirectory()) {
                stack.push(nextFile);
            }
        }
    }


}
