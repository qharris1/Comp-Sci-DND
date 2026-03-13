public class MiniGPTTester {
    public static void main(String[] args) {
        MiniGPT bill = new MiniGPT("missile.txt", 7);
        bill.generateText("al.txt", 8000);
    }
}
