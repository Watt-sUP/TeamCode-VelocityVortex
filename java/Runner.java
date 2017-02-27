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

    public void setTargetPosition(int posFrontLeft, int posFrontRight, int posBackLeft, int posBackRight)
    {
        frontLeft.setTargetPosition(posFrontLeft);
        frontRight.setTargetPosition(posFrontRight);
        backLeft.setTargetPosition(posBackLeft);
        backRight.setTargetPosition(posBackRight);
    }

    public int getCurrentPosition(int id)
    {
        if(id == 0) return frontLeft.getCurrentPosition();
        if(id == 1) return frontRight.getCurrentPosition();
        if(id == 2) return backLeft.getCurrentPosition();
        if(id == 3) return backRight.getCurrentPosition();
        return -1;
    }
}
