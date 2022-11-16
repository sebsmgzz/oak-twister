package com.oaktwister.domain.models.accounts;

import com.oaktwister.domain.seedwork.Repository;
import java.util.Collection;
import java.util.UUID;

public interface AccountsRepo extends Repository<Account> {

    Collection<Account> findByPlatformId(UUID platformId);

    Collection<Account> findByIdentityId(UUID platformId);

}
