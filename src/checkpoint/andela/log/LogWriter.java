package checkpoint.andela.log;

import checkpoint.andela.buffer.DataAndLogBuffer;
import checkpoint.andela.parser.FileParser;

import java.io.*;

/**
 * Created by Spykins on 28/01/2016.
 */
public class LogWriter implements Runnable {
  private String filePath;
  private DataAndLogBuffer dataAndLogBuffer;
  private FileWriter fileWriter;
  private BufferedWriter bufferedWriter;
  private PrintWriter printWriter;

  public LogWriter(String filePath){
    this.filePath = filePath;
    dataAndLogBuffer = DataAndLogBuffer.getInstance();
  }

  public void writeToLogger(String logString){
    int index = filePath.lastIndexOf("/");
    filePath = filePath.substring(0,index) + "/logger.txt";

    File loggerFile = new File(filePath);
    if(!loggerFile.exists()){
      try {
        loggerFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      fileWriter = new FileWriter(loggerFile, true);
      bufferedWriter = new BufferedWriter(fileWriter);
      printWriter = new PrintWriter(bufferedWriter);
      printWriter.println(logString);
      printWriter.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    while (true){
      writeToLogger(dataAndLogBuffer.removeItemFromLogBuffer());
      if(!dataAndLogBuffer.hasDataToRead()){
        break;
      }
    }
  }

}
