package algorithms;

import java.awt.Point;

public class Game {
	public static void main(String[] args) {
		String exact= "exact";
		String approx= "approx";
		String progress= "progress";
		Reader a = new Reader();
		VerifyInput game = new VerifyInput();
		
		if (args.length != 0){
			String text = a.readTxt(args[0]);
		
			if (game.input(text)){
				int[][] table = VerifyInput.getTable();
				TableroControl tab=game.getTableroInstance();
				System.out.println("Free Flow Game Solver!\n");
				System.out.println("Beginning GameBoard:");
				System.out.println("Rows: " + game.getFils() + "   Columns: " + game.getCols() + "\n");
				
		
				for (int x=0; x < game.getFils(); x++) {
					System.out.print("|");
			  		for (int y=0; y < game.getCols() ; y++) {
			  			System.out.print (table[x][y]);
			  			if (y!=game.getCols()) System.out.print("\t");
			  		}
			  		System.out.println("|");
				}
				
				System.out.println("\nWait. The program is Running...\n");
		
				MainFrame window0 = new MainFrame(game.getFils(),game.getCols(),table);
				window0.setVisible(true);
				Tablero tablero = new Tablero(table ,game.getFils(),game.getCols(),game.getColours(),game.getSinks());
        
				if (args[1].equals(exact)){
					ExactMethod solution = new ExactMethod();
 					if (args.length==3 && args[2].equals(progress)){
 						solution.ExactSolution(tablero, true);
 					}else if (args.length==2){
 						solution.ExactSolution(tablero, false);
 					}else throw new  IllegalArgumentException();
				}
				else if (args[1].equals(approx)){
					Integer time=0;
					ApproxMethod aprox= new ApproxMethod();
					if ((args.length==3 || args.length==4) && isInt(args[2])){
						time=Integer.parseInt(args[2]);
						if(args.length==4 && args[3].equals(progress)){
							aprox.approxSolution(tab, time, true); 
						}else if(args.length==3){
							aprox.approxSolution(tab, time, false);
						}else throw new  IllegalArgumentException();
					}else throw new  IllegalArgumentException();
			
				}else throw new IllegalArgumentException();
			}else{
			    return;
			}
		}
	}
	
		public static boolean isInt(String str) {
		    try {
		        Integer.parseInt(str);
		        return true;
		    } catch (NumberFormatException e) {
		        return false;
		    }
		}
}
