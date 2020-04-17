package framework.base.module.dialog.inf;

import androidx.appcompat.app.AlertDialog;

/**
 * 取消按钮点击的回调
 */
public interface OnDialogCancelListener {
    /**
     * 取消按钮点击的回调
     *
     * @param dialog 弹窗
     */
    void onDialogCancelListener(AlertDialog dialog);
}
