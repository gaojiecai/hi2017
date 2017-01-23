package request;

/**
 * Created by gaojicai1 on 2017/1/18.
 */

public interface IRequest {

    interface IListener<T> {
        void onReceiver(T obj);

        void onFailed();
    }

}
