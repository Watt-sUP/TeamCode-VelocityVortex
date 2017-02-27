public class RobotState {

    public double stateTime;
    public int tickFrontLeft, tickFrontRight, tickBackLeft, tickBackRight;
    public double throwSpeed;
    public double collectSpeed;
    public int touchBeacon;

    RobotState( double _st, int _tfl, int _tfr, int _tbl, int _tbr, double _ts, double _cs, int _tb)
    {
        stateTime = _st;
        tickFrontLeft = _tfl;
        tickFrontRight = _tfr;
        tickBackLeft = _tbl;
        tickBackRight = _tbr;
        throwSpeed = _ts;
        collectSpeed = _cs;
        touchBeacon = _tb;
    }

    public String toString()
    {
        String str = "";
        str += Double.toString(stateTime) + " " +
                Integer.toString(tickFrontLeft) + " " +
                Integer.toString(tickFrontRight) + " " +
                Integer.toString(tickBackLeft) + " " +
                Integer.toString(tickBackRight) + " " +
                Double.toString(throwSpeed) + " " +
                Double.toString(collectSpeed) + " " +
                Integer.toString(touchBeacon) + "\n";
        return str;
    }
}