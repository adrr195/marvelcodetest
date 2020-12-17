
package com.example.adrian.codetest.app.navigator.command;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class NavigationCommand {
    private final Context context;

    public NavigationCommand(Context context) {
        this.context = context;
    }

    protected Context getContext() {
        return context;
    }

    public void execute() {
        Intent intent = onRequestIntent(context);
        Bundle activityOptions = onRequestActivityOptions();
        if (activityOptions != null) {
            context.startActivity(intent, activityOptions);
        } else {
            context.startActivity(intent);
        }
    }

    @NonNull
    abstract Intent onRequestIntent(Context context);

    @Nullable
    protected Bundle onRequestActivityOptions() {
        return null;
    }
}
