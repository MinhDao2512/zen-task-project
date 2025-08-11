package com.zentask.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ApiResponse<T> {
    /**
     * Status of the response (SUCCESS or ERROR).
     */
    private ResponseStatus status;
    /**
     * Optional message for successful or contextual responses.
     */
    private String message;
    /**
     * The data payload of the response.
     */
    private T data;
    /**
     * Error details if the request fails.
     */
    private ErrorResponse error;
    /**
     * Pagination metadata for list endpoints.
     */
    private Pagination pagination;
    /**
     * HATEOAS links to related resources.
     */
    private Map<String, String> _links;

    public enum ResponseStatus {
        SUCCESS, ERROR, PARTIAL, PENDING
    }

    /**
     * Pagination metadata for list endpoints.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Data
    public static class Pagination {
        private int currentPage;
        private int totalPages;
        private long totalItems;
        private int itemsPerPage;
        private Integer nextPage;
        private Integer previousPage;

        public Pagination(int currentPage, int totalPages, long totalItems, int itemsPerPage) {
            this.currentPage = currentPage;
            this.totalPages = totalPages;
            this.totalItems = totalItems;
            this.itemsPerPage = itemsPerPage;
            this.nextPage = currentPage < totalPages ? currentPage + 1 : null;
            this.previousPage = currentPage > 1 ? currentPage - 1 : null;
        }
    }

    /**
     * Represents a single validation error for a field.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Data
    @Builder
    public static class ValidationError {
        private String field;
        private String message;
    }

    /**
     * Represents error details for failed requests.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Data
    @Builder
    public static class ErrorResponse {
        private String code;
        private String message;
        private List<ValidationError> validationErrors;
        private String details;
    }

    public static <T> ApiResponse<T> ok(String message) {
        return ApiResponse.<T>builder()
                .status(ResponseStatus.SUCCESS)
                .message(message)
                .build();
    }

    public static <T> ApiResponse<T> ok(String message, T data) {
        return ApiResponse.<T>builder()
                .status(ResponseStatus.SUCCESS)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(String code, String message) {
        return ApiResponse.<T>builder()
                .status(ResponseStatus.ERROR)
                .error(ErrorResponse.builder()
                        .code(code)
                        .message(message)
                        .build())
                .build();
    }

    public static <T> ApiResponse<T> error(String code, String message, List<ApiResponse.ValidationError> validationErrors) {
        return ApiResponse.<T>builder()
                .status(ResponseStatus.ERROR)
                .error(ErrorResponse.builder()
                        .code(code)
                        .message(message)
                        .validationErrors(validationErrors)
                        .build())
                .build();
    }

}
