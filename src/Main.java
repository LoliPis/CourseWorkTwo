import taskBook.Task;
import taskBook.TaskList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static taskBook.TasksScanner.*;


public class Main {
    public static TaskList taskList = new TaskList();
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner, taskList);
                            break;
                        case 2:
                            deleteTask(scanner, taskList);
                            break;
                        case 3:
                            printTasksByDate(scanner, taskList);
                            break;
                        case 4:
                            printTasksByPeriod(scanner, taskList);
                            break;
                        case 5:
                            editTasksTitleAndDescription(scanner, taskList);
                            break;
                        case 6:
                            taskList.showDeletedTasks();
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }
}