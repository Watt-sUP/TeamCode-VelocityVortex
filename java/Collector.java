import com.qualcomm.robotcore.hardware.DcMotor;

public class Collector {

    private DcMotor motor;

    Collector (DcMotor _motor)
    {
        motor = _motor;
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setRunMode(DcMotor.RunMode rm)
    {
        motor.setMode(rm);
    }

    public void setMaxSpeed(int speed)
    {
        motor.setMaxSpeed(speed);
    }

    public void setPower(double pw) { motor.setPower(pw); }
}
