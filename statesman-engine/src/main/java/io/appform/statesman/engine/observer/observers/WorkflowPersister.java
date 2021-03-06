package io.appform.statesman.engine.observer.observers;

import io.appform.statesman.engine.WorkflowProvider;
import io.appform.statesman.engine.observer.ObservableEvent;
import io.appform.statesman.engine.observer.ObservableEventBusSubscriber;
import io.appform.statesman.engine.observer.ObservableEventVisitor;
import io.appform.statesman.engine.observer.events.StateTransitionEvent;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 *
 */
@Singleton
public class WorkflowPersister extends ObservableEventBusSubscriber {

    private final Provider<WorkflowProvider> workflowProvider;

    @Inject
    public WorkflowPersister(@Named("actionHandler") ObservableEventBusSubscriber handler,
                             Provider<WorkflowProvider> workflowProvider) {
        super(handler);
        this.workflowProvider = workflowProvider;
    }

    @Override
    public void handleEvent(ObservableEvent event) {
        event.accept(new ObservableEventVisitor<Void>() {
            @Override
            public Void visit(StateTransitionEvent stateTransitionEvent) {
                workflowProvider.get().updateWorkflow(stateTransitionEvent.getWorkflow());
                return null;
            }
        });

    }
}
