public class HuffmanTester {
    public static void main(String[] args) {
        HuffmanCodeGenerator comp = new HuffmanCodeGenerator();

        comp.encode("helloWorld.txt");

        System.out.println(comp.getRoot().getFrequency());

        // System.out.println("Getting A: " + comp.getSegments().get("a"));
        // System.out.println("Getting B: " + comp.getSegments().get("b"));
        // System.out.println("Getting C: " + comp.getSegments().get("c"));
        // System.out.println("Getting D: " + comp.getSegments().get("d"));
        // System.out.println("Getting E: " + comp.getSegments().get("e"));
        // System.out.println("Getting F: " + comp.getSegments().get("f"));
        // System.out.println("Getting G: " + comp.getSegments().get("g"));
        // System.out.println("Getting H: " + comp.getSegments().get("h"));

    }
}
