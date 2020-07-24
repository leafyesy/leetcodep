package src.interview;

public class InterviewArrayCopy {

    public static <T> void arrayCopy(T[] srcArr, T[] destArr, int srcStart, int destStart, int count) {
        if (srcStart < 0) {
            throw new IllegalArgumentException("srcStart < 0");
        }
        if (srcStart + count > srcArr.length) {
            throw new IllegalArgumentException("srcStart + count > maxLength");
        }
        if (destStart < 0) {
            throw new IllegalArgumentException("destStart < 0");
        }
        if (destStart + count > destArr.length) {
            throw new IllegalArgumentException("destArr + count > maxLength");
        }
        for (int i = srcStart; i <= count; i++) {
            destArr[destStart + i] = srcArr[srcStart + i];
        }
    }

}
