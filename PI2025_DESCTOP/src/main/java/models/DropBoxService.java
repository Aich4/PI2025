package models;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

public class DropBoxService {


    private static final String ACCESS_TOKEN ="sl.u.AFmaQOh4b0BN_SBwL14m7az6eY_OvRTOAvl7VofQN44n6oFCRMjwmGmOqO5gXR2XDvT80Oo3q5f1mctE3CUx_Giy1D1W4OqCXDANgz5-PJsUcUbjYqybMZFfQx51AfWzKaQXaX92QrILDP6cgJ5c1dhVvlv9y-KtIlwX-f19ftPw4-7erCV3SssqSU8NEzlpgEGhHlEEMProKayoD8QCsK6AQj0Ax9CJpQBQACP3OQFSZV_AGvm6i2zPQFo_A804vknthYVqUseQjlwRULuKORGml5jPls5JLPsF9NOxsyzVKvs5Hd41Lwb4gwYz5Y5nED_tQHg-HNlkVaAHd1vWz5y03RV1kFR02K5CDCHq1XJAlxjEXRrWIJvpf_i2Px8EMuGEhn4IYy_m3NS5dDeXRvt6TJS4LaUACEjEQKhlykApemMVO3WZVlzqGot9gpch2bA472nB4P4BoFcyBIPy_ONNv0-Hl3wTQ3LCVW1-P8Mp1WC2R59PyoCgwzljGXv0peT3Sth6KgBvO9djz7RPWw_aTS_w3r9HuUswJdkbX8D2T0LBVCMS2e1IdcL1oUW99i8EUeSFM7LFpbFAvQ2hTXdB4oX3NTLcy3DgIytUNDdUN5v8zyfPVJ_Sg3erYpRkrAj3Ny9nX8uOtzkLQ9dvguKXYZtHCHCsvVT-i3Dt9RP2NkLdIzeVis2TP_NMMGDet5Q3H-X0rw4m8XelcOXY_DM16Hwo3WtZ2oNQoZgxvkLn-lYW3XB5H2N5zbksSBFYjR3oBECxARFBbgOQ-FLqojAoGDlmduzJUzHb5kMQXYF_FYqOb9on2Tab2eut01Z_TdANzj4bZzUVMGE9ry4tyPVBTcz6WqNTbcaTjUAMO6W0xdSL3NmBTtqcdLb5Ys1ylvq7B-lB3Tx_CeMeAkVp7Blix6BznnNT-CpiPt5DQZBZMqy0Mp1Vh_JGFB0msRYLQV9SZuIN5PibwOqApadXGklOmSnsiLHal7JXxgpNaLxPBnBDP1CnkkZ2ndIgOiJGbgo7RKd3KvHbjPLxVqki5fxeR5qJrDPTyMJsi-jg58EdAzchNbfrO3WW0AJqvLRrMCHE8zLu6NjT0nm-T_OlcF2RWLa6wan4eDJ0byghcqLLCFtlYgcr5VDSAf_aBMD6RTZ9xedPjXixXo2lPtVvqJMVzANTGlpNnoYK5kUdEFwJ1vXmVlwSf4ik5R0lvC-svoOkfkDYo272mKB-e2eieJ-ZZLexKmDVa06-umdQdux0w8z89vPEXzfKf-ILkkTDY5I";
    private static DbxClientV2 client;

    public static DbxClientV2 getClient() {
        if (client == null) {
            DbxRequestConfig config = DbxRequestConfig.newBuilder("Ghalia").build();
            client = new DbxClientV2(config, ACCESS_TOKEN);
        }
        return client;
    }

}
