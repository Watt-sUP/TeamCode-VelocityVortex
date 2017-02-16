import com.qualcomm.robotcore.hardware.DcMotor;

public class Thrower {

    private DcMotor left, right;

    Thrower(DcMotor _left, DcMotor _right)
    {
        left = _left;
        right = _right;
        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMaxSpeed(4000);
    }

    public void setRunMode(DcMotor.RunMode rm)
    {
        left.setMode(rm);
        right.setMode(rm);
    }

    public void setPower(double pw)
    {
        left.setPower(-pw);
        right.setPower(pw);
    }

    public void setPower(double powerLeft, double powerRight)
    {
        left.setPower(powerLeft);
        right.setPower(powerRight);
    }

    public void setMaxSpeed(int speed)
    {
        left.setMaxSpeed(speed);
        right.setMaxSpeed(speed);
    }

    public double getPower()
    {
        double ans = left.getPower() * left.getMaxSpeed();
        return ans;
    }

    public void addPower(double val)
    {
        double pw = -left.getPower();
        pw += val;
        if(pw > 1.0)    pw = 1.0;
        if(pw < -1.0)   pw = -1.0;
        setPower(pw);
    }

}
