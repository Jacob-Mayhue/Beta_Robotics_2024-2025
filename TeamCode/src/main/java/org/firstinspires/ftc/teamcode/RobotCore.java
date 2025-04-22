package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Arrays;
import java.util.List;

@Disabled
public class RobotCore extends OpMode{

    DcMotor frontLeft, frontRight, backLeft, backRight, verticalSlide;

    Servo leftSlide, rightSlide, bucketPivot, clawPivot;

    CRServo leftWheel, rightWheel;

    List<DcMotor> motors;
    @Override
    public void init() {

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        verticalSlide = hardwareMap.get(DcMotor.class, "slide");

        leftSlide = hardwareMap.get(Servo.class, "leftSlide");
        rightSlide = hardwareMap.get(Servo.class, "rightSlide");
        bucketPivot = hardwareMap.get(Servo.class, "bucketPivot");
        clawPivot = hardwareMap.get(Servo.class, "clawPivot");

        leftWheel = hardwareMap.get(CRServo.class, "leftWheel");
        rightWheel = hardwareMap.get(CRServo.class, "rightWheel");

        motors = Arrays.asList(frontLeft, frontRight, backLeft, backRight, verticalSlide);

        for(DcMotor motor: motors){
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        rightSlide.setDirection(Servo.Direction.REVERSE);

        rightWheel.setDirection(CRServo.Direction.REVERSE);
    }

    @Override
    public void loop() {

    }

    public void ResetAllDriveEncoders(){
        for(DcMotor motor: motors){
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
