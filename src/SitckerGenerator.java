import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class SitckerGenerator {

    public static void generate(InputStream inputStream, String text, String outputFileName) {

        try {

            BufferedImage originalImage = ImageIO.read(inputStream);
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();

            BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
            Graphics2D graphics = (Graphics2D) newImage.getGraphics();

            graphics.drawImage(originalImage, 0, 0, null);
            graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 64));
            graphics.setColor(Color.yellow);
            graphics.drawString(text, 0, 120);

            File file = new File("assets/" + outputFileName + ".png");
            file.getParentFile().mkdirs();

            ImageIO.write(newImage, "png", file);

        } catch (IOException e) {
            e.getMessage();
        }
    }
}
