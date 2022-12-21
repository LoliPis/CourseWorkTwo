package taskBook;

public enum Repeatability {
    DISPOSABLE("однократная"),
    DAILY("ежедневная"),
    WEEKLY("еженедельная"),
    MONTHLY("ежемесячная"),
    ANNUAL("ежегодная");

    private final String title;

    Repeatability(String title) {
        this.title = title;
    }

    public static Repeatability findRepeatabilityByTitle(String title){
        for (Repeatability repeatability : values()) {
            if (repeatability.getTitle().equals(title)){
                return repeatability;
            }
        }
        return null;
    }


    public String getTitle() {
        return title;
    }

}
