package ch.heigvd.commands;

import ch.heigvd.images.BmpIO;
import ch.heigvd.images.Image;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageCommands {

    public static int process(File inputFile, File outputFile) {
        Image image;

        // Read: file -> bytes (FileInputStream) -> buffer of 8 KiB (BufferedInputStream)
        try (InputStream in = new BufferedInputStream(new FileInputStream(inputFile))) {
            image = BmpIO.load(in);
        } catch (IOException e) {
            System.err.println("Error: cannot read '" + inputFile + "': " + e.getMessage());
            return 1;
        }

        // Write: buffer -> bytes -> file, flushed and closed automatically at the end of the try
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile))) {
            BmpIO.save(image, out);
        } catch (IOException e) {
            System.err.println("Error: cannot write '" + outputFile + "': " + e.getMessage());
            return 1;
        }

        return 0;
    }
}