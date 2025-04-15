package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MainTele", group = "A")
public class MainTele extends RobotCore {

    double slideTarget = 0.6;

    @Override
    public void init() {
        super.init();
        verticalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        verticalSlide.setTargetPosition(0);
        verticalSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalSlide.setPower(1);

        leftSlide.setPosition(slideTarget);
        rightSlide.setPosition(slideTarget);


    }

    @Override
    public void loop() {

        if(gamepad2.left_trigger > 0.5){
            leftWheel.setPower(1);
            rightWheel.setPower(1);
        } else if(gamepad2.right_trigger > 0.5){
            leftWheel.setPower(-1);
            rightWheel.setPower(-1);
        } else{
            leftWheel.setPower(0);
            rightWheel.setPower(0);
        }

        if(gamepad2.dpad_up){
            verticalSlide.setTargetPosition(2970);
        }
        if(gamepad2.dpad_down){
            verticalSlide.setTargetPosition(0);
        }

        leftSlide.setPosition(slideTarget);
        rightSlide.setPosition(slideTarget);

        if (slideTarget > 0.6) {
            if (gamepad2.left_stick_y > 0.9) {
                slideTarget -= 0.01;
            } else if (gamepad2.left_stick_y > 0.1) {
                slideTarget -= 0.005;
            }
        }

        if (slideTarget < 1) {
            if (gamepad2.left_stick_y < -0.9) {
                slideTarget += 0.01;
            } else if (gamepad2.left_stick_y < -0.1) {
                slideTarget += 0.005;
            }
        }

        if(gamepad2.dpad_left)
            bucketPivot.setPosition(0);
        else if(gamepad2.dpad_right)
            bucketPivot.setPosition(1);

        if(gamepad2.b)
            clawPivot.setPosition(1);
        else if(gamepad2.a)
            clawPivot.setPosition(0);

        telemetry.addData("Front Left Pos: ", frontLeft.getCurrentPosition());
        telemetry.addData("Front Right Pos: ", frontRight.getCurrentPosition());
        telemetry.addData("Back Left Pos: ", backLeft.getCurrentPosition());
        telemetry.addData("Back Right Pos: ", backRight.getCurrentPosition());
        telemetry.addData("\nverticalSlide Pos: ", verticalSlide.getCurrentPosition());
        telemetry.addData ("SlideTarget: ", slideTarget);

        telemetry.update();

    }


}

