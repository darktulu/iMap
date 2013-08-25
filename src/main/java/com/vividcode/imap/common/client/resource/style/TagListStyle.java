package com.vividcode.imap.common.client.resource.style;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.user.cellview.client.CellList;

public interface TagListStyle extends CellList.Resources {
    @ClientBundle.Source({"com/vividcode/imap/common/client/resource/style/tagListStyle.css"})
    ListStyle cellListStyle();

    interface ListStyle extends CellList.Style {
    }
}
