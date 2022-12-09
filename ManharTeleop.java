package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="ManharTeleop")
public class ManharTeleop extends LinearOpMode {
    //init motors
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor linearSlide;
    Servo clamp;

    @Override
    public void runOpMode() {
        // hardware map devices
        frontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        frontRight = hardwareMap.dcMotor.get("motorFrontRight");
        backLeft = hardwareMap.dcMotor.get("motorBackLeft");
        backRight = hardwareMap.dcMotor.get("motorBackRight");
        linearSlide = hardwareMap.dcMotor.get("linearSlide");
        clamp = hardwareMap.servo.get("clamp");
        telemetry.addData("<<", "press start to continue");
        telemetry.update();


        //intake = hardwareMap.servo.get("intake");

        //give motors specific direction
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        //zero power behavior
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //run using encoders
        linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        //reset encoders
        linearSlide.setMode(DcMotor.RunMode.RESET_ENCODERS);





        waitForStart();

        if (isStopRequested()) {
            return;
        }


        while (opModeIsActive()) {

            telemetry.update();

            if (!gamepad1.x) { //change as desired

                //reverse as necessary
                double z = -gamepad1.left_stick_y;
                double x = gamepad1.left_stick_x; //*1.1 to counteract imperfect strafing
                double y = gamepad1.right_stick_x;

                double frontLeftPower = -y-x-z;
                double backLeftPower = -y+x-z;
                double frontRightPower = y-x-z;
                double backRightPower = y+x-z;

                // double frontLeftPower = -y-x-z;
                // double backLeftPower = -y+x-z;
                // double frontRightPower = y-x-z;
                // double backRightPower = y+x-z;

                //put powers in the range -1 to 1 just in case they aren't already
                //not checking because we will almost always be driving at max rpm

                if (Math.abs(frontLeftPower) > 0.5 || Math.abs(frontRightPower) > 0.5 || Math.abs(backLeftPower) > 0.5 || Math.abs(backRightPower) >0.5) {
                    double max = 0;
                    max = Math.max(Math.abs(frontLeftPower), Math.abs(backLeftPower));
                    max = Math.max(Math.abs(frontRightPower), max);
                    max = Math.max(Math.abs(backRightPower), max);

                    //divide everything by max
                    frontLeftPower /= 1.7;
                    frontRightPower /= 1.7;
                    backRightPower /= 1.7;
                    backLeftPower /= 1.7;



                }

                if (gamepad1.right_trigger >= 0.5) {
                    frontLeftPower /= 2;
                    frontRightPower /= 2;
                    backLeftPower /= 2;
                    backRightPower /= 2;
                }

                frontLeft.setPower(frontLeftPower);
                backLeft.setPower(backLeftPower);
                backRight.setPower(backRightPower);
                frontRight.setPower(frontRightPower);

            }

            // if (gamepad1.x) {

            //     //if servo is right side up
            //     if (clamp.getPosition() < 5) { //you will need to test this value
            //         clamp.setPosition(1); //also test this value
            //     }
            //     if (gamepad1.x) {
            //         clamp.setPosition(-1); //test this value again
            //     }
            //     break;
            // }

            if (gamepad1.dpad_up||gamepad2.dpad_up) {

                linearSlide.setTargetPosition(3050);
                linearSlide.setPower(1);
                linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            }

            if (gamepad1.dpad_left||gamepad2.dpad_left) {

                linearSlide.setTargetPosition(2300);
                linearSlide.setPower(1);
                linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            }

            if (gamepad1.dpad_right||gamepad2.dpad_right) {

                linearSlide.setTargetPosition(1500);
                linearSlide.setPower(1);
                linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            }

            if (gamepad1.dpad_down||gamepad2.dpad_down) {
                linearSlide.setTargetPosition(0);
                linearSlide.setPower(1);

                linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            }
            if(gamepad1.a){
                clamp.setPosition(0.6);

            }

            if(gamepad1.y){
                clamp.setPosition(0.3);

            }

            telemetry.addData("Slide ticks: ", linearSlide.getCurrentPosition());

        }
    }
}
