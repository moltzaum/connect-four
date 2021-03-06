
package ConnectFour;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.concurrent.TimeUnit;

// API:
// {"grid": [[0, 0, 0], [0, 0, 0], [0, 0, 0]]}
// {"move":2}

// Rename to 'Driver'?
public class Main {

    //
    // --interpreter-1 <path>
    // --program-1 <path>
    //
    // --interpreter-2 <path>
    // --program-2 <path>
    //
    // If interpreter is not supplied, its program will be
    // interpreted as an executable (see what I did there?).
    // If the interpreter argument is used the program is
    // passed as an argument to it. The program args will
    // always be used.
    //

    public static void main(String[] args) {

        int height = 5;
        int width = 5;
        int tGames = 1;

        int draws = 0;
        int player1Wins = 0;
        int player2Wins = 0;
        
        String arg_i1 = "";
        String arg_p1 = "";
        String arg_i2 = "";
        String arg_p2 = "";
        
        for (int i = 0; i < args.length; i++) {

            if (!args[i].startsWith("--")) {
                System.out.printf("unknown argument '%s'\n", args[i]);
                return;
            }

            if (i + 1 == args.length) {
                System.out.printf("option '%s' requires value\n", args[i]);
                return;
            }

            switch (args[i]) {
                case "--interpreter-1":
                    arg_i1 = args[++i];
                    break;
                case "--program-1":
                    arg_p1 = args[++i];
                    break;
                case "--interpreter-2":
                    arg_i2 = args[++i];
                    break;
                case "--program-2":
                    arg_p2 = args[++i];
                    break;
                case "--height":
                    height = Integer.parseInt(args[++i]);
                    break;
                case "--width":
                    width = Integer.parseInt(args[++i]);
                    break;
                case "--tGames":
                    tGames = Integer.parseInt(args[++i]);
                    break;

                default:
                    System.out.printf("switch '%s' not recognized\n", args[i]);
                    return;
            }
        }

        PlayerProcess p1;
        PlayerProcess p2;
        
        ProcessBuilder pb = new ProcessBuilder()
            .redirectError(Redirect.INHERIT)
            .directory(new File("src/examples"));
        
        List<String> cmd1 = new LinkedList<String>(Arrays.asList(
            "--player", "1",
            "--height", Integer.toString(height),
            "--width", Integer.toString(width)
        ));
        
        List<String> cmd2 = new LinkedList<String>(Arrays.asList(
            "--player", "2",
            "--height", Integer.toString(height),
            "--width", Integer.toString(width)
        ));
        
        cmd1.add(0, arg_p1);
        if (!arg_i1.equals(""))
            cmd1.add(0, arg_i1);
        
        cmd2.add(0, arg_p2);
        if (!arg_i2.equals(""))
            cmd2.add(0, arg_i2);
        
        //if (!arg_p1.equals(""))
        //    cmd1.add(0, arg_p1);
        //cmd1.add(0, arg_i1);
        //
        //if (!arg_p2.equals(""))
        //    cmd2.add(0, arg_p2);
        //cmd2.add(0, arg_i2);

        //tGames = 2;

        for( int x = 0; x < tGames; ++x) {
            Board board = new Board(height, width, 4);

            try {

                //System.out.println("DEBUG: " + cmd1);
                //System.out.println("DEBUG: " + cmd2);
                p1 = new PlayerProcess(pb.command(cmd1).start());
                p2 = new PlayerProcess(pb.command(cmd2).start());

                int winner;
                while (true) {
                    p1.sendGrid(board);
                    board.addPlayerMove(1, p1.getMove());
                    if (board.playerWon(1)) {
                        player1Wins +=1;
                        winner = 1;
                        break;
                    }
                    p2.sendGrid(board);
                    board.addPlayerMove(2, p2.getMove());
                    if (board.playerWon(2)) {
                        player2Wins +=1;
                        winner = 2;
                        break;
                    }
                    if (board.checkDraw() == 0)
                    {
                        draws +=1;
                        winner = 3;
                        break;
                    }
                }

                if (winner <= 2) {
                    System.out.printf("The winner is %d\n", winner);
                } else {
                    System.out.printf("DRAW !!\n");
                }
                
                p1.close();
                p2.close();

            } catch (IOException e) {
                System.out.println(e);
                return;
            }
        }

        try{
             Thread.sleep(1000);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
        

        System.out.printf("Total Games played %d\n Total of Draws %d\n Player 1 Won %d Games!!\n Player 2 Won %d Games!!\n", tGames,draws,player1Wins,player2Wins);

    }
}
