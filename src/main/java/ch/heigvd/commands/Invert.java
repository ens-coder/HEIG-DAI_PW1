package ch.heigvd.commands;

import java.util.concurrent.Callable;

import ch.heigvd.processing.ProcBmp;
import picocli.CommandLine;

@CommandLine.Command(name = "invert",
        description = {
                "Inverts the colours of the input image (negative effect).",
                "Each red, green and blue value of every pixel is replaced by 255 minus its value.",
        })
public class Invert implements Callable<Integer> {

    // Injects the -i, -o and -f options into this subcommand
    @CommandLine.Mixin protected FileOptions files;

    @Override
    public Integer call() {
        ProcBmp img = new ProcBmp(files.getInputFile());
        if (!img.isLoaded()) {
            return 1;
        }
        img.invert();
        return img.save(files.getOutputFile());
    }
}