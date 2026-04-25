import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanCodeGenerator {
    private static HashMap<String, String> values = new HashMap<String, String>();
    private static HashMap<String, String> paths = new HashMap<String, String>();
    private static PriorityQueue<Value> pq = new PriorityQueue<Value>();
    private static Value root;

    public static String getCode(char c) {
        return values.get("" + c);
    }

    public static String getValue(String path) {
        return paths.get(path);
    }

    public static void makeCodeFile(String codeFile) {
        try {
            PrintWriter pw = new PrintWriter("codex.txt");
            HashMap<String, Integer> segments = new HashMap<String, Integer>();
            try (BufferedReader br = new BufferedReader(new FileReader(codeFile))) {
                int character;
                while ((character = br.read()) != -1) {
                    char c = (char) character;
                    if (segments.get("" + c) == null) {
                        segments.put("" + c, 1);
                    } else {
                        segments.put("" + c, segments.get("" + c) + 1);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            sortByFrequency(segments);
            createTree(pw);

            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sortByFrequency(HashMap<String, Integer> segments) {
        Object[] keys = segments.keySet().toArray();
        Object[] values = segments.values().toArray();

        for (int i = 0; i < keys.length; i++) {
            pq.add(new Value(keys[i].toString(), (Integer) (values[i])));
        }

        pq.add(new Value(null, 1));
    }

    private static void createTree(PrintWriter pw) {
        while (!(pq.size() <= 1)) {
            Value left = pq.poll(), right = pq.poll(), parent =
                    new Value(left.getFrequency() + right.getFrequency(), null, left, right);
            left.setParent(parent);
            right.setParent(parent);
            pq.add(parent);
        }

        root = pq.poll();

        buildCodes(root, "", pw);
    }

    private static void buildCodes(Value node, String path, PrintWriter pw) {
        if (node == null) {
            return;
        }

        if (node.getLeftChild() == null && node.getRightChild() == null) {
            if (node.getValue() == null) {
                node.setValue("EOF");
            }
            pw.append(node.getValue() + " " + path + '\n');
            values.put(node.getValue(), path);
            paths.put(path, node.getValue());
            return;
        }

        buildCodes(node.getLeftChild(), path + "0", pw);
        buildCodes(node.getRightChild(), path + "1", pw);
    }
}
