package app.cli.screens;

import famework.annotation.Service;

import java.io.PrintStream;

@Service
public class ConfigScreen implements Screen {
    public String message = "";

    private PrintStream output;

    public ConfigScreen(PrintStream output) {
        this.output = output;
    }


    @Override
    public void getContent() {
        this.output.println(message);
        this.message = "";
    }

    @Override
    public void getUsage() {
        this.output.println("--------------------------------------------");
        this.output.println(" \uF0B7 [activate] activate SubscriberName - aktiviert einen Subscriber");
        this.output.println(" \uF0B7 [addStoragePlace] place StorageName Size StorageNumber - Erstellt und aktiviert ein neues Lager");
        this.output.println(" \uF0B7 [storage] StorageName StorageName ...  - Erstellt und aktiviert ein neues Lager");
        this.output.println(" \uF0B7  example: storage 0");
        this.output.println(" \uF0B7  :x back");
        this.output.println("--------------------------------------------");
    }

}
