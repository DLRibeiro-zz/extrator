package extrator;

import java.util.List;

public class ComponentMetrics extends Metrics {

  private List<String> leftComponents;
  private List<String> rightComponents;

  public ComponentMetrics(String mergeId, boolean isConflicting, boolean existCommonSlices,
      int totalCommonSlices) {
    super(mergeId, isConflicting, existCommonSlices, totalCommonSlices);
  }

  public ComponentMetrics(String mergeId, boolean isConflicting, boolean existCommonSlices, int totalCommonSlices,
      List<String> leftComponents, List<String> rightComponents){
      super(mergeId, isConflicting, existCommonSlices, totalCommonSlices);
      this.leftComponents = leftComponents;
      this.rightComponents = rightComponents;
  }

  public List<String> getLeftComponents() {
    return leftComponents;
  }

  public void setLeftComponents(List<String> leftComponents) {
    this.leftComponents = leftComponents;
  }

  public List<String> getRightComponents() {
    return rightComponents;
  }

  public void setRightComponents(List<String> rightComponents) {
    this.rightComponents = rightComponents;
  }

  public String[] convertToComponentsStringArray(){
    String[] stringArray = new String[6];
    List<String> converted = this.convert();
    int index = 0;
    for(String column: converted){
      stringArray[index] = column;
      index++;
    }
    stringArray[4] = this.leftComponents.toString();
    stringArray[5] = this.rightComponents.toString();
    return stringArray;
  }

}
