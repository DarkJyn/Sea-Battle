package ObjectList;

public class ShipManager {
    Ship ship;
    boolean checkShipBeDestroyed(String[][] matrix){
        int numberOfBulletsHit = 0;
        if(coordinatesHeadRow == coordinatesEndRow){
            for(int i = coordinatesHeadCol; i <= coordinatesEndCol; ++i){
                if(matrix[coordinatesHeadRow][i].equals("[F]")) ++numberOfBulletsHit;
            }
        }
        else{
            for(int i = coordinatesHeadRow; i <= coordinatesEndRow; ++i){
                if(matrix[i][coordinatesHeadCol].equals("[F]")) ++numberOfBulletsHit;
            }
        }
        if(numberOfBulletsHit == shipLength) status = false;
        return status;
    }
}
