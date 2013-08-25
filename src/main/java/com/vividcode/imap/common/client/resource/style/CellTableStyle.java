package com.vividcode.imap.common.client.resource.style;

import com.google.gwt.user.cellview.client.CellTable;

public interface CellTableStyle extends CellTable.Resources {
    @Source({"com/vividcode/imap/common/client/resource/style/cellTableStyle.css"})
    Style cellTableStyle();

    interface Style extends CellTable.Style {
    }
}
