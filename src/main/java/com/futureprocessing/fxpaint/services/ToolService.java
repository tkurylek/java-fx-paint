package com.futureprocessing.fxpaint.services;

import com.futureprocessing.fxpaint.model.tools.NullTool;
import com.futureprocessing.fxpaint.model.tools.Tool;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

@Singleton
public class ToolService {

    public static final Tool NULL_TOOL = new NullTool();
    private Map<String, Tool> tools = new HashMap<>();

    @Inject
    public ToolService(Set<Tool> set) {
        tools = set.stream().collect(toMap(Tool::id, tool -> tool));
    }

    public Tool getToolById(String id) {
        return tools.getOrDefault(id, NULL_TOOL);
    }
}
