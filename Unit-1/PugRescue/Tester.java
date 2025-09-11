import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<String>();

        // Test rescuePugs - DONE
        // list.add(new Dog("Yari", "Golden"));
        // list.add(new Dog("Carter", "evil"));
        // list.add(new Dog("Taj", "Golden"));
        // list.add(new Dog("Dylan", "evil"));
        // list.add(new Dog("Morgan", "evil"));
        // list.add(new Dog("Jackson", "evil"));
        // list.add(new Dog("Felicia", "Golden"));

        // for (int i = 0; i < 100000000; i++) {
        //     if (i%3==0){
        //         list.add(new Dog("P"));
        //     } else if ( i % 3 == 1){
        //         list.add(new Dog("P"));
        //     } else {
        //         list.add(new Dog("G", "Golden"));
        //     }
        // }

        // System.out.println("done");

        // PugSaver.rescuePugs(list);

        // System.out.println("done");

        // System.out.println(list.toString());

        // // size()
        // System.out.println("\nExpected 7: " + list.size());

        // // isEmpty()
        // System.out.println("\nExpected true: " + new MyArrayList<Dog>().isEmpty());
        // System.out.println("Expected false: " + list.isEmpty());

        // System.out.println(list.toString());

        // Dog removable = new Dog("Remove me");
        // list.add(2, removable);

        // System.out.println(list.toString());

        // list.add(1, null);
        // System.out.println();

        // System.out.println(list.toString());
        // for (int i = 0; i < 99; i++){
        //     list.add(new Dog("dog"));
        // }

        // list.add(10, new Dog("LOOK AT ME HELLO"));

        // System.out.println(list.size());

        // System.out.println(list.get(10));

        // System.out.println(list.contains(null));


        // System.out.println(list.contains(removable));

        // list.remove(removable);

        // System.out.println(list.contains(removable));

        // System.out.println("\n"+list.toString());

        // list.add(null);
        
        // System.out.println("\n" + list.toString());

		list.add("A");
		list.add("B");
		list.add("C");

		list.add(1, "D");

		System.out.println(list.toString());

		list.add(4, "Yari");

		System.out.println(list.toString());
		
    }
}
