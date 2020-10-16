package kr.co.kesti.model.response;

import kr.co.kesti.model.statics.ResponseCode;
import lombok.*;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private boolean status;
    private int code;
    private T data;
    private Object param;
    private String message;

    public static ApiResponse<?> ok() {
        return ApiResponse.builder()
                .status(true)
                .code(ResponseCode.OK.getHttpStatus().value())
                .build();
    }

    public static <T> ApiResponse<?> ok(final T data) {
        return ApiResponse.builder()
                .status(true)
                .code(ResponseCode.OK.getHttpStatus().value())
                .data(data)
                .build();
    }

    public static <T> ApiResponse<?> ok(final T data, final Object param) {
        return ApiResponse.builder()
                .status(true)
                .code(ResponseCode.OK.getHttpStatus().value())
                .data(data)
                .param(param)
                .build();
    }

    public static <T> ApiResponse<?> ok(final T data, final Object param, final String message) {
        return ApiResponse.builder()
                .status(true)
                .code(ResponseCode.OK.getHttpStatus().value())
                .data(data)
                .param(param)
                .message(message)
                .build();
    }

    public static ApiResponse<?> error(final int code, final String msg) {
        return ApiResponse.builder()
                .status(false)
                .code(code)
                .message(msg).build();
    }
}