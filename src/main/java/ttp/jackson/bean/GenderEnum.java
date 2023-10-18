package ttp.jackson.bean;

/**
 * @author djh
 */
public enum GenderEnum {

    UNKNOWN(0, "未知"),

    MALE(1, "男"),

    FEMALE(2, "女");

    private final Integer code;
    private final String description;

    GenderEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static GenderEnum findByCode(int code) {
        for (GenderEnum ge : GenderEnum.values()) {
            if (ge.code == code) {
                return ge;
            }
        }

        return UNKNOWN;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "GenderEnum{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}
