package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MainTeleOp extends RobotCore{


    double y = 0;
    double x = 0;
    double rx = 0;


    double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
    double frontLeftPower = (y + x + rx) / denominator;
    double backLeftPower = (y - x + rx) / denominator;
    double frontRightPower = (y - x - rx) / denominator;
    double backRightPower = (y + x - rx) / denominator;

    double max;

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {

        y = gamepad1.left_stick_y; // Remember, Y stick value is reversed
        x = -gamepad1.left_stick_x; // Counteract imperfect strafing
        rx = gamepad1.right_stick_x;

        //denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        frontLeftPower = (y + x - rx);
        backLeftPower = (y - x - rx);
        frontRightPower = (y - x + rx);
        backRightPower = (y + x + rx);

        max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        max = Math.max(max, Math.abs(backLeftPower));
        max = Math.max(max, Math.abs(backRightPower));

        if (max > 1.0) {
            frontLeftPower  /= max;
            frontRightPower /= max;
            backLeftPower   /= max / 2.5;
            backRightPower  /= max / 2.5;
        }

        if (gamepad1.b && (Math.abs(gamepad1.left_stick_x) > 0.1 || Math.abs(gamepad1.left_stick_y) > 0.1 || Math.abs(gamepad1.right_stick_x) > 0.1)) {
            frontLeft.setPower(frontLeftPower * 0.5);
            frontRight.setPower(frontRightPower * 0.5);
            backLeft.setPower(backLeftPower * 0.5);
            backRight.setPower(backRightPower * 0.5);
        } else if (Math.abs(gamepad1.left_stick_x) > 0.1 || Math.abs(gamepad1.left_stick_y) > 0.1 || Math.abs(gamepad1.right_stick_x) > 0.1) {
            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);
        } else {
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }


        //Bucket Pivot Controls-------------------------------------------------------
        if(gamepad1.left_trigger > 0.5)
            slidePivot.setPosition(0);
        else
            slidePivot.setPosition(1);


        //Claw Controls---------------------------------------------------------------
        if(gamepad1.a)
            clawPivot.setPosition(1);
        else
            clawPivot.setPosition(0);


        if(gamepad1.left_bumper){
            leftWheel.setPower(1);
            rightWheel.setPower(-1);
        }else if (gamepad1.right_bumper){
            leftWheel.setPower(-1);
            rightWheel.setPower(1);
        } else{
            leftWheel.setPower(0);
            rightWheel.setPower(0);
        }

        //Slide Controls--------------------------------------------------------------
        if (gamepad2.dpad_up) {
            slide.setTargetPosition(4400);
        }
        else if(gamepad2.dpad_down) {
            slide.setTargetPosition(0);
        }
        else if(gamepad2.dpad_right  || gamepad1.dpad_right)
            slide.setTargetPosition(290);
        else if(gamepad2.dpad_left || gamepad1.dpad_left)
            slide.setTargetPosition(4480);
        else if(gamepad2.left_trigger > 0.3 || gamepad1.left_trigger > 0.3)
            slide.setTargetPosition(325);
        else if(gamepad2.right_trigger > 0.3  || gamepad1.right_trigger > 0.3)
            slide.setTargetPosition(2200);
        else
        {
            slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            slide.setPower(0.002);
        }
    }
}
