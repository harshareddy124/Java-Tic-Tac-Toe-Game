import java.util.HashSet;
import java.util.Scanner;

class TicTacToe{
    static HashSet<Integer> ur_set = new HashSet<Integer>();
    static HashSet<Integer> comp_set = new HashSet<Integer>();
    static int generate_computer_pos(){
        int min = 1;
        int max = 9;
        int range = max - min + 1;
        int rand = (int)(Math.random() * range) + min;
        return rand;
    }
    static void place_holder(char[][] board,int position,String user){
        char symbol = 'X';
        if(user.equals("You")){
            symbol = 'X';
            ur_set.add(position);
        }else if(user.equals("Computer")){
            symbol = 'O';
            comp_set.add(position);
        }else{
            System.out.println("Invalid User");
            return;
        }

        switch (position) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;  
                break;
            case 5:
                board[2][2] = symbol;
                break; 
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
            default:

        }
        print_Board(board);
    }
    static void print_Board(char[][] board){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
    static String check_winner(){
        HashSet<Integer> top_row = new HashSet<Integer>();
        top_row.add(1); top_row.add(2); top_row.add(3);
        HashSet<Integer> mid_row = new HashSet<Integer>();
        mid_row.add(4); mid_row.add(5); mid_row.add(6);
        HashSet<Integer> bot_row = new HashSet<Integer>(); 
        bot_row.add(7); bot_row.add(8); bot_row.add(9);
        HashSet<Integer> left_col = new HashSet<Integer>();
        left_col.add(1); left_col.add(4); left_col.add(7);
        HashSet<Integer> mid_col = new HashSet<Integer>();
        mid_col.add(2); mid_col.add(5); mid_col.add(8);
        HashSet<Integer> right_col = new HashSet<Integer>();
        right_col.add(3); right_col.add(6); right_col.add(9);
        HashSet<Integer> cross1 = new HashSet<Integer>();
        cross1.add(1); cross1.add(5); cross1.add(9);
        HashSet<Integer> cross2 = new HashSet<Integer>();
        cross2.add(3); cross2.add(5); cross2.add(7);
        HashSet<HashSet<Integer>> winning_combinations = new HashSet<HashSet<Integer>>();
        winning_combinations.add(top_row);
        winning_combinations.add(mid_row);
        winning_combinations.add(bot_row);
        winning_combinations.add(left_col);
        winning_combinations.add(mid_col);
        winning_combinations.add(right_col);
        winning_combinations.add(cross1);
        winning_combinations.add(cross2);
        for(HashSet<Integer> combination : winning_combinations){
            if(ur_set.containsAll(combination)){
                return "You win!";
            }else if(comp_set.containsAll(combination)){
                return "Computer wins!";
            }
        }
        if(ur_set.size() + comp_set.size() == 9){
            return "It's a tie!";
        }
        return "";
    }
    public static void main(String[] args) {
        char[][] board = {
            {' ','|',' ','|',' '},
            {'-','-','-','-','-'},
            {' ','|',' ','|',' '},
            {'-','-','-','-','-'},
            {' ','|',' ','|',' '}
        };
        print_Board(board);
        Scanner sc = new Scanner(System.in);
        while (true) { 
            System.out.println("Enter your position (1-9): ");
            int user_pos = sc.nextInt();
            while(user_pos<1 || user_pos>9 || ur_set.contains(user_pos) || comp_set.contains(user_pos)){
                System.out.println("Invalid position! Enter again: ");
                user_pos = sc.nextInt();
            }
            place_holder(board, user_pos, "You");

            String result = check_winner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }


            int computer_pos = generate_computer_pos();
            while(ur_set.contains(computer_pos) || comp_set.contains(computer_pos)){
                computer_pos = generate_computer_pos();
            }
            place_holder(board, computer_pos, "Computer");
            
            result = check_winner();
            if(result.length() > 0){
                System.out.println(result);
                break;
            }
        }
    }
}