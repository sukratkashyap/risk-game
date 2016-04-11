package game.graphic;

import game.core.IValidationInput;
import game.core.Result;
import java.awt.event.WindowEvent;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description
 */
public class GUI implements IRefreshable {

    private final RiskFrame _riskFrame;

    public GUI() {
        _riskFrame = new RiskFrame();
        _riskFrame.refresh();
    }

    public String getInputFromUser(String inputQuestion, IValidationInput validationInput) {
        Result result;
        String input = "";
        do {
            _riskFrame.getMessagePanel().addQuestion(inputQuestion);
            input = _riskFrame.getCommandPanel().getCommand().trim();
            result = validationInput.isValid(input);
            addResult(input);
            if (result.isSuccessful()) {
                break;
            }
            _riskFrame.getMessagePanel().addError(result.errorMsg());
        } while (true);
        return input;
    }

    public void addQuestion(String text) {
        _riskFrame.getMessagePanel().addQuestion(text);
    }

    public String getCommand() {
        return _riskFrame.getCommandPanel().getCommand();
    }

    public void addResult(String text) {
        _riskFrame.getMessagePanel().addResult(text);
    }

    /**
     * repaint and revalidate the frame.
     */
   
    @Override
    public void refresh() {
        _riskFrame.refresh();
    }

    public void close() {
        _riskFrame.dispose();
        _riskFrame.dispatchEvent(new WindowEvent(_riskFrame, WindowEvent.WINDOW_CLOSING));
    }

    public RiskFrame getRiskFrame() {
        return _riskFrame;
    }

}
