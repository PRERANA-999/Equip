import java.util.*;

public class EquipmentDealMatcher {
    
    public static List<Integer> matchDeals(List<String[]> requests, List<String[]> sellers) {
        Map<String, PriorityQueue<Integer>> equipmentPrices = new HashMap<>();
        
        for (String[] seller : sellers) {
            String equipment = seller[0];
            int price = Integer.parseInt(seller[1]);

            equipmentPrices
                .computeIfAbsent(equipment, k -> new PriorityQueue<>())
                .add(price);
        }

        List<Integer> result = new ArrayList<>();
        
        for (String[] request : requests) {
            String equipment = request[0];
            int maxPrice = Integer.parseInt(request[1]);

            if (equipmentPrices.containsKey(equipment)) {
                PriorityQueue<Integer> minHeap = equipmentPrices.get(equipment);

                while (!minHeap.isEmpty() && minHeap.peek() > maxPrice) {
                    minHeap.poll(); // Remove overpriced sellers
                }

                result.add(minHeap.isEmpty() ? null : minHeap.poll());
            } else {
                result.add(null); // No sellers for this equipment type
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<String[]> requests = Arrays.asList(
            new String[]{"excavator", "50000"}, 
            new String[]{"bulldozer", "70000"}
        );

        List<String[]> sellers = Arrays.asList(
            new String[]{"excavator", "45000"},
            new String[]{"bulldozer", "68000"},
            new String[]{"excavator", "48000"}
        );

        List<Integer> result = matchDeals(requests, sellers);
        System.out.println(result);
    }
}
