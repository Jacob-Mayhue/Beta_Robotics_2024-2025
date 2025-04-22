package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;

@TeleOp(name = "SoloTele", group = "A")
public class SoloTele extends RobotCore {

    double slideTarget = 0.6;
    public enum transferSteps {REST, RETRACT, TRANSFER, VERTICAL}
    transferSteps transfer;
    ElapsedTime transferTime = new ElapsedTime();
    ElapsedTime intakeTime = new ElapsedTime();
    ElapsedTime outtakeTime = new ElapsedTime();

    @Override
    public void init() {
        super.init();
        verticalSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        verticalSlide.setTargetPosition(0);
        verticalSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalSlide.setPower(1);

        leftSlide.setPosition(slideTarget);
        rightSlide.setPosition(slideTarget);

        transfer = transferSteps.REST;
    }

    @Override
    public void loop() {
        printDebugData();
        drivetrain();

        if(gamepad1.y && transfer != transferSteps.REST){
            transfer = transferSteps.REST;
            slideTarget = 0.75;
            verticalSlide.setTargetPosition(0);
            clawPivot.setPosition(0);
        }

        switch(transfer) {
            case REST:
                if (gamepad1.left_trigger > 0.5) {
                    if (intakeTime.time(TimeUnit.SECONDS) > 0.2 && leftWheel.getPower() == 1) {
                        leftWheel.setPower(0);
                        rightWheel.setPower(0);
                    } else if (intakeTime.time(TimeUnit.SECONDS) > 0.1) {
                        leftWheel.setPower(1);
                        rightWheel.setPower(1);
                        intakeTime.reset();
                    }
                }

                if (gamepad1.right_trigger > 0.5) {
                    if (outtakeTime.time(TimeUnit.SECONDS) > 0.2 && leftWheel.getPower() == -1) {
                        leftWheel.setPower(0);
                        rightWheel.setPower(0);
                    } else if (outtakeTime.time(TimeUnit.SECONDS) > 0.1) {
                        leftWheel.setPower(-1);
                        rightWheel.setPower(-1);
                        outtakeTime.reset();
                    }
                }

                if (gamepad1.dpad_up) {
                    clawPivot.setPosition(0.3);
                    bucketPivot.setPosition(0.5);
                    verticalSlide.setTargetPosition(2920);
                    slideTarget = 0.7;
                }
                if (gamepad1.dpad_down) {
                    verticalSlide.setTargetPosition(0);
                    slideTarget = 0.75;
                    bucketPivot.setPosition(0);
                }

                leftSlide.setPosition(slideTarget);
                rightSlide.setPosition(slideTarget);

                if (slideTarget > 0.6)
                    if (gamepad1.left_bumper)
                        slideTarget -= 0.005;
                if (slideTarget < 1) {
                    if (gamepad1.right_bumper) {
                        clawPivot.setPosition(0.76);
                        leftWheel.setPower(-1);
                        rightWheel.setPower(-1);
                        slideTarget += 0.005;
                    }
                }

                if (gamepad1.dpad_left)
                    bucketPivot.setPosition(0);
                else if (gamepad1.dpad_right)
                    bucketPivot.setPosition(1);

                if (gamepad1.b) {
                    if (slideTarget < 0.7) {
                        slideTarget = 0.7;
                    }
                    clawPivot.setPosition(0.76);
                }else if (gamepad1.a)
                    clawPivot.setPosition(0);

                if (gamepad1.x) {
                    transfer = transferSteps.RETRACT;
                    transferTime.reset();
                }
                break;

            case RETRACT:
                leftWheel.setPower(-1);
                rightWheel.setPower(-1);
                clawPivot.setPosition(0);
                bucketPivot.setPosition(0);
                slideTarget = 0.6;
                leftSlide.setPosition(slideTarget);
                rightSlide.setPosition(slideTarget);
                if(transferTime.time(TimeUnit.SECONDS) >= 0.7){
                    transfer = transferSteps.TRANSFER;
                    transferTime.reset();
                }
                break;

            case TRANSFER:
                leftWheel.setPower(1);
                rightWheel.setPower(1);
                if(transferTime.time(TimeUnit.SECONDS) >= 0.35){
                    transfer = transferSteps.VERTICAL;
                    transferTime.reset();
                }
                break;

            case VERTICAL:
                leftWheel.setPower(0);
                rightWheel.setPower(0);

                transfer = transferSteps.REST;
                transferTime.reset();

                break;
        }
    }
    private void printDebugData() {
        telemetry.addData("Front Left Pos: ", frontLeft.getCurrentPosition());
        telemetry.addData("Front Right Pos: ", frontRight.getCurrentPosition());
        telemetry.addData("Back Left Pos: ", backLeft.getCurrentPosition());
        telemetry.addData("Back Right Pos: ", backRight.getCurrentPosition());
        telemetry.addData("\nverticalSlide Pos: ", verticalSlide.getCurrentPosition());
        telemetry.addData ("SlideTarget: ", slideTarget);

        telemetry.update();
    }
    private void drivetrain() {
        double moveX;
        double moveY;
        double turnX;
        double frontLeftPower;
        double frontRightPower;
        double backLeftPower;
        double backRightPower;
        //Drivetrain
        moveX = gamepad1.left_stick_x;
        moveY = -gamepad1.left_stick_y;
        turnX = gamepad1.right_stick_x;

        frontLeftPower = moveY + moveX + turnX;
        frontRightPower = moveY - moveX - turnX;
        backLeftPower = moveY - moveX + turnX;
        backRightPower = moveY + moveX - turnX;

        //Drivetrain Driver Controls
        if (Math.abs(gamepad1.left_stick_x) > 0.1 || Math.abs(gamepad1.left_stick_y) > 0.1 || Math.abs(gamepad1.right_stick_x) > 0.1) {
            if (gamepad1.right_stick_button) {
                frontLeft.setPower(frontLeftPower * 0.8);
                frontRight.setPower(frontRightPower * 0.8);
                backLeft.setPower(backLeftPower * 0.8);
                backRight.setPower(backRightPower * 0.8);
            } else if (gamepad1.left_stick_button) {
                frontLeft.setPower(frontLeftPower * 0.25);
                frontRight.setPower(frontRightPower * 0.25);
                backLeft.setPower(backLeftPower * 0.25);
                backRight.setPower(backRightPower * 0.25);
            } else {
                frontLeft.setPower(frontLeftPower * 0.55);
                frontRight.setPower(frontRightPower * 0.55);
                backLeft.setPower(backLeftPower * 0.55);
                backRight.setPower(backRightPower * 0.55);
            }
        } else {
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }
    }
}
