package com.github.valentina810.weekplannerformarusia.service.parameterized.weeklyplan;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ParameterForWeeklyPlanTest {
    private String testName;
    private String jsonFileSource;
    private String expectedResult;
}