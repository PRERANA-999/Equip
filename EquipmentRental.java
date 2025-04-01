// Equipment Rental Availability (Graph Algorithm – BFS/DFS)
// Problem Statement:
// Equip9 manages a network of equipment rental providers. Each provider has
// connections with other providers, allowing customers to rent equipment even 
//if their preferred provider does not have availability. Given a list of 
//providers and their connections, determine the shortest path to find the 
//nearest available equipment of a given type.
// Input:
// n (number of rental providers)
// edges (list of connections between providers as [providerA, providerB])
// availability (a dictionary {provider: [equipment_type1, equipment_type2, ...]})
// start_provider (the provider where the customer is searching)
// target_equipment (equipment type the customer needs)
// Output:
// Return the shortest path (minimum number of provider connections) to a provider that has the requested equipment. If no provider has it, return -1.
// Example Input:
// n = 5
// edges = [(1, 2), (2, 3), (3, 4), (4, 5)]
// availability = {1: ["excavator"], 2: [], 3: ["bulldozer"], 4: ["excavator"], 5: ["crane"]}
// start_provider = 2
// target_equipment = "excavator"

// Example Output:
// [2, 3, 4]  # Shortest path to provider 4 with an excavator

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;

class EquipmentRental{
    static class Provider {
        int id;
        List<Integer> neigbors;
        Set<String> equipment;

        Provider(int id);
        {
            this.id = id;
            this. neigbors = new ArrayList<>();
            this.equipment = new Hashset<>();
        }

    }

    public static List<Integer> findShortestPath(int n, int[][] edges, Map<Integer, List<String>> availability, int startProvider, String targetEquipment)
    {
        Map<Integer, Provider> graph = new HashMap<>();
        for (int i = 0;i < n;i++){
            graph.put(i,new Provider(i));
        }

        for (Map.Entry<Integer, List<String>> entry : availability.entrySet())
        {
            graph.get(entry.getKey()).equipment.addAll(entry.getValue());
        }

        Queue<List<Integer>> queue = new LinkedList<>();
        Set<Integer>visited = new Hashset<>();
        queue.offer(Arrays.asList(startProvider));
        visited.add(startProvider);

        while (!queue.isEmpty()){
            List<Integer> path = queue.poll();
            int lastProvider = path.get(path.size() - 1);

            if(graph.get(lastProvider).equipment.contains(targetEquipment)){
               return path;
        }
        
       for( int neigbor : graph.get(lastProvider).neigbors)
    {
        if (!visited.contains(neigbor)){
            visited.add(neigbor);
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(neigbor);
            queue.offer(newPath);
        }
    }
}

return Collections.emptyList();
}


public static void main(String args[]){
    int n = 5;
    int[][] edges = {{1,2}, {2,3},{3,4}, {4,5}};
    Map<Integer,List<String>> availability = new HashMap<>();
    availability.put(1, Arrays.asList("Excavator"));
    availability.put(2, Arrays.asList());
    availability.put(3, Arrays.asList("Bulldozer"));
    availability.put(4, Arrays.asList("Excavator"));
    availability.put(5, Arrays.asList("Crane"));

    int startProvider = 2;
    String targetEquipment = "Excavator";

    List<Integer> result = findShortestPath(n,edges, availability, startProvider, targetEquipment);
    System.out.println(result.isEmpty() ? -1 : result);
}
}

