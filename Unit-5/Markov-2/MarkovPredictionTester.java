public class MarkovPredictionTester {
    public static void main(String[] args) {
        MarkovPrediction yari = new MarkovPrediction();
        System.out.println(yari.readData("activites.csv"));
        for (int i = 0; i < 20; i++) {
            System.out.println(yari.predictNextState("Sleeping"));
        }
    }
}
