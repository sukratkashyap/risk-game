package game.core;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description a result class to give back result after saving data
 */
public class Result {

    private final boolean _isSuccess;
    private final String _message;

    public Result(boolean isSuccess, String message) {
        this._isSuccess = isSuccess;
        this._message = message;
    }

    public boolean IsSuccessful() {
        return _isSuccess;
    }

    public String ErrorMsg() {
        return _message;
    }
}
