package controller.launcher;

public class Launcher {
    /**
     * Start the server and then a client by default
     * @param args
     */
    public static void main(String[] args) {
        ServerLauncher.main(args);
        ClientLauncher.main(args);
    }
}