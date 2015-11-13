package com.futureprocessing.fxpaint.inject;

import com.futureprocessing.fxpaint.model.HtmlColor;
import com.futureprocessing.fxpaint.model.operations.ExitOperation;
import com.futureprocessing.fxpaint.model.operations.FileOpenOperation;
import com.futureprocessing.fxpaint.model.operations.FlipHorizontallyOperation;
import com.futureprocessing.fxpaint.model.operations.FlipVerticallyOperation;
import com.futureprocessing.fxpaint.model.operations.ImageSaveOperation;
import com.futureprocessing.fxpaint.model.operations.Operation;
import com.futureprocessing.fxpaint.model.operations.RedoOperation;
import com.futureprocessing.fxpaint.model.operations.Rotate180Operation;
import com.futureprocessing.fxpaint.model.operations.RotateLeft90Operation;
import com.futureprocessing.fxpaint.model.operations.RotateRight90Operation;
import com.futureprocessing.fxpaint.model.operations.UndoOperation;
import com.futureprocessing.fxpaint.model.tools.BucketTool;
import com.futureprocessing.fxpaint.model.tools.EraserTool;
import com.futureprocessing.fxpaint.model.tools.LineTool;
import com.futureprocessing.fxpaint.model.tools.OvalTool;
import com.futureprocessing.fxpaint.model.tools.PencilTool;
import com.futureprocessing.fxpaint.model.tools.RectangleTool;
import com.futureprocessing.fxpaint.model.tools.Tool;
import com.futureprocessing.fxpaint.notifiers.ChangeColorOnColorSelectionNotifier;
import com.futureprocessing.fxpaint.notifiers.ChangeToolOnToolSelectionNotifier;
import com.futureprocessing.fxpaint.notifiers.Notifier;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;

import java.lang.reflect.Type;

public class NotifiersInjector extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<Notifier<HtmlColor>>() {}).to(ChangeColorOnColorSelectionNotifier.class);
        bind(new TypeLiteral<Notifier<Tool>>() {}).to(ChangeToolOnToolSelectionNotifier.class);
    }
}
