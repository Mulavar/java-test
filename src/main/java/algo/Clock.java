package algo;

public class Clock {
  public static void main(String[] args) {
    // n 分钟
    // 分钟分针转动角度 n*6
    // 时钟时针转动角度 n/2
    for (int i = 0; i < 12; i++) {
      // 0-11点 时钟分钟初始角度
      int initialInterval = 30 * i;
      // n*6 - n/2 = initialInterval - 5
      // n*6 - n/2 = initialInterval + 5
      // 计算 n
    }
  }
}
