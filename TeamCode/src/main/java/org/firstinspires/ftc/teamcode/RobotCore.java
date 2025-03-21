package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Arrays;
import java.util.List;

public class RobotCore extends OpMode
{
    DcMotor frontLeft, frontRight, backLeft, backRight, slide;
    Servo clawPivot, slidePivot;
    CRServo leftWheel, rightWheel;

    List<DcMotor> motors;
    @Override
    public void init() {

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        slide = hardwareMap.get(DcMotor.class, "slide");

        clawPivot = hardwareMap.get(Servo.class, "clawPivot");
        slidePivot = hardwareMap.get(Servo.class, "slidePivot");

        leftWheel = hardwareMap.get(CRServo.class, "leftWheel");
        rightWheel = hardwareMap.get(CRServo.class, "rightWheel");
        motors = Arrays.asList(frontLeft, frontRight, backLeft, backRight);

        for(DcMotor motor: motors){
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        slide.setMode(DcMotor.RunMode.RUN_TO_POSITION);



    }

    @Override
    public void loop() {

    }
}
