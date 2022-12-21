package taskBook;

public enum TypeOfTask {
    WORKING_TASK("рабочая"),
    PRIVATE_TASK("личная");
    private String title;

    TypeOfTask(String title) {
        this.title = title;
    }

    public static TypeOfTask findTypeOfTaskByTitle(String title){
        for (TypeOfTask typeOfTask : values()) {
            if (typeOfTask.getTitle().equals(title)){
                return typeOfTask;
            }
        }
        return null;
    }
    public String getTitle() {
        return title;
    }

}
