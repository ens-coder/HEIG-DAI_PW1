package ch.heigvd.commands;

import ch.heigvd.Main;
import java.io.File;
import picocli.CommandLine;

// Options shared by all subcommands, injected with @CommandLine.Mixin
public class FileOptions {

    @CommandLine.Option(names = {"-o", "--output"}, description = "The output image file.", required = true)
    protected File outputFile;

    @CommandLine.Option(names = {"-i", "--input"}, description = "The input image file.", required = true)
    protected File inputFile;

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }
}