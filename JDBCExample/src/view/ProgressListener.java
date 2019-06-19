package view;

public interface ProgressListener {
    /**
     * List of possible views of the application
     */
    enum AppState {
        LAUNCHER,MAIN
    }

    /**
     * Method responsible of swinging the view from a JPanel to another
     * @param currentState it will tell the JPanel that the program should call
     */
    void progressFrom(AppState currentState);
}

