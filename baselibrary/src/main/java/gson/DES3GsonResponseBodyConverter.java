package gson;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import framework.base.module.exception.NetErrorException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import static okhttp3.internal.Util.UTF_8;

/*
 * 对联网返回数据的解密后统一处理。
 * @author Alois
 * create at 2017/4/25 上午 10:09
 */
public class DES3GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    //    private final Gson gson;
    private final TypeAdapter<T> adapter;

    DES3GsonResponseBodyConverter(TypeAdapter<T> adapter) {
//        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
//        String response = Des3.decode(value.string());
        String jsonString = value.string();
        try {
            Log.e("TAG", "这里进行了返回结果的判断:" + jsonString);
            // ------------------ JsonObject 只做了初略的判断，具体情况自定
            JSONObject object = new JSONObject(jsonString);
            int code = object.getInt("errorCode");
            if (code != 0) {
                throw new NetErrorException(object.getString("errorMsg"), code);
            }
            return adapter.fromJson(object.getString("data"));

        } catch (JSONException e) {
            e.printStackTrace();
            throw new NetErrorException("数据解析异常", NetErrorException.PARSE_ERROR);
        } finally {
            value.close();
        }

//        MediaType contentType = value.contentType();
//        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
//        InputStream inputStream = new ByteArrayInputStream(response.getBytes());
//        Reader reader = new InputStreamReader(inputStream, charset);
//        JsonReader jsonReader = gson.newJsonReader(reader);

//        try {
//            return adapter.read(jsonReader);
//        } finally {
//            value.close();
//        }
    }

}