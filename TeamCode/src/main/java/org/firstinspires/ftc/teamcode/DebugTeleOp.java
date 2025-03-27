package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Debug Tele", group = "A")
public class DebugTeleOp extends RobotCore{
    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        if(gamepad1.a)
            frontLeft.setPower(1);
        else if(gamepad1.right_bumper)
            frontLeft.setPower(1);
        else 
            frontLeft.setPower(0);

        if(gamepad1.b)
            frontRight.setPower(1);
        else if(gamepad1.right_bumper)
            frontRight.setPower(1);
        else
            frontRight.setPower(0);

        if(gamepad1.x)
            backLeft.setPower(1);
        else if(gamepad1.right_bumper)
            backLeft.setPower(1);
        else
            backLeft.setPower(0);

        if(gamepad1.y)
            backRight.setPower(1);
        else if(gamepad1.right_bumper)
            backRight.setPower(1);
        else
            backRight.setPower(0);

        if(gamepad1.dpad_up)
            verticalSlide.setPower(1);
        else if (gamepad1.dpad_down)
            verticalSlide.setPower(-1);
        else
            verticalSlide.setPower(0);

        if(gamepad1.dpad_left)
            ResetAllDriveEncoders();


        if(gamepad2.left_bumper){
            leftSlide.setPosition(0.6);
            rightSlide.setPosition(0.6);
        } else if(gamepad2.right_bumper){
            leftSlide.setPosition(1);
            rightSlide.setPosition(1);
        }

        if(gamepad2.left_trigger > 0.5){
            leftWheel.setPower(1);
            rightWheel.setPower(1);
        } else if(gamepad2.right_trigger > 0.5){
            leftWheel.setPower(-1);
            rightWheel.setPower(-1);
        }

        if(gamepad2.dpad_down)
            bucketPivot.setPosition(0);
        else if(gamepad2.dpad_up)
            bucketPivot.setPosition(1);

        if(gamepad2.y)
            clawPivot.setPosition(1);
        else if(gamepad2.a)
            clawPivot.setPosition(0);


        telemetry.addData("Front Left Pos: ", frontLeft.getCurrentPosition());
        telemetry.addData("Front Right Pos: ", frontRight.getCurrentPosition());
        telemetry.addData("Back Left Pos: ", backLeft.getCurrentPosition());
        telemetry.addData("Back Right Pos: ", backRight.getCurrentPosition());
        telemetry.addData("\nverticalSlide Pos: ", verticalSlide.getCurrentPosition());

        telemetry.update();





    }
    
}
