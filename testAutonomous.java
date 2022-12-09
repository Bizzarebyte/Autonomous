package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous

public class JavaNautsTestAuton extends LinearOpMode{
    DcMotor motorFrontLeft;
    DcMotor motorFrontRight;
    DcMotor motorBackLeft;
    DcMotor motorBackRight;
    DcMotor linearSlide;
    // Servo clamp;



    @Override
    public void runOpMode() throws InterruptedException {
        // hardware map devices
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        linearSlide = hardwareMap.dcMotor.get("linearSlide");
        // clamp = hardwareMap.servo.get("clamp");

        //give motors specific direction
        motorFrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        //zero power behavior
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //run using encoders
        linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        //reset encoders
        linearSlide.setMode(DcMotor.RunMode.RESET_ENCODERS);



        waitForStart();



        // moves forward toward the cone
        DriveForward(0.5);
        sleep(1000);

        // stops for 1 second (so it can stop the values)
        Stop(0);
        sleep(1500);

        // strafes to the left so it is in line with the cone
        StrafeLeft(0.5);
        sleep(640);

        // stops so it can re ajust itself
        Stop(0);
        sleep(2000);



        //move arm up
        linearSlide.setPower(0.5);

        sleep(1000);


        // moves forward so we can drop the cone
        DriveForward(0.5);
        sleep(100);


        //drop cone, wait for 1 second


        // moves back
        DriveBackward(0.5);
        sleep(100);

        // stops so it can re-ajust itself
        Stop(0);
        sleep(1000);

        // strafes right and parks
        StrafeRight(0.5);
        sleep(640);

        // stops because this is the end(so far)
        Stop(0);
        sleep(1000);


    }

    public void Stop(double power){
        motorFrontLeft.setPower(power);
        motorFrontRight.setPower(power);
        motorBackLeft.setPower(power);
        motorBackRight.setPower(power);
    }

    public void DriveForward(double power){
        motorFrontLeft.setPower(-power);
        motorFrontRight.setPower(power);
        motorBackLeft.setPower(-power);
        motorBackRight.setPower(power);
    }

    public void DriveBackward(double power){
        motorFrontLeft.setPower(power);
        motorFrontRight.setPower(-power);
        motorBackLeft.setPower(power);
        motorBackRight.setPower(-power);
    }

    public void StrafeLeft(double power){
        motorFrontLeft.setPower(power);
        motorFrontRight.setPower(power);
        motorBackLeft.setPower(-power);
        motorBackRight.setPower(-power);
    }

    public void StrafeRight(double power){
        motorFrontLeft.setPower(-power);
        motorFrontRight.setPower(-power);
        motorBackLeft.setPower(power);
        motorBackRight.setPower(power);
    }

}