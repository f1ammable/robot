package com.coolteam;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;

public class ImageFilter implements BufferedImageOp {

  public ImageFilter() {
    super();
  }

  @Override
  public BufferedImage filter(BufferedImage src, BufferedImage dest) {
    if (dest == null) dest = createCompatibleDestImage(src, null);

    for (int x = 0; x < src.getWidth(); x++) {
      for (int y = 0; y < src.getHeight(); y++) {
        int rgb = src.getRGB(x, y);
        int a = (rgb >> 24) & 0xff;
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = rgb & 0xff;
        int avg = (r + g + b) / 3;

        rgb = (a << 24) | (avg << 16) | (avg << 8) | avg;
        dest.setRGB(x, y, rgb);
      }
    }

    return dest;
  }

  @Override
  public Rectangle2D getBounds2D(BufferedImage src) {
    return src.getRaster().getBounds();
  }

  @Override
  public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM) {
    if (destCM == null) destCM = src.getColorModel();
    return new BufferedImage(
        destCM,
        destCM.createCompatibleWritableRaster(src.getWidth(), src.getHeight()),
        destCM.isAlphaPremultiplied(),
        null);
  }

  @Override
  public Point2D getPoint2D(Point2D srcPt, Point2D dstPt) {
    if (dstPt == null) dstPt = (Point2D) srcPt.clone();
    dstPt.setLocation(srcPt.getX(), srcPt.getY());
    return dstPt;
  }

  @Override
  public RenderingHints getRenderingHints() {
    return null;
  }
}
