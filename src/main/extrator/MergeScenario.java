package extrator;

import com.opencsv.bean.CsvBindByName;

public class MergeScenario {

  @CsvBindByName(column = "mergeCommitId")
  private String mergeCommitId;
  @CsvBindByName(column = "parent1Id")
  private String parent1Id;

  @CsvBindByName(column = "parent2Id")
  private String parent2Id;

  @CsvBindByName(column = "ancestorId")
  private String ancestorId;
  @CsvBindByName(column = "isMergeConfliting")
  private boolean isMergeConfliting;
  @CsvBindByName(column = "filesConflictants")
  private String filesConflictants;

  @CsvBindByName(column = "parent1Files")
  private String parent1Files;

  @CsvBindByName(column = "parent2Files")
  private String parent2Files;

  @CsvBindByName(column = "numberOfConflicts")
  private int numberOfConflicts;

  public MergeScenario(String mergeCommitId, boolean isMergeConfliting, String filesConflictants,String parent1Id, String parent1Files,
      String parent2Id,String parent2Files ,String ancestorId, int numberOfConflicts) {
    this.mergeCommitId = mergeCommitId;
    this.parent1Id = parent1Id;
    this.parent2Id = parent2Id;
    this.ancestorId = ancestorId;
    this.isMergeConfliting = isMergeConfliting;
    this.filesConflictants = filesConflictants;
    this.parent1Files = parent1Files;
    this.parent2Files = parent2Files;
    this.numberOfConflicts = numberOfConflicts;
  }

  public String getMergeCommitId() {
    return mergeCommitId;
  }

  public void setMergeCommitId(String mergeCommitId) {
    this.mergeCommitId = mergeCommitId;
  }

  public String getParent1Id() {
    return parent1Id;
  }

  public void setParent1Id(String parent1Id) {
    this.parent1Id = parent1Id;
  }

  public String getParent2Id() {
    return parent2Id;
  }

  public void setParent2Id(String parent2Id) {
    this.parent2Id = parent2Id;
  }

  public String getAncestorId() {
    return ancestorId;
  }

  public void setAncestorId(String ancestorId) {
    this.ancestorId = ancestorId;
  }

  public boolean isMergeConfliting() {
    return isMergeConfliting;
  }

  public void setIsMergeConfliting(boolean isMergeConfliting) {
    this.isMergeConfliting = isMergeConfliting;
  }

  public String getFilesConflictants() {
    return filesConflictants;
  }

  public void setFilesConflictants(String filesConflictants) {
    this.filesConflictants = filesConflictants;
  }

  public String getParent1Files() {
    return parent1Files;
  }

  public void setParent1Files(String parent1Files) {
    this.parent1Files = parent1Files;
  }

  public String getParent2Files() {
    return parent2Files;
  }

  public void setParent2Files(String parent2Files) {
    this.parent2Files = parent2Files;
  }

  public int getNumberOfConflicts() {
    return numberOfConflicts;
  }

  public void setNumberOfConflicts(int numberOfConflicts) {
    this.numberOfConflicts = numberOfConflicts;
  }

}
