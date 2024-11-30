package System;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScoreBoard{
    static class DataFromWinner{
        String name;
        int numberOfShot;
        int remainShip;
        public DataFromWinner(String name, int numberOfShot, int remainShip){
            this.name = name;
            this.numberOfShot = numberOfShot;
            this.remainShip = remainShip;
        }
    }
    void addPlayerIntoFile(String name, int numberOfShot, int remainShip){
        String data = name +" "+ numberOfShot +" "+ remainShip+"\n";

        try(FileWriter writer = new FileWriter("scoreboard.txt",true)) {
            writer.write(data);
        }
        catch(IOException e){
            System.err.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
    public static void displayScoreBoard(){
        List<DataFromWinner> scoreBoard = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("scoreboard.txt"))) {
            String readData;
            while ((readData = reader.readLine()) != null) {
                String[] parts = readData.split(" ");
                String name = parts[0];
                int numberOfShot = Integer.parseInt(parts[1]);
                int remainShip = Integer.parseInt(parts[2]);
                scoreBoard.add(new DataFromWinner(name,numberOfShot,remainShip));
            }
        }catch (FileNotFoundException e){
            System.out.println("Không tìm thấy file bảng xếp hạng.");
        }catch (IOException e){
            System.err.println("Lỗi khi đọc bảng xếp hạng: " + e.getMessage());
        }
        System.out.printf("%-10s %-20s %-20s %-15s%n", "STT", "Player Name", "Number of Bullets", "Remain Ship");
        System.out.println("----------------------------------------------------------------");
        for (int i = 0;i < scoreBoard.size();++i) {
            System.out.printf("%-10s %-20s %-20s %-15s%n", i + 1, (scoreBoard.get(i)).name,(scoreBoard.get(i)).numberOfShot,(scoreBoard.get(i)).remainShip);
            System.out.println("----------------------------------------------------------------");
        }
    }
}
