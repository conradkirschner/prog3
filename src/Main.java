import PeerServer.Start;
import app.App;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

import static app.Bootstrap.run;
import static app.Bootstrap.setup;

public class Main {
    public static void main(String[] args) {
        // start server
        if (args.length != 0 && args[0].equals("server")) {
            Start startServer = new Start();
            startServer.start();
        }



        BufferedReader cliInput = new BufferedReader(new InputStreamReader(System.in));
        PrintStream output = System.out;
        App app = setup(cliInput, output);

        // if simulation is on, then we don't need any CLI input possibility
        run(app, true);
    }
}