package com.vividcode.imap.common.client.widget.renderer;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class EnumCell<T> extends AbstractCell<T> {
    @Override
    public void render(Context context, T value, SafeHtmlBuilder sb) {
        sb.appendEscaped(value == null ? "" : value.toString());
    }
}
