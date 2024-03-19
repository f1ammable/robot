package com.coolteam;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ImageScanner {
  public ImageScanner() {
    try (InputStream is = ImageScanner.class.getClassLoader().getResourceAsStream("dice.png")) {
      if (is != null) {
        BufferedImageOp op = new ImageFilter();
        BufferedImage src = ImageIO.read(is);
        BufferedImage dst;
        dst = op.filter(src, null);

        File out = new File("filter.png");
        ImageIO.write(dst, "png", out);
        dst.flush();
      } else {
        Log.error("Invalid Image!");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
