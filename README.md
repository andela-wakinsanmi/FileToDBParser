# File to DB Parser

File To DB Parser is a multi-thread program that reads data from a reaction file and writes the data into Database. The reaction file is a key : value paired data. Key is separated from value with " - ", the File has comment line that starts with "#". Data is separated by "//", the snippet below shows a sample of the file.

```
	# Authors:
	#    Pallavi Subhraveti
	#    Quang Ong
	#    Ingrid Keseler
	#    Anamika Kothari
	#    Tim Holland

	UNIQUE-ID - ALKAPHOSPHA-RXN
	TYPES - Small-Molecule-Reactions
	TYPES - Chemical-Reactions
	LEFT - WATER
	ORPHAN? - :NO
	PHYSIOLOGICALLY-RELEVANT? - T
	RIGHT - |Alcohols|
	RIGHT - |Pi|
//
	UNIQUE-ID - 6-PHOSPHO-BETA-GALACTOSIDASE-RXN
	TYPES - Small-Molecule-Reactions
	TYPES - Chemical-Reactions
	ATOM-MAPPINGS - (:NO-HYDROGEN-ENCODING (0 1 2 3 4 5 6 7 8 16 11 10 12 13 14 15 17 9) (((CPD-1241 0 15)
	LEFT - WATER
	PHYSIOLOGICALLY-RELEVANT? - T
	RIGHT - CPD-1241
	RIGHT - |Alcohols|
//
```

##checkpoint.andela.Model


###FileDelimeters Class

####Reading a different Type of File

This project was designed with reactions.dat file structure in mind. A client can read in another key value pair file, client will have to change the FileDelimeters enum class in checkpoint.andela.model package by replacing the KEY_VALUE_SEPARATOR, COMMENT and END_OF_DATA parameter.

```java
	KEY_VALUE_SEPARATOR(" - "),
  	COMMENT("#"),
  	END_OF_DATA("//");
```

###DatabaseConfig.java

Client using this project has to create a MYSQL database and change the configuration settings in this file

```java
	DATABASE_USER_NAME("root"),
  	DATABASE_PASSWORD("password"),
  	DATABASE_NAME ("reactiondb"),
  	TABLE_NAME("reactions");
  	DATABASE_TYPE("jdbc:mysql://localhost:3306/");
```

###InputFieldModel

Client will specify the keys he/she wants to read from the file and save to  database.

```java
	unique_id("UNIQUE-ID"),
  	common_name("COMMON-NAME"),
  	atom_mappings("ATOM-MAPPINGS"),
  	credits("CREDITS"),
  	ec_number("EC-NUMBER"),
  	enzymatic_reaction("ENZYMATIC-REACTION"),
  	in_pathway("IN-PATHWAY"),
  	orphan("ORPHAN?"),
  	physiologically_relevant("PHYSIOLOGICALLY-RELEVANT?"),
  	reation_direction("REACTION-DIRECTION");

```

##checkpoint.andela.main


### Class MainApp

Class MainApp is the gateway into the Application. A Client will have to pass the path to the reaction file into the class.

#### MainApp Constructor

+ ```  public MainApp(String filePath)```

#### MainApp Method

To start the Application, clients have to call the method runApplication. The Method implements the classes DBWriter, FileParser and LogWriter Class as Threads.

+ ```  public void runApplication()```

Implementation

```java
	MainApp mainApp = new MainApp("/Users/Spykins/IdeaProjects/FileToDBParser/res/reactions.dat");
    mainApp.runApplication();
```

##checkpoint.andela.parser


### File Parser Class

FileParser class implements Runnable. The FileParser Constructor takes in the File Path and it has a private variable readData of type ReadData.

#### FileParser Constructor

```   public FileParser(String filePath) ```

#### FileParser Methods

+ ```   public void run() ```
+ ```   private void initializeFileParser() ```

The run method is called when the Thread Starts, the run method calls initializeFileParser() which adds item into the buffer.


### ReadData Class

The ReadData class reads in Data from the file, it takes the File Path in the constructor. It has a public method getData() which reads the file line by line, it stops when it gets to the end of data. It returns a HashMap of the Key and Value in the data and returns null when it gets to end of file.

#### FileParser Constructor

+ ```   public ReadData(String pathToFile) ```

#### FileParser Method

+ ```   public HashMap<String,String> getData()```

## checkpoint.andela.db

###DBWriter Class

DBWriter implements Runnable, it takes Data from the Buffer and writes it into the database.

#### DBWriter Constructor

+ ```   public DBWriter()  ```

#### DBWriter Methods

+ ```   public void insertIntoDatabase (HashMap<String, String> dataToInsertInDatabase )```
+ ```  public void run() ```

insertIntoDatabase() takes data from the data Buffer and stores into database. The run method is called when the thread starts, The run method calls insertIntoDatabase method which removes the data stored into the Data buffer and writes it into database.

### QueryDatabase Class

QueryDatabase is the class that queries the Database. The QueryDatabase constructor takes in Connection object as a parameter.

#### QueryDatabase Constructor

+ ```  public QueryDatabase(Connection databaseConnection)```

#### QueryDatabase Method

+ ```   public boolean createTable(String tableName)```
+ ```   public boolean insertIntoDatabase(String tableName, HashMap<String, String> dataToInsertInDatabase) ```
+ ```   public boolean deleteTableFromDatabase(String tableName)```
+ ```   public boolean hasTable(String tableName)```

The createTable method creates table in the database if the table doesn't exist, the insertIntoDatabase method takes the table name,HashMap of the data and insert it into the database.

```java
	Connection databaseConnection = DriverManager.getConnection(DatabaseConfig.DATABASE_TYPE.getRealName() +
	                                DatabaseConfig.DATABASE_NAME.getGetRealName(),
	DatabaseConfig.DATABASE_USER_NAME.getGetRealName(), DatabaseConfig.DATABASE_PASSWORD.getGetRealName());
	QueryDatabase queryDatabase = new QueryDatabase(databaseConnection);

```


## checkpoint.andela.log

### LogWriter Class

LogWriter class writes the activity of the DBWriter and FileParser thread into an output text file. LogWriter implements Runnable.

#### LogWriter Constructor

+ ```   public LogWriter(String filePath) ```

#### LogWriter Method

+ ```   public void writeToLogger(String logString) ```
+ ```   public void run() ```

## checkpoint.andela.buffer

### DataAndLogBuffer Class

DataAndLogBuffer class is a Singleton class that keeps track of the data buffer and log buffer.

### DataAndLogBuffer Constructor

+ ```   private DataAndLogBuffer() ```

### DataAndLogBuffer Methods

+ ```   public static synchronized DataAndLogBuffer getInstance() ```
+  ```   public void addItemToDataBuffer(HashMap<String, String> itemToAddToBuffer) ```
+  ```   public HashMap<String, String> removeItemFromDataBuffer() ```
+  ```     public void addItemToLogBuffer(String logToAdd) ```
+  ```   public String removeItemFromLogBuffer() ```
+  ```   public ArrayBlockingQueue<String> getBufferUsedToStoreLogInfo()```
+  ```   public ArrayBlockingQueue<HashMap<String, String>> getBufferUsedToStoreData() ```
+  ```   public boolean hasDataToRead() ```
+  ```   public void setDataAvailable(boolean flag) ```


### UML Diagram

![fileparser](https://cloud.githubusercontent.com/assets/16117695/15604127/610df3d2-23f6-11e6-81ed-5343c97774a2.png)

