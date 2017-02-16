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

    public void setMaxSpeed(int speed)
    {
        frontLeft.setMaxSpeed(speed);
        frontRight.setMaxSpeed(speed);
        backLeft.setMaxSpeed(speed);
        backRight.setMaxSpeed(speed);
    }

}
