import java.util.Stack;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class TowerOfHanoi {
//is the playerMove method a good way to do this? should it be split into two methods?
//do I need to specify why the move is illegal?
//do you have to land on furthest rod? 
		//otherwise need to include (&& B.size()<discNum) 
//do we need to print the past moves so the user can see them?
//do we need the user to specify the disc to move, or just where to move from and to?
	public static void solver(int discNum, String from, String mid, String to) { //from=1, to=3, mid=2
			if (discNum>0) {
			solver(discNum-1, from, to, mid);
			System.out.println("Move disc " +discNum+ " from rod " +from+ " to rod "+to);
			solver(discNum-1, mid, from, to); 
		}
	}
	//check legality in player mode
	public static Boolean isLegal(Stack<Integer> from, Stack<Integer> to) {
		//if the stack being moved from holds discs and the move is legal
		if (!from.isEmpty()) {	
			if ((!to.isEmpty() && from.peek()<to.peek()) || (to.isEmpty())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false; //if move is illegal
		}
	}
	//move from stack to stack
	public static void playerMove(Stack<Integer> from, Stack<Integer> to) {
		to.push(from.pop());
	}
	
	
	public static Scanner input= new Scanner(System.in);
	public static void main(String[] args) {
		int moveCount=0;
		//create three rod stacks, let user chose how many discs use
		Stack<Integer> A=new Stack<Integer>();
		Stack<Integer> B=new Stack<Integer>();
		Stack<Integer> C=new Stack<Integer>();
		Map<String, Stack<Integer>> rods= new HashMap<String, Stack<Integer>>();
		rods.put("A", A);
		rods.put("B", B);
		rods.put("C", C);
		
		System.out.println("Which mode would you like to use? 'Solver' or 'Player?'");
		String answer= input.nextLine();
		System.out.println("How many discs would you like to play with? Chose up to eight.");
		int discNum=input.nextInt();
		if (discNum>8 || discNum<1) {
			System.out.print("Invalid number of discs. Restart.");
			} else {
			if (answer.equals("Solver")) {
				solver(discNum, "A", "B", "C");
			}
			if (answer.equals("Player")) {
				//put all discs in order on 1st rod
				for (int i=discNum; i>=1; i--) {
					A.push(i);
				}
				//play until all discs ordered on 3rd rod
				while(C.size()<discNum) { 
					System.out.println ("Enter rod to move from:");
					String moveFrom=input.next();
					System.out.println("Enter rod to move to:");
					String moveTo=input.next();
					if (isLegal(rods.get(moveFrom), rods.get(moveTo))) {
						playerMove(rods.get(moveFrom), rods.get(moveTo));
						System.out.println("Disc " + rods.get(moveTo).peek() + " moved from "+ moveFrom+ " to " + moveTo);
						moveCount++;
					} else {
						System.out.println("Illegal move! Try again.");
					}
				}
				System.out.print("Good job! Tower completed in " +moveCount+ " moves.");
			}	
		}
	}
}
