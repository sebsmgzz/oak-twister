package com.oaktwister.app.services.resources;

public interface ImageResources {
    String BASE_URL = "/com/oaktwister/app/images";

    interface Cyberpunk {
        String PLATFORM = BASE_URL + "/cyberpunk/platform.png";
    }

    interface FontAwesome {
        String FINGERPRINT_SOLID = BASE_URL + "/font-awesome/fingerprint-solid.png";
        String HOUSE_SOLID = BASE_URL + "/font-awesome/house-solid.png";
        String LAYER_GROUP_SOLID = BASE_URL + "/font-awesome/layer-group-solid.png";
        String USER_SOLID = BASE_URL + "/font-awesome/user-solid.png";
        String USERS_SOLID = BASE_URL + "/font-awesome/users-solid.png";
    }

    interface HelpSupport {
        String INFORMATION = BASE_URL + "/help-support/information.png";
        String KEY = BASE_URL + "/help-support/key.png";
        String SETTINGS = BASE_URL + "/help-support/settings.png";
    }

    interface Placeholders {
        String IMAGE = BASE_URL + "/placeholders/image.png";
    }

    interface Social {
        String FACEBOOK = BASE_URL + "/social/facebook.png";
    }

    interface SocialNetwork {
        String ADD = BASE_URL + "/social-network/add.png";
        String FAVOURITE = BASE_URL + "/social-network/favourite.png";
        String FRIENDS = BASE_URL + "/social-network/friends.png";
        String HOME = BASE_URL + "/social-network/home.png";
        String MESSAGE = BASE_URL + "/social-network/message.png";
        String NEWSFEED = BASE_URL + "/social-network/newsfeed.png";
        String POLL = BASE_URL + "/social-network/poll.png";
        String SHARE = BASE_URL + "/social-network/share.png";
        String TAG = BASE_URL + "/social-network/tag.png";
        String USER = BASE_URL + "/social-network/user.png";
    }

    interface Vikings {
        String OAK = BASE_URL + "/vikings/oak.png";
    }

}
