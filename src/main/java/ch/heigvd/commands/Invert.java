package ch.heigvd.commands;

import java.util.concurrent.Callable;
import picocli.CommandLine;

// TODO: verif description

@CommandLine.Command(name = "invert",
        description = {
                "Inverts the colours of the input image (negative effect).",
                "Each red, green and blue value of every pixel is replaced by 255 minus its value.",
                "Inverting an image twice gives back the original image.",
        })
public class Invert implements Callable<Integer> {

    // Injects the -i, -o and -f options into this subcommand
    @CommandLine.Mixin protected FileOptions files;

    @Override
    public Integer call() {
        // TODO: call the image processing
        System.out.println("invert: " + files.getInputFile() + " -> " + files.getOutputFile());
        return 0;
    }
}