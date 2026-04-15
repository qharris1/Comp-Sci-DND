import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RLECompression {

    // Creates a compressed version with fileName + ".rle.bw"
    public static void compress(String fileName) throws IOException {
        bwTransform(fileName);
        encode(fileName + ".bw");
    }

    // Decompresses a .rle.bw file
    public static void decompress(String fileName) throws IOException {
        decode(fileName);
        invertBWTransform(fileName.substring(0, fileName.length() - 4));
    }

    // Encodes the contents of a file using the RLE compression scheme:
    // single characters are left alone, and runs of 2+ characters are encoded as
    // that letter twice, followed by the length of the run, cast as a char
    public static void encode(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        PrintWriter pw = new PrintWriter(fileName + ".rle");

        char previousChar = (char) br.read();
        int count = 1;

        while (br.ready()) {
            char c = (char) br.read();
            if (previousChar != c) {
                if (count > 1) {
                    pw.write("" + count);
                    count = 1;
                }
                pw.write("" + previousChar);
            } else {
                count++;
            }
            previousChar = c;
        }

        if (count > 1) {
            pw.write("" + count);
            count = 1;
        }
        pw.write("" + previousChar);

        br.close();
        pw.close();
    }

    // Decodes the above scheme
    public static void decode(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        PrintWriter pw = new PrintWriter(fileName.substring(0, fileName.length() - 4));

        char previousChar = (char) br.read();
        int count = 0;

        while (br.ready()) {
            char c = (char) br.read();
            if ((previousChar > 47 && previousChar < 58) && !((c > 47 && c < 58) || c <= 32)) {
                count += Integer.parseInt("" + previousChar);
            } else {
                for (int i = 0; i < (count != 0 ? count : 1); i++) {
                    pw.write(previousChar);
                }
                count = 0;
            }
            previousChar = c;
        }

        for (int i = 0; i < (count != 0 ? count : 1); i++) {
            pw.write(previousChar);
        }

        br.close();
        pw.close();
    }

    public static void bwTransform(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        // Add a null character at the beginning, as a
        // "beginning of file" character
        StringBuilder originalText = new StringBuilder("" + '\0');

        while (br.ready()) {
            char c = (char) br.read();
            originalText.append(c);
        }
        br.close();

        ArrayList<String> rotations = new ArrayList<String>();
        for (int i = 0; i < originalText.length(); i++) {
            char front = originalText.charAt(0);
            originalText.delete(0, 1).append(front);
            rotations.add(originalText.toString());
        }

        rotations.sort(String::compareToIgnoreCase);

        // And then write the transformation into a file
        PrintWriter pw = new PrintWriter(fileName + ".bw");

        for (String rotation : rotations) {
            pw.write(rotation.charAt(rotation.length() - 1));
        }

        pw.close();
    }

    public static void invertBWTransform(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        StringBuilder originalText = new StringBuilder();

        while (br.ready()) {
            char c = (char) br.read();
            originalText.append(c);
        }
        br.close();

        StringBuilder[] reconstructions = new StringBuilder[originalText.length()];
        for (int i = 0; i < reconstructions.length; i++) {
            reconstructions[i] = new StringBuilder("" + originalText.charAt(i));
        }

        for (int i = 1; i < reconstructions.length; i++) {
            reconstructions = sortReconstructions(reconstructions);
            for (int j = 0; j < originalText.length(); j++) {
                reconstructions[j] = new StringBuilder().append(originalText.charAt(j))
                        .append(reconstructions[j]);
            }

        }

        PrintWriter pw = new PrintWriter(fileName.substring(0, fileName.length() - 3));

        for (int i = 0; i < reconstructions.length; i++) {
            if (reconstructions[i].charAt(0) == '\0') {
                pw.write(reconstructions[i].toString().substring(1));
                break;
            }
        }

        pw.close();
    }

    private static StringBuilder[] sortReconstructions(StringBuilder[] reconstructions) {
        ArrayList<String> recons = new ArrayList<String>();
        for (int i = 0; i < reconstructions.length; i++) {
            recons.add(reconstructions[i].toString());
        }
        recons.sort(String::compareToIgnoreCase);
        StringBuilder[] sorted = new StringBuilder[recons.size()];
        for (int i = 0; i < recons.size(); i++) {
            sorted[i] = new StringBuilder(recons.get(i));
        }
        return sorted;
    }
}
