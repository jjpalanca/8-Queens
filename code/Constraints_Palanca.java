package constraints;

import java.util.Arrays;
import java.util.Scanner;

public class Constraints_Palanca {

	public static int oldBackTrackCounter = 0;
	public static int backTrackCounter = 0;
	public static int totalBackTrackCounter = 0;
	public static int oldBackTrackCounter2 = 0;
	public static int backTrackCounter2 = 0;
	public static int totalBackTrackCounter2 = 0;
	public static int prevK = 0;
	public static int prevK2 = 0;
	public static int solutionCounter = 1;
	public static String queenPos;
   /***************************************************************************
    * Returns true if queen placement q[n] does not conflict with
    * other queens q[0] through q[n-1]
    ***************************************************************************/
    public static boolean noAttack(int[] q, int n) {
        for (int i = 0; i < n; i++) {
            if (q[i] == q[n]) {								// column constraints
            	return false;   
            }
            if ((q[i] - q[n]) == (n - i)) {					// upper diagonal constraints
            	return false;   
            }
            if ((q[n] - q[i]) == (n - i)) {					// lower diagonal constraints
            	return false;   
            }
        }
        return true;
    }
    
    public static boolean noAttack2(int[] q, int n) {
        for (int i = 0; i < n; i++) {
        	if(n==8) {
        		return false;								// break the lookahead
        	}
            if (q[i] == q[n]) {								// column constraints
            	return false;   
            }
            if ((q[i] - q[n]) == (n - i)) {					// upper diagonal constraints
            	return false;   
            }
            if ((q[n] - q[i]) == (n - i)) {					// lower diagonal constraints
            	return false;   
            }
        }
        return true;
    }
    
    public static int positionToNum(String pos) {
    	int temp = 0;
    	switch(pos) {
    		case "1A":
    			temp = 0;
    			break;
    		case "1B":
    			temp = 1;
    			break;
    		case "1C":
    			temp = 2;
    			break;
    		case "1D":
    			temp = 3;
    			break;
    		case "1E":
    			temp = 4;
    			break;
    		case "1F":
    			temp = 5;
    			break;
    		case "1G":
    			temp = 6;
    			break;
    		case "1H":
    			temp = 7;   
    			break;
    	}
    	return temp;
    }

   /***************************************************************************
    * Prints the solution for all the Queen positions
    ***************************************************************************/
    public static void printSolutions(int[] q) {
    	
        int n = q.length;
        if (q[0]==positionToNum(queenPos)) {
        	System.out.printf("Solution %d with queen in " + queenPos, solutionCounter++);
        	System.out.println("\n   The positions of the Queens are:\n");
//        	System.out.println("   "+Arrays.toString(q));
        	for (int i = 0; i < n; i++) {
            	System.out.print("   Row "+ (i+1) + ": " );
            	switch(q[i]) {
            		case 0:
            			System.out.println(i+1+"A");
            			break;
            		case 1:
            			System.out.println(i+1+"B");
            			break;
            		case 2:
            			System.out.println(i+1+"C");
            			break;
            		case 3:
            			System.out.println(i+1+"D");
            			break;
            		case 4:
            			System.out.println(i+1+"E");
            			break;
            		case 5:
            			System.out.println(i+1+"F");
            			break;
            		case 6:
            			System.out.println(i+1+"G");
            			break;
            		case 7:
            			System.out.println(i+1+"H");
            			break;
            	}
            }
        	
        	System.out.println();
            System.out.println("   Total numbers of backtracks before this solution was found: ");
            System.out.println("   Forward Checking: " + oldBackTrackCounter);
            System.out.println("   Directional Look Ahead: " + oldBackTrackCounter2);
            System.out.println();
            totalBackTrackCounter+=oldBackTrackCounter;
            totalBackTrackCounter2+=oldBackTrackCounter2;
        }
        
    }


    /***************************************************************************
     *  Backtracking function using recursion
     ***************************************************************************/
    public static void recursiveBacktrack(int[] q, int k) { 
        int n = q.length;
        
        if (k == n) {
        	oldBackTrackCounter=backTrackCounter-oldBackTrackCounter;
        	oldBackTrackCounter2=backTrackCounter2 - oldBackTrackCounter2;
        	printSolutions(q);
        }
        else {
        	
            for (int i = 0; i < n; i++) {
                q[k] = i;
                if (noAttack(q, k)) {
                	if (noAttack2(q, k+1)) {
                		if (prevK2 > k&&q[0]==positionToNum(queenPos)) {
                    		backTrackCounter2+=prevK2-k;
                    	}
                		prevK2 = k;
//                		recursiveBacktrack(q, k+1);
                	}
                	if (prevK > k&&q[0]==positionToNum(queenPos)) {
                		backTrackCounter+=prevK-k;
                	}

                	prevK = k;
                	recursiveBacktrack(q, k+1);
                }
            }   
        }
    }  

    public static void main(String[] args) {
    	Scanner userInput = new Scanner(System.in);
    	System.out.println("Please enter the position of a queen: ");
    	queenPos = userInput.nextLine();
    	System.out.println();
        int boardSize = 8;
        int[] queue = new int[boardSize];
        recursiveBacktrack(queue, 0);
        System.out.println();
        System.out.println("Total numbers of backtracks before all solutions having queen in: " + queenPos + " was found (or found there are no solutions): ");
        System.out.println("   Forward Checking: " + totalBackTrackCounter);
        System.out.println("   Directional Look Ahead: " + totalBackTrackCounter2);
        System.out.println();
    }

}