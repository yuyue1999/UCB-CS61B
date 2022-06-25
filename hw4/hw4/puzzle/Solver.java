package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;


/**
 * @author YY
 * @create 2022-06-23 4:03 PM
 */
public class Solver  {
    private MinPQ<SearchNode> store;
    private ArrayList<WorldState> finalresult=new ArrayList<>();
    private class SearchNode implements Comparable<Solver.SearchNode>{
        private int move;
        public SearchNode previous;
        public int priority;
        private WorldState current;

        public SearchNode(WorldState current, int move, SearchNode previous){
            this.current=current;
            this.move=move;
            this.previous=previous;
            this.priority=move+current.estimatedDistanceToGoal();
        }
        public int compareTo(SearchNode o){
            return this.priority-o.priority;
        }
    }

    public Solver(WorldState initial){
        this.store=new MinPQ<>();
        store.insert(new SearchNode(initial,0,null));
        while(!store.min().current.isGoal()){
            SearchNode target=store.delMin();
            for(WorldState temp:target.current.neighbors()){
                if(target.previous==null || !target.previous.current.equals(temp)){
                    store.insert(new SearchNode(temp,1+target.move,target));
                }
            }
        }
    }

    public int moves(){
        return store.min().move;
    }
    public Iterable<WorldState> solution(){
        Stack<WorldState> result=new Stack<>();
        SearchNode destination=store.min();
        while(destination!=null){
            result.push(destination.current);
            destination=destination.previous;
        }
        while(!result.isEmpty()){
            finalresult.add(result.pop());
        }

        return finalresult;
    }
}
