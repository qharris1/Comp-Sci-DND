import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        ArrayList<Dog> list = new ArrayList<>();
        list.add(new Dog("Yari"));
        list.add(new Dog("Carter", "evil"));
        list.add(new Dog("Taj"));
        list.add(new Dog("Dylan", "evil"));
        list.add(new Dog("Morgan", "evil"));
        list.add(new Dog("Jackson", "evil"));
        list.add(new Dog("Felicia"));

        PugSaver.rescueGolden(list);

        for (Dog dog : list) {
            System.out.println(dog.getName());
        }
    }
}
