package framework.base.mvp.base.activity;

import androidx.appcompat.app.AlertDialog;

import framework.base.activity.LoadingBaseActivity;
import framework.base.module.exception.NetErrorException;
import framework.base.mvp.presenter.BasePresenter;
import framework.base.mvp.view.BaseView;

/**
 * Mvp BaseActivity
 *
 * @param <P>
 */
abstract public class MVPBaseActivity<P extends BasePresenter> extends LoadingBaseActivity
        implements BaseView {

    private P mPresenter;

    /**
     * 底层获取P
     *
     * @return P
     */
    protected synchronized P getPresenter() {
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        return mPresenter;
    }


    /**
     * 创建Presenter
     *
     * @return 返回Presenter的实例
     */
    protected abstract P createPresenter();

    @Override
    public void onComplete() {
        // 请求完成、关闭dialog
        dismissDialog();
    }

    @Override
    public void onDialogCancelListener(AlertDialog dialog) {
        super.onDialogCancelListener(dialog);
        // dialog取消，取消订阅
        getPresenter().unDisposable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 销毁 取消订阅
        getPresenter().unDisposable();
    }

    @Override
    public void onFailure(NetErrorException exception) {
        showWarningDialog(exception.getMessage());
    }
}
