package org.firstinspires.ftc.teamcode.util;

import java.util.ArrayList;

public class MyMath {

  public static double AngleWrap(double angle) {
    while (angle < -Math.PI) {
      angle += 2 * Math.PI;
    }
    while (angle > Math.PI) {
      angle -= 2 * Math.PI;
    }
    return angle;
  }

  /**
   * @param c coordinate of the center of the circle
   * @param r radius of the circle
   * @param p1 coordinate of the first point of the line
   * @param p2 coordinate of the second point of the line
   * @return An array of the intersection points
   */
  public static ArrayList<Vector2d> lineCircleIntersection(
      Vector2d c, double r, Vector2d p1, Vector2d p2) {

    // Make sure the points don't exactly line up so the slopes work
    if (Math.abs(p1.getY() - p2.getY()) < 0.003) {
      p1.setY(p2.getY() + 0.003);
    }
    if (Math.abs(p1.getX() - p2.getX()) < 0.003) {
      p1.setX(p2.getX() + 0.003);
    }

    // Calculate Slope of the line
    double m1 = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());

    // the first coefficient in the quadratic
    double quadraticA = 1.0 + Math.pow(m1, 2);

    // shift one of the line's points so it is relative to the circle
    double x1 = p1.getX() - c.getX();
    double y1 = p1.getY() - c.getY();

    // the second coefficient in the quadratic
    double quadraticB = (2.0 * m1 * y1) - (2.0 * Math.pow(m1, 2) * x1);

    // the third coefficient in the quadratic
    double quadraticC =
        ((Math.pow(m1, 2) * Math.pow(x1, 2))
            - (2.0 * y1 * m1 * x1)
            + Math.pow(y1, 2)
            - Math.pow(r, 2));

    ArrayList<Vector2d> allPoints = new ArrayList<>();

    try {
      // now solve the quadratic equation given the coefficients
      double xRoot1 =
          (-quadraticB + Math.sqrt(Math.pow(quadraticB, 2) - (4.0 * quadraticA * quadraticC)))
              / (2.0 * quadraticA);

      // we know the line equation so plug into that to get root
      double yRoot1 = m1 * (xRoot1 - x1) + y1;

      // now we can add back in translations
      xRoot1 += c.getX();
      yRoot1 += c.getY();

      // make sure it was within range of the segment
      double minX = p1.getX() < p2.getX() ? p1.getX() : p2.getX();
      double maxX = p1.getX() > p2.getX() ? p1.getX() : p2.getX();
      if (xRoot1 > minX && xRoot1 < maxX) {
        allPoints.add(new Vector2d(xRoot1, yRoot1));
      }

      // do the same for the other root
      double xRoot2 =
          (-quadraticB - Math.sqrt(Math.pow(quadraticB, 2) - (4.0 * quadraticA * quadraticC)))
              / (2.0 * quadraticA);

      double yRoot2 = m1 * (xRoot2 - x1) + y1;
      // now we can add back in translations
      xRoot2 += c.getX();
      yRoot2 += c.getY();

      // make sure it was within range of the segment
      if (xRoot2 > minX && xRoot2 < maxX) {
        allPoints.add(new Vector2d(xRoot2, yRoot2));
      }
    } catch (Exception e) {

    }
    return allPoints;
  }

  /**
   * @param p1 point one
   * @param p2 point two
   * @return distance between them
   */
  public static double distance(Vector2d p1, Vector2d p2) {
    return Math.sqrt(Math.pow((p2.getX() - p1.getX()), 2) + Math.pow((p2.getY() - p1.getY()), 2));
  }

  /**
   * @param p1 First point of the line
   * @param p2 Second point of the line
   * @param point Point to check
   * @return true or false
   */
  public static boolean liesOnLine(Vector2d p1, Vector2d p2, Vector2d point) {
    if (distance(p1, point) + distance(p2, point) == distance(p1, p2)) {
      return true;
    }
    return false;
  }

  /**
   * @param orig Vector to rotate
   * @param angle Angle in degrees to rotate by
   * @return a rotated vector
   */
  public static Vector2d rotateVector(Vector2d orig, double angle) {
    Vector2d rotated = new Vector2d(0, 0);
    double angleRad = Math.toRadians(angle);

    rotated.setX(orig.getX() * Math.cos(angleRad) - orig.getY() * Math.sin(angleRad));
    rotated.setY(orig.getX() * Math.sin(angleRad) - orig.getY() * Math.cos(angleRad));

    return rotated;
  }
}
