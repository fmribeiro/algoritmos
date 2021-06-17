import java.time.Duration;
import java.time.Instant;

public class QuickUnionUF {

    private int[] id;

    public QuickUnionUF(int n){
        id = new int[n];
        for(int i =0; i < n; i++){
            id[i] = i;
        }
    }

    private int root(int i){
        while(i != id[i]){
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }

    public static void main(String[] args) {
        System.out.println("Quick Union...");
        Instant start = Instant.now();
        QuickUnionUF quickUnionUF = new QuickUnionUF(100000000);
//        Instant start = Instant.now();
        System.out.println("connected: " + quickUnionUF.connected(1, 1000));
        System.out.println("union: 1 <> 1000");
        quickUnionUF.union(1, 1000);
        System.out.println("connected: " + quickUnionUF.connected(1, 1000));

        Instant end = Instant.now();
        System.out.println("Texmpo de execucao: " + Duration.between(start, end).toMillis());
    }

}
