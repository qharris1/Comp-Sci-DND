import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class HuffmanDecoder {
    public static void decodeFile(String encodedFile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(encodedFile));
            PrintWriter pw = new PrintWriter("decode.txt");

            StringBuilder segment = new StringBuilder();
            int read;
            while ((read = br.read()) != -1) {
                char c = (char) (read);
                String toString = segment.toString();
                if (HuffmanCodeGenerator.getValue(toString) == null) {
                    segment.append(c);
                } else {
                    pw.write(HuffmanCodeGenerator.getValue(toString));
                    segment = new StringBuilder().append(c);
                }
            }

            if (HuffmanCodeGenerator.getValue(segment.toString()) != null) {
                pw.write(HuffmanCodeGenerator.getValue(segment.toString()));
            }

            br.close();
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
