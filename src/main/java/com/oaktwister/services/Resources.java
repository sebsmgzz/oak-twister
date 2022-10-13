package com.oaktwister.services;

public interface Resources {

    interface Strings {
        interface App {
            String TITLE = "OakTwister";
        }
    }

    interface Images {

        interface Cyberpunk {
            String PLATFORM = "/images/cyberpunk/platform.png";
        }

        interface HelpSupport {
            String INFORMATION = "/images/help-support/information.png";
            String KEY = "/images/help-support/key.png";
            String SETTINGS = "/images/help-support/settings.png";
        }

        interface SocialNetwork {
            String ADD = "/images/social-network/add.png";
            String FAVOURITE = "/images/social-network/favourite.png";
            String FRIENDS = "/images/social-network/friends.png";
            String HOME = "/images/social-network/home.png";
            String MESSAGE = "/images/social-network/message.png";
            String NEWSFEED = "/images/social-network/newsfeed.png";
            String POLL = "/images/social-network/poll.png";
            String SHARE = "/images/social-network/share.png";
            String TAG = "/images/social-network/tag.png";
            String USER = "/images/social-network/user.png";
        }

        interface Vikings {
            String OAK = "/images/vikings/oak.png";
        }

    }

    interface Styles {
        String LANDING = "/styles/landing.css";
        String MAIN = "/styles/landing.css";
    }

    interface Views {

        interface Landing {
            String LANDING = "/views/landing/landing.fxml";
        }

        interface Main {
            String MAIN = "/views/main/main.fxml";
            String IDENTITIES = "/views/main/identities.fxml";
            String PLATFORMS = "/views/main/platforms.fxml";
            String ACCOUNTS = "/views/main/accounts.fxml";
            String LATERAL_BUTTON = "/views/main/lateral-button.fxml";
        }

    }

}
