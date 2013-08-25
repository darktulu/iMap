package com.vividcode.imap.common.client.resource.style;

import com.google.gwt.user.cellview.client.DataGrid;

public interface DataGridStyle extends DataGrid.Resources {
    @Source({DataGrid.Style.DEFAULT_CSS, "com/vividcode/imap/common/client/resource/style/dataGridStyle.css"})
    Style dataGridStyle();

    interface Style extends DataGrid.Style {
    }
}
