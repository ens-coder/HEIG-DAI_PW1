package ch.heigvd.commands;

import java.util.concurrent.Callable;
import picocli.CommandLine;

// TODO: verif description [ENiS]

@CommandLine.Command(name = "gradient",
        description = {
                "Converts the input image towards black and white.",
                "The percentage sets how strong the conversion is:",
                "  0 keeps the original colours, 100 gives a full black-and-white image,",
                "  values in between blend the original colours with their grey level."
        })
public class Gradient implements Callable<Integer> {

    // Injects the -i, -o and -f options into this subcommand
    @CommandLine.Mixin protected FileOptions files;

    @CommandLine.Option(
            names = {"-p", "--percentage"},
            description = "The intensity of the gradient, from 0 to 100.",
            defaultValue = "50")
    protected int percentage;

    @Override
    public Integer call() {
        // TODO: validate the percentage and call the image processing
        System.out.println("gradient " + percentage + "%: " + files.getInputFile() + " -> " + files.getOutputFile());
        return 0;
    }
}