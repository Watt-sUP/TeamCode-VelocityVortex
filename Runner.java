import android.util.Pair;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Runner {

    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private double eps = 0.01;

    Runner(DcMotor fl, DcMotor fr, DcMotor bl, DcMotor br)
    {
        frontLeft = fl;
        frontRight = fr;
        backLeft = bl;
        backRight = br;
        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMaxSpeed(4000);
    }

    public void move(double x, double y, double r)
    {
        double eps = 0.001;
        if( Math.abs(x) < eps && Math.abs(y) < eps )
        {
            if( Math.abs(r) < eps )
                setPower(0.0);
            else
                setPower(r);
            return;
        }

        double d = Math.sqrt( 1.0 * x * x + 1.0 * y * y );
        double absX = Math.abs(x), absY = Math.abs(y);

        double rap = 1.0 / Math.max(absX, absY);
        double finD = Math.sqrt( 1.0 * x * x * rap * rap + 1.0 * y * y * rap * rap );
        double power = d / finD;

        double v = 0, u = 0;

        if(x == 0)
        {
            v = 1.0 * sign(-y);
            u = 1.0 * sign(y);
        }
        else if(y == 0)
        {
            v = 1.0 * sign(x);
            u = 1.0 * sign(x);
        }
        else
        {
            double prap = x / y;

            if(x < 0)
            {
                if(y < 0)
                {
                    u = -1;
                    v = u * (1.0 - prap) / (prap - 1.0);
                }
                else
                {
                    v = -1;
                    u = v * (prap - 1.0) / (1.0 - prap);
                }
            }
            else
            {
                if(y < 0)
                {
                    v = 1;
                    u = v * (prap - 1.0) / (1.0 - prap);
                }
                else
                {
                    u = 1;
                    v = u * (1.0 - prap) / (prap - 1.0);
                }
            }

            v *= power;
            u *= power;
        }

        setPower(v, u, -u, v);
    }

    public double sign(double x)
    {
        if(x < 0)   return -1;
        if(x > 0)   return 1;
        return 0;
    }

    public void setRunMode(DcMotor.RunMode rm)
    {
        frontLeft.setMode(rm);
        frontRight.setMode(rm);
        backLeft.setMode(rm);
        backRight.setMode(rm);
    }

    public void setPower(double powerFrontLeft, double powerFrontRight, double powerBackLeft, double powerBackRight)
    {
        frontLeft.setPower(powerFrontLeft);
        frontRight.setPower(powerFrontRight);
        backLeft.setPower(powerBackLeft);
        backRight.setPower(powerBackRight);
    }

    public void setPower(double power)
    {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }
    public void setMaxSpeed(int speed)
    {
        frontLeft.setMaxSpeed(speed);
        frontRight.setMaxSpeed(speed);
        backLeft.setMaxSpeed(speed);
        backRight.setMaxSpeed(speed);
    }

}
