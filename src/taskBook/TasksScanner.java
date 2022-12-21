package taskBook;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TasksScanner {
    public static void inputTask(Scanner scanner, TaskList taskList) {
        System.out.print("Введите название задачи: ");
        String taskTitle = scanner.next();
        System.out.println("Введите описание задачи:");
        String taskDescription = scanner.next();
        System.out.println("Введите тип задачи:\nРабочая\nЛичная");
        String taskType = scanner.next();
        System.out.println("Введите дату выполнения задачи в формате ГГГГ-ММ-ДД");
        scanner.nextLine();
        LocalDate taskDate = null;
        while (taskDate == null){
            try {
                taskDate = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Введенная дата некорректная! Напишите в формате ГГГГ-ММ-ДД. - ОБЯЗАТЕЛЬНО");
            }
        }
        System.out.println("Введите время выполнения задачи в формате ЧЧ:ММ");
        LocalTime taskTime = null;
        while (taskTime == null){
            try {
                taskTime = LocalTime.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Введенное время некорректно! Напишите в формате ЧЧ:ММ. : ОБЯЗАТЕЛЬНО");
            }
        }
        System.out.println("Введите переодичность выполнения задачи. Задача может быть: \n" +
                "Однократная\nЕжедневная\nЕженедельная\nЕжемесячная\nЕжегодная");
        String taskRepeatability = scanner.next();
        Task task = new Task(taskTitle, taskDescription, taskType, taskDate.getYear(),
                taskDate.getMonthValue(), taskDate.getDayOfMonth(), taskTime.getHour(),
                taskTime.getMinute(), taskRepeatability);
        taskList.addTask(task);
        System.out.println("Задача успешно добавлена!");
    }

    public static void deleteTask(Scanner scanner, TaskList taskList){
        System.out.println(taskList.toString());
        System.out.print("Введите ID задачи, которую хотите удалить: ");
        int taskId = Integer.parseInt(scanner.next());
        taskList.deleteTaskById(taskId);
        System.out.println("Задача успешно удалена!");
    }

    public static void printTasksByDate(Scanner scanner, TaskList taskList){
        System.out.print("Введите дату на которую хотите задачи: ");
        LocalDate taskDate = LocalDate.parse(scanner.next());
        System.out.println("Задачи на " + taskDate + ":\n" + taskList.getTaskByDate(taskDate).toString());
    }

    public static void editTasksTitleAndDescription(Scanner scanner, TaskList taskList){
        System.out.println(taskList.toString());
        System.out.print("Введите ID задачи, которую хотите отредактировать: ");
        int taskId = Integer.parseInt(scanner.next());
        System.out.println("Вы будете изменять название задачи?");
        if (scanner.next().toLowerCase().equals("да")){
            System.out.println("Введите новое название: ");
            taskList.editOnlyTitle(taskId, scanner.next());
            System.out.println("Название успешно изменено!");
            System.out.println("Вы будете изменять описание задачи?");
            if (scanner.next().toLowerCase().equals("да")){
                System.out.println("Введите новое описание: ");
                taskList.editOnlyDescription(taskId, scanner.next());
                System.out.println("Описание успешно изменено!");
            }
        } else if (scanner.next().toLowerCase().equals("нет")){
            System.out.println("Вы будете изменять описание задачи?");
            if (scanner.next().toLowerCase().equals("да")){
                System.out.println("Введите новое описание: ");
                taskList.editOnlyDescription(taskId, scanner.next());
                System.out.println("Описание успешно изменено!");
            }
        }
    }

    public static void printTasksByPeriod(Scanner scanner, TaskList taskList){
        System.out.print("Введите начальную дату поиска задач в формате ГГГГ-ММ-ДД:");
        LocalDate firstDate = LocalDate.parse(scanner.next());
        System.out.print("Введите конечную дату поиска задач в формате ГГГГ-ММ-ДД:");
        LocalDate secondDate = LocalDate.parse(scanner.next());
        System.out.println("Задачи c " + firstDate + " по " + secondDate + ":\n"
                + taskList.tasksByPeriod(firstDate, secondDate, taskList));
    }

    public static void printMenu() {
        System.out.println(
                "1. Добавить задачу\n2. Удалить задачу\n3. Показать задачи на указанный день\n"
                        + "4. Показать задачи по выбранному периоду\n" + "5. Редактировать заголовок и описание\n"
                        + "6. Показать удаленные задачи\n" + "0. Выход");
    }
}
