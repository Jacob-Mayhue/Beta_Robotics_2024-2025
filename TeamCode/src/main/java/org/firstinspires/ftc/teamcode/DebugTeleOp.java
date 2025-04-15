package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Debug Tele", group = "A")
public class DebugTeleOp extends RobotCore{

    double zeroPower = 0.03;
    @Override
    public void init() {
        super.init();
        verticalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        verticalSlide.setTargetPosition(0);
        verticalSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalSlide.setPower(1);
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

//        if(gamepad1.dpad_up && verticalSlide.getCurrentPosition() < 2850)
//            verticalSlide.setPower(1);
//        else if (gamepad1.dpad_down  && verticalSlide.getCurrentPosition() > 0)
//            verticalSlide.setPower(-1);
//        else
//            verticalSlide.setPower(0);

        if(gamepad1.dpad_up){
            verticalSlide.setTargetPosition(verticalSlide.getCurrentPosition() + 10);
        }
        if(gamepad1.dpad_down){
            verticalSlide.setTargetPosition(verticalSlide.getCurrentPosition() - 10);
        }

        if(gamepad1.dpad_left)
            ResetAllDriveEncoders();

//        if(gamepad1.right_trigger > 0.5)
//            zeroPower+= 0.01;
//        else if (gamepad1.left_trigger > 0.5)
//            zeroPower -= 0.001;


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
        telemetry.addData ("Vertical Slide zero power: ", zeroPower);

        telemetry.update();





    }
    
}
