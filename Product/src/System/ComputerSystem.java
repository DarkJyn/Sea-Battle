package System;

import ObjectList.Player;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ComputerSystem {
    ArrayList<Player> players = new ArrayList<>();
    public int settingSizeScreen = 10;
    ScoreBoard scoreBoard = new ScoreBoard();
    public void startGame() throws InterruptedException {
        while(true){
            Terminal.clear();
            MenuList.showMainMenu();
            int choice = Integer.parseInt(InputSystem.sc.nextLine());
            switch(choice) {
                case 1:
                    System.out.print("                                                       Starting");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.print(".");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.print(".");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.print(".");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.print(".");
                    newGame();
                    break;
                case 2:
                    System.out.println("Updating....");
                    TimeUnit.SECONDS.sleep(1);
                    break;
                case 3:
                    Terminal.clear();
                    MenuList.gameName();
                    System.out.print("\n");
                    System.out.println("                                                Current size: " + settingSizeScreen);
                    System.out.print("\n");
                    System.out.print("                                               Enter new size: ");
                    settingSizeScreen = Integer.parseInt(InputSystem.sc.nextLine());
                    break;
                case 4:
                    Terminal.clear();
                    MenuList.rankTitle();
                    ScoreBoard.displayScoreBoard();
                    System.out.println(" ");
                    System.out.println("Press Enter to return");
                    InputSystem.sc.nextLine();
                    break;
                case 5:
                    System.out.println("See u soon!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
                    TimeUnit.SECONDS.sleep(1);
                    break;
            }
        }

    }

    void newGame() throws InterruptedException {
        while(true){
            Terminal.clear();
            MenuList.showGameModeMenu();
            int choice = Integer.parseInt(InputSystem.sc.nextLine());
            switch(choice) {
                case 1:
                    System.out.print("                                                       Starting");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.print(".");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.print(".");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.print(".");
                    TimeUnit.MILLISECONDS.sleep(500);
                    System.out.print(".");
                    setUpPlayer();
                    inGame();
                    return;
                case 2:
                    System.out.println("Updating....");
                    TimeUnit.SECONDS.sleep(1);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice");
                    TimeUnit.SECONDS.sleep(1);
                    break;
            }
        }
    }

    void setUpPlayer() throws InterruptedException {
        int quantityPlayers = 2;
        for(int i = 1;i <= quantityPlayers;++i){
            Terminal.clear();
            System.out.println("Set up PLAYER " + i + " in \n");
            MenuList.printNumber(3);
            TimeUnit.SECONDS.sleep(1);
            Terminal.clear();
            System.out.println("Set up PLAYER " + i + " in \n");
            MenuList.printNumber(2);
            TimeUnit.SECONDS.sleep(1);
            Terminal.clear();
            System.out.println("Set up PLAYER " + i + " in \n");
            MenuList.printNumber(1);
            TimeUnit.SECONDS.sleep(1);
            Player player = new Player(i,settingSizeScreen);
            Terminal.clear();
            player.setInformation();
            players.add(player);
            if(i == 2){
                Terminal.clear();
                MenuList.letStartTitle();
                TimeUnit.SECONDS.sleep(3);
            }
        }
    }

    void inGame(){
        int playerTurn = 1;
        while(!players.get(0).isDefeat() && !players.get(1).isDefeat()){
            playerTurn = (playerTurn == 1) ? 0 : 1;
            while(true){
                Terminal.clear();
                System.out.println(">>> " + players.get(playerTurn).getName() + "'s turn <<<<\n\n");
                System.out.println(">>>Number of bullets fired: " + players.get(playerTurn).score);
                System.out.println(">>>Number of enemy ships destroyed: " + (5 - players.get(1 - playerTurn).getRemainShip()));
                System.out.println(">>>Number of remaining ships: " + players.get(playerTurn).getRemainShip()+"\n\n");
                MenuList.showPlayerOption();
                int playerChoice = Integer.parseInt(InputSystem.sc.nextLine());
                if(playerChoice == 1){
                    players.get(playerTurn).shotShip(players.get(1 - playerTurn));
                    break;
                }
                else if(playerChoice == 2){
                    Terminal.clear();
                    System.out.println("                Player's Board\n");
                    players.get(playerTurn).getPlayerScreen().display();
                    System.out.println(" ");
                    System.out.println("Press Enter to return");
                    InputSystem.sc.nextLine();
                }
                else if(playerChoice == 3){
                    break;
                }
            }
        }
        scoreBoard.addPlayerIntoFile(players.get(playerTurn).getName(),players.get(playerTurn).getScore(),players.get(playerTurn).getRemainShip());
        Terminal.clear();
        MenuList.showPlayerTitle(playerTurn + 1);
        MenuList.isWinnerNotify();
        System.out.println("Press Enter to return to the main menu");
        InputSystem.sc.nextLine();
    }
}
