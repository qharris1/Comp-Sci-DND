public class Hash {

    private String[] list = new String[500];

    public int hashFn(String name) {
        name = name.toLowerCase();
        String[] split = name.split(" ");
        long index = 0;
        for (int i = 0; i < split[0].length(); i++) {
            index += Math.pow(split[0].charAt(i) - 96, i + 1);
        }
        for (int i = 0; i < split[1].length(); i++) {
            index += Math.pow(split[1].charAt(i) - 96, i + 1);
        }
        int id = (int) (index % 500);
        if (list[id] != null) {
            System.out.println("Duplicate found at index " + id + " for names " + list[id] + " and " + name);
        } else {
            list[id] = name;
        }
        return id;
    }

}