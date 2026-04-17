import java.util.HashMap;

public class HuffmanTester {
    public static void main(String[] args) {
        HuffmanCompressor comp = new HuffmanCompressor();
        HashMap<String, Integer> map = comp.readData("frequencyCountInput.txt", 1);

        System.out.println("Getting A: " + map.get("a"));
        System.out.println("Getting B: " + map.get("b"));
        System.out.println("Getting C: " + map.get("c"));
        System.out.println("Getting D: " + map.get("d"));
        System.out.println("Getting E: " + map.get("e"));
        System.out.println("Getting F: " + map.get("f"));
        System.out.println("Getting G: " + map.get("g"));
        System.out.println("Getting H: " + map.get("h"));
    }
}
