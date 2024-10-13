package Network;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class FileSave {

    private static FileSave fileSave;

    /**
     * Private Constructor avoids instantiation.
     */
    private FileSave(){
        //do nothing
    }

    public static FileSave getInstance(){
        if(fileSave == null){
            fileSave = new FileSave();
        }
        return fileSave;
    }


    public void writeFile(String filename, String message){
        try{
            FileOutputStream fileWriter = new FileOutputStream(filename);
            PrintWriter logWriter = new PrintWriter(fileWriter);
            logWriter.println(message);
            fileWriter.flush();
            logWriter.flush();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void writeFolder(String folderPath){
        new File(folderPath).mkdirs();
    }


}
