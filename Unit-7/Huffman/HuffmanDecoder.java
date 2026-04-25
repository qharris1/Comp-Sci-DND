import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class HuffmanDecoder {
    private HashMap<String, String> paths = new HashMap<>();

    public HuffmanDecoder(String codex) {
        loadPaths(codex);
    }

    private void loadPaths(String codex) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(codex));
            String line;
            while ((line = br.readLine()) != null && !(line.isEmpty())
                    && line.lastIndexOf(' ') != -1) {
                int lastSpace = line.lastIndexOf(' ');

                paths.put(line.substring(lastSpace + 1), line.substring(0, lastSpace));
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decodeFileFromHuffmanCodes(String encodedFile, String decodedFile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(encodedFile));
            PrintWriter pw = new PrintWriter(decodedFile);

            StringBuilder segment = new StringBuilder();
            int read;
            while ((read = br.read()) != -1) {
                char c = (char) read;
                segment.append(c);

                if (isCode(segment.toString())) {
                    String decoded = decodeStr(segment.toString());

                    if (decoded.charAt(0) == (char) 26) {
                        break;
                    }

                    if (decoded.length() > 1) {
                        pw.write((char) Integer.parseInt(decoded));
                        segment.setLength(0);
                    } else {
                        pw.write(decoded);
                        segment.setLength(0);
                    }
                }
            }

            br.close();
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isCode(String binary) {
        return paths.get(binary) != null;
    }

    public char decodeChar(String binary) {
        return paths.get(binary).charAt(0);
    }

    public String decodeStr(String binary) {
        if (paths.get(binary).equals("\\n")) {
            return "\n";
        }
        return paths.get(binary);
    }
}
