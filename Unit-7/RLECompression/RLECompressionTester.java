import java.io.IOException;

public class RLECompressionTester {
    public static void main(String[] args) {
        try {
            // RLECompression.encode("HelloWorld.txt");
            RLECompression.compress("HelloWorld.txt");
            // RLECompression.decode("HelloWorld.txt.bw.rle");
            RLECompression.decompress("HelloWorld.txt.bw.rle");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
