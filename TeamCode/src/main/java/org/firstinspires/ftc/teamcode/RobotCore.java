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
public class RobotCore extends OpMode {
DcMotor leftBack;
DcMotor leftFront;
DcMotor rightBack;
DcMotor rightFront;



    @Override
    public void init() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }

    @Override
    public void loop() {

    }
}
