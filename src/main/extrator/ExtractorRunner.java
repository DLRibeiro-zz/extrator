package extrator;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.help.Merge;

public class ExtractorRunner implements Runnable {

  private ComponentExtractor componentExtractor;
  private PackageExtractor packageExtractor;
  private MergeScenarioReader mergeScenarioReader;

  public ExtractorRunner() {
    this.componentExtractor = null;
    this.packageExtractor = null;
    this.mergeScenarioReader = null;
  }

  @Override
  public void run() {
    Properties properties = new Properties();
    this.packageExtractor = new PackageExtractor();
    try {
      loadProperties(properties);

      buildComponentExtractor();

      String[] repoNames = properties.getProperty("repos").split(",");
      String[] csvFilesPaths = new String[repoNames.length];

      int index = 0;

      for (String csvFilePath : csvFilesPaths) {
        String fileName =
            "resources/" + properties.get("folder") + "/" + repoNames[index].replace("\"", "")
                + "_MergeScenarioList.csv";
        File file = new File(this.getClass().getClassLoader().getResource(fileName).getFile());
        csvFilesPaths[index] = file.getPath();
        index++;
        System.out.println(file.getPath());
      }
      index = 0;
      for (String csvFile : csvFilesPaths) {
        Reader reader = Files.newBufferedReader(Paths.get(csvFile));
        CSVReader csvReader = new CSVReader(reader);
        // Reading Records One by One in a String array
        String[] nextRecord;
        List<Metrics> componentMetrics = new ArrayList<>();
        List<Metrics> packageMetricecs = new ArrayList<>();
        csvReader.readNext();
        while ((nextRecord = csvReader.readNext()) != null) {
          String[] ms = nextRecord;
          MergeScenario mergeScenario = new MergeScenario(ms[0], Boolean.parseBoolean(ms[1]), ms[2],
              ms[3], ms[4], ms[5], ms[6], ms[7], Integer.parseInt(ms[8]));
          Metrics mergeScenarioComponentMetrics = this.componentExtractor.extract(mergeScenario);
          Metrics mergeScenearioPackageMetrics = this.packageExtractor.extract(mergeScenario);
        }
        index++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void writeToComponentsToCsvFile(String repository, String extraName, List<Metrics> metrics)
      throws IOException {
    Writer writer = Files.newBufferedWriter(Paths.get(repository + extraName + ".csv"));
    CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR,
        CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
        CSVWriter.DEFAULT_LINE_END);
    String[] headerRecord = {"mergeID","isConfliting","existsCommonSlices","totalCommonSlices","leftComponents","rightComponents"};
    csvWriter.writeNext(headerRecord);
    for(Metrics metric: metrics){
      csvWriter.writeNext(((ComponentMetrics) metric).convertToComponentsStringArray());
    }
  }

  private void writeToCsvFile(String repository, String extraName, List<Metrics> metrics)
      throws IOException {
    Writer writer = Files.newBufferedWriter(Paths.get(repository + extraName + ".csv"));
    CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR,
        CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
        CSVWriter.DEFAULT_LINE_END);
    String[] headerRecord = {"mergeID","isConfliting","existsCommonSlices","totalCommonSlices"};
    csvWriter.writeNext(headerRecord);
    for(Metrics metric: metrics){
      csvWriter.writeNext(metric.convertToStringArray());
    }
  }

  private void buildComponentExtractor() throws IOException {
    File stopWords = new File(getClass().getClassLoader().getResource("stopWords").getFile());
    BufferedReader buffReader = new BufferedReader(new FileReader(stopWords));
    List<String> listStopWords = new ArrayList<>();
    String stopWord = "";
    while ((stopWord = buffReader.readLine()) != null) {
      listStopWords.add(stopWord);
    }
    File componentWords = new File(
        getClass().getClassLoader().getResource("componentWords").getFile());
    buffReader = new BufferedReader(new FileReader(componentWords));
    List<String> listComponentWords = new ArrayList<>();
    String componentWord = "";
    while ((componentWord = buffReader.readLine()) != null) {
      listComponentWords.add(componentWord);
    }
    this.componentExtractor = new ComponentExtractor(listComponentWords, listStopWords);
  }

  private void loadProperties(Properties properties) throws IOException {
    InputStream inputStream = getClass().getClassLoader()
        .getResourceAsStream(Constants.configFilename);
    if (inputStream != null) {
      properties.load(inputStream);
    }
  }
}
