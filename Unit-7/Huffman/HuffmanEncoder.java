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
        gen.makeCodeFile(encodedFile.substring(0, encodedFile.length() - 4) + "Codex.txt");

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

    public void encodeFile(String fileToCompress) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileToCompress));
            PrintWriter pw = new PrintWriter(fileToCompress + ".huf");

            StringBuilder segment = new StringBuilder();
            int read;
            while ((read = br.read()) != -1) {
                segment.append((char) read);
                if (segment.length() == 8) {
                    pw.write(binaryToChar(segment.toString()));
                    segment.setLength(0);
                }
            }

            br.close();
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private char binaryToChar(String binary) {
        int value = 0;
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                value += (int) Math.pow(2, binary.length() - 1 - i);
            }
        }
        return (char) value;
    }

    private String encodeChar(char input) {
        return values.get("" + input) != null ? values.get("" + input) : "";
    }

    private String encodeString(String input) {
        return values.get("" + input) != null ? values.get("" + input) : "";
    }
}
