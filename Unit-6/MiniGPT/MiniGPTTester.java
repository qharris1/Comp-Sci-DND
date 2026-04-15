public class MiniGPTTester {
    public static void main(String[] args) {

        MiniGPT gpt = new MiniGPT("thegreatgatsby.txt", 10);
        gpt.generateText("Result.txt", 2000);
        
    }
}
