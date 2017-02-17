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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="moveAndThrow", group="Linear Opmode")  // @Autonomous(...) is the other common choice

public class moveAndThrow extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    Runner runner;
    Thrower thrower;
    Collector collector;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        /// Initialize
        runner = new Runner (
                    hardwareMap.dcMotor.get("mfl"),
                    hardwareMap.dcMotor.get("mfr"),
                    hardwareMap.dcMotor.get("mbl"),
                    hardwareMap.dcMotor.get("mbr")
                        );

        thrower = new Thrower(
                    hardwareMap.dcMotor.get("mtl"),
                    hardwareMap.dcMotor.get("mtr")
                        );

        collector = new Collector(hardwareMap.dcMotor.get("mcll"));

        runner.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        thrower.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        collector.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double throwerAdd = 0.01;
        boolean moveActive = false;
        boolean aIsPressed = false;
        boolean xIsPressed = false;
        boolean throwTrigger = true;
        boolean dpadupPressed = false;
        boolean dpaddownPressed = false;

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Throw Power", -thrower.getPower());
            //telemetry.update();

            /// Move
            double lx = gamepad1.left_stick_x;
            double ly = gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;

            if(gamepad1.a)
            {
                if(!aIsPressed)
                {
                    aIsPressed = true;
                    moveActive = !moveActive;
                }
            }
            else
                aIsPressed = false;

            if(!moveActive)
            {
                double pfl = valueInInterval(lx - ly + rx, -1, 1);
                double pfr = valueInInterval(lx + ly + rx, -1, 1);
                double pbl = valueInInterval(-lx - ly + rx, -1, 1);
                double pbr = valueInInterval(-lx + ly + rx, -1, 1);

                runner.setPower(pfl, pfr, pbl, pbr);
            }
            else
            {
                double x = lx;
                double y = ly;
                double r = rx;
                double eps = 0.001;
                if( Math.abs(x) < eps && Math.abs(y) < eps )
                {
                    if( Math.abs(r) < eps )
                        runner.setPower(0.0);
                    else
                        runner.setPower(r);
                }
                else
                {
                    double d = Math.sqrt(1.0 * x * x + 1.0 * y * y);
                    double absX = Math.abs(x), absY = Math.abs(y);

                    double rap = 1.0 / Math.max(absX, absY);
                    double finD = Math.sqrt(1.0 * x * x * rap * rap + 1.0 * y * y * rap * rap);
                    double power = d / finD;

                    double v = 0.0, u = 0.0;

                    if (x == 0) {
                        v = 1.0 * runner.sign(-y) * power;
                        u = 1.0 * runner.sign(y) * power;
                    } else if (y == 0) {
                        v = 1.0 * runner.sign(x) * power;
                        u = 1.0 * runner.sign(x) * power;
                    } else {
                        double prap = x / y;

                        if (x < 0) {
                            if (y < 0) {
                                u = -1;
                                v = u * (prap - 1.0) / (prap + 1.0);
                            } else {
                                v = -1;
                                u = v * (prap + 1.0) / (prap - 1.0);
                            }
                        } else {
                            if (y < 0) {
                                v = 1;
                                u = v * (prap + 1.0) / (prap - 1.0);
                            } else {
                                u = 1;
                                v = u * (prap - 1.0) / (prap + 1.0);
                            }
                        }

                        v *= power;
                        u *= power;
                    }

                    telemetry.addData("X", x);
                    telemetry.addData("Y", y);
                    telemetry.addData("d", d);
                    telemetry.addData("finD", finD);
                    telemetry.addData("Power", power);
                    telemetry.addData("V", v);
                    telemetry.addData("U", u);
                    telemetry.addData("PRap", x / y);

                    //runner.setPower(v, u, -u, v);

                    if(gamepad1.right_bumper)
                        runner.setPower(v, u, -u, -v);
                    else
                        runner.setPower(0.0);
                }

                //runner.move(lx, ly, rx);
            }

            /// Collect
            double sgn = -1.0;
            if(gamepad1.b)  sgn = 1.0;
            collector.setPower(sgn * gamepad1.left_trigger);

            /// Throw
            if(gamepad1.x)
            {
                if(!xIsPressed)
                {
                    xIsPressed = true;
                    throwTrigger = !throwTrigger;
                }
            }
            else
                xIsPressed = false;

            if(throwTrigger)
                thrower.setPower(gamepad1.right_trigger);
            else
            {
                if(gamepad1.y)
                    thrower.setPower(0);

                if(gamepad1.dpad_up)
                {
                    if(!dpadupPressed)
                    {
                        dpadupPressed = true;
                        thrower.addPower(throwerAdd);
                    }
                }
                else
                    dpadupPressed = false;

                if(gamepad1.dpad_down)
                {
                    if(!dpaddownPressed)
                    {
                        dpaddownPressed = true;
                        thrower.addPower(-throwerAdd);
                    }
                }
                else
                    dpaddownPressed = false;
            }
            telemetry.update();
        }
    }

    private double valueInInterval(double val, double lft, double rgt)
    {
        double ans = val;
        if(lft > ans)   ans = lft;
        if(rgt < ans)   ans = rgt;
        return ans;
    }
}
