package ObjectList;

public class Ship {
    private final int coordinatesHeadRow;
    private final int coordinatesHeadCol;
    private int coordinatesEndRow;
    private int coordinatesEndCol;
    private boolean status = true;
    private final String nameShip;
    private final int shipLength;
    public Ship(String nameShip, String coordinates , int direction){
        this.nameShip = nameShip;
        switch (nameShip) {
            case "Patrol Boat" -> shipLength = 2;
            case "Submarine" -> shipLength = 4;
            case "Destroyer Boat" -> shipLength = 3;
            default -> shipLength = 5;
        }
        coordinatesHeadRow = (int)coordinates.charAt(0) - 'a' + 1;
        if(coordinates.length() == 2){
            coordinatesHeadCol = Integer.parseInt(coordinates.substring(1, 2));
        }
        else{
            coordinatesHeadCol = Integer.parseInt(coordinates.substring(1, 3));
        }
        if(direction == 1){
            coordinatesEndCol = coordinatesHeadCol;
            coordinatesEndRow = coordinatesHeadRow + shipLength - 1;
        }
        else if(direction == 2){
            coordinatesEndCol = coordinatesHeadCol + shipLength - 1;
            coordinatesEndRow = coordinatesHeadRow;
        }
    }

    public int getCoordinatesEndCol() {
        return coordinatesEndCol;
    }
    public int getCoordinatesEndRow() {
        return coordinatesEndRow;
    }
    public int getCoordinatesHeadCol() {
        return coordinatesHeadCol;
    }
    public int getCoordinatesHeadRow() {
        return coordinatesHeadRow;
    }
    public String getNameShip() {
        return nameShip;
    }

    boolean checkShipBeDestroyed(String[][] matrix){
        int numberOfBulletsHit = 0;
        if(coordinatesHeadRow == coordinatesEndRow){
            for(int i = coordinatesHeadCol; i <= coordinatesEndCol; ++i){
                if(matrix[coordinatesHeadRow][i].equals("F")) ++numberOfBulletsHit;
            }
        }
        else{
            for(int i = coordinatesHeadRow; i <= coordinatesEndRow; ++i){
                if(matrix[i][coordinatesHeadCol].equals("F")) ++numberOfBulletsHit;
            }
        }
        if(numberOfBulletsHit == shipLength) status = false;
        return status;
    }
    boolean checkShipPosition(String[][] matrix){
        if(coordinatesEndRow > Screen.sizeScreen) return false;
        else if(coordinatesEndCol > Screen.sizeScreen) return false;
        else if(coordinatesHeadRow <= 0) return false;
        else if(coordinatesHeadCol <= 0) return false;
        else if(coordinatesHeadRow == coordinatesEndRow){
            for(int i = coordinatesHeadCol; i <= coordinatesEndCol; ++i){
                if(!matrix[coordinatesHeadRow][i].equals("~")) return false;
            }
        }
        else{
            for(int i = coordinatesHeadRow; i <= coordinatesEndRow; ++i){
                if(!matrix[i][coordinatesHeadCol].equals("~")) return false;
            }
        }
        return true;
    }
}
