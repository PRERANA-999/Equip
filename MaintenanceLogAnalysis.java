import java.util.*;

class SegmentTree {
    int[] tree;
    int n;

    public SegmentTree(int size) {
        this.n = size;
        this.tree = new int[4 * size]; // Segment tree size is 4 * n
    }

    // Build segment tree
    public void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    // Query for range sum
    public int query(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return 0; // Out of range
        if (left <= start && end <= right) return tree[node]; // Complete overlap

        int mid = (start + end) / 2;
        int leftSum = query(2 * node + 1, start, mid, left, right);
        int rightSum = query(2 * node + 2, mid + 1, end, left, right);
        return leftSum + rightSum;
    }
}

public class MaintenanceLogAnalysis {
    public static void main(String[] args) {
        // Example maintenance logs (equipment_id, date, cost)
        String[][] maintenanceLogs = {
            {"101", "2024-01-01", "500"},
            {"102", "2024-01-10", "300"},
            {"101", "2024-01-15", "700"}
        };

        // Queries (start_date, end_date)
        String[][] queries = {
            {"2024-01-01", "2024-01-10"},
            {"2024-01-01", "2024-01-15"}
        };

        // Step 1: Extract unique dates and sort them
        TreeSet<String> uniqueDates = new TreeSet<>();
        for (String[] log : maintenanceLogs) uniqueDates.add(log[1]);
        List<String> dateList = new ArrayList<>(uniqueDates);
        Map<String, Integer> dateIndex = new HashMap<>();
        for (int i = 0; i < dateList.size(); i++) dateIndex.put(dateList.get(i), i);

        // Step 2: Convert logs into an array
        int[] costs = new int[dateList.size()];
        for (String[] log : maintenanceLogs) {
            int index = dateIndex.get(log[1]);
            costs[index] += Integer.parseInt(log[2]); // Aggregate cost
        }

        // Step 3: Build Segment Tree
        SegmentTree segmentTree = new SegmentTree(dateList.size());
        segmentTree.build(costs, 0, 0, dateList.size() - 1);

        // Step 4: Process Queries
        List<Integer> results = new ArrayList<>();
        for (String[] query : queries) {
            int left = dateIndex.get(query[0]);
            int right = dateIndex.get(query[1]);
            results.add(segmentTree.query(0, 0, dateList.size() - 1, left, right));
        }

        // Output the results
        System.out.println(results);
    }
}
