import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class HuffmanEncoder {
    public static void encodeFile(String fileToCompress) {
        HuffmanCodeGenerator.makeCodeFile(fileToCompress);

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileToCompress));
            PrintWriter pw = new PrintWriter("encoded.txt");

            int read;
            while ((read = br.read()) != -1) {
                char c = (char) (read);
                pw.write(HuffmanCodeGenerator.getCode(c));
            }

            br.close();
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
