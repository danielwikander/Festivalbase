package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ResponsibilityTable {
       private ObservableList<responsibilityRow> rows = FXCollections.observableArrayList();

       public void addNewRow(String workerPersonNumber, String workerName, int responsibilityCount) {
              responsibilityRow newRow = new responsibilityRow(workerPersonNumber, workerName, responsibilityCount);
              rows.add(newRow);
       }

       public ObservableList getRowList() {
              return rows;
       }

       public class responsibilityRow {

              public String getWorkerPersonNumber() {
                     return workerPersonNumber;
              }

              public String getWorkerName() {
                     return workerName;
              }

              public int getResponsibilityCount() {
                     return responsibilityCount;
              }

              private String workerPersonNumber;
              private String workerName;
              private int    responsibilityCount;

              public responsibilityRow(String workerPersonNumber, String workerName, int responsibilityCount) {
                     this.workerPersonNumber = workerPersonNumber;
                     this.workerName = workerName;
                     this.responsibilityCount = responsibilityCount;
              }
       }

}
