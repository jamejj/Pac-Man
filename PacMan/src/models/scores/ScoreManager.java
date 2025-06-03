package models.scores;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreManager {
    private static final String FILENAME = "scores.bin";
    private List<GameResult> resultList;

    public ScoreManager() {
        resultList = new ArrayList<>();
    }

    public void addScore(GameResult result) {
        resultList.add(result);
    }

    public List<GameResult> getResultList() {
        return resultList;
    }

    public void load() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))) {
            resultList = (List<GameResult>) in.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("I/O exception");
        } catch (ClassNotFoundException e) {
            System.out.println("Invalid file structure");
        }
    }

    public void save() {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            out.writeObject(resultList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("I/O exception");
        }
    }
}
