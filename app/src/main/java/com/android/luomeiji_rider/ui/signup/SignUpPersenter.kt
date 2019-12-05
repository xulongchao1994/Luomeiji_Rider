
import android.content.Context
import com.android.luomeiji_rider.base.LBasePersenter
import com.android.luomeiji_rider.ui.signup.ISignUpView

class SignUpPersenter(mView: ISignUpView, context: Context) :
    LBasePersenter<ISignUpView>(mView, context) {
    fun getcode(phonenumber: String) {
//        ApiData.getcode(phonenumber, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
//            override fun onSuccess(result: String?) {
//                mView.getcodesuccess(result!!)
//                Log.d("zhuce", result)
//            }
//
//            override fun onFault(errorMsg: String?) {
//                mView.getcodeerror(errorMsg!!)
//                Log.d("zhuce_errormsg", errorMsg)
//            }
//        }))

//        mApi!!.getcode2(phonenumber)
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe(object : Observer<GetCodeBean> {
//                override fun onComplete() {
//                }
//
//                override fun onSubscribe(d: Disposable) {
//                    mDisposable = d
//                }
//
//                override fun onNext(t: GetCodeBean) {
//                }
//
//                override fun onError(e: Throwable) {
//                }
//            })
    }

//fun getfindpswcode(phonenumber: String) {
//        ApiData.getfindpswcode(phonenumber, OnSuccessAndFaultSub(object : OnSuccessAndFaultListener {
//            override fun onSuccess(result: String?) {
//                mView.getcodesuccess(result!!)
//            }
//
//            override fun onFault(errorMsg: String?) {
//                mView.getcodeerror(errorMsg!!)
//            }
//        }))
//}
}