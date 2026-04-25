public class HuffmanTester {
    public static void main(String[] args) {
        HuffmanEncoder encoder = new HuffmanEncoder("helloWorld.txt");

        encoder.encodeFileToHuffmanCodes("helloWorld.txt", "encoded.txt");

        HuffmanDecoder decoder = new HuffmanDecoder("codex.txt");

        decoder.decodeFileFromHuffmanCodes("encoded.txt", "decoded.txt");

        // System.out.println(comp.getRoot().getFrequency());

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
