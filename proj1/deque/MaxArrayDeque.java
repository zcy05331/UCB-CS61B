package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    public T max() {
        if (isEmpty()) {
            return null;
        }
        T result = this.get(0);
        for (int i = 1; i < this.size(); i++) {
            result = this.comparator.compare(result, this.get(i)) < 0 ? this.get(i) : result;
        }
        return result;
    }
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T result = this.get(0);
        for (int i = 1; i < this.size(); i++) {
            result = c.compare(result, this.get(i)) < 0 ? this.get(i) : result;
        }
        return result;
    }
}
