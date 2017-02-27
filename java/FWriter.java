import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FWriter {

    File ftcFolder, writeFile;
    String filename;

    String text;

    FWriter(String str)
    {
        filename = str;
        ftcFolder = new File(Environment.getExternalStorageDirectory() + "/FTC/");
        writeFile = new File(ftcFolder, filename);
    }

    public void write()
    {
        try
        {
            FileWriter fw = new FileWriter(writeFile);
            fw.append(text);
            fw.flush();
            fw.close();
        }
        catch (IOException e)
        {
        }
    }

    public void addState(RobotState rs)
    {
        text += rs.toString();
    }
}
