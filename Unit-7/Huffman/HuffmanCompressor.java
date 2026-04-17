import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class HuffmanCompressor {
    private HashMap<String, Integer> segments = new HashMap<String, Integer>();

    public HashMap<String, Integer> readData(String filePath, int cutLength) {
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
        return segments;
    }

    public HashMap<String, Integer> getSegments() {
        return segments;
    }

    // public Segment[] sortByFrequency(){
        
    // }
}
