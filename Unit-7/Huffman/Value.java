@SuppressWarnings("rawtypes")
public class Value implements Comparable {

    private int frequency;
    private String value;
    private Value parent;
    private Value leftChild;
    private Value rightChild;

    public Value(int frequency, Value parent, Value leftChild, Value rightChild) {
        this.frequency = frequency;
        this.value = null;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Value(String value, int frequency) {
        this.frequency = frequency;
        this.value = value;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Value getParent() {
        return parent;
    }

    public void setParent(Value parent) {
        this.parent = parent;
    }

    public Value getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Value leftChild) {
        this.leftChild = leftChild;
    }

    public Value getRightChild() {
        return rightChild;
    }

    public void setRightChild(Value rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public int compareTo(Object o) {
        Value other = (Value) (o);
        return getFrequency() - other.getFrequency();
    }
}