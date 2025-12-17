public class SortedArrayList<E extends Comparable<E>> extends MyArrayList<E> {

    public SortedArrayList() {
        super();
    }

    public SortedArrayList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean contains(E obj) {
        return binarySearchSpecific(obj) != -1;
    }

    @Override
    public boolean add(E obj) {
        if (binarySearchIndex(obj) != -1) {
            super.add(binarySearchIndex(obj), obj);
            return true;
        } else {
            super.add(obj);
            return true;
        }
    }

    @Override
    public boolean remove(E obj) {
        if (contains(obj)) {
            super.remove(binarySearchIndex(obj));
            return true;
        }
        return false;
    }

    public E min() {
        return super.get(0);
    }

    public E max() {
        return super.get(super.size() - 1);
    }

    private int binarySearchSpecific(Comparable<E> obj) {
        int left = 0, right = super.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (super.get(mid).equals(obj)) {
                return mid;
            } else if (obj.compareTo(super.get(mid)) > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    private int binarySearchIndex(Comparable<E> obj) {
        int left = 0, right = super.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (super.get(mid).equals(obj)) {
                return mid;
            } else if (obj.compareTo(super.get(mid)) > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}