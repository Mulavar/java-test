package tax;


import java.util.HashMap;

public class TaxContext {
  /**
   * 累计收入
   */
  private double accumulativeIncome;
  /**
   * 累计扣除
   */
  private double accumulativeDeduct;
  /**
   * 上个月应交税
   */
  private double lastMonthTax;
  private TaxConfig config;
  private HashMap<Integer, Double> map = new HashMap<>();

  public TaxContext(TaxConfig config) {
    this.config = config;
  }

  public HashMap<Integer, Double> getMap() {
    return map;
  }

  /**
   * 累计
   */
  public void plusOneMonthRegular() {
    accumulativeIncome += config.getIncome();
    accumulativeDeduct += config.getSocialAssure();
    accumulativeDeduct += config.getBasic();
  }

  public void logLastMonthTax(double amount) {
    lastMonthTax = amount;
  }

  public double getLastMonthTax() {
    return lastMonthTax;
  }

  public double getAccumulativeIncome() {
    return accumulativeIncome;
  }

  public double getAccumulativeDeduct() {
    return accumulativeDeduct;
  }

  public TaxConfig getConfig() {
    return config;
  }

  public double getTaxRate(double taxAmount) {
    if (taxAmount <= 36000) {
      return 0.03;
    } else if (taxAmount > 36000 && taxAmount <= 144000) {
      return 0.1;
    } else if (taxAmount > 144000 && taxAmount <= 300000) {
      return 0.2;
    } else if (taxAmount > 300000 && taxAmount <= 420000) {
      return 0.25;
    } else if (taxAmount > 420000 && taxAmount <= 660000) {
      return 0.3;
    } else if (taxAmount > 660000 && taxAmount <= 960000) {
      return 0.35;
    } else {
      return 0.45;
    }
  }


  public double getTaxDeduct(double taxAmount) {
    if (taxAmount <= 36000) {
      return 0;
    } else if (taxAmount > 36000 && taxAmount <= 144000) {
      return 2520;
    } else if (taxAmount > 144000 && taxAmount <= 300000) {
      return 16920;
    } else if (taxAmount > 300000 && taxAmount <= 420000) {
      return 31920;
    } else if (taxAmount > 420000 && taxAmount <= 660000) {
      return 52920;
    } else if (taxAmount > 660000 && taxAmount <= 960000) {
      return 85920;
    } else {
      return 181920;
    }
  }
}