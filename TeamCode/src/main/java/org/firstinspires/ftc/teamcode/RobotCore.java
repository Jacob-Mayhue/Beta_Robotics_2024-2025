package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Arrays;
import java.util.List;

//   In our lesson on Wednesday I showed you how to create a RobotCore class.
//   If you need a refresher, your RobotCore class should house all of the physical objects on your robot
//   such as motors and servos.  Your job is to create a simple RobotCore class that declares and initializes all components
//   of the new Chim-ung robot.  (This will be the robot we use for all training)


/*
TODO: complete the RobotCore class in these steps
    note: I have already imported all the necessary classes, but if problems arise use alt+enter
    1. Extend the class to OpMode (after extending the class use alt+enter to add the two required functions to clear the error)
    2. Declare all motors and Servos outside of both the init and loop functions
    3. Initialize all motors and servos in the init() function
    (HINT: objectName = hardwareMap.get(hardwareType.class, "objectName in config");)
    4. Set the ZeroPowerBehavior of all motors to BRAKE (HINT: just type "objectName." and look at the options for auto complete)
    5. Set all motor modes to RUN_USING_ENCODER
    6. (Optional) If you want to challenge your Java knowledge create a list that stores all your motors and set their
       ZeroPowerBehaviors and Modes using a single for loop
*/
public class RobotCore extends OpMode{

    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor backLeft;
    DcMotor backRight;

    Servo clawPivot;
    Servo claw;
    Servo rightSlide;
    Servo leftSlide;

    List<DcMotor> motors;


    @Override
    public void init() {

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        clawPivot = hardwareMap.get(Servo.class, "clawPivot");
        motors = Arrays.asList(leftFront, rightFront, backLeft, backRight);
        claw = hardwareMap.get(Servo.class, "claw");
        rightSlide = hardwareMap.get(Servo.class, "rightSlide");
        leftSlide = hardwareMap.get(Servo.class, "leftSlide");
        for(DcMotor motor: motors){
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }












    @Override
    public void loop() {

    }
}
