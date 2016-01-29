package checkpoint.andela.main;

import checkpoint.andela.db.DBWriter;
import checkpoint.andela.log.LogWriter;
import checkpoint.andela.parser.FileParser;

/**
 * Created by Spykins on 29/01/2016.
 */
public class MainApp {
  String filePath;

  public MainApp(String filePath){
    this.filePath = filePath;
  }

  public void runApplication(){
    FileParser fileParser = new FileParser(filePath);
    DBWriter dbWriter = new DBWriter();
    LogWriter logWriter = new LogWriter(filePath);

    Thread fileParserThread = new Thread(fileParser);
    Thread dbWriterThread = new Thread(dbWriter);
    Thread logWriterThread = new Thread(logWriter);

    fileParserThread.start();
    dbWriterThread.start();
    logWriterThread.start();

    try {
      fileParserThread.join();
      dbWriterThread.join();
      logWriterThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }



  }
}
