package com.futureprocessing.fxpaint.inject;

import com.futureprocessing.fxpaint.model.tools.BucketTool;
import com.futureprocessing.fxpaint.model.tools.EraserTool;
import com.futureprocessing.fxpaint.model.tools.LineTool;
import com.futureprocessing.fxpaint.model.tools.OvalTool;
import com.futureprocessing.fxpaint.model.tools.PencilTool;
import com.futureprocessing.fxpaint.model.tools.RectangleTool;
import com.futureprocessing.fxpaint.model.tools.Tool;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class ToolsInjector extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<Tool> tools = Multibinder.newSetBinder(binder(), Tool.class);
        tools.addBinding().to(BucketTool.class);
        tools.addBinding().to(EraserTool.class);
        tools.addBinding().to(LineTool.class);
        tools.addBinding().to(OvalTool.class);
        tools.addBinding().to(PencilTool.class);
        tools.addBinding().to(RectangleTool.class);
    }
}
