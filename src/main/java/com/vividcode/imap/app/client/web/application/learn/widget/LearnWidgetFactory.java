package com.vividcode.imap.app.client.web.application.learn.widget;

import com.vividcode.imap.common.shared.vo.LearnVO;

public interface LearnWidgetFactory {
    LearnWidgetPresenter create(LearnVO learn);
}
