package com.vividcode.imap.app.client.gin;

import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.gwtplatform.dispatch.client.gin.RestDispatchAsyncModule;
import com.gwtplatform.dispatch.client.rest.RestApplicationPath;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.vividcode.imap.common.client.CommonModule;
import com.vividcode.imap.app.client.place.NameTokens;
import com.vividcode.imap.app.client.resource.Resources;
import com.vividcode.imap.app.client.resource.message.MessageBundle;
import com.vividcode.imap.app.client.web.RootModule;

public class ClientModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new DefaultModule());
        install(new CommonModule());
        install(new RootModule());
        install(new RestDispatchAsyncModule.Builder().build());

        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.home);
        bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.login);
        bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.login);
        bindConstant().annotatedWith(RestApplicationPath.class).to("rest");
        bindConstant().annotatedWith(Names.named("importUrl")).to("rest/import");

        bind(Resources.class).in(Singleton.class);
        bind(MessageBundle.class).in(Singleton.class);

        bind(ResourceLoader.class).asEagerSingleton();
    }
}
