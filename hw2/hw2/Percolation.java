package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] Grid;
    private WeightedQuickUnionUF WQ;
    private int count;
    public Percolation(int N){
        if(N<0){
            throw new IllegalArgumentException();
        }
        Grid=new boolean[N][N];
        int s=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                Grid[i][j]=false;
            }
        }
        WQ=new WeightedQuickUnionUF(N*N);
        count=0;
    }// create N-by-N grid, with all sites initially blocked
    private int xyTo1D(int r,int c){
        return r*Grid.length+c;
    }
    public void open(int row, int col) {
        if(row<0 ||row>Grid.length-1){
            throw new IndexOutOfBoundsException();
        }
        if(col<0 ||col>Grid.length-1){
            throw new IndexOutOfBoundsException();
        }
        if(isOpen(row, col)){
            return;
        }
        Grid[row][col]=true;
        count++;
        int current=xyTo1D(row,col);
        if(Grid.length==1){
            return;
        }
        if(row==0 && col==0){
            if(Grid[row+1][col]==true){
                WQ.union(current,xyTo1D(row+1,col));
            }
            if(Grid[row][col+1]==true){
                WQ.union(current,xyTo1D(row,col+1));
            }
        }else if(row==0 &&col== Grid.length-1){
            if(Grid[row+1][col]==true){
                WQ.union(current,xyTo1D(row+1,col));
            }
            if(Grid[row][col-1]==true){
                WQ.union(current,xyTo1D(row,col-1));
            }
        }else if(row== Grid.length-1 && col==0){
            if(Grid[row-1][col]==true){
                WQ.union(current,xyTo1D(row-1,col));
            }
            if(Grid[row][col+1]==true){
                WQ.union(current,xyTo1D(row,col+1));
            }
        }else if(row== Grid.length-1&&col== Grid.length-1){
            if(Grid[row-1][col]==true){
                WQ.union(current,xyTo1D(row-1,col));
            }
            if(Grid[row][col-1]==true){
                WQ.union(current,xyTo1D(row,col-1));
            }
        }else if(row==0){
            if(Grid[row+1][col]==true){
                WQ.union(current,xyTo1D(row+1,col));
            }
            if(Grid[row][col+1]==true){
                WQ.union(current,xyTo1D(row,col+1));
            }
            if(Grid[row][col-1]==true){
                WQ.union(current,xyTo1D(row,col-1));
            }
        }else if(row== Grid.length-1){
            if(Grid[row-1][col]==true){
                WQ.union(current,xyTo1D(row-1,col));
            }
            if(Grid[row][col+1]==true){
                WQ.union(current,xyTo1D(row,col+1));
            }
            if(Grid[row][col-1]==true){
                WQ.union(current,xyTo1D(row,col-1));
            }
        }else if(col==0){
            if(Grid[row-1][col]==true){
                WQ.union(current,xyTo1D(row-1,col));
            }
            if(Grid[row+1][col]==true){
                WQ.union(current,xyTo1D(row+1,col));
            }
            if(Grid[row][col+1]==true){
                WQ.union(current,xyTo1D(row,col+1));
            }
        }else if(col== Grid.length-1){
            if(Grid[row-1][col]==true){
                WQ.union(current,xyTo1D(row-1,col));
            }
            if(Grid[row+1][col]==true){
                WQ.union(current,xyTo1D(row+1,col));
            }
            if(Grid[row][col-1]==true){
                WQ.union(current,xyTo1D(row,col-1));
            }
        }else{
            if(Grid[row-1][col]==true){
                WQ.union(current,xyTo1D(row-1,col));
            }
            if(Grid[row+1][col]==true){
                WQ.union(current,xyTo1D(row+1,col));
            }
            if(Grid[row][col+1]==true){
                WQ.union(current,xyTo1D(row,col+1));
            }
            if(Grid[row][col-1]==true){
                WQ.union(current,xyTo1D(row,col-1));
            }
        }
    } // open the site (row, col) if it is not open already
    public boolean isOpen(int row, int col){
        if(row<0 ||row>Grid.length-1){
            throw new IndexOutOfBoundsException();
        }
        if(col<0 ||col>Grid.length-1){
            throw new IndexOutOfBoundsException();
        }
        return Grid[row][col]==true;
    }  // is the site (row, col) open?
    public boolean isFull(int row, int col){
        if(row<0 ||row>Grid.length-1){
            throw new IndexOutOfBoundsException();
        }
        if(col<0 ||col>Grid.length-1){
            throw new IndexOutOfBoundsException();
        }
        if(row==0 && Grid[row][col]==false){
            return false;
        }

        int current=xyTo1D(row,col);
        for(int i=0;i< Grid.length;i++){
            if(WQ.connected(current,xyTo1D(0,i))){
                return true;
            }
        }
        return false;
    }  // is the site (row, col) full?
    public int numberOfOpenSites() {
        return count;
    }          // number of open sites
    public boolean percolates() {

        for(int i=0;i< Grid.length;i++){
            if(isFull(Grid.length-1,i )){
                return true;
            }
        }
        return false;
    }   // does the system percolate?
//    public static void main(String[] args) {
//        Percolation P=new Percolation(1);
//        //P.open(0,0);
//        System.out.println(P.percolates());
////        P.open(3,4);
////        P.open(2,4);
////        P.open(2,2);
////        P.open(2,3);
////        P.open(0,2);
////        P.open(1,2);
////        System.out.println(P.isFull(3, 4));
//        WeightedQuickUnionUF Q=new WeightedQuickUnionUF(10);
//        System.out.println(Q.connected(0, 0));
//
//    }  // use for unit testing (not required)
}
