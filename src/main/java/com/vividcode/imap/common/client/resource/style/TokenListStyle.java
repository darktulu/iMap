package com.vividcode.imap.common.client.resource.style;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.user.cellview.client.CellList;

public interface TokenListStyle extends CellList.Resources {
    @Source({CellList.Style.DEFAULT_CSS, "com/vividcode/imap/common/client/resource/style/tokenListStyle.css"})
    ListStyle cellListStyle();

    @Source("com/vividcode/imap/common/client/resource/image/remove-token.png")
    @ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
    ImageResource removeToken();

    @Source("com/vividcode/imap/common/client/resource/image/remove-token-hover.png")
    @ImageOptions(repeatStyle = ImageResource.RepeatStyle.None)
    ImageResource removeTokenHover();

    interface ListStyle extends CellList.Style {
    }
}
