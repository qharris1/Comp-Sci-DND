import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanCodeGenerator {
    private HashMap<String, String> values = new HashMap<String, String>();
    private HashMap<String, String> paths = new HashMap<String, String>();
    private PriorityQueue<Value> pq = new PriorityQueue<Value>();
    private Value root;
    private String[] codex = new String[256];

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

            createTree();
            buildCodes(root, "");

            for (int i = 0; i < codex.length; i++) {
                if (i < 26) {
                    pw.append("\\" + i + " " + codex[i] + "\n");
                } else {
                    pw.append((char) (i) + " " + codex[i] + "\n");
                }
            }

            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sortByFrequency(HashMap<String, Integer> segments) {
        Object[] keys = segments.keySet().toArray();
        Object[] values = segments.values().toArray();

        for (int i = 0; i < keys.length; i++) {
            if (keys[i].toString().charAt(0) < 26) {
                keys[i] = "" + (int) (keys[i].toString().charAt(0));
            }
            pq.add(new Value(keys[i].toString(), (Integer) (values[i])));
        }

        pq.add(new Value("" + (char) (26), 1));
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
    }

    private void buildCodes(Value node, String path) {
        if (node == null) {
            return;
        }

        if (node.getLeftChild() == null && node.getRightChild() == null) {
            try {
                if (node.getValue().length() > 1) {
                    codex[Integer.parseInt(node.getValue())] = path;
                } else {
                    codex[(int) node.getValue().charAt(0)] = path;
                }
            } catch (Exception e) {
                try {
                    codex[(int) node.getValue().charAt(0)] = path;
                } catch (Exception error) {
                }
            }
            values.put(node.getValue(), path);
            paths.put(path, node.getValue());
            return;
        }

        buildCodes(node.getLeftChild(), path + "0");
        buildCodes(node.getRightChild(), path + "1");
    }
}
