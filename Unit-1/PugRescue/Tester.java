public class Tester {
    public static void main(String[] args) {
        MyArrayList<Dog> list = new MyArrayList<Dog>();
        list.add(new Dog("Yari", "Golden"));
        list.add(new Dog("Carter", "evil"));
        list.add(new Dog("Taj", "Golden"));
        list.add(new Dog("Dylan", "evil"));
        list.add(new Dog("Morgan", "evil"));
        list.add(new Dog("Jackson", "evil"));
        list.add(new Dog("Felicia", "Golden"));

        Dog removable = new Dog("Remove me");
        list.add(removable);
        // for (int i = 0; i < 100000000; i++){
        //     list.add(new Dog("dog"));
        // }

        PugSaver.rescuePugs(list);

        System.out.println(list.toString());

        list.remove(removable);

        System.out.println("\n"+list.toString());
    }
}
