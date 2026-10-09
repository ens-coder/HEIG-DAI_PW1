package ch.heigvd.images;

// In-memory representation of a 24-bit BMP image
public class Image {

    private byte[] header; // copied unchanged when saving
    private final int width;
    private final int height;

    // One 2D array per colour channel, indexed [row][col], values from 0 to 255
    private final int[][] red;
    private final int[][] green;
    private final int[][] blue;

    public Image(byte[] header, int width, int height) {
        this.header = header;
        this.width = width;
        this.height = height;
        this.red = new int[height][width];
        this.green = new int[height][width];
        this.blue = new int[height][width];
    }

    public byte[] getHeader() {
        return header;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getRed() {
        return red;
    }

    public int[][] getGreen() {
        return green;
    }

    public int[][] getBlue() {
        return blue;
    }

    public void setRGB(int x, int y, int r, int g, int b) {
        red[y][x] = r;
        green[y][x] = g;
        blue[y][x] = b;
    }

}