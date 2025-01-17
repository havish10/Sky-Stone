package org.firstinspires.ftc.teamcode.opModes.teleOp

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.computerDebuging.ComputerDebugging
import org.firstinspires.ftc.teamcode.subsystems.Robot
import org.firstinspires.ftc.teamcode.util.Pose2d
import java.text.DecimalFormat

@TeleOp(name = "Encoder Test", group = "T")
class EncoderTest : OpMode() {
    private lateinit var robot: Robot
    private lateinit var computerDebugging: ComputerDebugging
    private var df = DecimalFormat("#.##")

    //    private var path1: ArrayList<PathSegment> = t
//            .addPoint(Vector2d(100.0, 15.0), "moving forward")
//            .addPoint(Vector2d(15.0, 15.0), "moving forward")
//            .create()
    private lateinit var powers: DoubleArray

    override fun init() {
        robot = Robot(Pose2d(20.32, 81.7, -Math.PI / 2), this, this.telemetry)
//        pathFollower = PathFollower(path, 55.0)
        //pathFollower1 = PathFollower(path1, 55.0)
        computerDebugging = ComputerDebugging()

        telemetry.addData("ff", "fff")
        robot.start()
    }

    override fun start() {
        robot.drive.resetEncoders()
    }

    override fun loop() {
        //robot.update()
        //robot.drive.setVelocity(Vector2d(gamepad1.left_stick_x.toDouble(), gamepad1.left_stick_y.toDouble()), gamepad1.right_stick_x.toDouble())


        var fixPos = Pose2d(robot.drive.position.x, robot.drive.position.y, robot.drive.position.heading * (180 / Math.PI))

        telemetry.addData("Left", robot.drive.trackingWheelPositions[0])
        telemetry.addData("Right", robot.drive.trackingWheelPositions[1])
        telemetry.addData("Center", robot.drive.trackingWheelPositions[2])
        telemetry.addData("pos", robot.drive.position)


//        ComputerDebugging.sendRobotLocation(fixPos)
//        ComputerDebugging.sendLog("\nPosition - " + robot.drive.position.toString() +
//                "\nPowersg - " + df.format(gamepad1.left_stick_x) + ", " + df.format(gamepad1.left_stick_y) + ", " + df.format(gamepad1.right_stick_x)
//        )
//        ComputerDebugging.sendPacket()
    }

    override fun stop() {
        robot.stop()
        powers = doubleArrayOf(0.0, 0.0, 0.0)
    }
}
