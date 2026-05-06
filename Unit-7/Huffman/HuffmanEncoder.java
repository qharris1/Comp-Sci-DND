import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class HuffmanEncoder {
    public HashMap<String, String> paths = new HashMap<>();
    public HashMap<String, String> values = new HashMap<>();

    public HuffmanEncoder(String codeFile) {
        loadPaths(codeFile);
    }

    public void loadPaths(String codex) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(codex));
            String line;
            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    lineCount++;
                    continue;
                }
                paths.put(line, "" + (char) (lineCount));
                values.put("" + (char) (lineCount), line);
                lineCount++;
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void encodeFileToHuffmanCodes(String fileToCompress, String encodedFile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileToCompress));
            PrintWriter pw = new PrintWriter(encodedFile);

            int length = 0;
            int read;
            while ((read = br.read()) != -1) {
                char c = (char) (read);
                pw.write(encodeChar(c));
                length += encodeChar(c).length();
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

    public void encodeLong(String fileToCompress, String encodedFile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileToCompress));
            PrintWriter pw = new PrintWriter(encodedFile);

            int length = 0;
            int read;
            while ((read = br.read()) != -1) {
                char c = (char) (read);
                pw.write(encodeChar(c));
                length += encodeChar(c).length();
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
        String output = fileToCompress.substring(0, fileToCompress.length() - 4) + "Encoded.txt";
        encodeFileToHuffmanCodes(fileToCompress, output);
        try {
            BufferedReader br = new BufferedReader(new FileReader(output));
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

    public char binaryToChar(String binary) {
        int value = 0;
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                value += (int) Math.pow(2, binary.length() - 1 - i);
            }
        }
        return (char) value;
    }

    public String encodeChar(char input) {
        return values.get("" + input) != null ? values.get("" + input) : "";
    }

    public String encodeString(String input) {
        return values.get("" + input) != null ? values.get("" + input) : "";
    }
}
