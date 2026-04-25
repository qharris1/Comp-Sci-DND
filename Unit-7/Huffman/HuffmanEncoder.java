import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class HuffmanEncoder {
    private HashMap<String, String> values = new HashMap<String, String>();
    private HuffmanCodeGenerator gen;

    public HuffmanEncoder(String codeFile) {
        gen = new HuffmanCodeGenerator(codeFile);
        values = gen.getValues();
    }

    public void encodeFileToHuffmanCodes(String fileToCompress, String encodedFile) {
        gen.makeCodeFile("codex.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileToCompress));
            PrintWriter pw = new PrintWriter(encodedFile);

            int length = 0;
            int read;
            while ((read = br.read()) != -1) {
                char c = (char) (read);
                if (c < 26) {
                    pw.write(encodeString("" + (int) (c)));
                    length += encodeString("" + (int) (c)).length();
                } else {
                    pw.write(encodeChar(c));
                    length += encodeChar(c).length();
                }
            }

            String eof = encodeChar((char) 26);
            pw.write(eof);
            length += eof.length();

            while (length % 8 != 0) {
                pw.write("0");
                length++;
            }

            br.close();
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String encodeChar(char input) {
        return values.get("" + input) != null ? values.get("" + input) : "";
    }

    public String encodeString(String input) {
        return values.get("" + input) != null ? values.get("" + input) : "";
    }
}
