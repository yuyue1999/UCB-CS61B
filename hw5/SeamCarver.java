import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Picture;

import java.awt.*;
import java.security.PublicKey;
import java.util.ArrayList;

/**
 * @author YY
 * @create 2022-06-26 3:20 PM
 */
public class SeamCarver {
    private Picture picture;
    private MinPQ<searchNode> MP= new MinPQ<>();
    public SeamCarver(Picture picture) {
        this.picture=picture;
    }

    public Picture picture() {
        return picture;
    }
    // current picture

    public int width() {
        return picture.width();
    }
        // width of current picture
    public int height() {
        return picture.height();
    }
    // height of current picture
    public  double energy(int x, int y)    {
        // energy of pixel at column x and row y
        if(x==0 && y==0){
            Color X1=picture.get(x+1,y);
            Color X2=picture.get(picture().width()-1, y);
            Color Y1=picture.get(x,height()-1);
            Color Y2=picture.get(x,y+1);
            int X=(X1.getRed()-X2.getRed())*(X1.getRed()-X2.getRed())
                    +(X1.getGreen()-X2.getGreen())*(X1.getGreen()-X2.getGreen())
                    +(X1.getBlue()-X2.getBlue())*(X1.getBlue()-X2.getBlue());
            int Y=(Y1.getRed()-Y2.getRed())*(Y1.getRed()-Y2.getRed())+
                    +(Y1.getGreen()-Y2.getGreen())*(Y1.getGreen()-Y2.getGreen())
                    +(Y1.getBlue()-Y2.getBlue())*(Y1.getBlue()-Y2.getBlue());
            return X+Y;
        }
        if(x==width()-1 && y==0){
            Color X1=picture.get(0,y);
            Color X2=picture.get(x-1,y);
            Color Y1=picture.get(x,height()-1);
            Color Y2=picture.get(x,y+1);
            int X=(X1.getRed()-X2.getRed())*(X1.getRed()-X2.getRed())
                    +(X1.getGreen()-X2.getGreen())*(X1.getGreen()-X2.getGreen())
                    +(X1.getBlue()-X2.getBlue())*(X1.getBlue()-X2.getBlue());
            int Y=(Y1.getRed()-Y2.getRed())*(Y1.getRed()-Y2.getRed())+
                    +(Y1.getGreen()-Y2.getGreen())*(Y1.getGreen()-Y2.getGreen())
                    +(Y1.getBlue()-Y2.getBlue())*(Y1.getBlue()-Y2.getBlue());
            return X+Y;
        }
        if(x==0 && y==height()-1){
            Color X1=picture.get(x+1,y);
            Color X2=picture.get(width()-1,y);

            Color Y1=picture.get(x,y-1);
            Color Y2=picture.get(x,0);

            int X=(X1.getRed()-X2.getRed())*(X1.getRed()-X2.getRed())
                    +(X1.getGreen()-X2.getGreen())*(X1.getGreen()-X2.getGreen())
                    +(X1.getBlue()-X2.getBlue())*(X1.getBlue()-X2.getBlue());
            int Y=(Y1.getRed()-Y2.getRed())*(Y1.getRed()-Y2.getRed())+
                    +(Y1.getGreen()-Y2.getGreen())*(Y1.getGreen()-Y2.getGreen())
                    +(Y1.getBlue()-Y2.getBlue())*(Y1.getBlue()-Y2.getBlue());
            return X+Y;
        }
        if(x==width()-1 && y==height()-1){
            Color X1=picture.get(0,y);
            Color X2=picture.get(x-1,y);
            Color Y1=picture.get(x,y-1);
            Color Y2=picture.get(x,0);
            int X=(X1.getRed()-X2.getRed())*(X1.getRed()-X2.getRed())
                    +(X1.getGreen()-X2.getGreen())*(X1.getGreen()-X2.getGreen())
                    +(X1.getBlue()-X2.getBlue())*(X1.getBlue()-X2.getBlue());
            int Y=(Y1.getRed()-Y2.getRed())*(Y1.getRed()-Y2.getRed())+
                    +(Y1.getGreen()-Y2.getGreen())*(Y1.getGreen()-Y2.getGreen())
                    +(Y1.getBlue()-Y2.getBlue())*(Y1.getBlue()-Y2.getBlue());

            return X+Y;
        }
        if(x==0){
            Color X1=picture.get(x+1,y);
            Color X2=picture.get(width()-1,y);

            Color Y1=picture.get(x,y-1);
            Color Y2=picture.get(x,y+1);

            int X=(X1.getRed()-X2.getRed())*(X1.getRed()-X2.getRed())
                    +(X1.getGreen()-X2.getGreen())*(X1.getGreen()-X2.getGreen())
                    +(X1.getBlue()-X2.getBlue())*(X1.getBlue()-X2.getBlue());
            int Y=(Y1.getRed()-Y2.getRed())*(Y1.getRed()-Y2.getRed())+
                    +(Y1.getGreen()-Y2.getGreen())*(Y1.getGreen()-Y2.getGreen())
                    +(Y1.getBlue()-Y2.getBlue())*(Y1.getBlue()-Y2.getBlue());

            return X+Y;
        }
        if(x==width()-1){
            Color X1=picture.get(0,y);
            Color X2=picture.get(x-1,y);

            Color Y1=picture.get(x,y-1);
            Color Y2=picture.get(x,y+1);

            int X=(X1.getRed()-X2.getRed())*(X1.getRed()-X2.getRed())
                    +(X1.getGreen()-X2.getGreen())*(X1.getGreen()-X2.getGreen())
                    +(X1.getBlue()-X2.getBlue())*(X1.getBlue()-X2.getBlue());
            int Y=(Y1.getRed()-Y2.getRed())*(Y1.getRed()-Y2.getRed())+
                    +(Y1.getGreen()-Y2.getGreen())*(Y1.getGreen()-Y2.getGreen())
                    +(Y1.getBlue()-Y2.getBlue())*(Y1.getBlue()-Y2.getBlue());

            return X+Y;
        }
        if(y==0){
            Color X1=picture.get(x+1,y);
            Color X2=picture.get(x-1,y);

            Color Y1=picture.get(x,height()-1);
            Color Y2=picture.get(x,y+1);

            int X=(X1.getRed()-X2.getRed())*(X1.getRed()-X2.getRed())
                    +(X1.getGreen()-X2.getGreen())*(X1.getGreen()-X2.getGreen())
                    +(X1.getBlue()-X2.getBlue())*(X1.getBlue()-X2.getBlue());
            int Y=(Y1.getRed()-Y2.getRed())*(Y1.getRed()-Y2.getRed())+
                    +(Y1.getGreen()-Y2.getGreen())*(Y1.getGreen()-Y2.getGreen())
                    +(Y1.getBlue()-Y2.getBlue())*(Y1.getBlue()-Y2.getBlue());

            return X+Y;
        }
        if(y==height()-1){
            Color X1=picture.get(x+1,y);
            Color X2=picture.get(x-1,y);

            Color Y1=picture.get(x,y-1);
            Color Y2=picture.get(x,0);

            int X=(X1.getRed()-X2.getRed())*(X1.getRed()-X2.getRed())
                    +(X1.getGreen()-X2.getGreen())*(X1.getGreen()-X2.getGreen())
                    +(X1.getBlue()-X2.getBlue())*(X1.getBlue()-X2.getBlue());
            int Y=(Y1.getRed()-Y2.getRed())*(Y1.getRed()-Y2.getRed())+
                    +(Y1.getGreen()-Y2.getGreen())*(Y1.getGreen()-Y2.getGreen())
                    +(Y1.getBlue()-Y2.getBlue())*(Y1.getBlue()-Y2.getBlue());

            return X+Y;
        }
        Color X1=picture.get(x+1,y);
        Color X2=picture.get(x-1,y);

        Color Y1=picture.get(x,y-1);
        Color Y2=picture.get(x,y+1);

        int X=(X1.getRed()-X2.getRed())*(X1.getRed()-X2.getRed())
                +(X1.getGreen()-X2.getGreen())*(X1.getGreen()-X2.getGreen())
                +(X1.getBlue()-X2.getBlue())*(X1.getBlue()-X2.getBlue());
        int Y=(Y1.getRed()-Y2.getRed())*(Y1.getRed()-Y2.getRed())+
                +(Y1.getGreen()-Y2.getGreen())*(Y1.getGreen()-Y2.getGreen())
                +(Y1.getBlue()-Y2.getBlue())*(Y1.getBlue()-Y2.getBlue());

        return X+Y;
        }
        private class searchNode implements Comparable<searchNode>{
        private Color current;
        private searchNode previous;
        private double e;
        private double priority;
        private int i;
        private int j;

