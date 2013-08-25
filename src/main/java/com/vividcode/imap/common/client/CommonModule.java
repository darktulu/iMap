package com.vividcode.imap.common.client;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.vividcode.imap.common.client.resource.ResourceLoader;
import com.vividcode.imap.common.client.resource.SharedResources;
import com.vividcode.imap.common.client.resource.message.SharedMessageBundle;
import com.vividcode.imap.common.client.rest.AsyncCallbackImpl;
import com.vividcode.imap.common.client.rest.ValidatedAsyncCallbackImpl;
import com.vividcode.imap.common.client.security.HasRoleGatekeeper;
import com.vividcode.imap.common.client.security.LoggedInGatekeeper;
import com.vividcode.imap.common.client.widget.messages.MessageModule;

public class CommonModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new MessageModule());

        bind(SharedMessageBundle.class).in(Singleton.class);
        bind(SharedResources.class).in(Singleton.class);
        bind(LoggedInGatekeeper.class).in(Singleton.class);
        bind(HasRoleGatekeeper.class).in(Singleton.class);

        bind(ResourceLoader.class).asEagerSingleton();

        requestStaticInjection(AsyncCallbackImpl.class);
        requestStaticInjection(ValidatedAsyncCallbackImpl.class);
    }
}
