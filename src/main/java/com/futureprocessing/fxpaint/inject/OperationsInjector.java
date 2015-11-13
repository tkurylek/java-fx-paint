package com.futureprocessing.fxpaint.inject;

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
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class OperationsInjector extends AbstractModule {
    @Override
    protected void configure() {
        Multibinder<Operation> operations = Multibinder.newSetBinder(binder(), Operation.class);
        operations.addBinding().to(FileOpenOperation.class);
        operations.addBinding().to(ImageSaveOperation.class);
        operations.addBinding().to(ExitOperation.class);
        operations.addBinding().to(UndoOperation.class);
        operations.addBinding().to(RedoOperation.class);
        operations.addBinding().to(RotateRight90Operation.class);
        operations.addBinding().to(RotateLeft90Operation.class);
        operations.addBinding().to(Rotate180Operation.class);
        operations.addBinding().to(FlipHorizontallyOperation.class);
        operations.addBinding().to(FlipVerticallyOperation.class);
    }
}
