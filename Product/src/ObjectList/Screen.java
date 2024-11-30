package ObjectList;

import java.util.ArrayList;

public class Screen{
    public static int sizeScreen = 10;
    private String[][] matrix = new String[sizeScreen+1][sizeScreen+1];
    private ArrayList<Ship> activeShip = new ArrayList<>();
    public Screen(){
        for(int i = 0; i <= sizeScreen; i++){
            for(int j = 0; j <= sizeScreen; j++){
                matrix[i][j] = "~";
            }
        }

    }

    void addShip(Ship ship){
        activeShip.add(ship);
        for(int i = ship.getCoordinatesHeadRow();i <= ship.getCoordinatesEndRow();++i){
            for(int j = ship.getCoordinatesHeadCol();j <= ship.getCoordinatesEndCol();++j){
                switch (ship.getNameShip()){
                    case "Patrol Boat" -> matrix[i][j] = "P";
                    case "Destroyer Boat" -> matrix[i][j] = "D";
                    case "Submarine" -> matrix[i][j] = "S";
                    case "Battle Boat" -> matrix[i][j] = "B";
                }
            }
        }
    }

    public void display(){
        for(int i = 0; i < sizeScreen; i++){
            if(i==0){
                System.out.print("    ");
            }
            System.out.print("────");
        }
        System.out.print("\n");
        for(int i = 0; i <= sizeScreen; i++){
            if(i == 0){
                System.out.print("   |");
            }
            else if(i >= 10){
                System.out.print("\u001B[47m"+" "+"\u001B[30m"+i+"\u001B[0m" + "|");
            }
            else{
                System.out.print("\u001B[47m"+" "+"\u001B[30m"+i+" "+"\u001B[0m"+"|");
            }
        }
        System.out.println(" ");
        for(int j = 0; j <= sizeScreen; j++){
            System.out.print("────");
        }
        System.out.print("\n");
        for(int i = 1; i <= sizeScreen; i++){
            System.out.print("\u001B[47m"+" "+"\u001B[30m"+ (char)(i-1 + 'a')+" "+"\u001B[0m"+ "|");
            for(int j = 1; j <= sizeScreen; j++){
                if(matrix[i][j].equals("P")){
                    System.out.print(" "+"\u001B[32m"+matrix[i][j]+"\u001B[0m"+" |");
                }
                else if(matrix[i][j].equals("D")){
                    System.out.print(" "+"\u001B[33m"+matrix[i][j]+"\u001B[0m"+" |");

                }
                else if(matrix[i][j].equals("S")){
                    System.out.print(" "+"\u001B[36m"+matrix[i][j]+"\u001B[0m"+" |");
                }
                else if(matrix[i][j].equals("B")){
                    System.out.print(" "+"\u001B[35m"+matrix[i][j]+"\u001B[0m"+" |");
                }
                else if(matrix[i][j].equals("F")){
                    System.out.print(" "+"\u001B[31m"+matrix[i][j]+"\u001B[0m"+" |");
                }
                else if(matrix[i][j].equals("~")){
                    System.out.print(" "+"\u001B[34m"+matrix[i][j]+"\u001B[0m"+" |");
                }
            }
            System.out.print("\n");
            for(int j = 0; j <= sizeScreen; j++){
                if(j == 0){
                    System.out.print("    ");
                }
                else{
                    System.out.print("────");
                }
            }
            System.out.print("\n");
        }
    }

    boolean checkShot(String coordinates){
        if(coordinates.length() == 2){
             return  (matrix[(int)coordinates.charAt(0) - 'a' + 1][Integer.parseInt(coordinates.substring(1, 2))].equals("B")) ||
                     (matrix[(int)coordinates.charAt(0) - 'a' + 1][Integer.parseInt(coordinates.substring(1, 2))].equals("P")) ||
                     (matrix[(int)coordinates.charAt(0) - 'a' + 1][Integer.parseInt(coordinates.substring(1, 2))].equals("D")) ||
                     (matrix[(int)coordinates.charAt(0) - 'a' + 1][Integer.parseInt(coordinates.substring(1, 2))].equals("S"));
        }
        else{
            return  (matrix[(int)coordinates.charAt(0) - 'a'+ 1][Integer.parseInt(coordinates.substring(1, 3))].equals("B")) ||
                    (matrix[(int)coordinates.charAt(0) - 'a'+ 1][Integer.parseInt(coordinates.substring(1, 3))].equals("P")) ||
                    (matrix[(int)coordinates.charAt(0) - 'a'+ 1][Integer.parseInt(coordinates.substring(1, 3))].equals("D")) ||
                    (matrix[(int)coordinates.charAt(0) - 'a'+ 1][Integer.parseInt(coordinates.substring(1, 3))].equals("S"));
        }
    }

    int checkShipBeDestroyed(){
        for(int i = 0; i < activeShip.size(); i++){
            if(!activeShip.get(i).checkShipBeDestroyed(matrix)){
                activeShip.remove(i);
                return i;
            }
        }
        return -1;
    }

    public boolean checkCoordinates(String coordinates){
        if(coordinates.length() < 2 || coordinates.length() > 3) return false;
        if(coordinates.charAt(0) < 'a' || coordinates.charAt(0) > 'z'){
            return false;
        }
        if(coordinates.charAt(1) <= '0' || coordinates.charAt(1) > (char)(sizeScreen + '0')) {
            return false;
        }
        if(coordinates.length() == 3){
            int valueOfCol = Integer.parseInt(coordinates.substring(1, 3));
            if (valueOfCol > sizeScreen) return false;
        };
        return true;
    }

    String[][] getMatrix(){
        return matrix;
    }
}
