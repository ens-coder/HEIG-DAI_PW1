package ch.heigvd;

import ch.heigvd.commands.Gradient;
import ch.heigvd.commands.Invert;
import java.io.File;
import picocli.CommandLine;

@CommandLine.Command(
        description = "Process an input image and write the result to an output image.",
        version = "1.0.0",
        showDefaultValues = true,
        subcommands = {
                Invert.class,
                Gradient.class,
                CommandLine.HelpCommand.class,
        },
        scope = CommandLine.ScopeType.INHERIT,
        mixinStandardHelpOptions = true)
public class Main {
    public static void main(String[] args) {
        // Use the JAR file name in the "Usage:" line (cosmetic)
        String jarFilename =
                new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath())
                        .getName();

        // TODO: setCaseInsensitiveEnumValuesAllowed
        // à enlever si on a que un format img (sert à ignoré la casse pour les enums)
        int exitCode =
                new CommandLine(new Main())
                        .setCommandName(jarFilename)
                        .setCaseInsensitiveEnumValuesAllowed(true)
                        .execute(args);

        System.exit(exitCode);
    }
}