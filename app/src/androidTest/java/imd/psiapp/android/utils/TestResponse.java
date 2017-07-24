package imd.psiapp.android.utils;

/**
 * model class for test case response api
 * Created by egiadtya on 7/24/17.
 */

public class TestResponse<T> {
    private T data;
    private String errorMessage;
    private boolean success;
    private boolean idle;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isIdle() {
        return idle;
    }

    public void setIdle(boolean idle) {
        this.idle = idle;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
