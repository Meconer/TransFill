/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TransFill;

/**
 *
 * @author mats
 */
class MeasuredTool {
    private int dNo = -Integer.MAX_VALUE;
    private int sL = -Integer.MAX_VALUE;
    private int toolType = -Integer.MAX_VALUE;
    private String qVal = "";
    private String lVal = "";
    private String rVal = "";

    public int getdNo() {
        return dNo;
    }

    public void setdNo(int dNo) {
        this.dNo = dNo;
    }

    public int getsL() {
        return sL;
    }

    public void setsL(int sL) {
        this.sL = sL;
    }

    public String getqVal() {
        return qVal;
    }

    public void setqVal(String qVal) {
        this.qVal = qVal;
    }

    public String getlVal() {
        return lVal;
    }

    public void setlVal(String lVal) {
        this.lVal = lVal;
    }

    public String getrVal() {
        return rVal;
    }

    public void setrVal(String rVal) {
        this.rVal = rVal;
    }

    public void setToolType(int toolType) {
        this.toolType = toolType;
    }
    
    public int getToolType() {
        return toolType;
    }
    
    
}
