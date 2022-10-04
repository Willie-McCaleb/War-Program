/*Name: Willie McCaleb
        Current Date: 02/25/21
        Sources Consulted: Stack Overflow, Youtube
        By submitting this work, I attest that it is my original work and that I did
        not violate the University of Mississippi academic policies set forth in the
        M book.*/

import java.util.Scanner; //Imports the Scanner
import java.util.ArrayList; //Imports Array Lists

public class War{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in); //Creates a new Scanner called 'scan'

//Initialize String variables:
        String player1;
        String player2;
        String option;
        String roundwinner = "";
        String gamewinner = "";

//Initialize integer variables:
        int temp;
        int play = 1;

//Initialize Boolean variables:
        boolean winner;

        while (play != 0){ //This while-loop will run as long as the user decides to play (play value = 1)
            winner = false; //For the beginning of each game, winner values are set to false
            Deck deck = new Deck(); //Creates a new Deck object called deck
            deck.shuffle(); //Calls the method shuffle for the deck

//The lines below create 3 Array Lists of type Card for both players' Deck and the duel Deck
            ArrayList<Card> Deck1 = new ArrayList<>(44);
            ArrayList<Card> Deck2 = new ArrayList<>(44);
            ArrayList<Card> duelDeck = new ArrayList<>(9);
            divideDeck(Deck1, Deck2, deck); //Uses the method divideDeck to Divide the deck between the two players

//The lines take input from the user for and print out the expected results:
            System.out.println("Welcome to Head to Head! Let's Play!\n");
            System.out.println("Head to head, where each player randomly get numbers. Players than each turn a number over");
            System.out.println("The highest number would win both numbers. The game is over once a player runs out of cards.");
            System.out.println();
            System.out.print("Enter the first player's name: ");
            player1 = scan.nextLine();
            System.out.print("Enter the second player's name: ");
            player2 = scan.nextLine();
            System.out.printf("%-20s %-15s %-20s %-15s %-20s\n", player1 ,"#Cards" , player2 , "#Cards", "Winner");


            while (!winner){ //This while-loop will run as long as no winner exists (winner value = false)
                System.out.printf("%-20s %-15d %-20s %-15d ", Deck1.get(0) , Deck1.size() , Deck2.get(0) ,Deck2.size());


                int x = Deck1.get(0).isGreater(Deck2.get(0));
                if (x == 1){ //When Deck1's first card has more value than Deck 2's first card
//The lines below adds the first cards of both decks to the end and removes the first card of the deck:
                    Deck1.add(Deck2.get(0));
                    Deck1.add(Deck1.get(0));
                    Deck1.remove(0);
                    Deck2.remove(0);
                    roundwinner = player1;
                }
                if (x == 2){ //When Deck2's first card has more value than Deck 1's first card
//The lines below adds the first cards of both decks to the end and removes the first card of the deck:
                    Deck2.add(Deck1.get(0));
                    Deck2.add(Deck2.get(0));
                    Deck2.remove(Deck2.get(0));
                    Deck1.remove(0);
                    roundwinner = player2;
                }
                if (x == 3) //Otherwise, a duel occurs
                    roundwinner = "DUEL";
                System.out.println(roundwinner);
                if (x == 3){ //^
                    roundwinner = "DUEL";
                    duel();
                    System.out.printf("%-20s %-15d %-20s %-15d ", Deck1.get(0) , Deck1.size() , Deck2.get(0) ,Deck2.size());
                    if (Deck1.size() <= 4){ //When the first player has 4 cards or less, they do not have enough cards to go to war and the winner will automatically be the other player
                        roundwinner = player2;
                        System.out.println(roundwinner);
                        endduel();
                        gamewinner = player2;
                        break; //Breaks out of the loop
                    }
                    else if (Deck2.size() <= 4){ //When the first player has 4 cards or less, they do not have enough cards to go to war and the winner will automatically be the other player
                        roundwinner = player1;
                        System.out.println(roundwinner);
                        endduel();
                        gamewinner = player1;
                        break; //Breaks out of the loop
                    }

                    duelDeckTransfer(Deck1, Deck2, duelDeck); //warDeckTransfer is a method which adds 4 cards of both players to the warDeck
                    temp = duelDeck.get(3).isGreater(duelDeck.get(7));

//The lines below analyze which of the two players wins the War, the winner gains all cards from the War Deck:
                    if(temp == 1){ //When player 1 wins..
                        while (duelDeck.size() != 0) {
                            Deck1.add(duelDeck.get(0));
                            duelDeck.remove(0);
                            roundwinner = player1;
                        }
                    }
                    else if (temp == 2){ //When player 2 wins..
                        while (duelDeck.size() != 0){
                            Deck2.add(duelDeck.get(0));
                            duelDeck.remove(0);
                            roundwinner = player2;
                        }
                    }
//^

                    else{ //Otherwise, another War begins
                        shift(duelDeck); //Uses the method shift which move all the cards to the right
                        temp = duelDeck.get(3).isGreater(duelDeck.get(7));

//The lines below analyze which of the two players wins the second War, the winner gains all cards from the War Deck:
                        if (temp == 1){ //When player 1 wins..
                            while (duelDeck.size() != 0){
                                Deck1.add(duelDeck.get(0));
                                duelDeck.remove(0);
                                roundwinner = player1;
                            }
                        }
                        else if (temp == 2){ //When player 2 wins..
                            while (duelDeck.size() != 0){
                                Deck2.add(duelDeck.get(0));
                                duelDeck.remove(0);
                                roundwinner = player2;
                            }
                        }
//^
                    }
                    System.out.println(roundwinner);
                    endduel();
                }
                if (Deck1.size() == 40){ //When Deck 1 has 52 cards, player 1 wins, loop terminates
                    gamewinner = player1;
                    winner = true;
                }
                if (Deck2.size() == 40){ //When Deck 2 has 52 cards, player 2 wins, loop terminates
                    gamewinner = player2;
                    winner = true;
                }
            }//Loop ends when there is a winner (winner = true)

//The lines below print out the expected results, and asks if the user wants to play again:
            System.out.println(gamewinner + " WINS! Congratulations");
            System.out.print("Play again (y/n)? ");

//The lines below scan for the user's response then proceeds to different options based on the response
            option = scan.nextLine().toLowerCase();
            option = "" + option.charAt(0);
            while (!(option.equals("y") || option.equals("n"))){ //This while-loop continuously runs until the user has provided a valid response
                System.out.print("Invalid option. Please enter y or n: ");
                option = scan.nextLine().toLowerCase();
                option = "" + option.charAt(0);
            }
            if (option.equals("y")){ //When the user decides to keep playing..
                play = 1;
                System.out.println();
            }
            else{ //When the user decides to terminate the game..
                play = 0;
            }
        }//Loop ends when user decides to stop playing (play = 0)
        System.out.print("Thanks for playing! ");
    }

    public static void duel(){ //war is a method that prints out the War message
        System.out.println("************************************************DUEL*******************************************");
    }

    public static void endduel(){ //endwar is a method that prints out the End War message
        System.out.println("************************************************END DUEL***************************************");
    }

    public static void shift(ArrayList<Card> duelDeck) { //shift is a method which moves all the cards in warDeck to the right
        duelDeck.add(duelDeck.get(3));
        duelDeck.remove(3);
        duelDeck.add(2, duelDeck.get(7));
        duelDeck.remove(7);
    }

    public static void divideDeck(ArrayList<Card> Deck1, ArrayList<Card> Deck2, Deck deck){ //divideDeck is a method which divides the Deck evenly for both players
        for (int x = 0; x < 20; x++){
            Deck1.add(deck.getFromShuffledDeck(x));
            Deck2.add(deck.getFromShuffledDeck(x+20));
        }
    }

    public static void duelDeckTransfer(ArrayList<Card> Deck1, ArrayList<Card> Deck2, ArrayList<Card> duelDeck){ //warDeckTransfer is a method that populates the war Deck by taking 4 cards from each players' deck
        for (int y = 0; y < 4 && Deck1.size() < 36; y++){
            duelDeck.add(Deck1.get(0));
            Deck1.remove(0);
        }
        for (int y = 0; y < 4 && Deck2.size() < 36; y++){
            duelDeck.add(Deck2.get(0));
            Deck2.remove(0);
        }
    }
}