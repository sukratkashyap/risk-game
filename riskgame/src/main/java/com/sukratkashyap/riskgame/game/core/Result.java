package com.sukratkashyap.riskgame.game.core;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description a result class to give back result after saving data
 */
public class Result<T> {

    private final boolean _isSuccess;
    private final String _message;
    private final T _result;

    public Result(boolean isSuccess, String message, T result) {
        this._isSuccess = isSuccess;
        this._message = message;
        this._result = result;
    }

    public Result(boolean isSuccess, String message) {
        this._isSuccess = isSuccess;
        this._message = message;
        this._result = null;
    }

    public Result(boolean isSuccess) {
        this._isSuccess = isSuccess;
        this._message = "";
        this._result = null;
    }

    public boolean isSuccessful() {
        return _isSuccess;
    }

    public String errorMsg() {
        return _message;
    }

    public T result() {
        return _result;
    }
}
