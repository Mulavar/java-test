package tax;


public class TaxConfig {
  /**
   * 每月收入
   */
  private double income;
  /**
   * 免税基数
   */
  private double basic;
  /**
   * 社保
   */
  private double socialAssure;

  public TaxConfig(double income, double basic, double socialAssure) {
    this.income = income;
    this.basic = basic;
    this.socialAssure = socialAssure;
  }


  public double getIncome() {
    return income;
  }

  public void setIncome(double income) {
    this.income = income;
  }

  public double getBasic() {
    return basic;
  }

  public double getSocialAssure() {
    return socialAssure;
  }

  public void setSocialAssure(double socialAssure) {
    this.socialAssure = socialAssure;
  }
}
