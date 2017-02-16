/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
/*package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cCompassSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;

@TeleOp(name="autonom", group="Linear Opmode")  // @Autonomous(...) is the other common choice
//@Disabled
public class autonom extends LinearOpMode {
    ModernRoboticsI2cCompassSensor compass;
    DcMotor mfs,mfd,mss,msd;
    ModernRoboticsI2cGyro gyro;
    private ElapsedTime runtime = new ElapsedTime();
    private ElapsedTime timprulaj =new ElapsedTime();
    int nr=0;
    long timp;
    @Override
    public void runOpMode() {
        gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro");
        compass = hardwareMap.get(ModernRoboticsI2cCompassSensor.class, "compass");
        mfs = hardwareMap.dcMotor.get("mfs");
        mfd = hardwareMap.dcMotor.get("mfd");
        mss = hardwareMap.dcMotor.get("mss");
        msd = hardwareMap.dcMotor.get("msd");
        double dist1, dist2;
        dist1 = 1.48;
        dist2 = 0.64;
        double x = 0.0;
        double x1=0.0;
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();
            gyro.calibrate();
            compass.setMode(CompassSensor.CompassMode.CALIBRATION_MODE);
            telemetry.addData("calibrare,rotiti incet sensorul la 360 de grade", "...");
            if(compass.calibrationFailed())
                telemetry.addData("calibrare","esuata")
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            compass.setMode(CompassSensor.CompassMode.MEASUREMENT_MODE);
            Acceleration acceleratie;
            acceleratie = compass.getAcceleration();
            double viteza = Math.sqrt(acceleratie.xAccel * acceleratie.xAccel + acceleratie.yAccel * acceleratie.yAccel);
            double timp1 = dist1 / viteza;
            double timp2 = dist2 / viteza;


            while(x >= 0 && x < timp1) {
                mersinainte(0.5);
                x= timprulaj.milliseconds();
            }
            timprulaj.reset();
                mersinainte(0.0);
            while (gyro.getHeading() < 90)
                rotire(0.7);
                rotire(0.0);
            while(x1 < timp2)
            {
                x1= timprulaj.milliseconds();
                mersinainte(0.5);
            }
            timprulaj.reset();

            mersinainte(0.0);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
            // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
            // leftMotor.setPower(-gamepad1.left_stick_y);
            // rightMotor.setPower(-gamepad1.right_stick_y);
        }
    }
    public void stangadreapta(double pw){
        mfs.setPower(-pw);
        mss.setPower(pw);
        mfd.setPower(-pw);
        msd.setPower(pw);
    }
    public void rotire(double pw){
        mfs.setPower(pw);
        mfd.setPower(pw);
        mss.setPower(pw);
        msd.setPower(pw);
    }
    public void diagonala(double pw,double pw1){
        mfs.setPower(-pw);
        msd.setPower(pw);
        mfd.setPower(-pw1);
        mss.setPower(pw1);
    }
    public void mersinainte(double pw){
        mfs.setPower(-pw);
        mfd.setPower(pw);
        mss.setPower(-pw);
        msd.setPower(pw);
    }

    }

*/