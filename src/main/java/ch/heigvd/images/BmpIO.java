package ch.heigvd.images;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class BmpIO {

    // Size of BITMAPFILEHEADER (14) + BITMAPINFOHEADER (40)
    private static final int HEADER_SIZE = 54;

    // Positions of the fields in the header (see BMP format)
    private static final int OFFSET_DATA_OFFSET = 10;
    private static final int OFFSET_WIDTH = 18;
    private static final int OFFSET_HEIGHT = 22;
    private static final int OFFSET_BITS_PER_PIXEL = 28;
    private static final int OFFSET_COMPRESSION = 30;

    public static Image load(InputStream in) throws IOException {
        byte[] header = in.readNBytes(HEADER_SIZE);
        if (header.length < HEADER_SIZE) {
            throw new IOException("the file is too short to be a BMP image");
        }

        if (header[0] != 'B' || header[1] != 'M') {
            throw new IOException("the file is not a BMP image");
        }

        int dataOffset = readIntLittleEndian(header, OFFSET_DATA_OFFSET);
        int width = readIntLittleEndian(header, OFFSET_WIDTH);
        int height = readIntLittleEndian(header, OFFSET_HEIGHT);
        int bitsPerPixel = readShortLittleEndian(header, OFFSET_BITS_PER_PIXEL);
        int compression = readIntLittleEndian(header, OFFSET_COMPRESSION);

        if (bitsPerPixel != 24) {
            throw new IOException(
                    "only 24-bit BMP images are supported (this one has " + bitsPerPixel + " bits per pixel)");
        }

        if (compression != 0) {
            throw new IOException("compressed BMP images are not supported");
        }

        if (dataOffset < HEADER_SIZE) {
            throw new IOException("invalid BMP header: pixel data offset is " + dataOffset);
        }

        if (width <= 0 || height == 0) {
            throw new IOException("invalid image size: " + width + " x " + height);
        }

        // A negative height means the rows are stored from top to bottom instead of bottom to top.
        // We read and write the rows in the same order, so only the number of rows matters here.
        height = Math.abs(height);

        // Protects against corrupted headers that would ask for huge arrays
        if ((long) width * height > Integer.MAX_VALUE / 3) {
            throw new IOException("the image is too large: " + width + " x " + height);
        }

        // Some BMP files have a bigger header: keep everything up to the pixels,
        // so that save() can copy it unchanged
        if (dataOffset > HEADER_SIZE) {
            byte[] extra = in.readNBytes(dataOffset - HEADER_SIZE);
            if (extra.length < dataOffset - HEADER_SIZE) {
                throw new IOException("the BMP header is truncated");
            }
            byte[] fullHeader = Arrays.copyOf(header, dataOffset);
            System.arraycopy(extra, 0, fullHeader, HEADER_SIZE, extra.length);
            header = fullHeader;
        }

        Image image = new Image(header, width, height);
        int[][] red = image.getRed();
        int[][] green = image.getGreen();
        int[][] blue = image.getBlue();
        int padding = rowPadding(width);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // BMP stores the colours in the order blue, green, red
                blue[y][x] = readByte(in);
                green[y][x] = readByte(in);
                red[y][x] = readByte(in);
            }
            // Skip the padding bytes at the end of the row (not part of the image)
            for (int p = 0; p < padding; p++) {
                readByte(in);
            }
        }

        return image;
    }

    public static void save(Image image, OutputStream out) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] red = image.getRed();
        int[][] green = image.getGreen();
        int[][] blue = image.getBlue();
        int padding = rowPadding(width);

        // The header is written unchanged: size, offset and dimensions did not change
        out.write(image.getHeader());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                out.write(blue[y][x]);
                out.write(green[y][x]);
                out.write(red[y][x]);
            }
            for (int p = 0; p < padding; p++) {
                out.write(0);
            }
        }
    }

    // Number of bytes added at the end of each row so that its size is a multiple of 4
    private static int rowPadding(int width) {
        int rowBytes = width * 3;
        return (4 - rowBytes % 4) % 4;
    }

    // Reads one byte (0-255) and fails clearly if the file ends too early
    private static int readByte(InputStream in) throws IOException {
        int value = in.read();
        if (value == -1) {
            throw new IOException("the BMP file is truncated");
        }
        return value;
    }

    // Reads a 4-byte integer stored in little endian at the given position
    private static int readIntLittleEndian(byte[] bytes, int position) {
        int result = 0;
        for (int k = 0; k < 4; k++) {
            // & 0xFF: keeps the byte as 0-255 instead of a signed value
            int value = bytes[position + k] & 0xFF;
            result |= value << (8 * k);
        }
        return result;
    }

    // Reads a 2-byte integer stored in little endian at the given position
    private static int readShortLittleEndian(byte[] bytes, int position) {
        return (bytes[position] & 0xFF) | ((bytes[position + 1] & 0xFF) << 8);
    }
}