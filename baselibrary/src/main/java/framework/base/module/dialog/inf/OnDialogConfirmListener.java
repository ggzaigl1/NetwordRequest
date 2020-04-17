package framework.base.module.dialog.inf;

import androidx.appcompat.app.AlertDialog;

/**
 * 确认按钮点击的回调
 */
public interface OnDialogConfirmListener {
    /**
     * 确定按钮点击的回调
     * @param dialog 弹窗
     */
    void onDialogConfirmListener(AlertDialog dialog);
}
