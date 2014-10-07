package com.vedidev.nativebridge.unity3d;

import android.os.Bundle;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;
import com.vedidev.nativebridge.BunchManager;
import com.vedidev.nativebridge.JsonSerializer;
import com.vedidev.nativebridge.ProcessorEngine;
import org.json.JSONObject;

/**
 * @author vedi
 *         date 05/10/14
 */
public class UnityGatewayAdapter extends UnityPlayerActivity {

    private static boolean contextSet = false;

    @SuppressWarnings("UnusedDeclaration")
    public static String dispatch(String strParams) {
        if (!contextSet) {
            BunchManager.getInstance().setContext(UnityPlayer.currentActivity.getApplicationContext());
            contextSet = true;
        }

        JSONObject dictParams = JsonSerializer.deserialize(strParams);
        if (dictParams == null) {
            return null;
        }

        JSONObject retDictParams = ProcessorEngine.getInstance().proceed(dictParams);

        if (retDictParams == null) {
            return null;
        }

        return JsonSerializer.serialize(retDictParams);
    }
}