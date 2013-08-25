package com.vividcode.imap.common.client.resource.style;

import com.google.gwt.user.cellview.client.CellList;

public interface DetailsListStyle extends CellList.Resources {
    @Source({CellList.Style.DEFAULT_CSS, "com/vividcode/imap/common/client/resource/style/detailsListStyle.css"})
    ListStyle cellListStyle();

    interface ListStyle extends CellList.Style {
    }
}
