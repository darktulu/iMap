package com.vividcode.imap.app.client.web.application.learn.widget;

import com.gwtplatform.mvp.client.UiHandlers;
import com.vividcode.imap.common.shared.vo.LearnVO;

public interface AddLearnUiHandlers extends UiHandlers {
    void saveLearn(LearnVO learn);
}
