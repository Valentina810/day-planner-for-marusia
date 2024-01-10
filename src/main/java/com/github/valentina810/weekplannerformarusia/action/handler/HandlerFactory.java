package com.github.valentina810.weekplannerformarusia.action.handler;

import com.github.valentina810.weekplannerformarusia.action.Command;
import com.github.valentina810.weekplannerformarusia.action.TypeAction;
import com.github.valentina810.weekplannerformarusia.action.handler.composite.BaseCompositeExecutor;
import com.github.valentina810.weekplannerformarusia.action.handler.template.CompositeHandler;
import com.github.valentina810.weekplannerformarusia.action.handler.template.SimpleHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class HandlerFactory {
    private final Map<TypeAction, BaseCompositeExecutor> baseExecutors;

    public HandlerFactory(List<BaseCompositeExecutor> baseExecutors) {
        this.baseExecutors = baseExecutors.stream()
                .collect(Collectors.toMap(BaseCompositeExecutor::getType, Function.identity()));
    }

    public SimpleHandler getHandler(Command command) {
        ParametersHandler parametersHandler = ParametersHandler.builder().command(command).build();
        if (command.getIsSimple()) {
            return new SimpleHandler(parametersHandler);
        } else {
            BaseCompositeExecutor baseCompositeExecutor = baseExecutors.get(command.getOperation());
            return baseCompositeExecutor == null ? new CompositeHandler(parametersHandler, baseExecutors.get(TypeAction.UNKNOWN).getActionExecute()) :
                    new CompositeHandler(parametersHandler, baseCompositeExecutor.getActionExecute());
        }
    }
}