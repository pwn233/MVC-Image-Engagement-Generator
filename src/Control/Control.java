/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Model;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class Control {
    Model m = new Model();
    String p = "";
    public void inputPath(String p) {
        this.p = p;
    }
    public String returnStatus() {
        return m.insertDataToDB(p);
    }
    public void generate() {
        m.generator();
        m.viralCal();
    }
    public DefaultTableModel showData(DefaultTableModel model) {
        m.showData(model);
        return model;
    }
}
