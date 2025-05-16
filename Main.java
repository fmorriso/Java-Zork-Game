class Main {

    public static void main(String[] args) {
        KeyValueSettingsUtilities.setFileName("settings.txt");
        int numFloors = getNumFloors();
        int numRooms = getNumRooms();

        Game g = new Game(numFloors, numRooms);
        g.start();
    }

    private static int getNumRooms() {
        return Integer.parseInt(KeyValueSettingsUtilities.getValue("NUM_ROOMS"));
    }

    private static int getNumFloors() {
        return Integer.parseInt(KeyValueSettingsUtilities.getValue("NUM_FLOORS"));
    }
}