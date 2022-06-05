package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.ArrayList;

public class PercolationStats {
        private int[] numberofopensites;
        public PercolationStats(int N, int T, PercolationFactory pf){
            if(N<=0 ||T<=0){
                throw new IllegalArgumentException();
            }
            numberofopensites=new int[T];
            pf=new PercolationFactory();
            for(int i=0;i<T;i++){
               Percolation P= pf.make(N);
               while(P.percolates()){
                   P.open(StdRandom.uniform(N),StdRandom.uniform(N));
               }
               numberofopensites[i]=P.numberOfOpenSites();
            }
        }   // perform T independent experiments on an N-by-N grid
        public double mean(){
            int sum=0;
            for(int i=0;i<numberofopensites.length;i++){
                sum=sum+numberofopensites[i];
            }
            return sum/numberofopensites.length;
        }                                         // sample mean of percolation threshold
        public double stddev() {
            return Math.sqrt(StdStats.var(numberofopensites));
        }                                        // sample standard deviation of percolation threshold
        public double confidenceLow(){
            return mean()-1.96*stddev()/Math.sqrt(numberofopensites.length);
        }                                  // low endpoint of 95% confidence interval
        public double confidenceHigh()  {
            return mean()+1.96*stddev()/Math.sqrt(numberofopensites.length);
        }                               // high endpoint of 95% confidence interval


}
