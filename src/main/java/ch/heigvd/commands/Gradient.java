package ch.heigvd.commands;

import java.util.concurrent.Callable;

import ch.heigvd.processing.ProcBmp;
import picocli.CommandLine;



@CommandLine.Command(name = "gradient",
        description = {
                "Converts the input image towards black and white.",
                "The percentage sets how strong the luminosity is:",
                "  0 is no luminosity , 100 gives a full luminosity,",
        })
public class Gradient implements Callable<Integer> {

    // Injects the -i, -o options into this subcommand
    @CommandLine.Mixin protected FileOptions files;

    @CommandLine.Option(
            names = {"-p", "--percentage"},
            description = "The intensity of the gradient, from 0 to 100.",
            defaultValue = "100")
    protected int percentage;

    @Override
    public Integer call() {
        System.out.println("gradient " + percentage + "%: " + files.getInputFile() + " -> " + files.getOutputFile());
        ProcBmp img = new ProcBmp(files.getInputFile());
        if (!img.isLoaded()) {
            return 1;
        }
        img.gradient(percentage);
        return img.save(files.getOutputFile());
    }
}