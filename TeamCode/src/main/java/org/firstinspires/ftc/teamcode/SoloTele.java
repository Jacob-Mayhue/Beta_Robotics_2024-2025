package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "SoloTele")
public class SoloTele extends RobotCore{

    double leftFrontPower;
    double rightFrontPower;
    double leftBackPower;
    double rightBackPower;

    @Override
    public void init() {
        super.init();
    }


    @Override
    public void loop() {
        double max;

        // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
        double axial   = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
        double lateral =  gamepad1.left_stick_x;
        double yaw     =  gamepad1.right_stick_x;

        // Combine the joystick requests for each axis-motion to determine each wheel's power.
        // Set up a variable for each drive wheel to save the power level for telemetry.
        leftFrontPower  = axial + lateral + yaw;
        rightFrontPower = axial - lateral - yaw;
        leftBackPower   = axial - lateral + yaw;
        rightBackPower  = axial + lateral - yaw;

        // Normalize the values so no wheel power exceeds 100%
        // This ensures that the robot maintains the desired motion.
        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }


//        leftFrontPower  = gamepad2.x ? 1.0 : 0.0;  // X gamepad
//        leftBackPower   = gamepad2.a ? 1.0 : 0.0;  // A gamepad
//        rightFrontPower = gamepad2.y ? 1.0 : 0.0;  // Y gamepad
//        rightBackPower  = gamepad2.b ? 1.0 : 0.0;  // B gamepad


        frontLeft.setPower(leftFrontPower);
        frontRight.setPower(rightFrontPower);
        backLeft.setPower(leftBackPower);
        backRight.setPower(rightBackPower);


        if(gamepad1.dpad_up && leftSlide.getCurrentPosition() < 4300){
            leftSlide.setPower(1);
            rightSlide.setPower(1);
        } else if(gamepad1.dpad_down  && leftSlide.getCurrentPosition() > 0){
            leftSlide.setPower(-1);
            rightSlide.setPower(-1);
        } else {
            leftSlide.setPower(0.001);
            rightSlide.setPower(0.001);
        }


        if(gamepad1.y)
            clawPivot.setPosition(1);
        else if(gamepad1.b)
            clawPivot.setPosition(0.495);
        else if(gamepad1.a)
            clawPivot.setPosition(0.1);

        if(gamepad1.left_trigger > 0.5)
            horizontal.setPosition(1);
        else if(gamepad1.right_trigger > 0.5)
            horizontal.setPosition(0);

        if(gamepad1.left_bumper){
            leftWheel.setPower(1);
            rightWheel.setPower(-1);
        } else if(gamepad1.right_bumper){
            leftWheel.setPower(-1);
            rightWheel.setPower(1);
        } else {
            leftWheel.setPower(0);
            rightWheel.setPower(0);
        }

    }
}
