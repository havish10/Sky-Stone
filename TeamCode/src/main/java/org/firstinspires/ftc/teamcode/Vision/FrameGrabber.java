package org.firstinspires.ftc.teamcode.Vision;

import org.corningrobotics.enderbots.endercv.OpenCVPipeline;
import org.firstinspires.ftc.teamcode.Util.Vector2d;
import org.firstinspires.ftc.teamcode.Vision.Filters.ColorFilter;
import org.firstinspires.ftc.teamcode.Vision.Filters.Filter;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class FrameGrabber extends OpenCVPipeline {
  public static Vector2d offset = new Vector2d(50, 50);
  public static Vector2d offset1 = new Vector2d(0, 0);

  public static double[] f = {0};
  public static double len = 0;
  public static String color = "";
  public double threshold = 100;
  private Filter colorFiler = new ColorFilter();
  private Mat workingMat = new Mat();
  private Mat displayMat = new Mat();
  private Mat yellowMask = new Mat();
  private Mat hiarchy = new Mat();
  private double sum = 0;
  private double sum1 = 0;

  @Override
  public Mat processFrame(Mat rgba, Mat gray) {
    rgba.copyTo(displayMat);
    rgba.copyTo(workingMat);
    rgba.release();

    colorFiler.processFrame(workingMat.clone(), yellowMask, threshold);

    Imgproc.rectangle(
            displayMat,
            new Point(0 + offset.x, 0 + offset.y),
            new Point(20 + offset.x, 20 + offset.y),
            new Scalar(255, 0, 0),
            1,
            0);

    Imgproc.rectangle(
            displayMat,
            new Point(0 + offset1.x, 0 + offset1.y),
            new Point(20 + offset1.x, 20 + offset1.y),
            new Scalar(0, 255, 0),
            1,
            0);


    for (int i = (int) offset.x; i < 20 + offset.x; i++) {
      for (int j = (int) offset.y; j < 20 + offset.y; j++) {
        sum += yellowMask.get(i, j)[0];
      }
    }
    for (int i = (int) offset1.x; i < 20 + offset1.x; i++) {
      for (int j = (int) offset1.y; j < 20 + offset1.y; j++) {
        sum1 += yellowMask.get(i, j)[0];
      }
    }

    sum /= 100;
    sum1 /= 100;

    if (sum > 100) {
      color = "yellow";
    } else {
      color = "not yellow";
    }
    
    if (sum1 > 100) {
      color.concat(", yellow");
    } else {
      color.concat(", not yellow");
    }



    return displayMat;
  }
}