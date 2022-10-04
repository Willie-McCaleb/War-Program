import java.util.ArrayList; //Imports Array Lists
import java.util.Random; //Imports the Randomizer
public class Deck{

    Random random = new Random();
    //Initialize instance variables:
    private ArrayList <Card> Deck = new ArrayList <Card>(40);
    private ArrayList <Card> Shuffled = new ArrayList <Card>(40);

    //Initialize constructor:
    public Deck(){
//Initialize the string to blank values to avoid initialization error messages:
        String suit = "";
        String rank = "";

//The lines below are a combination of if-else statements and nested for-loops which completes a Deck by assigning their corresponding suites and ranks
        for(int x = 0; x < 4; x++){
            if(x == 0){
                suit = "Clubs";
            }
            else if(x == 1){
                suit = "Hearts";
            }
            else if(x == 2){
                suit = "Spades";
            }
            else if(x == 3){
                suit = "Diamonds";
            }
            for(int y = 1; y <= 13; y++){
                if(y == 1){
                    rank = "Ace";
                }
                else if(y > 1 && y < 11){
                    rank = "" + y;
                }
                else if(y == 11){
                    rank = "Jack";
                }
                else if(y == 12){
                    rank = "Queen";
                }
                else if(y == 13){
                    rank = "King";
                }
                Card card = new Card(suit, rank);
                Deck.add(card);
            }
//^
        }
    }

    public Card getFromShuffledDeck(int x){ //getFromShuffled is a method used when the user draws a Card from the shuffled deck
        if(!Shuffled.isEmpty()){
            return Shuffled.get(x);
        }
        return null;
    }

    public void shuffle(){ //shuffle is a method that takes all Card objects from the Normal deck and shuffles the card in a random order
        ArrayList<Integer> usednums = new ArrayList<>(40); //usednums is an integer Array List that collects the used indexes
        int counter = 0;
        while (counter < 40){ //This nested while-loop traversers through all Card objects in the Deck and keeps adding Card objects to the Shuffled deck in a random order
            int cardnum = random.nextInt((39 - 0) + 1) + 0;
            while (usednums.indexOf(cardnum) == -1){
                usednums.add(counter, cardnum); //Gets added to the usednums Array List
                Shuffled.add(counter, Deck.get(cardnum)); //Gets added to the Shuffled Array List
                counter++; //Increments the counter
            }
        }
    }
}

