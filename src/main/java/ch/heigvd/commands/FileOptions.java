package ch.heigvd.commands;

import ch.heigvd.Main;
import java.io.File;
import picocli.CommandLine;

// Options shared by all subcommands, injected with @CommandLine.Mixin
public class FileOptions {

    // Supported output formats, shown in the help message
    public enum OutputFormat {
        BMP
    }

    @CommandLine.Option(names = {"-i", "--input"}, description = "The input image file.", required = true)
    protected File inputFile;

    @CommandLine.Option(names = {"-o", "--output"}, description = "The output image file.", required = true)
    protected File outputFile;

    @CommandLine.Option(
            names = {"-f", "--format"},
            description = "The output image format: ${COMPLETION-CANDIDATES}.",
            defaultValue = "BMP")
    protected OutputFormat outputFormat;

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public OutputFormat getOutputFormat() {
        return outputFormat;
    }
}