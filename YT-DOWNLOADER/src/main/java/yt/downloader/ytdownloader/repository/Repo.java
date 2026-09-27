package yt.downloader.ytdownloader.repository;

import java.io.*;

public class Repo {
    File file = new File("config.txt");


    public void setConfig(String dir) throws Exception
    {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(dir);
            writer.close();
        } catch (IOException e) {
            throw new Exception("FILE NOT EXIST");
        }
    }

    public String LoadConfigDir() throws Exception
    {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(reader);
            return buffReader.readLine();
        }catch (IOException e)
        {
            throw new Exception("FILE NOT EXIST");
        }
    }
}
