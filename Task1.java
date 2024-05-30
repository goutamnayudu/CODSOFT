import java.util.*;
public class numberGame{
    Scanner sc=new Scanner(System.in);
    void execute(int rand_int){
        int limit=5;
        while(limit>0){
            System.out.print("Guess any number from 0 - 100:");
            int num=sc.nextInt();
            if(rand_int==num){
                System.out.println("You have gussed the correct number");
                break;
            }
            else if(rand_int>num)
                System.out.println("You have gussed the number too low!");
            else
                System.out.println("You have gussed the number too high!");
            limit--;
        }
        if(limit==0)
            System.out.println("You have reached to the maximum limit! The correct answer is:"+rand_int);
        System.out.println("Your total score is:"+limit*100);
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        Random rand=new Random();
        while(true){
            int rand_int=rand.nextInt(101);
            numberGame game = new numberGame();
            game.execute(rand_int);
            System.out.print("If you want to exit the game press 0 and if you want to continue press 1:");
            int inp=sc.nextInt();
            if(inp==0)
                break;
        }
        System.out.println("Thank you for playing this game and hope you like it!");
    }
}