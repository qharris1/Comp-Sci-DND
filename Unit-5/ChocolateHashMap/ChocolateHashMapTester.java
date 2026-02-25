public class ChocolateHashMapTester {
    public static void main(String[] args) {
        ChocolateHashMap map = new ChocolateHashMap(2, 1);

        String key = "SKU-A";
        System.out.println(map.put(key, "Milk"));
        System.out.println(map.put("NRA-201", "392831"));
        System.out.println(map.currentLoadFactor());
        System.out.println(map.put("IJOAFIHEI", "2221"));
        System.out.println(map);
        map.rehash(10);
        System.out.println(map.put("HDOIH*#EY", "12083"));
        System.out.println(map);
        System.out.println(map.size());
        System.out.println(map.get(key));
        System.out.println(map.containsValue("Dark"));
        System.out.println(map);
        System.out.println(map.isEmpty());
        System.out.println(map.size());
        System.out.println(map.currentLoadFactor());
        System.out.println(map.containsKey("NRA-201"));
        System.out.println(map.containsValue("208412"));
        System.out.println(map);
        System.out.println(map.remove(key));
        System.out.println(map.get("NRA-201"));
        System.out.println(map);
        map.rehash(1000);
        System.out.println(map.toString());
        System.out.println(map.getBuckets());
    }
}
