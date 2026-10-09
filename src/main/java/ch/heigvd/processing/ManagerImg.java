package ch.heigvd.processing;

import java.io.*;

import ch.heigvd.images.BmpIO;
import ch.heigvd.images.Image;

/*
The purpose of this class is to:

extract first informations and RGB values of a picture
 into a Image object with a format (here derived from BmpIO)

and

writing all data into a format file (here derived from BmpIO)
 from an Image object to a file.bmp

note:
to this day, only BMP format is supported
with the BmpIO class
 */
public class ManagerImg {
    public Image read(File inputFile) {
        try (InputStream in = new BufferedInputStream(new FileInputStream(inputFile))) {
            return BmpIO.load(in);
        } catch (IOException e) {
            System.err.println("Error: cannot read '" + inputFile + "': " + e.getMessage());
            return null;
        }
    }

    public int write(File outputFile, Image image){
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile))) {
            BmpIO.save(image, out);
            return 0;
        } catch (IOException e) {
            System.err.println("Error: cannot write '" + outputFile + "': " + e.getMessage());
            return 1;
        }
    }
}
