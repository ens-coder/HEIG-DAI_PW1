package ch.heigvd.processing;

import ch.heigvd.images.Image;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
This class is used to do some unit test

Each test fixes a behaviour that the filters must respect

Covered behaviour:
- invert() each colour pixel / channel become inverted
- gradient(percentage) - each pixel become grey and we
    apply a luminosity (gradient) filter

 */

class ProcBmpTest {
    //set some color test
    int[] colorFill = {0, 255, 128};
    int height = 5000;
    int width = 10000;
    int header = 54;
    int percentage_gradient = 50;

    @Test
    void invert() {
        System.out.println("Checking test invert()...");

        Image img_bmp = new Image(new byte[header], width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                img_bmp.setRGB(x, y, colorFill[1], colorFill[1], colorFill[1]);
                //System.out.print(img_bmp.getRed()[y][x]);
            }
            //System.out.println("/");
        }

        ProcBmp tmp = new ProcBmp(img_bmp);
        tmp.invert();

        //test the test
        //img_bmp.setRGB(299, 299, colorFill[1], colorFill[0], colorFill[0]);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                assertEquals(colorFill[0], img_bmp.getRed()[y][x]);
                assertEquals(colorFill[0], img_bmp.getGreen()[y][x]);
                assertEquals(colorFill[0], img_bmp.getBlue()[y][x]);
            }
        }

        System.out.println("invert() test: passed");
    }

    @Test
    void gradient() {
        System.out.println("Checking test gradient(50)...");

        Image img_bmp = new Image(new byte[header], width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                img_bmp.setRGB(x, y, colorFill[2], colorFill[2], colorFill[2]);
            }
        }

        ProcBmp tmp = new ProcBmp(img_bmp);
        tmp.gradient(percentage_gradient);

        //128 / 2 = 64
        int moy = 64;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                assertEquals(moy, img_bmp.getRed()[y][x]);
                assertEquals(moy, img_bmp.getGreen()[y][x]);
                assertEquals(moy, img_bmp.getBlue()[y][x]);
            }
        }

        System.out.println("gradient(50) test: passed");
    }
}