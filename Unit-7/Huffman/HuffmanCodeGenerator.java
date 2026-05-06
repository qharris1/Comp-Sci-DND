import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanCodeGenerator {
    public HashMap<String, String> values = new HashMap<String, String>();
    public HashMap<String, String> paths = new HashMap<String, String>();
    public PriorityQueue<Value> pq = new PriorityQueue<Value>();
    public Value root;
    public String[] codex = new String[256];

    public int getFrequency(char c) {
        return getFrequencyHelper(root, c);
    }

    private int getFrequencyHelper(Value node, char c) {
        if (node == null) {
            return 0;
        }

        if (node.getLeftChild() == null && node.getRightChild() == null) {
            if (node.getValue() != null && node.getValue().charAt(0) == c) {
                return node.getFrequency();
            }
            return 0;
        }

        int left = getFrequencyHelper(node.getLeftChild(), c);
        if (left != 0) {
            return left;
        } else {
            return getFrequencyHelper(node.getRightChild(), c);
        }
    }

    public HuffmanCodeGenerator(String frequencyFile) {
        HashMap<String, Integer> segments = new HashMap<String, Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader(frequencyFile))) {
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
        createTree();
        buildCodes(root, "");
    }

    public String getCode(char c) {
        return values.get("" + c) != null ? values.get("" + c) : "";
    }

    public String getValue(String path) {
        return paths.get(path) != null ? paths.get(path) : "";
    }

    public HashMap<String, String> getValues() {
        return values;
    }

    public HashMap<String, String> getPaths() {
        return paths;
    }

    public void makeCodeFile(String codeFile) {
        try {
            PrintWriter pw = new PrintWriter(codeFile);

            for (int i = 0; i < codex.length; i++) {
                if (codex[i] == null) {
                    codex[i] = "";
                }
                pw.append(codex[i] + "\n");
            }

            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortByFrequency(HashMap<String, Integer> segments) {
        Object[] keys = segments.keySet().toArray();
        Object[] values = segments.values().toArray();

        for (int i = 0; i < keys.length; i++) {
            // if (keys[i].toString().charAt(0) < 26) {
            //     keys[i] = "" + (int) (keys[i].toString().charAt(0));
            // }
            pq.add(new Value(keys[i].toString(), (Integer) (values[i])));
        }

        pq.add(new Value("" + (char) (26), 1));
    }

    public void createTree() {
        while (!(pq.size() <= 1)) {
            Value left = pq.poll(), right = pq.poll(), parent =
                    new Value(left.getFrequency() + right.getFrequency(), null, left, right);
            left.setParent(parent);
            right.setParent(parent);
            pq.add(parent);
        }

        root = pq.poll();
    }

    public void buildCodes(Value node, String path) {
        if (node == null) {
            return;
        }

        if (node.getLeftChild() == null && node.getRightChild() == null) {
            codex[(int) node.getValue().charAt(0)] = path;
            values.put(node.getValue(), path);
            paths.put(path, node.getValue());
            return;
        }

        buildCodes(node.getLeftChild(), path + "0");
        buildCodes(node.getRightChild(), path + "1");
    }
}
