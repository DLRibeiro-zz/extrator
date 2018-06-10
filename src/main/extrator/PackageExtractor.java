package extrator;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

public class PackageExtractor implements Extractor<MergeScenario> {

  @Override
  public Metrics extract(MergeScenario mergeScenario) {
    Metrics metrics = null;
    List<String> leftJavaFiles = this.getJavaFiles(mergeScenario.getParent1Files());
    List<String> rightJavaFiles = this.getJavaFiles(mergeScenario.getParent2Files());
    List<String> leftPackage = new ArrayList<>();
    List<String> rightPackage = new ArrayList<>();
    for(String javaFile : leftJavaFiles){
      leftPackage.add(this.getPackage(javaFile));
    }
    for(String javaFile: rightJavaFiles){
      rightPackage.add(this.getPackage(javaFile));
    }
    int commonPackageCount = this.checkCommonPackages(leftPackage, rightPackage);
    boolean existCommonPackage = (commonPackageCount > 0) ? true : false;
    metrics = new Metrics(mergeScenario.getMergeCommitId(), mergeScenario.isMergeConfliting(), existCommonPackage, commonPackageCount);
    return metrics;
  }

  private List<String> getJavaFiles(String fileList) {
    List<String> javaFiles = new ArrayList<>();
    String[] allFiles = fileList.replace("[", "").replace("]", "").trim().split("@");
    for (String file : allFiles) {
      if (file.endsWith(".java")) {
        javaFiles.add(file);
      }
    }
    return javaFiles;
  }

  private int checkCommonPackages(List<String> packagesLeft, List<String> packagesRight){
    int commonPackageCount = 0;
    for(String packageLeft: packagesLeft){
      if(packagesRight.contains(packageLeft)){
        commonPackageCount++;
      }
    }
    return commonPackageCount;
  }

  private String getPackage(String file){
    String packageName = FilenameUtils.getPath(file);
    return packageName;
  }
}
