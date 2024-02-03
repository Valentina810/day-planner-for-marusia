package com.github.valentina810.weekplannerformarusia.storage.session;


import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;

/**
 * Хранилище, которое хранит данные в контексте сессии
 * поле session_state - передаем, state.session - получаем
 * лимит размера json-объекта session_state — 5 Кбайт
 * состояние теряется в таких случаях:
 * - пользователь выходит из скилла;
 * - скилл сам явно завершает работу, передав "end_session": true;
 * - выход происходит по таймауту, когда пользователь не отвечает некоторое время (1 минуту).
 * https://dev.vk.com/ru/marusia/session-state
 */

@Slf4j
public class SessionStorage {

    private static final Actions ACTIONS_EMPTY = Actions.builder().prevActions(Collections.emptyList()).build();
    private static final ActionsStorage ACTIONS_STORAGE_EMPTY = ActionsStorage.builder().actions(ACTIONS_EMPTY).build();

    private ActionsStorage actionsStorage;

    public void clear() {
        actionsStorage = ACTIONS_STORAGE_EMPTY;
    }

    public void setActions(Actions actions) {
        actionsStorage = ActionsStorage.builder().actions(actions).build();
    }

    public ActionsStorage getSession_state() {
        return actionsStorage;
    }

    public Actions getActions() {
        return getActionsStorage().getActions();
    }

    public void addPrevAction(PrevAction prevAction) {
        Actions actions = actionsStorage.getActions();
        actions.addAction(prevAction);
        actionsStorage = ActionsStorage.builder().actions(actions).build();
    }

    public void setActionsInSessionState(Object object) {
        try {
            actionsStorage = Optional.of(object)
                    .filter(obj -> obj instanceof JSONObject)
                    .map(parseObject())
                    .orElse(ACTIONS_STORAGE_EMPTY);
        } catch (Exception e) {
            log.error("Ошибка при преобразовании ActionsStorage из входящего запроса:{}", e.getMessage());
            setActions(ACTIONS_EMPTY);
        }
    }

    /**
     * Возвращает session_state в виде объекта ActionsStorage
     */
    private ActionsStorage getActionsStorage() {
        return Optional.ofNullable(actionsStorage)
                .map(obj -> new Gson().fromJson(new Gson().toJson(obj), ActionsStorage.class))
                .orElse(ACTIONS_STORAGE_EMPTY);
    }

    private Function<Object, ActionsStorage> parseObject() {
        return obj -> {
            if ("{}".equals(obj.toString())) {
                return ACTIONS_STORAGE_EMPTY;
            }
            return new Gson().fromJson(obj.toString(), ActionsStorage.class);
        };
    }
}
