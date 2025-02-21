package minion;

import minion.task.Deadline;
import minion.task.Event;
import minion.task.Task;
import minion.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    File f = new File(FILE_PATH);
    private static final String FILE_PATH = "./taskInfo.txt";

    public void saveString(String[] tasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);

            for (String task : tasks) {
                fw.append(task);
                fw.append(System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to save tasks!");
        }
    }

    public String getString() throws MinionException {
        StringBuilder tasks = new StringBuilder();
        if (!f.exists()) {
            return tasks.toString();
        }
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                tasks.append(s.next());
                tasks.append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load file!");
        }
        return tasks.toString();
    }

}
