import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class HuffmanDecoder {
    private HashMap<String, String> paths = new HashMap<>();
    private HashMap<String, String> values = new HashMap<>();

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
                values.put(line.substring(0, lastSpace), line.substring(lastSpace + 1));
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
                    System.out.println(decoded);

                    if (decoded.charAt(0) == (char) 26) {
                        break;
                    }

                    if (decoded.charAt(0) == '\\') {
                        pw.write(Integer.parseInt(decoded.substring(1)));
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

    public void decodeFile(String encodedFile) {
        if (!encodedFile.endsWith(".huf")) {
            throw new IllegalArgumentException("Must end in .huf");
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(encodedFile));
            PrintWriter pw = new PrintWriter(encodedFile.substring(0, encodedFile.length()-4));

            int read;
            while ((read = br.read()) != -1) {
                String binary = charToBinary((char) read);
                pw.write(binary);
            }

            br.close();
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        decodeFileFromHuffmanCodes(encodedFile.substring(0, encodedFile.length()-4), encodedFile.substring(0, encodedFile.length()-15) + ".txt");
    }

    private boolean isCode(String binary) {
        return paths.get(binary) != null;
    }

    private String decodeStr(String binary) {
        if (paths.get(binary).equals("\\n")) {
            return "\n";
        }
        return paths.get(binary);
    }

    public String charToBinary(char c) {
        String binary = Integer.toBinaryString(c);

        while (binary.length() < 8) {
            binary = "0" + binary;
        }

        return binary;
    }
}
