import java.time.Duration;
import java.time.Instant;

public class QuickFind {

    private int[] id;

    public QuickFind(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Quick Find...");
        Instant start = Instant.now();
        QuickFind quickFind = new QuickFind(100000000);
//        Instant start = Instant.now();
        System.out.println("connected: " + quickFind.connected(1, 1000));
        System.out.println("union: 1 <> 1000");
        quickFind.union(1, 1000);
        System.out.println("connected: " + quickFind.connected(1, 1000));

        Instant end = Instant.now();
        System.out.println("Texmpo de execucao: " + Duration.between(start, end).toMillis());
    }
}
