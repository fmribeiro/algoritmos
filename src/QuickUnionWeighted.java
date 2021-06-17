import java.time.Duration;
import java.time.Instant;

public class QuickUnionWeighted {

    private int[] id;
    private int[] sz;

    public QuickUnionWeighted(int n) {
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;

        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
        id[i] = j;
    }

    public static void main(String[] args) {
        System.out.println("Quick Union weighted...");
        Instant start = Instant.now();

        QuickUnionWeighted quickUnionWeighted = new QuickUnionWeighted(100);
        System.out.println("connected: " + quickUnionWeighted.connected(1, 10));
        System.out.println("union: 1 <> 10");
        quickUnionWeighted.union(1, 10);
        quickUnionWeighted.union(2, 10);
        quickUnionWeighted.union(3, 10);
        quickUnionWeighted.union(4, 10);

        quickUnionWeighted.union(15, 20);
        quickUnionWeighted.union(16, 20);
        quickUnionWeighted.union(17, 20);

        quickUnionWeighted.union(4, 20);
        System.out.println("connected: " + quickUnionWeighted.connected(1, 10));
        System.out.println("connected: " + quickUnionWeighted.connected(4, 20));

        Instant end = Instant.now();
        System.out.println("Texmpo de execucao: " + Duration.between(start, end).toMillis());
    }
}
