package taskBook;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Task {

    private int id;
    private static int counter = 1;
    private String title;
    private String description;
    private TypeOfTask typeOfTask;
    private LocalDate date;
    private LocalTime time;
    private Repeatability repeatability;
    private boolean deleted = false;

    public Task(String title, String description, String typeOfTask, int year, int month, int day,
                int hour, int minutes, String repeatability) {
        this.id = counter++;
        if (title != null && !title.isEmpty() && !title.isBlank()) {
            this.title = title;
        }  else {
            throw new IllegalArgumentException("Введен некорректный заголовок задачи!");
        }
        if (description != null && !description.isEmpty() && !description.isBlank()) {
            this.description = description;
        }  else {
            throw new IllegalArgumentException("Введен некорректное описание задачи!");
        }
        try {
            this.typeOfTask = TypeOfTask.findTypeOfTaskByTitle(typeOfTask.toLowerCase());
        } catch (IllegalArgumentException e){
            System.out.println("Такого типа нет");;
        }
        this.date = LocalDate.of(year, month, day);
        this.time = LocalTime.of(hour, minutes);
        try {
            this.repeatability =  Repeatability.findRepeatabilityByTitle(repeatability.toLowerCase());
        } catch (IllegalArgumentException e){
            System.out.println("Такого типа нет");;
        }
    }


    @Override
    public String toString() {
        return "=====================================================================================================\n"
                + id +
                ". " + title +
                ". " + description +
                ". " + typeOfTask.getTitle() +
                ". " + date +
                ". " + time +
                ". " + repeatability.getTitle() + ".\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(typeOfTask, task.typeOfTask) && Objects.equals(date, task.date) && Objects.equals(time, task.time) && repeatability == task.repeatability;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, typeOfTask, date, time, repeatability);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeOfTask getTypeOfTask() {
        return typeOfTask;
    }

    public void setTypeOfTask(TypeOfTask typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    public Repeatability getRepeatability() {
        return repeatability;
    }

    public void setRepeatability(Repeatability repeatability) {
        this.repeatability = repeatability;
    }


}
