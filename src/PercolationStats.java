import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    private int n;
    private int trials;
    int[] grid;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 0 || trials < 0) {
            throw new IllegalArgumentException();
        }

        grid = new int[n];
        for (int i = 0; i < grid.length; i++) {
            grid[i] = i;
        }

        this.n = n;
        this.trials = trials;
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(grid);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(grid);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double zScore = 1.959964;
        double standardError = stddev() / Math.sqrt(trials);
        double marginError = standardError * zScore;
        return mean() - marginError ;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double zScore = 1.959964;
        double standardError = mean() / Math.sqrt(trials);
        double marginError = standardError * zScore;
        return mean() + marginError ;
    }

    // test client (see below)
    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(200, 100);

        System.out.println("mean: " + percolationStats.mean());
        System.out.println("stddev: " + percolationStats.stddev());
        System.out.println("95% confidence interval: [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");

        System.out.println((StdRandom.uniform(10)));
        System.out.println(stopwatch.elapsedTime());
    }
}
