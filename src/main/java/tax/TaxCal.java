package tax;

import java.util.HashMap;
import java.util.Map;

public class TaxCal {

  /**
   * 计算 month 个月每个月实际收入
   *
   * @param context
   * @param month
   */
  public static void cal(TaxContext context, int month) {

    if (month > 12 || month < 1) {
      throw new IllegalArgumentException("");
    }

    for (int i = 1; i <= month; i++) {
      // 累计
      context.plusOneMonthRegular();
      // 应税额度
      double needTaxAmount = context.getAccumulativeIncome() - context.getAccumulativeDeduct();

      // 计算应税率和累计扣除数
      double rate = context.getTaxRate(needTaxAmount);
      double deduct = context.getTaxDeduct(needTaxAmount);

      // 计算出应缴税
      double monthTaxLogical = needTaxAmount * rate - deduct;
      // 计算出实缴税
      double monthTaxPhysical = monthTaxLogical - context.getLastMonthTax();
      // 记录本月纳税额度
      context.logLastMonthTax(monthTaxLogical);

      context.getMap().put(i, context.getConfig().getIncome() - monthTaxPhysical - context.getConfig().getSocialAssure());
      System.out.println(i + "月份：缴税扣除" + monthTaxPhysical + "元， 社保扣除"
              + context.getConfig().getSocialAssure() + ", 到手" + context.getMap().get(i));
    }
  }

  public static void main(String[] args) {
    TaxConfig config = new TaxConfig(27000, 5000, 6078);
    TaxContext context = new TaxContext(config);
    cal(context, 12);

    double accumulativeAmount = 0;
    for (Map.Entry<Integer, Double> entry : context.getMap().entrySet()) {
      accumulativeAmount += entry.getValue();
    }

    System.out.println(accumulativeAmount);

  }

}

