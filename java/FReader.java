import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FReader {

    public int maxLines = 1000;
    RobotState[] states = new RobotState[maxLines];

    public String filename;
    File ftcFolder, readFile;
    public int cnt = -1;

    FReader(String str)
    {
        filename = str;
        ftcFolder = new File(Environment.getExternalStorageDirectory() + "/FTC/");
        readFile = new File(ftcFolder, filename);
    }

    public void read()
    {
        try
        {
            BufferedReader rdr = new BufferedReader(new FileReader(readFile));
            String line;

            int cnt = -1;
            while( (line = rdr.readLine()) != null )
            {
                cnt++;
                states[cnt] = getState(line);
            }
            rdr.close();
        }
        catch (IOException e)
        {
        }
    }

    RobotState getState(String line)
    {
        double[] nmb = new double[10];
        String str[] = line.split(" ");

        int pos = 0;
        for(int i = 0; i < 8; i++)
        {
            nmb[i] = Double.parseDouble(str[i]);
        }

        RobotState stt = new RobotState(nmb[0], (int)nmb[1], (int)nmb[2], (int)nmb[3], (int)nmb[4], nmb[5], nmb[6], (int)nmb[7]);
        return stt;
    }

    public RobotState getNextState()
    {
        cnt++;
        return states[cnt];
    }
}
