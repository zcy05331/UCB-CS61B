package tester;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import static org.junit.Assert.assertEquals;


public class TestArrayDequeEC {
    StringBuilder result = new StringBuilder();
    private final ArrayDequeSolution<Integer> op = new ArrayDequeSolution<>();
    private final ArrayDequeSolution<Integer> number = new ArrayDequeSolution<>();

    private void appendMessage(int i) {
        int n = op.size();
        if (op.get(i) == 0) {
            result.append("addLast(").append(number.get(i)).append(")");
            result.append("\n");
        } else if (op.get(i) == 1) {
            result.append("addFirst(").append(number.get(i)).append(")");
            result.append("\n");
        } else if (op.get(i) == 2 && number.get(i) != -1) {
            result.append("removeFirst()");
            result.append("\n");
        } else if (op.get(i) == 3 && number.get(i) != -1) {
            result.append("removeLast()");
            result.append("\n");
        }
    }

    @Test
    public void test() {
        StudentArrayDeque sd = new StudentArrayDeque();
        ArrayDequeSolution<Integer> ad = new ArrayDequeSolution<>();

        int n = StdRandom.uniform(100, 150);
        for (int i = 0; i < n; i++) {
            int type = StdRandom.uniform(4);
            op.addLast(type);
            if (type == 0) {
                int num = StdRandom.uniform(n);
                ad.addLast(num);
                sd.addLast(num);
                number.addLast(num);
                appendMessage(i);
            } else if (type == 1) {
                int num = StdRandom.uniform(n);
                ad.addFirst(num);
                sd.addFirst(num);
                number.addLast(num);
                appendMessage(i);
            } else if (type == 2) {
                if (ad.size() == 0) {
                    number.addLast(-1);
                    appendMessage(i);
                    continue;
                } else if (sd.size() == 0) {
                    number.addLast(0);
                    appendMessage(i);
                    assertEquals(result.toString(), ad.size(), sd.size());
                    continue;
                }
                int A = ad.removeFirst();
                int S = (int) sd.removeFirst();
                number.addLast(A);
                appendMessage(i);
                assertEquals(result.toString(), A, S);
            } else if (type == 3) {
                if (ad.size() == 0) {
                    number.addLast(-1);
                    appendMessage(i);
                    continue;
                } else if (sd.size() == 0) {
                    number.addLast(0);
                    appendMessage(i);
                    assertEquals(result.toString(), ad.size(), sd.size());
                    continue;
                }
                int A = ad.removeLast();
                int S = (int) sd.removeLast();
                number.addLast(A);
                appendMessage(i);
                assertEquals(result.toString(), A, S);
            }
        }
    }
}
