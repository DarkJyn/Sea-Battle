package ObjectList;

import java.util.ArrayList;
import java.util.Random;
import System.*;

public class Player {
    private String name;
    private final int idPlayer;
    private ArrayList<Ship> playerShip = new ArrayList<>();
    Screen playerScreen = new Screen();
    Screen shotScreen = new Screen();
    public int score = 0;

    public Player(int idPlayer){
        this.idPlayer = idPlayer;
    }
    public boolean isDefeat(){
        return playerShip.isEmpty();
    }
    public void setInformation(){
        Terminal.clear();
        MenuList.showPlayerTitle(idPlayer);
        System.out.print("Enter name: ");
        name = InputSystem.sc.nextLine();
        playerScreen = new Screen();
        setShip();
    }
    void setShip(){
        Terminal.clear();
        MenuList.showPlayerTitle(idPlayer);
        MenuList.setShipOptionMenu();
        int setUpChoice = Integer.parseInt(InputSystem.sc.nextLine());
        if(setUpChoice == 1){
            for(int i = 0;i < 5;++i){
                Terminal.clear();
                playerScreen.display();
                MenuList.showShipChoice();
                String nameShip;
                int shipChoice = Integer.parseInt(InputSystem.sc.nextLine());
                nameShip = switch (shipChoice){
                    case 1 -> "Patrol Boat";
                    case 2 -> "Destroyer Boat";
                    case 3 -> "Submarine";
                    case 4 -> "Battle Boat";
                    default -> {
                        System.out.println("invalid ship choice");
                        yield "";
                    }
                };
                Ship ship;
                while(true){
                    System.out.println("Enter the coordinates of the boat's head:");
                    String coordinates = InputSystem.sc.nextLine();
                    System.out.println("Will your ship be columnar or columnar: ");
                    System.out.println("1. Col");
                    System.out.println("2. Row");
                    int directionChoice = Integer.parseInt(InputSystem.sc.nextLine());
                    ship = new Ship(nameShip, coordinates, directionChoice);
                    if(ship.checkShipPosition(playerScreen.getMatrix())){
                        break;
                    }
                    System.out.println("Let try again!");
                }
                playerShip.add(ship);
                playerScreen.addShip(ship);
                if(i == 4){
                    Terminal.clear();
                    playerScreen.display();
                    System.out.println("Press Enter to continue...");
                    InputSystem.sc.nextLine();
                }
            }
        }
        else if(setUpChoice == 2){
            ArrayList<String> nameTypeShip = new ArrayList<>();
            nameTypeShip.add("Patrol Boat");
            nameTypeShip.add("Patrol Boat");
            nameTypeShip.add("Destroyer Boat");
            nameTypeShip.add("Submarine");
            nameTypeShip.add("Battle Boat");
            for(int i = 0;i < 5;++i){
                while(true){
                    Random random = new Random();
                    String coordinatesFirstLetter = new String (String.valueOf((char) (random.nextInt(10)  + 'a' + 1)));
                    String coordinatesSecondNumber =  Integer.toString(random.nextInt(10) + 1);
                    Ship ship = new Ship(nameTypeShip.get(i),coordinatesFirstLetter + coordinatesSecondNumber, random.nextInt(2) + 1);
                    if(ship.checkShipPosition(playerScreen.getMatrix())){
                        playerShip.add(ship);
                        playerScreen.addShip(ship);
                        break;
                    }
                }
            }
            Terminal.clear();
            MenuList.showPlayerTitle(idPlayer);
            playerScreen.display();
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("Press Enter to continue...");
            InputSystem.sc.nextLine();
        }
    }
    public void shotShip(Player Enemy){
        Terminal.clear();
        System.out.println(name + "'s turn");
        System.out.println("FIRE!!! \n");
        System.out.println("               Enemy's Board\n");
        shotScreen.display();
        System.out.println(" ");
        String coordinates;
        while(true){
        System.out.println("Enter the coordinates to fire:");
            coordinates = InputSystem.sc.nextLine();
            if(shotScreen.checkCoordinates(coordinates)){
                break;
            }
            System.out.println("\nCannot fire to outside position !!\n");
        }
        if(Enemy.playerScreen.checkShot(coordinates)){
            MenuList.hitNotify();
            if(coordinates.length() == 3){
                shotScreen.getMatrix()[(int)coordinates.charAt(0) - 'a' + 1][Integer.parseInt(coordinates.substring(1, 3))] = "F";
                Enemy.playerScreen.getMatrix()[(int)coordinates.charAt(0) - 'a' + 1][Integer.parseInt(coordinates.substring(1, 3))] = "F";
            }
            else{
                shotScreen.getMatrix()[(int)coordinates.charAt(0) - 'a' + 1][Integer.parseInt(coordinates.substring(1, 2))] = "F";
                Enemy.playerScreen.getMatrix()[(int)coordinates.charAt(0) - 'a' + 1][Integer.parseInt(coordinates.substring(1, 2))] = "F";
            }
            int checkShipDown = Enemy.playerScreen.checkShipBeDestroyed();
            if(checkShipDown != -1){
                System.out.println("\n");
                MenuList.sunkNotify();
                Enemy.playerShip.remove(checkShipDown);
            }
            System.out.println("You have one more bullet!!!!");
            System.out.println("Press Enter to continue shot...");
            InputSystem.sc.nextLine();
            if(!Enemy.isDefeat()) shotShip(Enemy);
        }
        else{
            if(coordinates.length() == 3){
                shotScreen.getMatrix()[(int)coordinates.charAt(0) - 'a' + 1][Integer.parseInt(coordinates.substring(1, 3))] = "F";
                Enemy.playerScreen.getMatrix()[(int)coordinates.charAt(0) - 'a' + 1][Integer.parseInt(coordinates.substring(1, 3))] = "F";
            }
            else{
                shotScreen.getMatrix()[(int)coordinates.charAt(0) - 'a' + 1][Integer.parseInt(coordinates.substring(1, 2))] = "F";
                Enemy.playerScreen.getMatrix()[(int)coordinates.charAt(0) - 'a' + 1][Integer.parseInt(coordinates.substring(1, 2))] = "F";
            }
            MenuList.missNotify();
            System.out.println("Change Turn...");
            System.out.println("Press Enter to continue...");
            InputSystem.sc.nextLine();
        }
    }
    public String getName(){
        return name;
    }

    public Screen getPlayerScreen(){
        return playerScreen;
    }
    public int getRemainShip(){
        return playerShip.size();
    }
}
