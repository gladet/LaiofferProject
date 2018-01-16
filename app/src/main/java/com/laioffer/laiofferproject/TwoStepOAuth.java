package com.laioffer.laiofferproject; /**
 * Created by music on 11/29/2017.
 */

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

public class TwoStepOAuth extends DefaultApi10a {
    @Override
    public String getAccessTokenEndpoint() { return null; }

    @Override
    public String getAuthorizationUrl(Token unused) {
        return null;
    }

    @Override
    public String getRequestTokenEndpoint() {
        return null;
    }

}
