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

import android.hardware.Sensor;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRGyro;

/***
 *
 *  TAVI NU STIE SA REDENUMEASCA UN COD !!!!!!!!!!!!!!!!
 *
 */

@TeleOp(name="Template: Linear OpMode", group="Linear Opmode")  // @Autonomous(...) is the other common choice
@Disabled
public class gamepad extends LinearOpMode {
    DcMotor mfs,mfd,mss,msd;
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        double pwlx;
        double pwly;
        double pwrx;
        double pwry = 0;
        mfs = hardwareMap.dcMotor.get("mfs");
        mfd = hardwareMap.dcMotor.get("mfd");
        mss = hardwareMap.dcMotor.get("mss");
        msd = hardwareMap.dcMotor.get("msd");
        mfs.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mfd.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mss.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        msd.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mfs.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mfd.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mss.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        msd.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        runtime.reset();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double glsx, glsy, grsy, grsx;
            glsx = -gamepad1.left_stick_x;
            glsy = -gamepad1.left_stick_y;
            grsx = -gamepad1.right_stick_x;
            grsy = -gamepad1.right_stick_y;
            while (Math.abs(glsy) > 0 && Math.abs(glsx) >= 0 && Math.abs(glsx) < 0.5)
                mersinainte(grsy);
            while (glsy > 0 && glsx >= 0.5 && glsx <= 1)
                diagonala(glsy, 0);
            while (glsy > 0 && glsx <= -0.5 && glsx >= -1)
                diagonala(0, glsy);
            while (glsy < 0 && glsx <= -0.5 && glsx >= -1)
                diagonala(glsy, 0);
            while (glsy < 0 && glsx >= 0.5 && glsx <= 1)
                diagonala(glsy, 0);

            while (Math.abs(glsx) > 0 && Math.abs(glsy) < 0.5)
                stangadreapta(glsx);
            if (grsx > 0)
                rotire(grsx);
            else
                rotire(grsx);
        }
    }
    public void stangadreapta(double pw  ) {
        mfs.setPower(-pw);
        mss.setPower(pw);
        mfd.setPower(-pw);
        msd.setPower(pw);
    }

    public void rotire(double pw) {
        mfs.setPower(pw);
        mfd.setPower(pw);
        mss.setPower(pw);
        msd.setPower(pw);
    }

    public void diagonala(double pw, double pw1) {
        mfs.setPower(-pw);
        msd.setPower(pw);
        mfd.setPower(-pw1);
        mss.setPower(pw1);
    }

    public void mersinainte(double pw) {
        mfs.setPower(-pw);
        mfd.setPower(pw);
        mss.setPower(-pw);
        msd.setPower(pw);
    }
}
