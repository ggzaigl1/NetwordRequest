package framework.base.utlis;

import android.content.Context;
import android.widget.Toast;

public class T {

    /**
     * 描述：Toast提示文本.
     *
     * @param textStr 文本
     */
    public static void showToast(Context context, String textStr) {
        Toast.makeText(context.getApplicationContext(), textStr, Toast.LENGTH_SHORT).show();
    }

    /**
     * 描述：Toast提示文本.
     *
     * @param resId 文本的资源ID
     */
    public static void showToast(Context context, int resId) {
        Context mContext = context.getApplicationContext();
        showToast(mContext, mContext.getResources().getString(resId));
    }
}
