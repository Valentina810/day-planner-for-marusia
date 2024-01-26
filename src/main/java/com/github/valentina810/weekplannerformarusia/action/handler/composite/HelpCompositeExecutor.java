package com.github.valentina810.weekplannerformarusia.action.handler.composite;

import com.github.valentina810.weekplannerformarusia.action.TypeAction;
import com.github.valentina810.weekplannerformarusia.dto.Command;
import com.github.valentina810.weekplannerformarusia.dto.ExecutorParameter;
import com.github.valentina810.weekplannerformarusia.dto.ResponseParameters;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.github.valentina810.weekplannerformarusia.action.TypeAction.HELP;

@Component
@RequiredArgsConstructor
public class HelpCompositeExecutor implements BaseCompositeExecutor {
    @Override
    public TypeAction getType() {
        return HELP;
    }

    @Override
    public ResponseParameters getResponseParameters(ExecutorParameter executorParameter) {
        return null;
    }

    @Override
    public Command getCommand() {
        return BaseCompositeExecutor.super.getCommand();
    }

//    @Override
//    public UnaryOperator<ResponseParameters> getActionExecute() {
//        return parHandler ->
//        {
//            parHandler.setRespPhrase(parHandler.getCommand().getMessagePositive());
//            parHandler.getSessionStorage().addAction(
//                    PrevAction.builder()
//                            .number(0)
//                            .operation(parHandler.getCommand().getOperation())
//                            .valueAction("").build());
//            return parHandler;
//        };
//    }
}