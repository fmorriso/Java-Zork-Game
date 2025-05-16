public enum Commands {
    LEFT("Left"),
    RIGHT("Right"),
    UP("Up"),
    DOWN("Down"),
    GRAB("Grab"),
    FIGHT("Fight"),
    HELP("Help"),;

    private final String displayName;

    Commands(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
