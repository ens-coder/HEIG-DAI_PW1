package ch.heigvd.processing;

import ch.heigvd.images.Image;
import java.io.File;

/*
This class is used to apply some filter
to a bmp picture with this lifecycle:
- create a ManagerImg object
- import data from constructor to image manage by ManagerImg
- call the method (filter) that you need
- save the image into output file

invert filter:
- output a negativ picture
    - difference between 255 and the value or pixel

grey (algorithm is inspired by a laboratory in PRG2 from HEIG-VD):
- add RGB and divied by three and add set it to all color of a pixel
- multiply by the percentage choice (luminosity)
 */
public class ProcBmp {
    private final ManagerImg file_bmp = new ManagerImg();
    private Image img_bmp;

    public ProcBmp(File in) {
        img_bmp = file_bmp.read(in);
    }

    public boolean isLoaded() {
        return img_bmp != null;
    }

    public int save(File out) {
        return file_bmp.write(out, img_bmp);
    }

    public void invert() {
        int r,g,b;
        for (int y = 0; y < img_bmp.getHeight(); y++) {
            for (int x = 0; x < img_bmp.getWidth(); x++) {
                r = img_bmp.getRed()[y][x];
                g = img_bmp.getGreen()[y][x];
                b = img_bmp.getBlue()[y][x];
                img_bmp.setRGB(x, y, 255 - r, 255 - g, 255 - b);
            }
        }
    }

    public void gradient(int percentage) {
        int gr;
        double pr = percentage/100.0;
        for (int y = 0; y < img_bmp.getHeight(); y++) {
            for (int x = 0; x < img_bmp.getWidth(); x++) {
                gr = (int) (pr*(img_bmp.getRed()[y][x] + img_bmp.getGreen()[y][x] + img_bmp.getBlue()[y][x])/3);
                img_bmp.setRGB(x, y, gr, gr, gr);
            }
        }
    }
}