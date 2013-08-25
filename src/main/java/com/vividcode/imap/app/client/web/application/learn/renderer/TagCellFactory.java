package com.vividcode.imap.app.client.web.application.learn.renderer;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.inject.assistedinject.Assisted;
import com.vividcode.imap.common.shared.vo.TagVO;

public interface TagCellFactory {
    TagCell create(@Assisted Delegate<TagVO> close);
}
