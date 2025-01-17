package org.firstinspires.ftc.teamcode.path;

import org.firstinspires.ftc.teamcode.util.Pose2d;
import org.firstinspires.ftc.teamcode.util.Vector2d;

import java.util.ArrayList;

public class PathBuilder {
  private Vector2d currentPose;
  private ArrayList<PathSegment> path;
  private double followAngle, speed, turnSpeed;

  public PathBuilder(Pose2d pose) {
    this.currentPose = pose.pos();
    path = new ArrayList<>();
  }

  /**
   * @param point Point to go to
   * @param label Keyword or phrase to describe to movement
   * @return Adds the segment to the path
   */
  public PathBuilder addPoint(
      Vector2d point, double followAngle, double speed, double turnSpeed, String label) {
    PathSegment t = new PathSegment(currentPose, point, followAngle, speed, turnSpeed, label);
    path.add(t);
    currentPose = t.end;

    this.followAngle = followAngle;
    this.speed = speed;
    this.turnSpeed = turnSpeed;

    return this;
  }

  public ArrayList<PathSegment> create() {
    PathSegment seg = path.get(path.size() - 1);

    double dX = seg.end.getX() - seg.start.getX();
    double dY = seg.end.getY() - seg.start.getY();

    PathSegment newPoint =
        new PathSegment(
            seg.end,
            new Vector2d(seg.end.getX() + dX, seg.end.getY() + dY),
            followAngle,
            speed,
            turnSpeed,
            "extend");

    path.add(newPoint);

    System.out.println("n: " + newPoint);
    return path;
  }
}
