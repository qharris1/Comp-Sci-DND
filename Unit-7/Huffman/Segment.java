public class Segment {

    private int frequency;
    private String value;

    public Segment() {
        this.frequency = 0;
        this.value = "";
    }

    public Segment(String value, int frequency) {
        this.frequency = frequency;
        this.value = value;
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
}