        public searchNode(int i,int j,searchNode previous){
            this.i=i;
            this.j=j;
            this.current=picture.get(i,j);
            this.previous=previous;
            e=energy(i,j);
            if(previous==null){
                this.priority=e;
            }else {
                this.priority=e+ previous.priority;
            }
        }
        public int compareTo(searchNode o){
            return (int) (this.priority-o.priority);
        }
        private Iterable<searchNode> neighbors(){
            ArrayList<searchNode> store1=new ArrayList<>();
            if(i==0){
                store1.add(new searchNode(i,j-1,this));
                store1.add(new searchNode(i+1,j-1,this));
                return store1;
            }
            if(i==width()-1){
                store1.add(new searchNode(i-1,j-1,this));
                store1.add(new searchNode(i,j-1,this));
                return store1;
            }
            store1.add(new searchNode(i-1,j-1,this));
            store1.add(new searchNode(i,j-1,this));
            store1.add(new searchNode(i+1,j-1,this));
            return store1;
        }
    }

    public   int[] findHorizontalSeam()     {
        // sequence of indices for horizontal seam
        return  null;
        }
    public   int[] findVerticalSeam()     {
        // sequence of indices for vertical seam
        double[] result=new double[width()];
        MinPQ<searchNode>[] PQs=new MinPQ[width()];
        for(int k=0;k<width();k++){
            PQs[k]=new MinPQ<>();
        }
        for(int k=0;k<width();k++){
            PQs[k].insert(new searchNode(k,height()-1,null));
            while(PQs[k].min().j>0){
                searchNode min=PQs[k].delMin();
                for(searchNode c:min.neighbors()){
                    PQs[k].insert(c);
                }
            }
        }
        for(int k=0;k<width();k++){
            result[k]=  PQs[k].min().priority;
        }

        double mini=Integer.MAX_VALUE;
        int index=0;
        for(int k=0;k<width();k++){
            if(result[k]<mini){
                mini=result[k];
                index=k;
            }
        }
        int[] final_result=new int[height()];
        searchNode target=PQs[index].min();
        for(int k=0;k<height();k++){
            final_result[k]=target.i;
            target=target.previous;
        }
        return  final_result;
        }
    public    void removeHorizontalSeam(int[] seam)  {
        // remove horizontal seam from picture

        }
    public    void removeVerticalSeam(int[] seam)   {
        // remove vertical seam from picture
        picture=SeamRemover.removeVerticalSeam(picture,seam);

        }

//    public static void main(String[] args) {
//        Picture p = new Picture(3, 4);
//        int[][][] exampleArray = {{{255, 101, 51}, {255, 101, 153}, {255, 101, 255}},
//                {{255, 153, 51}, {255, 153, 153}, {255, 153, 255}},
//                {{255, 203, 51}, {255, 204, 153}, {255, 205, 255}},
//                {{255, 255, 51}, {255, 255, 153}, {255, 255, 255}}};
//        double[][] exampleEnergy = {{20808.0, 52020.0, 20808.0},
//                                    {20808.0, 52225.0, 21220.0},
//                                    {20809.0, 52024.0, 20809.0},
//                                    {20808.0, 52225.0, 21220.0}};
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 4; j++) {
//                int[] colorVals = exampleArray[j][i];
//                p.set(i, j, new Color(colorVals[0], colorVals[1], colorVals[2]));
//            }
//        }
//
//        SeamCarver sc = new SeamCarver(p);
//        int[] test=sc.findVerticalSeam();
//        for(int t:test){
//            System.out.println(t);
//        }
//    }
}

