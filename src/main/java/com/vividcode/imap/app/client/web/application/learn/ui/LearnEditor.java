package com.vividcode.imap.app.client.web.application.learn.ui;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.vividcode.imap.app.client.web.application.learn.renderer.TagCellFactory;
import com.vividcode.imap.common.client.util.EditorView;
import com.vividcode.imap.common.shared.vo.LearnVO;
import com.vividcode.imap.common.shared.vo.TagVO;

import javax.inject.Inject;

import static com.google.gwt.query.client.GQuery.$;

public class LearnEditor extends Composite implements EditorView<LearnVO> {
    public interface Binder extends UiBinder<Widget, LearnEditor> {
    }

    public interface Driver extends SimpleBeanEditorDriver<LearnVO, LearnEditor> {
    }

    @UiField
    TextBox title;
    @UiField
    TextArea content;

    private final Driver driver;

    @Inject
    LearnEditor(Binder uiBinder,
                Driver driver,
                TagCellFactory tagCellFactory,
                ListDataProvider<TagVO> dataProvider) {
        this.driver = driver;

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);

        $(title).id("title");
        $(content).id("content");

        title.getElement().setAttribute("placeholder", "Title");
        content.getElement().setAttribute("placeholder", "Add learn");
    }

    @Override
    public void edit(LearnVO learn) {
        driver.edit(learn);
    }

    @Override
    public LearnVO get() {
        LearnVO learn = driver.flush();
        if (driver.hasErrors()) {
            return null;
        } else {
            return learn;
        }
    }
}
