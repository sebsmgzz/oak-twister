package serializers;

import models.Account;
import models.BaseModel;
import models.Identity;
import models.Password;
import models.Platform;

public class SerializerFactory {

    public Serializer<?> createSerializer(Class<? extends BaseModel> type) {
        if(type == Account.class) {
            return new AccountSerializer();
        } else if(type == Identity.class) {
            return new IdentitySerializer();
        } else if(type == Password.class) {
            return new PasswordSerializer();
        } else if(type == Platform.class) {
            return new PlatformSerializer();
        }
        return null;
    }

}
