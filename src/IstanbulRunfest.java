import java.util.*;

public class IstanbulRunfest {

    public static void main(String[] args) {

        HashMap<String, Integer> map = new HashMap<>();
        map.put("Ibrahim", 272);
        map.put("Berke", 479);
        map.put("Metin", 278);
        map.put("Irem", 329);
        map.put("Yigit", 445);
        map.put("Melis", 402);
        map.put("Mehmet", 388);
        map.put("Akif", 275);
        map.put("Furkan", 243);
        map.put("Fırat", 334);
        map.put("Tolga", 412);
        map.put("Ozkan", 393);
        map.put("Umut", 299);
        map.put("Seda", 343);
        map.put("Selcan", 317);
        map.put("Hatice", 265);


        ArrayList<Integer> RunfestNumberList = new ArrayList<>(map.values());
        int fastestRunnerMin = Collections.min(RunfestNumberList);
        String runnerName = "";
        for (Map.Entry<String, Integer> Quickest : map.entrySet()) {
            if (Quickest.getValue() == fastestRunnerMin) {
                runnerName = Quickest.getKey();
                break;

            }

        }
        System.out.println("Quickest runner and it's recor is : " + fastestRunnerMin + " and " + runnerName);

    }


}
