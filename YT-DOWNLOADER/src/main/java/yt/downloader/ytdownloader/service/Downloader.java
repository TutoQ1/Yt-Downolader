package yt.downloader.ytdownloader.service;


import yt.downloader.ytdownloader.repository.Repo;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Downloader {
    Repo repo = new Repo();

    public void execute(String URL) throws Exception
    {
        String outPutDir = repo.LoadConfigDir(); //output video setter
        System.out.println(outPutDir);
        if(URL == null || URL.isBlank())
        {
            throw new Exception("EMPTY FIELD");
        }
        if(outPutDir == null || outPutDir.isBlank())
        {
            throw new Exception("EMPTY DIRECTORY");
        }
        try {
            File builderDir = new File("."); //loads direct
            ProcessBuilder builder = new ProcessBuilder(
                    "CMD","/c","start",
                    "yt-dlp.exe",
                    "-f", "bv*+ba/best", "--merge-output-format",
                    "mp4","-o", outPutDir + "\\%(title)s.%(ext)s" ,
                    URL.trim());

            builder.directory(builderDir);
            builder.redirectErrorStream(true);
            Process process = builder.start();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                System.out.println(line);
            }
            int exit = process.waitFor();
            if(exit==0)
            {
                System.out.println("DONE!");
            }
        }catch (Exception e)
        {
            throw new Exception("ERROR COMAND FAILED");
        }
    }

    public void installDependencies()
    {
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "CMD","/c", "start",
                    "winget", "install", "ffmpeg",
                    "winget", "install", "DenoLand.Deno"
                    );
            builder.start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
