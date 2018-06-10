package extrator;


import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MergeScenarioReader {
  private CSVReader reader;
  private CsvToBean<MergeScenario> mergeScenarios;
  private List<MergeScenario> allMergeScenarios;
  private ColumnPositionMappingStrategy mappingStrategy;
  private String[] columns;

  public MergeScenarioReader(Path csvPath) throws IOException {
    try (Reader reader = new FileReader(csvPath.toFile())) {
      this.reader = new CSVReader(reader,',','"',1);
      this.mappingStrategy =
          new ColumnPositionMappingStrategy();
      //Set mappingStrategy type to Employee Type
      mappingStrategy.setType(MergeScenario.class);
      //Fields in Employee Bean
      this.columns = new String[]{"mergeCommitId", "isMergeConflicting", "filesConflictants",
          "parent1Id", "parent1Files", "parent2Id", "parent2Files", "ancestorId",
          "numberOfConflicts"};
      //Setting the colums for mappingStrategy
      mappingStrategy.setColumnMapping(columns);
      this.mergeScenarios= new CsvToBean<>();
      this.allMergeScenarios = null;
    }
  }

  public Iterator<MergeScenario> getIterator() {
    return this.mergeScenarios.iterator();
  }

  public synchronized List<MergeScenario> getAll() {
    if (this.allMergeScenarios == null) {
      this.allMergeScenarios = this.mergeScenarios.parse(this.mappingStrategy, this.reader);
    }
    return this.allMergeScenarios;
  }

  public CsvToBean<MergeScenario> getMergeScenarios() {
    return mergeScenarios;
  }

  public List<MergeScenario> getAllMergeScenarios() {
    return allMergeScenarios;
  }
}
