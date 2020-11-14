import java.util.Scanner;

public class BlackJack {

    public static void main(String[] args) {

        System.out.println("Welcome To BlackJack!");

        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        double playerMoney = 100.00;
        Scanner userInput = new Scanner(System.in);

//        Game loop
        while (playerMoney > 0) {
            System.out.println("you have £" + playerMoney + "how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if (playerBet > playerMoney) {
                System.out.println("You are poor please get the FUDGE OUT!");
                break;
            }

            boolean endRound = false;

            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);
            while (true) {
                System.out.println("Your hand");
                System.out.println(playerDeck.toString());
                System.out.println("Your hand is: " + playerDeck.cardsValue());

                System.out.println("Dealer Hand: " + dealerDeck.getCards(0).toString() + "and [Hidden]");

                System.out.println("Would you like to (1)Hit or (2)Stick?");
                int response = userInput.nextInt();

                if (response == 1) {
                    playerDeck.draw(playingDeck);
                    System.out.println("You draw a: " + playerDeck.getCards(playerDeck.deckSize() - 1).toString());
                    if (playerDeck.cardsValue() > 21) {
                        System.out.println("BUST currently values at: " + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }
                if (response == 2) {
                    break;
                }
            }
            System.out.println("Dealer Cards: " + dealerDeck.toString());

            if (dealerDeck.cardsValue() > playerDeck.cardsValue() && endRound == false) {
                System.out.println("Dealer beats you");
                playerBet -= playerMoney;
                endRound = true;
            }
            while ((dealerDeck.cardsValue() < 17) && endRound == false) {
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer drew: " + dealerDeck.getCards(dealerDeck.deckSize() - 1).toString());
            }
            System.out.println("Dealer's hand is: " + dealerDeck.cardsValue());
            if ((dealerDeck.cardsValue() > 21) && endRound == false) {
                System.out.println("Dealer bust you win!!!");
                playerMoney += playerBet;
                endRound = true;
            }

            if ((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false) {
                System.out.println("Game is a Draw");
                endRound = true;
            }

            if ((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false) {
                System.out.println("You WIN");
                playerMoney += playerBet;
                endRound = true;
            }

            else if(endRound == false){
                System.out.println("You lose this hand.");
                playerMoney -= playerBet;
                endRound = true;
            }

            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);
            System.out.println("End of hand.");
        }

            System.out.println("Game Over u SUCK!!!!!");
        }

    }
