package com.github.valentina810.weekplannerformarusia.context;


import lombok.Builder;

/**
 * Хранилище, которое хранит данные в контексте сессии
 * поле session_state
 * лимит размера json-объекта session_state — 5 Кбайт
 * состояние теряется в таких случаях:
 * - пользователь выходит из скилла;
 * - скилл сам явно завершает работу, передав "end_session": true;
 * - выход происходит по таймауту, когда пользователь не отвечает некоторое время (1 минуту).
 * https://dev.vk.com/ru/marusia/session-state
 */
@Builder
public class SessionStorage {
    //содержит название предыдущей активности
    private Object session_state;
}
