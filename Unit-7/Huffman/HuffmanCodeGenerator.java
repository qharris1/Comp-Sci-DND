import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanCodeGenerator {
    private HashMap<String, String> codes = new HashMap<String, String>();
    private PriorityQueue<Value> pq = new PriorityQueue<Value>();
    private Value root;

    public Value getRoot() {
        return root;
    }

    private void readData(String filePath, int cutLength) {
        HashMap<String, Integer> segments = new HashMap<String, Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int character;
            String str = "";

            while ((character = br.read()) != -1) {
                str += (char) character;
                if (str.length() == cutLength) {
                    if (segments.get(str) == null) {
                        segments.put(str, 1);
                    } else {
                        segments.put(str, segments.get(str) + 1);
                    }
                    str = "";
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        sortByFrequency(segments);
        createTree();
    }

    private void sortByFrequency(HashMap<String, Integer> segments) {
        Object[] keys = segments.keySet().toArray();
        Object[] values = segments.values().toArray();

        for (int i = 0; i < keys.length; i++) {
            pq.add(new Value(keys[i].toString(), (Integer) (values[i])));
        }

        pq.add(new Value(null, 1));
    }

    private void createTree() {
        while (!(pq.size() <= 1)) {
            Value left = pq.poll(), right = pq.poll(),
                    parent = new Value(left.getFrequency() + right.getFrequency(), null, left, right);
            left.setParent(parent);
            right.setParent(parent);
            pq.add(parent);
        }

        root = pq.poll();

        buildCodes(root, "");
    }

    private void buildCodes(Value node, String path) {
        if (node == null) {
            return;
        }

        if (node.getLeftChild() == null && node.getRightChild() == null) {
            codes.put(node.getValue(), path);
            return;
        }

        buildCodes(node.getLeftChild(), path + "0");
        buildCodes(node.getRightChild(), path + "1");
    }

    public void encode(String filePath) {
        readData(filePath, 1);

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            PrintWriter pw = new PrintWriter("encoded.txt");

            int read;
            while ((read = br.read()) != -1) {
                char c = (char) (read);
                pw.write(codes.get("" + c));
            }

            br.close();
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
