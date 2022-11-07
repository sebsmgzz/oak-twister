package com.oaktwister.app.services.parsers;

import com.oaktwister.app.exceptions.UnknownGrantTypeException;
import com.oaktwister.domain.models.grants.DateTimeGrant;
import com.oaktwister.domain.models.grants.FlagGrant;
import com.oaktwister.domain.models.grants.Grant;
import com.oaktwister.domain.models.grants.NumberGrant;
import com.oaktwister.domain.models.grants.SecretGrant;
import com.oaktwister.domain.models.grants.TextGrant;

import java.util.ArrayList;

public class GrantTypeParser {

    public String getGrantTypeName(Class<?> grantType) {
        return grantType.getSimpleName();
    }

    public Class<? extends Grant<?>> getGrantType(String grantTypeName) throws UnknownGrantTypeException {
        ArrayList<Class<? extends Grant<?>>> grantTypes = getGrantTypes();
        for (Class<? extends Grant<?>> grantType : grantTypes) {
            if(getGrantTypeName(grantType).equals(grantTypeName)) {
                return grantType;
            }
        }
        throw new UnknownGrantTypeException(grantTypeName);
    }

    public ArrayList<Class<? extends Grant<?>>> getGrantTypes() {
        ArrayList<Class<? extends Grant<?>>> grantTypes = new ArrayList<>();
        grantTypes.add(DateTimeGrant.class);
        grantTypes.add(FlagGrant.class);
        grantTypes.add(NumberGrant.class);
        grantTypes.add(SecretGrant.class);
        grantTypes.add(TextGrant.class);
        return grantTypes;
    }

}
