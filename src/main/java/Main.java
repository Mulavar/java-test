import java.util.Arrays;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        String join = String.join(",", "a");
        System.out.println(join);
        String[] split = join.split(",");
        System.out.println(split.length);
        System.out.println(Arrays.toString(split));

        System.out.println(UUID.randomUUID().toString());
    }
}
