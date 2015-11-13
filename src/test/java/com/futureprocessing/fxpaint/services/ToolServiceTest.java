package com.futureprocessing.fxpaint.services;

import com.futureprocessing.fxpaint.model.tools.Tool;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.futureprocessing.fxpaint.services.ToolService.NULL_TOOL;
import static org.assertj.core.api.Assertions.assertThat;

public class ToolServiceTest {

    private static final String NON_EXISTING_TOOL = "nonExistingTool";
    public static final String TOOL_ID = "toolId";

    @Test
    public void shouldReturnNullToolWhenGettingNonExistingTool() {
        // given
        Set<Tool> emptySetOfTools = new HashSet<>();
        ToolService toolService = new ToolService(emptySetOfTools);

        // when
        Tool result = toolService.getToolById(NON_EXISTING_TOOL);

        // then
        assertThat(result).isSameAs(NULL_TOOL);
    }

    @Test
    public void shouldReturnToolByItsId() {
        // given
        Tool tool = () -> TOOL_ID;
        Set<Tool> tools = new HashSet<>();
        tools.add(tool);
        ToolService toolService = new ToolService(tools);

        // when
        Tool result = toolService.getToolById(TOOL_ID);

        // then
        assertThat(result).isSameAs(result);
    }
}