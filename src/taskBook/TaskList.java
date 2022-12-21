package taskBook;

import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class TaskList {

    private Map<Integer, Task> taskMap =  new LinkedHashMap<>();
    private Set deletedTasks = new LinkedHashSet();

    public void addTask(Task task){
        taskMap.put(task.getId(), task);
    }

    public Set<Task> getTaskByDate(LocalDate date){
        Set<Task> tasksList = new HashSet<>();
        for (Integer integer : taskMap.keySet()) {
            if (taskMap.get(integer).getRepeatability() != null && !taskMap.get(integer).isDeleted()) {
                switch (taskMap.get(integer).getRepeatability()) {
                    case DISPOSABLE:
                        if (taskMap.get(integer).getDate() == date) {
                            tasksList.add(taskMap.get(integer));
                        }
                    case DAILY:
                        tasksList.add(taskMap.get(integer));
                    case WEEKLY:
                        if ((DAYS.between(taskMap.get(integer).getDate(), date)) % 7 == 0) {
                            tasksList.add(taskMap.get(integer));
                        }
                    case MONTHLY:
                        if (taskMap.get(integer).getDate().getDayOfMonth() == date.getDayOfMonth()) {
                            tasksList.add(taskMap.get(integer));
                        }
                    case ANNUAL:
                        if (taskMap.get(integer).getDate().getDayOfMonth() == date.getDayOfMonth() &&
                                taskMap.get(integer).getDate().getMonthValue() == date.getMonthValue()) {
                            tasksList.add(taskMap.get(integer));
                        }
                    default:
                }
            }
        }
        return tasksList;
    }

    public void deleteTaskById(int id){
        taskMap.get(id).setDeleted(false);
        deletedTasks.add(taskMap.get(id));
    }

    public void showDeletedTasks(){
         deletedTasks.forEach(System.out::println);
    }

    public Set<Task> tasksByPeriod(LocalDate firstDate, LocalDate  secondDate, TaskList tasks){
        Set<Task> joinedSet = new HashSet();
        for (LocalDate i = firstDate; i.isBefore(secondDate) ; i = i.plusDays(1)) {
           joinedSet.addAll(tasks.getTaskByDate(i));
        }
        return joinedSet;
    }

    public void editOnlyTitle(int id, String newTitle){
        taskMap.get(id).setTitle(newTitle);
    }
    public void editOnlyDescription(int id, String newDescription){
        taskMap.get(id).setDescription(newDescription);
    }

    @Override
    public String toString() {
        return taskMap.values().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskList taskList = (TaskList) o;
        return Objects.equals(taskMap, taskList.taskMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskMap);
    }


}
