package game.graphic;

import game.core.Result;
import game.data.IValidationInput;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description 
 */
public class GUI implements IRefreshable {

    private RiskFrame _riskFrame;

    public GUI() {
        _riskFrame = new RiskFrame();
        _riskFrame.refresh();
    }

    @Override
    public void refresh() {
        _riskFrame.refresh();
    }

    public String getCommand() {
        return _riskFrame.getCommandPanel().getCommand();
    }

    public String getInputFromUser(String inputQuestion, IValidationInput validationInput) {
        Result result;
        String input = "";
        do {
            _riskFrame.getMessagePanel().addQuestion(inputQuestion);
            input = _riskFrame.getCommandPanel().getCommand();
            result = validationInput.isValid(input);
            if (result.IsSuccessful()) {
                break;
            }
            _riskFrame.getMessagePanel().addError(result.ErrorMsg());
        } while (true);
        return input;
    }

    public void addQuestion(String text) {
        _riskFrame.getMessagePanel().addQuestion(text);
    }

    public void addResult(String text) {
        _riskFrame.getMessagePanel().addResult(text);
    }
}
