package extrator;

import java.util.ArrayList;
import java.util.List;

public class Metrics implements BooleanConvertable {

  private String mergeId;
  private boolean isConflicting;
  private boolean existCommonSlices;
  private int totalCommonSlices;

  public Metrics(String mergeId, boolean isConflicting, boolean existCommonSlices,
      int totalCommonSlices) {
    this.mergeId = mergeId;
    this.isConflicting = isConflicting;
    this.existCommonSlices = existCommonSlices;
    this.totalCommonSlices = totalCommonSlices;
  }

  public String getMergeId() {
    return mergeId;
  }

  public boolean isConflicting() {
    return isConflicting;
  }

  public boolean isExistCommonSlices() {
    return existCommonSlices;
  }

  public int getTotalCommonSlices() {
    return totalCommonSlices;
  }

  @Override
  public List<String> convert() {
    List<String> convertedMetric = new ArrayList<>();
    convertedMetric.add(this.mergeId);
    convertedMetric.add("" + ((this.isConflicting)? 1 : 0));
    convertedMetric.add(""+ ((this.existCommonSlices)? 1 : 0));
    convertedMetric.add(""+ this.totalCommonSlices);
    return convertedMetric;
  }

  public String[] convertToStringArray(){
    String[] stringArray = new String[4];
    List<String> converted = this.convert();
    int index = 0;
    for(String column: converted){
      stringArray[index] = column;
      index++;
    }
    return stringArray;
  }
}
