package appewtc.masterung.ishihara;

/**
 * Created by masterUNG on 5/26/15 AD.
 */
public class SSRUmodel {

    private int buttonAnInt;

    //Create Interface
    public interface OnSSRUmodelChangeListener {
        void onSSRUmodelChangeListener(SSRUmodel ssrUmodel);
    }   //Interface

    private OnSSRUmodelChangeListener onSSRUmodelChangeListener;

    public void setOnSSRUmodelChangeListener(OnSSRUmodelChangeListener onSSRUmodelChangeListener) {
        this.onSSRUmodelChangeListener = onSSRUmodelChangeListener;
    }

    public int getButtonAnInt() {
        return buttonAnInt;
    }   // getter

    public void setButtonAnInt(int buttonAnInt) {
        this.buttonAnInt = buttonAnInt;

        if (this.onSSRUmodelChangeListener != null) {

            this.onSSRUmodelChangeListener.onSSRUmodelChangeListener(this);

        }

    }   // setter

}   // Main Class
