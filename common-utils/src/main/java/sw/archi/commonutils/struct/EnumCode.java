package sw.archi.commonutils.struct;

import lombok.Getter;

@Getter
public enum EnumCode {
    SUCCESS(200, "ok"),

    AUTHORITY_ERROR(401, "Unauthorized"),

    REQUEST_ERROR(515, "Request failed"),

    INTERNAL_ERROR(500, "Server internal error"),

    FILE_EMPTY_ERROR(431, "File empty error"),

    FILE_TRANSFER_ERROR(430, "File transfer error");

    private int code; // 状态码
    private String message; // 描述

    private EnumCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static EnumCode getEnumCodeType(int code) {
        switch (code) {
            case 200:
                return EnumCode.SUCCESS;
            case 401:
                return EnumCode.AUTHORITY_ERROR;
            case 515:
                return EnumCode.REQUEST_ERROR;
            default:
                return EnumCode.INTERNAL_ERROR;
        }
    }
}
