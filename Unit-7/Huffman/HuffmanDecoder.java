import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class HuffmanDecoder {
    public HashMap<String, String> paths = new HashMap<>();
    public HashMap<String, String> values = new HashMap<>();

    public HuffmanDecoder(String codeFile) {
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
                    String decoded = "" + decodeChar(segment.toString());

                    if (decoded.charAt(0) == (char) 26) {
                        break;
                    }
                    pw.write(decoded);
                    segment.setLength(0);
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
            PrintWriter pw = new PrintWriter(encodedFile.substring(0, encodedFile.length() - 4));

            StringBuilder segment = new StringBuilder();
            int read;

            while ((read = br.read()) != -1) {
                String binary = charToBinary((char) read);

                for (int i = 0; i < binary.length(); i++) {
                    segment.append(binary.charAt(i));

                    if (isCode(segment.toString())) {
                        String decoded = "" + decodeChar(segment.toString());

                        if (decoded.charAt(0) == (char) 26) {
                            br.close();
                            pw.close();
                            return;
                        }

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

    public Character decodeChar(String binary) {
        return paths.get(binary) == null ? 0 : paths.get(binary).charAt(0);
    }

    public String charToBinary(char c) {
        String binary = Integer.toBinaryString(c);

        while (binary.length() < 8) {
            binary = "0" + binary;
        }

        return binary;
    }
}
